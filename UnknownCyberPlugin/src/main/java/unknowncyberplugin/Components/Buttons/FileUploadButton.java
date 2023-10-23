package unknowncyberplugin.Components.Buttons;

// import com.unknowncyber.magic.api.FilesApi;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponse200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponseList200;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.Components.Popups.FileUploadPopup;
import unknowncyberplugin.api;
import unknowncyberplugin.helpers;
import unknowncyberplugin.prolog;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import net.lingala.zip4j.ZipFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ghidra.util.Msg;
import ghidra.util.task.TaskMonitor;

import ghidra.framework.Application;
import ghidra.program.model.block.BasicBlockModel;
import ghidra.program.model.block.CodeBlock;
import ghidra.program.model.block.CodeBlockIterator;
import ghidra.program.model.block.CodeBlockReferenceIterator;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.Instruction;
import ghidra.program.model.listing.InstructionIterator;



public class FileUploadButton extends BaseButton {
	private UnknownCyberFileProvider fileProvider; 

	public FileUploadButton(UnknownCyberFileProvider fileProvider) {
		super("Upload File");
		this.fileProvider = fileProvider;
	}

	@Override
	protected void runClickedAction(){
		System.out.println("File upload button clicked");
		FileUploadPopup uploadPopup = new FileUploadPopup();
		String uploadType = uploadPopup.displayAndGetResponse();

		if (uploadType.equals("Binary")) {
			System.out.println("Binary upload button clicked");
			api.submitFile(fileProvider);
		} else if (uploadType.equals("Disassembly")) {
			System.out.println("Disassembly upload button clicked");
			api.submitDisassembly(fileProvider);
			// TODO: Remove IDB elif, Ghidra cannot upload IDB files
		} else if (uploadType.equals("IDB")) {
			System.out.println("IDB upload clicked");
			// upload IDB
		}
	}
}
