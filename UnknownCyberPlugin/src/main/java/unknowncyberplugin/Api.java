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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.lingala.zip4j.ZipFile;

import com.unknowncyber.magic.model.EnvelopedFileGenomicsResponse200;
import com.unknowncyber.magic.model.EnvelopedFileList200;
import com.unknowncyber.magic.model.EnvelopedFile200;
import com.unknowncyber.magic.model.EnvelopedFileMatchResponseList200EnvelopedIdList200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponse200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponseList200;
import com.unknowncyber.magic.model.EnvelopedNote200;
import com.unknowncyber.magic.model.EnvelopedNote201;
import com.unknowncyber.magic.model.EnvelopedNoteList200;
import com.unknowncyber.magic.model.EnvelopedProcedureList200;
import com.unknowncyber.magic.model.EnvelopedTag200;
import com.unknowncyber.magic.model.EnvelopedTagCreatedResponse200;
import com.unknowncyber.magic.model.EnvelopedTagList200;
import com.unknowncyber.magic.model.ExtendedProcedureResponse;
import com.unknowncyber.magic.model.Note;
import com.unknowncyber.magic.model.Procedure;
import com.unknowncyber.magic.model.Tag;
import com.unknowncyber.magic.model.TagCreatedResponse;

// Can't double import things of the same name
// import unknowncyberplugin.models.responsedata.Note;
// import unknowncyberplugin.models.responsedata.Procedure;

//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import okhttp3.ResponseBody;  // TODO: remove these and gradle import if unneeded

/**
 * Serves to hold easy-use wrappers for Unknown Cyber API calls.
 */
public class Api {

	// TODO: grab environment variable for these, be mindful of whether v2 is included
	//   for use with okhttp
	//private static String baseUrl = "http://api:8000/v2/";
	//private static String apiKey = "&key=adminkey";

	// Globally usable link disabler to clean up calls and inherently include the mandatory
	//   ? symbol needed for this and other parameters, for use with okhttp.
	//private static String noLinks = "?no_links=true";

  private static UnknownCyberFileProvider fileProvider = References.getFileProvider();
	//private static OkHttpClient client = new OkHttpClient();

  private Api() {
	throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  /**
   * Wraps the original file upload endpoint.
   *  - Takes a fileProvider to access the current program and other at-runtime data.
   */
  public static void submitFile() {
		File myFile = new File(fileProvider.getProgram().getExecutablePath());
		List<File> files = Arrays.asList(myFile);
		try {
			EnvelopedFileUploadResponseList200 response = fileProvider.getFilesApi().uploadFile(files, "", Arrays.asList(), Arrays.asList(), "json", false, false, "", true, false, false, false, false, false, false);
		} catch (Exception e) {
			Msg.error(fileProvider, e);
		}
		// announce("Success or Failure");

		// Edit file access on success
	}

  /**
   * Wraps the disassembled file upload endpoint.
   *  - Takes a fileProvider to access the current program and other at-runtime data.
   */
  public static void submitDisassembly() {
		// Declare important paths here so they can be deleted in the finally clause
		Path procDirectory = null;
		Path fileJson = null;
		ZipFile zip = null;

		// TODO: number stuff:
		//   Potentially look into cleaning up 0-padding?

		try {
			BasicBlockModel blockModel = new BasicBlockModel(fileProvider.getProgram());
			String fileType = fileProvider.getProgram().getExecutableFormat();

			// Generate the file's JSON data
			JSONObject fileData = new JSONObject();
			fileData.put("image_base", fileProvider.getProgram().getImageBase());
			fileData.put("md5", fileProvider.getProgram().getExecutableMD5());
			fileData.put("sha1", fileProvider.getOriginalSha1());
			fileData.put("sha256", fileProvider.getProgram().getExecutableSHA256());
			fileData.put("sha512", fileProvider.getOriginalSha512());
			fileData.put("unix_filetype", fileType);
			fileData.put("version", "Ghidra-" + Application.getApplicationVersion());

			// Create and write to the file's JSON file
			fileJson = Files.createTempFile("", ".json");
			Files.write(fileJson, fileData.toJSONString().getBytes());

			// Create the procedure's subdirectory
			procDirectory = Files.createTempDirectory("");

			// Iterate over all functions in a program
			for (Function f : fileProvider.getFunctionIterator()) {
				// Set top-level blocks array
				JSONArray blockArray = new JSONArray();

				// Set top-level cfg object
				JSONObject cfgObject = new JSONObject();

				// Set top-level api_calls array
				JSONArray apiCallArray = new JSONArray();

				// Iterate over blocks
				CodeBlockIterator blockIterator = blockModel.getCodeBlocksContaining(f.getBody(), TaskMonitor.DUMMY);
				while (blockIterator.hasNext()) {
					CodeBlock currentBlock = blockIterator.next();

					// Set line array that exists per block
					JSONArray lineArray = new JSONArray();

					// Iterate over lines
					InstructionIterator lineIterator = fileProvider.getProgram().getListing().getInstructions(currentBlock, true);
					while (lineIterator.hasNext()) {
						Instruction currentLine = lineIterator.next();

						// Calculate byteString
						String byteString = "";
						byte[] byteArray = currentLine.getBytes();
						for (byte myByte : byteArray) {
							byteString = byteString + " " + String.format("%02x", myByte & 0xff);
						}
						byteString = byteString.trim();

						// Generate operand JSONArray
						// Simultaneously, handle api_call behavior since that requires operand-level granularity
						JSONArray operandArray = new JSONArray();

						String apiCallName = null;
						Boolean isCall = false;

						int operandCount = currentLine.getNumOperands();
						for (int i = 0; i < operandCount; i++) {
							if (currentLine.getExternalReference(i) != null) {
								apiCallName = currentLine.getExternalReference(i).getLabel();
								isCall = true;
								apiCallArray.add(currentLine.getExternalReference(i).getLabel());
							}
							operandArray.add(currentLine.getDefaultOperandRepresentation(i).toLowerCase());
						}

						// Create each JSON line object
						JSONObject lineJson = new JSONObject();
						lineJson.put("startEA", Helpers.formatEA(currentLine.getMinAddress()));
						lineJson.put("endEA", Helpers.formatEA(currentLine.getMaxAddress()));
						// TODO: figure out type
						lineJson.put("type", "code");
						lineJson.put("bytes", byteString);
						lineJson.put("mnem", currentLine.getMnemonicString().toLowerCase());
						// TODO: operands come out of ghidra with 0x-format hexes; we prefer h-format hexes
						//   except sometimes, because jumps tend to be off.  For now, I'll leave operands as
						//   they natively appear; it will be easier to adjust the backend to account for 0x-
						//   and h-format hexes, than try to brute force acceptable decision logic here
						lineJson.put("operands", operandArray);
						lineJson.put("prolog_format", Prolog.formatInstruction(currentLine.getMnemonicString(), operandArray));
						lineJson.put("api_call_name", apiCallName);
						lineJson.put("is_call", isCall);

						lineArray.add(lineJson);
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
					CodeBlockReferenceIterator destinationIterator = currentBlock.getDestinations(TaskMonitor.DUMMY);

					// TODO: these values are mosly in hex, but prepend 0040 instead of 0x
					//   likely to do with a need to normalize for image base or something
					// Ghidra addresses do not use 0x-format; cleanAddress cleans all non-hex digits.
					//   Ergo, we must prepend "0x" to a cleaned hex to make it 0x-formatted
					while (destinationIterator.hasNext()) {
						blockDestinations.add("0x" + Helpers.cleanAddress(destinationIterator.next().getDestinationAddress().toString()));
					}
					cfgObject.put("0x" + Helpers.cleanAddress(currentBlock.getMinAddress().toString()), blockDestinations);
				}

				// Create and populate the overall JSON object for this procedure
				JSONObject procData = new JSONObject();
				procData.put("blocks", blockArray);
				procData.put("is_library", (f.isExternal() ? 128 : 0));
				procData.put("is_thunk", (f.isThunk() ? 128 : 0));
				procData.put("startEA", Helpers.formatEA(f.getBody().getMinAddress()));
				procData.put("endEA", Helpers.formatEA(f.getBody().getMaxAddress()));
				procData.put("procedure_name", f.getName());
				procData.put("segment_name", fileProvider.getProgram().getMemory().getBlock(f.getBody().getMinAddress()).getName());
				// TODO: Strings are the likely-string values held in referenced memory addresses
				procData.put("strings", new String[0]);
				procData.put("api_calls", apiCallArray);
				procData.put("cfg", cfgObject);

				// Create and write to the temporary file containing this procedure's JSON data
				Path procJson = Files.createTempFile(procDirectory, f.getBody().getMinAddress().toString() + "_", ".json");
				Files.write(procJson, procData.toJSONString().getBytes());
			}

			// Create zip file in temp directory, load in the file.json and procedure directory
			zip = new ZipFile(Files.createTempFile(fileProvider.getOriginalSha1() + "_", ".zip").toFile());
			zip.addFile(fileJson.toFile());
			zip.addFolder(procDirectory.toFile());

			try {
				// TODO: program.getExecutableFormat() does not return values that we use; it will need some form of mapping
				EnvelopedFileUploadResponse200 response = fileProvider.getFilesApi().uploadDisassembly(zip.getFile(), fileType, fileProvider.getOriginalSha1(), "json", false, false, "", true, false, false);
			} catch (Exception e) {
        // TODO: again, verify this and the next fileProvider to make sure they work
				Msg.error(fileProvider, e);
			}
		// TODO: Granularize try/catch behavior so we can have more intelligent error handling
		} catch (Exception e) {
			Msg.error(fileProvider, e);
		} finally {
			// Clean up
			if (fileJson != null) {
				if (fileJson.toFile().exists()) {
					fileJson.toFile().delete();
				}
			}
			if (procDirectory != null) {
				if (procDirectory.toFile().exists()) {
					procDirectory.toFile().delete();
				}
			}
			if (zip != null) {
				if (zip.getFile().exists()) {
					zip.getFile().delete();
				}
			}
		}
	}

  /**
   * Checks if a file exists and is accessible to the user.
   * - Takes a hash string to query the API with.
   * Returns boolean true/false
   */
  public static boolean isFileAccessible(String hash) {
		String readMask = "";
    String expandMask = "";
    String dynamicMask = "";
    try {
		  EnvelopedFile200 response = fileProvider.getFilesApi().getFile(hash, "json", false, false, "", true, false, readMask, expandMask, dynamicMask);
			return true;
		} catch (Exception e) {
			Msg.error("Unknown Cyber API", e);
			return false;
		}
  }

  /**
   * Wraps the getFileMatches endpoint.
   *  - Takes a hash string to query the API with.
   */
  public static void getFileMatches(String hash) {
		//busted, returns null
		try {
			String readMask = "";
			String expandMask = "matches";
			Integer pageCount = 1;
			Integer pageSize = 25;
			Float maxThreshold = 1.0f;
			Float minThreshold = 0.7f;
			EnvelopedFileMatchResponseList200EnvelopedIdList200 response = fileProvider.getFilesApi().listFileMatches(hash, "json", false, false, "", true, false, pageCount, pageSize, 0, readMask, expandMask, maxThreshold, minThreshold);
			//Msg.info("RESP", response);
		} catch (Exception e) {
			Msg.error(fileProvider, e);
		}
	}

  /**
   * Wraps the getFileGenomics endpoint.
   *  - Takes a hash string to query the API with.
	 * Returns an array of Unknown Cyber Plugin Procedure objects.
   */
  public static unknowncyberplugin.models.responsedata.Procedure[] getFileGenomics(String hash) {
    try {
      String readMask = "binary_id,occurrence_count,procedure_name,start_ea,status";
      String orderBy = "start_ea";
      Integer pageCount = 1;
      Integer pageSize = 25;
      EnvelopedFileGenomicsResponse200 response = fileProvider.getFilesApi().listFileGenomics(hash, "json", false, false, "", true, false, pageCount, pageSize, 0, readMask, orderBy, false);
			List<unknowncyberplugin.models.responsedata.Procedure> procList = new ArrayList<unknowncyberplugin.models.responsedata.Procedure>();
			
			for (ExtendedProcedureResponse proc : response.getResource().getProcedures()) {
				procList.add(new unknowncyberplugin.models.responsedata.Procedure(proc.getOccurrenceCount(), proc.getStatus(), proc.getStartEA(), proc.getProcedureName(), proc.getBinaryId()));
			}

			return procList.toArray(new unknowncyberplugin.models.responsedata.Procedure[procList.size()]);
		} catch (Exception e) {
      Msg.error(fileProvider, e);
			return null;
    }
  }

  /**
   * Wraps the listFileNotes endpoint.
   *  - Takes a hash string to query the API with.
	 * Returns an array of Unknown Cyber Plugin Note objects.
   */
  public static unknowncyberplugin.models.responsedata.Note[] listFileNotes(String hash) {
    try {
      EnvelopedNoteList200 response = fileProvider.getFilesApi().listFileNotes(hash, "json", false, false, "", true, false);
			List<unknowncyberplugin.models.responsedata.Note> noteList = new ArrayList<unknowncyberplugin.models.responsedata.Note>();

			for (Note note : response.getResources()) {
				noteList.add(new unknowncyberplugin.models.responsedata.Note(note.getNote(), note.getId(), note.getUsername(), note.getCreateTime()));
			}

			return noteList.toArray(new unknowncyberplugin.models.responsedata.Note[noteList.size()]);
    } catch (Exception e) {
      Msg.error(fileProvider, e);
			return null;
    }
  }

  /** 
   * Wraps the createFileNote endpoint.
   *  - Takes a hash string to reference the file.
   *  - Takes a note string that contains the text of the note.
	 * Returns an Unknown Cyber Plugin Note object.
   */
  public static unknowncyberplugin.models.responsedata.Note createFileNote(String hash, String note) {
    try {
      EnvelopedNote201 response = fileProvider.getFilesApi().createFileNote(note, false, hash, "json", false, false, "", true, false, false);
			Note newNote = response.getResource();

			return new unknowncyberplugin.models.responsedata.Note(newNote.getNote(), newNote.getId(), newNote.getUsername(), newNote.getCreateTime());
    } catch (Exception e) {
      Msg.error(fileProvider, e);
			return null;
    }
  }

  /**
   * Wraps the updateFileNote endpoint.
   *  - Takes a hash string to reference the file.
   *  - Takes a noteId string to reference the specific note.
   *  - Takes a note string that contains the updated text of the note.
   */
  public static void updateFileNote(String hash, String noteId, String note) {
		//busted, returns field accessibility error
    try {
      String updateMask = "note";
      // This does not return a response
      fileProvider.getFilesApi().updateFileNote(hash, noteId, note, false, "json", false, false, "", true, false, updateMask);
    } catch (Exception e) {
      Msg.error(fileProvider, e);
    }
  }

  /**
   * Wraps the deleteFileNote endpoint.
   *  - Takes a hash string to reference the file.
   *  - Takes a noteId string to reference the specific note.
	 * Returns boolean true/false to indicate success/failure.
   */
  public static boolean deleteFileNote(String hash, String noteId) {
    try {
      // This does not return a response
      fileProvider.getFilesApi().deleteFileNote(hash, noteId, "json", false, false, "", true, false, true);
			return true;
    } catch (Exception e) {
      Msg.error(fileProvider, e);
			return false;
    }
  }

  /**
   * Wraps the listFileTags endpoint.
   *  - Takes a hash string to query the API with.
   */
  public static void listFileTags(String hash) {
		//busted, returns null
    try {
      String expandMask = "tags";
      EnvelopedTagList200 response = fileProvider.getFilesApi().listFileTags(hash, "json", false, false, "", true, false, expandMask);
			//Msg.info("Tag List", response);
    } catch (Exception e) {
      Msg.error(fileProvider, e);
    }
  }

  /**
   * Wraps the createFileTag endpoint.
   *  - Takes a hash string to reference the file.
   *  - Takes a name string to label the tag with.
	 * Returns an Unknown Cyber Plugin Tag object.
   */
  public static unknowncyberplugin.models.responsedata.Tag createFileTag(String hash, String name) {
		//busted, error reading entity from input stream
		//api call still goes through, though; tag clearly exists on-site
		// endpoint does not return username when creating tag
    try {
      // Color is set to null to use default color
      EnvelopedTagCreatedResponse200 response = fileProvider.getFilesApi().createFileTag(hash, name, null, "json", false, false, "", true, false);
			TagCreatedResponse newTag = response.getResource();

			// Endpoint does not return a username
			return new unknowncyberplugin.models.responsedata.Tag(newTag.getName(), null, newTag.getCreateTime().toString(), newTag.getId());
    } catch (Exception e) {
      Msg.error(fileProvider, e);
			return null;
    }
  }

  /**
   * Wraps the removeFileTag endpoint.
   *  - Takes a hash string to reference the file.
   *  - Takes a tagId string to reference the specific tag.
	 * Returns boolean true/false to indicate success/failure.
   */
  public static boolean removeFileTag(String hash, String tagId) {
    try {
      // This does not return a response
      fileProvider.getFilesApi().removeFileTag(hash, tagId, "json", false, false, "", true, false, true);
			return true;
    } catch (Exception e) {
      Msg.error(fileProvider, e);
			return false;
    }
  }

	/**
	 * Wraps the listProcedureSimilarities endpoint.
   *  - Takes a hash string to reference the file.
	 *  - Takes an address string to reference the procedure.
	 * Returns an array of Unknown Cyber Procedure Plugin objects.
	 */
	public static unknowncyberplugin.models.responsedata.Procedure[] listProcedureSimilarities(String hash, String address) {
		try {
			String method = "semantic_similarity";
			Integer pageCount = 1;
			Integer pageSize = 25;
			Float minThreshold = 0.7f;
			Float maxThreshold = 1.0f;
			EnvelopedProcedureList200 response = fileProvider.getFilesApi().listProcedureSimilarities(hash, address, "json", false, false, "", true, false, pageCount, pageSize, 0, maxThreshold, method, minThreshold);
			
			List<unknowncyberplugin.models.responsedata.Procedure> procList = new ArrayList<unknowncyberplugin.models.responsedata.Procedure>();
			
			for (Procedure proc : response.getResources()) {
				// The client code Procedure object doesn't have occurrenceCount or status
				procList.add(new unknowncyberplugin.models.responsedata.Procedure(-1, null, proc.getStartEa(), proc.getProcedureName(), proc.getBinaryId()));
			}

			return procList.toArray(new unknowncyberplugin.models.responsedata.Procedure[procList.size()]);
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the listProcedureGenomicsNotes endpoint.
   *  - Takes a hash string to reference the file.
	 *  - Takes an address string to reference the procedure.
	 * Returns an array of Unknown Cyber Plugin Note objects.
	 */
	public static unknowncyberplugin.models.responsedata.Note[] listProcedureGenomicsNotes(String hash, String address) {
		try {
			EnvelopedNoteList200 response = fileProvider.getFilesApi().listProcedureGenomicsNotes(hash, address, "json", false, false, "", true, false);
			
			List<unknowncyberplugin.models.responsedata.Note> noteList = new ArrayList<unknowncyberplugin.models.responsedata.Note>();

			for (Note note : response.getResources()) {
				noteList.add(new unknowncyberplugin.models.responsedata.Note(note.getNote(), note.getId(), note.getUsername(), note.getCreateTime()));
			}

			return noteList.toArray(new unknowncyberplugin.models.responsedata.Note[noteList.size()]);
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the createProcedureGenomicsNote endpoint.
   *  - Takes a hash string to reference the file.
	 *  - Takes an address string to reference the procedure.
	 *  - Takes a note string that contains the text of the note.
	 * Returns an Unknown Cyber Plugin Note object.
	 */
	public static unknowncyberplugin.models.responsedata.Note createProcedureGenomicsNote(String hash, String address, String note) {
		try {
			EnvelopedNote200 response = fileProvider.getFilesApi().createProcedureGenomicsNote(note, false, hash, address, "json", false, false, "", true, false);
			
			Note newNote = response.getResource();

			return new unknowncyberplugin.models.responsedata.Note(newNote.getNote(), newNote.getId(), newNote.getUsername(), newNote.getCreateTime());
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the updateProcedureGenomicsNote endpoint.
   *  - Takes a hash string to reference the file.
	 *  - Takes an address string to reference the procedure.
	 *  - Takes a noteId string that refernces the specific note.
	 *  - Takes a note string that contains the updated text of the note.
	 */
	public static void updateProcedureGenomicsNote(String hash, String address, String noteId, String note) {
		//busted, gives the unable to make field accessible error
		try {
			String updateMask = "note";
			EnvelopedNote200 response = fileProvider.getFilesApi().updateProcedureGenomicsNote(hash, address, noteId, note, false, "json", false, false, "", true, false, updateMask);
		} catch (Exception e) {
			Msg.error(fileProvider, e);
		}
	}

	/**
	 * Wraps the deleteProcedureGenomicsNote endpoint.
   *  - Takes a hash string to reference the file.
	 *  - Takes an address string to reference the procedure.
	 *  - Takes a noteId string that refernces the specific note.
	 * Returns a boolean true/false to indicate success/failure
	 */
	public static boolean deleteProcedureGenomicsNote(String hash, String address, String noteId) {
		try {
			// This does not return a response
			fileProvider.getFilesApi().deleteProcedureGenomicsNote(hash, address, noteId, "json", false, false, "", true, false, true);
			return true;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return false;
		}
	}

	/**
	 * Wraps the listProcedureGenomicsTags endpoint.
   *  - Takes a hash string to reference the file.
	 *  - Takes an address string to reference the procedure.
	 */
	public static void listProcedureGenomicsTags(String hash, String address) {
		//busted, returns null
		try {
			EnvelopedTagList200 response = fileProvider.getFilesApi().listProcedureGenomicsTags(hash, address, "json", false, false, "", true, false);
			//Msg.info("List proc tags", response);
		} catch (Exception e) {
			Msg.error(fileProvider, e);
		}
	}

	/**
	 * Wraps the createProcedureGenomicsTag endpoint.
   *  - Takes a hash string to reference the file.
	 *  - Takes an address string to reference the procedure.
	 *  - Takes a name string to label the tag with.
	 * Returns an Unknown Cyber Plugin Tag object.
	 */
	public static unknowncyberplugin.models.responsedata.Tag createProcedureGenomicsTag(String hash, String address, String name) {
		try {
			EnvelopedTag200 response = fileProvider.getFilesApi().createProcedureGenomicsTag(name, hash, address, "json", false, false, "", true, false);
			
			Tag tag = response.getResource();

			return new unknowncyberplugin.models.responsedata.Tag(tag.getName(), tag.getUsername(), tag.getCreateTime(), tag.getId());
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return null;
		}
	}

	/**
	 * Wraps the deleteProcedureGenomicsTagById endpoint.
   *  - Takes a hash string to reference the file.
	 *  - Takes an address string to reference the procedure.
	 *  - Takes a tagId string to reference the specific tag.
	 * Returns a boolean true/false to indicate success/failure.
	 */
	public static boolean deleteProcedureGenomicsTagById(String hash, String address, String tagId) {
		try {
			// This does not return a response
			fileProvider.getFilesApi().deleteProcedureGenomicsTagById(hash, address, tagId, "json", false, false, "", true, false, true);
			return true;
		} catch (Exception e) {
			Msg.error(fileProvider, e);
			return false;
		}
	}
}