package unknowncyberplugin;

import unknowncyberplugin.UnknownCyberFileProvider;

import ghidra.framework.Application;
import ghidra.program.model.block.BasicBlockModel;
import ghidra.program.model.block.CodeBlock;
import ghidra.program.model.block.CodeBlockIterator;
import ghidra.program.model.block.CodeBlockReferenceIterator;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.Instruction;
import ghidra.program.model.listing.InstructionIterator;
import ghidra.util.Msg;
import ghidra.util.task.TaskMonitor;
import ghidra.program.model.listing.Program;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.lingala.zip4j.ZipFile;

import com.unknowncyber.magic.api.FilesApi;
import com.unknowncyber.magic.api.ProceduresApi;
import com.unknowncyber.magic.model.EnvelopedFileGenomicsResponse200;
import com.google.gson.JsonObject;
import com.unknowncyber.magic.model.EnvelopedFile200;
import com.unknowncyber.magic.model.EnvelopedFileMatchResponseList200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponse200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponseList200;
import com.unknowncyber.magic.model.EnvelopedNote200;
import com.unknowncyber.magic.model.EnvelopedNote201;
import com.unknowncyber.magic.model.EnvelopedNoteList200;
import com.unknowncyber.magic.model.EnvelopedProcedureList200;
import com.unknowncyber.magic.model.EnvelopedProcedureTagCreatedResponse201;
import com.unknowncyber.magic.model.EnvelopedTagCreatedResponse200;
import com.unknowncyber.magic.model.EnvelopedTagResponseList200;
import com.unknowncyber.magic.model.ExtendedProcedureResponse;
import com.unknowncyber.magic.model.FileMatchResponse;
import com.unknowncyber.magic.model.FilePipeline;
import com.unknowncyber.magic.model.Match;
import com.unknowncyber.magic.model.Note;
import com.unknowncyber.magic.model.Procedure;
import com.unknowncyber.magic.model.ProcedureTagCreatedResponse;
import com.unknowncyber.magic.model.Tag;
import com.unknowncyber.magic.model.TagResponse;
import com.unknowncyber.magic.model.TagCreatedResponse;

import unknowncyberplugin.models.responsedata.FileModel;
import unknowncyberplugin.models.responsedata.FileStatusModel;
import unknowncyberplugin.models.responsedata.ImageBase;
import unknowncyberplugin.models.responsedata.MatchModel;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.ProcedureModel;
import unknowncyberplugin.models.responsedata.SimilarProcedureModel;
import unknowncyberplugin.models.responsedata.TagModel;

import unknowncyberplugin.Helpers;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import io.swagger.client.ApiException;

/**
 * Serves to hold easy-use wrappers for Unknown Cyber API calls.
 */
public class Api {
	// TODO: swap these out once the ENV VARS change
	//private static final String API_HOST_ENV = "MAGIC_API_HOST";
	//private static final String API_KEY_ENV = "MAGIC_API_KEY";
	private static final String API_HOST_ENV = "API_URI";
	private static final String API_KEY_ENV = "API_KEY";

	private static String baseUrl = System.getenv(API_HOST_ENV) + "/";
	private static String apiKey = "&key=" + System.getenv(API_KEY_ENV);

	// Globally usable link disabler to clean up calls and inherently include the
	// mandatory ? symbol needed for this and other parameters, for use with
	// okhttp.
	private static String noLinks = "?no_links=true";
	private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

	private static UnknownCyberFileProvider fileProvider = References.getFileProvider();
	private static FilesApi filesApi = fileProvider.getFilesApi();
	private static ProceduresApi procsApi = fileProvider.getProcsApi();
	private static Program program = fileProvider.getProgram();
	private static OkHttpClient client = new OkHttpClient();

	private Api() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	// Global storage for an exception to hold it while un-nesting via
	// NestedException
	private static Exception stashedException;

	// Dummy exception to allow us to detect and specifically handle an exception
	// within nested try/catch blocks
	private static class NestedException extends Exception {
		private NestedException() {
		}
	}

	/**
	 * Wraps the original file upload endpoint.
	 * - Takes a fileProvider to access the current program and other at-runtime
	 * data.
	 * Returns a boolean true/false to indicate success/failure.
	 */
	public static boolean submitFile() {
		File myFile = new File(program.getExecutablePath());
		List<File> files = Arrays.asList(myFile);
		try {
			EnvelopedFileUploadResponseList200 response = filesApi.uploadFile(files, "",
				Arrays.asList(), Arrays.asList(), "json", false, false, "", true, false, false, false, false, false,
				false, false);
			String uploadHash = response.getResources().get(0).getSha1();
			References.setUploadHash(uploadHash);
			References.getFileButtonsPanel().getStatusButton().setEnabled(true);
			References.enableFullPlugin(true);
			return true;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return false;
		}
	}

	/**
	 * Wraps the disassembled file upload endpoint.
	 * - Takes a fileProvider to access the current program and other at-runtime
	 * data.
	 * Returns a boolean true/false to indicate success/failure.
	 */
	public static boolean submitDisassembly() {
		// Declare important paths here so they can be deleted in the finally clause
		Path procDirectory = null;
		Path fileJson = null;
		ZipFile zip = null;

		// Output boolean to denote success/failure
		boolean toReturn = false;

		// Byte string to be used to identify content-version of file
		String fileByteString = "";

		try {
			BasicBlockModel blockModel = new BasicBlockModel(program);
			String fileType = program.getExecutableFormat();

			// Generate the file's JSON data
			JSONObject fileData = new JSONObject();
			fileData.put("image_base", program.getImageBase());
			fileData.put("md5", program.getExecutableMD5());
			fileData.put("sha1", fileProvider.getOriginalSha1());
			fileData.put("sha256", program.getExecutableSHA256());
			fileData.put("sha512", fileProvider.getOriginalSha512());
			fileData.put("unix_filetype", fileType);
			fileData.put("version", Application.getApplicationVersion());
			fileData.put("disassembler", "ghidra");
			int architecture = Helpers.getArchitecture(program);
			if (architecture == 32) {
				fileData.put("use_32", true);
				fileData.put("use_64", false);
			} else if (architecture == 64) {
				fileData.put("use_64", true);
				fileData.put("use_32", false);
			} else {
				fileData.put("use_32", false);
				fileData.put("use_64", false);
			}
			String path = program.getExecutablePath();
			if (path.contains("/") && !path.substring(path.length() - 1).equals("/")) {
				fileData.put("filename", path.substring(path.lastIndexOf("/") + 1));
			} else {
				fileData.put("filename", null);
			}

			try {
				// Create the procedure's subdirectory
				procDirectory = Files.createTempDirectory("");
			} catch (Exception e) {
				// File error occurs, specify issue and escalate to highest try/catch block
				Msg.error(fileProvider, "Error occurred while creating temp disassembly files.");
				stashedException = e;
				throw new NestedException();
			}

			try {
				// Iterate over all functions in a program
				for (Function f : fileProvider.getFunctionIterator()) {
					// Set top-level blocks array
					JSONArray blockArray = new JSONArray();

					// Set top-level cfg object
					JSONObject cfgObject = new JSONObject();

					// Set top-level api_calls array
					JSONArray apiCallArray = new JSONArray();

					// Iterate over blocks
					try {
						CodeBlockIterator blockIterator = blockModel.getCodeBlocksContaining(f.getBody(),
							TaskMonitor.DUMMY);
						while (blockIterator.hasNext()) {
							CodeBlock currentBlock = blockIterator.next();

							// Set line array that exists per block
							JSONArray lineArray = new JSONArray();

							// Iterate over lines
							try {
								InstructionIterator lineIterator = program.getListing()
									.getInstructions(currentBlock, true);
								while (lineIterator.hasNext()) {
									Instruction currentLine = lineIterator.next();

									// Calculate byteString
									String byteString = "";
									byte[] byteArray = currentLine.getBytes();
									for (byte myByte : byteArray) {
										byteString = byteString + " " + String.format("%02x", myByte & 0xff);
									}
									byteString = byteString.trim();

									// Add instruction byte string to global byte string
									fileByteString = fileByteString + " " + byteString;

									// Generate operand JSONArray
									// Simultaneously, handle api_call behavior since that requires operand-level
									// granularity
									JSONArray operandArray = new JSONArray();

									String apiCallName = null;
									Boolean isCall = false;

									// Iterate over operands
									try {
										for (int i = 0; i < currentLine.getNumOperands(); i++) {
											if (currentLine.getExternalReference(i) != null) {
												apiCallName = currentLine.getExternalReference(i).getLabel();
												isCall = true;
												apiCallArray.add(currentLine.getExternalReference(i).getLabel());
											}
											operandArray
												.add(currentLine.getDefaultOperandRepresentation(i).toLowerCase());
										}
									} catch (Exception e) {
										// Unexpected error occurrs while looping operands, specify location and
										// escalate
										Msg.error(fileProvider,
											"Error occurred while traversing an instruction's operands.");
										stashedException = e;
										throw new NestedException();
									}

									// Create each JSON line object
									JSONObject lineJson = new JSONObject();
									lineJson.put("startEA", Helpers.formatEA(currentLine.getMinAddress()));
									lineJson.put("endEA", Helpers.formatEA(currentLine.getMaxAddress()));
									// TODO: figure out type
									lineJson.put("type", "code");
									lineJson.put("bytes", byteString);
									lineJson.put("mnem", currentLine.getMnemonicString().toLowerCase());
									// TODO: operands come out of ghidra with 0x-format hexes; we prefer h-format
									// hexes except sometimes, because jumps tend to be off. For now, I'll leave
									// operands as they natively appear; it will be easier to adjust the backend
									// to account for 0x- and h-format hexes, than try to brute force acceptable
									// decision logic here
									lineJson.put("operands", operandArray);
									lineJson.put("prolog_format",
										Prolog.formatInstruction(currentLine.getMnemonicString(), operandArray));
									lineJson.put("api_call_name", apiCallName);
									lineJson.put("is_call", isCall);

									lineArray.add(lineJson);
								}
							} catch (Exception e) {
								// Unexpected error occurrs while looping instructions, specify location and
								// escalate
								Msg.error(fileProvider, "Error occurred while traversing a block's instructions.");
								stashedException = e;
								throw new NestedException();
							}

							// Create each JSON block object
							JSONObject blockJson = new JSONObject();
							blockJson.put("startEA", Helpers.formatEA(currentBlock.getMinAddress()));
							blockJson.put("endEA", Helpers.formatEA(currentBlock.getMaxAddress()));

							// Populate the JSON block's lines field with the line array
							blockJson.put("lines", lineArray);

							// Add the newly created JSON block object to the array
							blockArray.add(blockJson);

							// Do cfg stuff for this block
							JSONArray blockDestinations = new JSONArray();
							CodeBlockReferenceIterator destinationIterator = currentBlock
								.getDestinations(TaskMonitor.DUMMY);

							// TODO: these values are mosly in hex, but prepend 0040 instead of 0x
							// likely to do with a need to normalize for image base or something
							// Ghidra addresses do not use 0x-format; cleanAddress cleans all non-hex
							// digits.
							// Ergo, we must prepend "0x" to a cleaned hex to make it 0x-formatted
							try {
								while (destinationIterator.hasNext()) {
									blockDestinations.add("0x" + Helpers.cleanAddress(
										destinationIterator.next().getDestinationAddress().toString()));
								}
								cfgObject.put("0x" + Helpers.cleanAddress(currentBlock.getMinAddress().toString()),
									blockDestinations);
							} catch (Exception e) {
								// Unexpected error occurrs while generating cfg, specify location and escalate
								Msg.error(fileProvider,
									"Error occurred while generating a block's control flow graph.");
								stashedException = e;
								throw new NestedException();
							}
						}
					} catch (NestedException e) {
						// Exception's most granular location has already been reported
						// Escalate this to next highest try/catch block
						throw stashedException;
					} catch (Exception e) {
						// Unexpected error occurrs while looping blocks, specify location and escalate
						Msg.error(fileProvider, "Error occurred while traversing a function's blocks.");
						stashedException = e;
						throw new NestedException();
					}

					// Create and populate the overall JSON object for this procedure
					JSONObject procData = new JSONObject();
					procData.put("blocks", blockArray);
					procData.put("is_library", (f.isExternal() ? 128 : 0));
					procData.put("is_thunk", (f.isThunk() ? 128 : 0));
					procData.put("startEA", Helpers.formatEA(f.getBody().getMinAddress()));
					procData.put("endEA", Helpers.formatEA(f.getBody().getMaxAddress()));
					procData.put("procedure_name", f.getName());
					procData.put("segment_name",
						program.getMemory().getBlock(f.getBody().getMinAddress()).getName());
					// TODO: Strings are the likely-string values held in referenced memory
					// addresses
					procData.put("strings", new String[0]);
					procData.put("api_calls", apiCallArray);
					procData.put("cfg", cfgObject);

					// Create and write to the temporary file containing this procedure's JSON data
					try {
						Path procJson = Files.createTempFile(procDirectory,
							f.getBody().getMinAddress().toString() + "_", ".json");
						Files.write(procJson, procData.toJSONString().getBytes());
					} catch (Exception e) {
						// Unexpected error occurrs while writing procedure file, specify location and
						// escalate
						Msg.error(fileProvider, "Error occurred while writing a procedure's data to temp file.");
						stashedException = e;
						throw new NestedException();
					}
				}

				fileByteString = fileByteString.trim();
				fileByteString = Base64.getEncoder().encodeToString(fileByteString.getBytes());
				fileData.put("byte_data", fileByteString);
			} catch (NestedException e) {
				// Exception's most granular location has already been reported
				// Escalate this to the highest try/catch block
				throw stashedException;
			} catch (Exception e) {
				// Unexpected error occurrs while looping functions, specify location and
				// escalate
				Msg.error(fileProvider, "Error occurred while traversing program functions.");
				stashedException = e;
				throw new NestedException();
			}

			try {
				// Create and write to the file's JSON file
				fileJson = Files.createTempFile("", ".json");
				Files.write(fileJson, fileData.toJSONString().getBytes());
			} catch (Exception e) {
				// File error occurs, specify issue and escalate to highest try/catch block
				Msg.error(fileProvider, "Error occurred while creating temp disassembly files.");
				stashedException = e;
				throw new NestedException();
			}

			// Create zip file in temp directory, load in the file.json and procedure
			// directory
			try {
				zip = new ZipFile(Files.createTempFile(fileProvider.getOriginalSha1() + "_", ".zip").toFile());
				zip.addFile(fileJson.toFile());
				zip.addFolder(procDirectory.toFile());
			} catch (Exception e) {
				// Unexpected error occurrs while zipping data, specify location and escalate
				Msg.error(fileProvider, "Error occurred while generating temporary zip file for disassembly upload.");
				stashedException = e;
				throw new NestedException();
			}

			try {
				EnvelopedFileUploadResponse200 response = filesApi.uploadDisassembly(zip.getFile(),
					"json", false, false, "", true, false, false);
				String uploadHash = response.getResource().getSha1();
				References.setUploadHash(uploadHash);
				References.getFileButtonsPanel().getStatusButton().setEnabled(true);
				References.enableFullPlugin(true);
			} catch (Exception e) {
				// Unexpected error occurrs during disassembly upload, specify location and
				// escalate
				Msg.error(fileProvider, "Error occurred during disassembly upload.");
				stashedException = e;
				throw new NestedException();
			}

			// Set output boolean to true if all critical code runs successfully.
			toReturn = true;
		} catch (NestedException e) {
			// Unexpected, nested error in disassembly upload; report the stashed exception
			Msg.error(fileProvider, stashedException);
		} catch (Exception e) {
			// Unexpected error in disassembly upload
			Msg.error(fileProvider, e);
		} finally {
			// Clean up any dangling files
			// Wrapped separately to ensure each one is tried
			try {
				if (fileJson != null) {
					if (fileJson.toFile().exists()) {
						fileJson.toFile().delete();
					}
				}
			} catch (Exception e) {
				Msg.error(fileProvider, "Error occurred when attempting to delete temporary File JSON.");
				Msg.error(fileProvider, e);
			}

			try {
				if (procDirectory != null) {
					if (procDirectory.toFile().exists()) {
						procDirectory.toFile().delete();
					}
				}
			} catch (Exception e) {
				Msg.error(fileProvider, "Error occurred when attempting to delete temporary Procedure Directory.");
				Msg.error(fileProvider, e);
			}

			try {
				if (zip != null) {
					if (zip.getFile().exists()) {
						zip.getFile().delete();
					}
				}
			} catch (Exception e) {
				Msg.error(fileProvider, "Error occurred when attempting to delete temporary Disassembly ZIP.");
				Msg.error(fileProvider, e);
			}

			return toReturn;
		}
	}

	/**
	 * Checks if a file exists and is accessible to the user.
	 * - Takes a hash string to query the API with.
	 * Returns boolean true/false to denote whether a user can access a file
	 */
	public static boolean isFileAccessible(String hash) {
		String readMask = "";
		String expandMask = "";
		String dynamicMask = "";
		try {
			EnvelopedFile200 response = filesApi.getFile(hash, "json", false, false, "", true, false,
				readMask, expandMask, dynamicMask);
			return true;
		} catch (ApiException e) {
			Msg.error(fileProvider, e);
			if (e.getCode() == 401) {
				fileProvider.announce(
					"Invalid API Key",
					"The API key set under the " + API_KEY_ENV + " environment variable does not match " +
					"any API keys in your MAGIC system.  Please check to make sure you are using a valid " +
					"MAGIC API key.",
					true
				);
			}
			return false;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			Msg.error(fileProvider, e);
			if (e.toString().contains("UnknownHostException")) {
				fileProvider.announce(
					"Invalid API Host",
					"The API host set under the " + API_HOST_ENV + " environment variable cannot connect " +
					"to the MAGIC system.  Please check to make sure you have set the API host correctly.",
					true
				);
			}
			return false;
		}
	}

	/**
	 * Gets a file object information.
	 * - Takes a hash string to query the API with.
	 * Returns file status.
	 */
	public static FileStatusModel getFileStatus(String hash) {
		String readMask = "status,pipeline";
		String expandMask = "";
		String dynamicMask = "";
		try {
			EnvelopedFile200 response = filesApi.getFile(hash, "json", false, false, "", true, false,
					readMask, expandMask, dynamicMask);
	
			FilePipeline pipeStatus = response.getResource().getPipeline();

			Map<String, String> pipelineStatus = Helpers.parsePipelines(pipeStatus);

			return new FileStatusModel(response.getResource().getStatus(), pipelineStatus);

		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the listFileMatches endpoint.
	 * - Takes a hash string to query the API with.
	 */
	public static MatchModel[] listFileMatches(String hash, int pageCount) {
		try {
			String readMask = "";
			String expandMask = "matches";
			Integer pageSize = 25;
			Float maxThreshold = 1.0f;
			Float minThreshold = 0.7f;
			EnvelopedFileMatchResponseList200 response = filesApi.listFileMatches(hash, "json", false, false, "",
				true, false, pageCount, pageSize, 0, readMask, expandMask, maxThreshold, minThreshold);

			List<FileMatchResponse> responseMatches = response.getResources();
			MatchModel[] matchList = new MatchModel[responseMatches.size()];
			
			for(int i=0; i < responseMatches.size(); i++){
				FileMatchResponse match = responseMatches.get(i);
				matchList[i] = new MatchModel(match.getSha1(), match.getMaxSimilarity());
			}

			return matchList;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the getFileGenomics endpoint.
	 * - Takes a hash string to query the API with.
	 * Returns an array of Unknown Cyber Plugin Procedure objects.
	 */
	public static ProcedureModel[] getFileGenomics(String hash) {
		hash = hash.toLowerCase();
		try {
			String readMask = "binary_id,occurrence_count,procedure_hash,procedure_name,start_ea,status,notes,tags";
			String orderBy = "start_ea";
			Integer pageCount = 1;
			Integer pageSize = 0;
			EnvelopedFileGenomicsResponse200 response = filesApi.listFileGenomics(hash, "json", false,
				false, "", true, false, pageCount, pageSize, 0, readMask, orderBy, false);

			List<ExtendedProcedureResponse> responseProcs = response.getResource().getProcedures();
			ProcedureModel[] procList = new ProcedureModel[responseProcs.size()];

			for (int i=0; i < responseProcs.size(); i++){
				ExtendedProcedureResponse proc = responseProcs.get(i);
				procList[i] = new ProcedureModel(proc.getStartEA(), proc.getProcedureName(), proc.getOccurrenceCount(),
					proc.getStatus(), proc.getNotes().size(), proc.getTags().size(), proc.getBinaryId(), proc.getHardHash());
			}

			return procList;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the listFileNotes endpoint.
	 * - Takes a hash string to query the API with.
	 * Returns an array of Unknown Cyber Plugin Note objects.
	 */
	public static NoteModel[] listFileNotes(String hash) {
		try {
			EnvelopedNoteList200 response = filesApi.listFileNotes(hash, "json", false, false, "",
				true, false);

			List<Note> responseNotes = response.getResources();
			NoteModel[] noteList = new NoteModel[responseNotes.size()];

			for (int i=0; i < responseNotes.size(); i++) {
				Note note = responseNotes.get(i);
				noteList[i] = new NoteModel(note.getNote(), note.getId(), note.getUsername(), note.getCreateTime());
			}

			return noteList;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the createFileNote endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes a note string that contains the text of the note.
	 * Returns an Unknown Cyber Plugin Note object.
	 */
	public static NoteModel createFileNote(String hash, String note) {
		try {
			EnvelopedNote201 response = filesApi.createFileNote(note, false, hash, "json", false,
				false, "", true, false, false);
			Note newNote = response.getResource();

			return new NoteModel(newNote.getNote(), newNote.getId(), newNote.getUsername(), newNote.getCreateTime());
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the updateFileNote endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes a noteId string to reference the specific note.
	 * - Takes a note string that contains the updated text of the note.
	 * Returns boolean true/false to indicate success/failure.
	 * Uses okhttp to manage PATCH behavior.
	 */
	public static boolean updateFileNote(String hash, String noteId, String note) {
		Response response = null;
		try {
			String updateMask = "&update_mask=note";
			JSONObject noteData = new JSONObject();
			noteData.put("note", note);

			RequestBody body = RequestBody.create(noteData.toString(), JSON);
			Request request = new Request.Builder()
				.url(baseUrl + "files/" + hash + "/notes/" + noteId + "/" + noLinks + updateMask + apiKey)
				.patch(body).build();

			response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				response.close();
				return true;
			}
			// Data returned via okhttp on failure does not exactly match "normal" swagger
			// API fail responses
			// Regardless, attempt to ape the error in a similar fashion for consistency's
			// sake
			throw new ApiException(response.code(), response.message());

		} catch (Exception e) {
			Msg.error(fileProvider, e);
			if (response != null) {
				try {
					response.close();
				} catch (Exception f) {
					// Allow this to silently fail
				}
			}
			return false;
		}
	}

	/**
	 * Wraps the deleteFileNote endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes a noteId string to reference the specific note.
	 * Returns boolean true/false to indicate success/failure.
	 */
	public static boolean deleteFileNote(String hash, String noteId) {
		try {
			// This does not return a response
			filesApi.deleteFileNote(hash, noteId, "json", false, false, "", true, false, true);
			return true;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return false;
		}
	}

	/**
	 * Wraps the listFileTags endpoint.
	 * - Takes a hash string to query the API with.
	 * Returns an array of Unknown Cyber Plugin Tag objects.
	 */
	public static TagModel[] listFileTags(String hash) {
		try {
			String expandMask = "tags";
			EnvelopedTagResponseList200 response = filesApi.listFileTags(hash, "json", false, false,
				"", true, false, expandMask);
			List<TagResponse> responseTags = response.getResources();
			TagModel[] tagList = new TagModel[responseTags.size()];

			for (int i=0; i < responseTags.size(); i++) {
				TagResponse tag = responseTags.get(i);
				tagList[i] = new TagModel(tag.getName(), tag.getUsername(), tag.getCreateTime(), tag.getId());
			}

			return tagList;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the createFileTag endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes a name string to label the tag with.
	 * Returns an Unknown Cyber Plugin Tag object.
	 */
	public static TagModel createFileTag(String hash, String name) {
		try {
			// Color is set to null to use default color
			EnvelopedTagCreatedResponse200 response = filesApi.createFileTag(hash, name, null, "json",
				false, false, "", true, false, false);
			TagCreatedResponse newTag = response.getResource();

			return new TagModel(newTag.getName(), newTag.getUsername(), newTag.getCreateTime().toString(),
				newTag.getId());
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the removeFileTag endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes a tagId string to reference the specific tag.
	 * Returns boolean true/false to indicate success/failure.
	 */
	public static boolean removeFileTag(String hash, String tagId) {
		try {
			// This does not return a response
			filesApi.removeFileTag(hash, tagId, "json", false, false, "", true, false, true);
			return true;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return false;
		}
	}

	/**
	 * Wraps the listProcedureSimilarities endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes an address string to reference the procedure.
	 * Returns an array of Unknown Cyber Procedure Plugin objects.
	 */
	public static ProcedureModel[] listProcedureSimilarities(String hash, String address) {
		try {
			String method = "semantic_similarity";
			Integer pageCount = 1;
			Integer pageSize = 25;
			Float minThreshold = 0.7f;
			Float maxThreshold = 1.0f;
			EnvelopedProcedureList200 response = filesApi.listProcedureSimilarities(hash, address,
				"json", false, false, "", true, false, pageCount, pageSize, 0, maxThreshold, method, minThreshold);

			List<Procedure> responseProcs = response.getResources();
			ProcedureModel[] procList = new ProcedureModel[responseProcs.size()];

			for (int i=0; i < responseProcs.size(); i++) {
				Procedure proc = responseProcs.get(i);
				procList[i] = new ProcedureModel(proc.getStartEa(), proc.getProcedureName(), -1, null, 0, 0, proc.getBinaryId(), null);
			}

			return procList;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the listProcedureGenomicsNotes endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes an address string to reference the procedure.
	 * Returns an array of Unknown Cyber Plugin Note objects.
	 */
	public static NoteModel[] listProcedureGenomicsNotes(String hash, String address) {
		try {
			EnvelopedNoteList200 response = filesApi.listProcedureGenomicsNotes(hash, address, "json",
				false, false, "", true, false);
			List<Note> responseNotes = response.getResources();
			NoteModel[] noteList = new NoteModel[responseNotes.size()];

			for (int i=0; i < responseNotes.size(); i++) {
				Note note = responseNotes.get(i);
				noteList[i] = new NoteModel(note.getNote(), note.getId(), note.getUsername(), note.getCreateTime());
			}

			return noteList;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the createProcedureGenomicsNote endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes an address string to reference the procedure.
	 * - Takes a note string that contains the text of the note.
	 * Returns an Unknown Cyber Plugin Note object.
	 */
	public static NoteModel createProcedureGenomicsNote(String hash, String address, String note) {
		try {
			EnvelopedNote200 response = filesApi.createProcedureGenomicsNote(note, false, hash,
				address, "json", false, false, "", true, false);

			Note newNote = response.getResource();

			return new NoteModel(newNote.getNote(), newNote.getId(), newNote.getUsername(), newNote.getCreateTime());
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the updateProcedureGenomicsNote endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes an address string to reference the procedure.
	 * - Takes a noteId string that refernces the specific note.
	 * - Takes a note string that contains the updated text of the note.
	 * Returns boolean true/false to indicate success/failure.
	 * Uses okhttp to manage PATCH behavior.
	 */
	public static boolean updateProcedureGenomicsNote(String hash, String address, String noteId, String note) {
		Response response = null;
		try {
			String updateMask = "&update_mask=note";
			JSONObject noteData = new JSONObject();
			noteData.put("note", note);

			RequestBody body = RequestBody.create(noteData.toString(), JSON);
			Request request = new Request.Builder().url(baseUrl + "files/" + hash + "/genomics/" + address + "/notes/"
				+ noteId + "/" + noLinks + updateMask + apiKey).patch(body).build();

			response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				response.close();
				return true;
			}
			// Data returned via okhttp on failure does not exactly match "normal" swagger
			// API fail responses
			// Regardless, attempt to ape the error in a similar fashion for consistency's
			// sake
			throw new ApiException(response.code(), response.message());

		} catch (Exception e) {
			Msg.error(fileProvider, e);
			if (response != null) {
				try {
					response.close();
				} catch (Exception f) {
					// Allow this to silently fail
				}
			}
			return false;
		}
	}

	/**
	 * Wraps the deleteProcedureGenomicsNote endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes an address string to reference the procedure.
	 * - Takes a noteId string that refernces the specific note.
	 * Returns a boolean true/false to indicate success/failure
	 */
	public static boolean deleteProcedureGenomicsNote(String hash, String address, String noteId) {
		try {
			// This does not return a response
			filesApi.deleteProcedureGenomicsNote(hash, address, noteId, "json", false, false, "",
				true, false, true);
			return true;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return false;
		}
	}

	/**
	 * Wraps the listProcedureGenomicsTags endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes an address string to reference the procedure.
	 * Returns an array of Unknown Cyber Plugin Tag objects.
	 */
	public static TagModel[] listProcedureGenomicsTags(String hash, String address) {
		try {
			EnvelopedTagResponseList200 response = filesApi.listProcedureGenomicsTags(hash, address,
				"json", false, false, "", true, false);

			List<TagResponse> responseTags = response.getResources();
			TagModel[] tagList = new TagModel[responseTags.size()];

			for (int i=0; i < responseTags.size(); i++) {
				TagResponse tag = responseTags.get(i);
				tagList[i] = new TagModel(tag.getName(), tag.getUsername(), tag.getCreateTime(), tag.getId());
			}

			return tagList;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the createProcedureGenomicsTag endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes an address string to reference the procedure.
	 * - Takes a name string to label the tag with.
	 * Returns an Unknown Cyber Plugin Tag object.
	 */
	public static TagModel createProcedureGenomicsTag(String hash, String address, String name) {
		try {
			EnvelopedTagCreatedResponse200 response = filesApi.createProcedureGenomicsTag(name, hash,
				address, "json", false, false, "", true, false, false);

			TagCreatedResponse tag = response.getResource();

			return new TagModel(tag.getName(), tag.getUsername(), tag.getCreateTime(), tag.getId());
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the deleteProcedureGenomicsTagById endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes an address string to reference the procedure.
	 * - Takes a tagId string to reference the specific tag.
	 * Returns a boolean true/false to indicate success/failure.
	 */
	public static boolean deleteProcedureGenomicsTagById(String hash, String address, String tagId) {
		try {
			// This does not return a response
			filesApi.deleteProcedureGenomicsTagById(hash, address, tagId, "json", false, false, "",
				true, false, true);
			return true;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return false;
		}
	}

	/**
	 * Wraps the updateFileProcedureGenomics endpoint.
	 * - Takes a hash string to reference the file.
	 * - Takes an address string to reference the procedure.
	 * - Takes a name string to apply to the procedure.
	 * Returns a boolean true/false to indicate success/failure.
	 */
	public static boolean updateProcedureName(String hash, String address, String name) {
		Response response = null;
		try {
			String updateMask = "&update_mask=procedure_name";
			JSONObject nameData = new JSONObject();
			nameData.put("procedure_name", name);

			RequestBody body = RequestBody.create(nameData.toString(), JSON);
			Request request = new Request.Builder()
				.url(baseUrl + "files/" + hash + "/genomics/" + address + "/" + noLinks + updateMask + apiKey)
				.patch(body).build();

			response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				response.close();
				return true;
			}
			// Data returned via okhttp on failure does not exactly match "normal" swagger
			// API fail responses
			// Regardless, attempt to ape the error in a similar fashion for consistency's
			// sake
			throw new ApiException(response.code(), response.message());
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			if (response != null) {
				try {
					response.close();
				} catch (Exception f) {
					// Allow this to silently fail
				}
			}
			return false;
		}
	}

	public static NoteModel createProcedureGroupNote(String hardHash, String note){
		try {
			EnvelopedNote200 response = procsApi.createProcedureNote(note, false, hardHash, "json", false, false, "", true, false, false);

			Note newNote = response.getResource();

			return new NoteModel(newNote.getNote(), newNote.getId(), newNote.getUsername(), newNote.getCreateTime());
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	public static boolean updateProcedureGroupNote(String hardHash, String noteId, String note){
		Response response = null;
		try {
			String updateMask = "&update_mask=note";
			JSONObject noteData = new JSONObject();
			noteData.put("note", note);

			RequestBody body = RequestBody.create(noteData.toString(), JSON);
			Request request = new Request.Builder().url(baseUrl + "procedures/" + hardHash + "/notes/" + noteId
				+ "/" + noLinks + updateMask + apiKey).patch(body).build();

			response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				response.close();
				return true;
			}
			// Data returned via okhttp on failure does not exactly match "normal" swagger
			// API fail responses
			// Regardless, attempt to ape the error in a similar fashion for consistency's
			// sake
			throw new ApiException(response.code(), response.message());

		} catch (Exception e) {
			Msg.error(fileProvider, e);
			if (response != null) {
				try {
					response.close();
				} catch (Exception f) {
					// Allow this to silently fail
				}
			}
			return false;
		}
	}

	public static boolean deleteProcedureGroupNote(String hardHash, String noteId){
		try {
			procsApi.deleteProcedureNote(hardHash, noteId, "json", false, false, "", true, false, true);
			return true;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return false;
		}
	}

	public static TagModel createProcedureGroupTag(String hardHash, String name){
		try {
			EnvelopedProcedureTagCreatedResponse201 response = procsApi.addProcedureTag(hardHash, name, "#329db6", "json", false, false, "", true, false, false);

			ProcedureTagCreatedResponse tag = response.getResource();

			return new TagModel(tag.getName(), null, tag.getCreateTime().toString(), tag.getId());
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	public static boolean deleteProcedureGroupTag(String hardHash, String tagId){
		try {
			procsApi.deleteProcedureTag(hardHash, tagId, "json", false, false, "", true, false, true);
			return true;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return false;
		}
	}


}
