package unknowncyberplugin.components.buttons;

import unknowncyberplugin.Api;
import unknowncyberplugin.components.popups.FileUploadPopup;
import unknowncyberplugin.References;
import ghidra.program.util.GhidraProgramUtilities;


public class FileUploadButton extends BaseButton {

	public FileUploadButton() {
		super("Upload File");
	}

	@Override
	protected void runClickedAction(){
		FileUploadPopup uploadPopup = new FileUploadPopup();
		String uploadType = uploadPopup.displayAndGetResponse();

		if (uploadType != null) {
			if (uploadType.startsWith("Binary")) {
				boolean skipUnpack = Boolean.parseBoolean(uploadType.split(",")[1]);
				if (Api.submitFile(skipUnpack)) {
					References.getFileProvider().announce(
						"Upload Successful",
						"Original file was successfully uploaded.",
						false
					);
				} else {
					References.getFileProvider().announce(
						"Upload Failed",
						"Original file failed to upload.  See the User Log for more information.",
						true
					);
				}
			} else if (uploadType.equals("Disassembly")) {
				if (GhidraProgramUtilities.shouldAskToAnalyze(References.getFileProvider().getProgram())) {
					References.getFileProvider().announce("Cannot Disassemble File", "You must first analyze a file before submitting its disassembly.", false);
					return;
				}

				if (Api.submitDisassembly()) {
					References.getFileProvider().announce(
						"Upload Successful",
						"Disassembled file was successfully uploaded.",
						false
					);
				} else {
					References.getFileProvider().announce(
						"Upload Failed",
						"Disassembled file failed to upload.  See the User Log for more information.",
						true
					);
				}
			}
		}
	}
}
