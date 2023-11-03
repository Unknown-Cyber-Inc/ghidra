package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

import unknowncyberplugin.Api;
import unknowncyberplugin.components.popups.FileUploadPopup;


public class FileUploadButton extends BaseButton {

	public FileUploadButton() {
		super("Upload File");
	}

	@Override
	protected void runClickedAction(){
		Msg.info(this, "File upload button clicked");
		FileUploadPopup uploadPopup = new FileUploadPopup();
		String uploadType = uploadPopup.displayAndGetResponse();

		if (uploadType != null) {
			if (uploadType.equals("Binary")) {
				Msg.info(this, "Binary upload button clicked");
				Api.submitFile();
			} else if (uploadType.equals("Disassembly")) {
				Msg.info(this, "Disassembly upload button clicked");
				Api.getFileMatches("b87a947f3e85701fcdadd733e9b055a65a3b1308");
				Api.listFileTags("b87a947f3e85701fcdadd733e9b055a65a3b1308");
				Api.createFileTag("b87a947f3e85701fcdadd733e9b055a65a3b1308", "Test tag again");
				Api.listProcedureGenomicsTags("b87a947f3e85701fcdadd733e9b055a65a3b1308", "0x1000");
				//Api.listProcedureFiles("b87a947f3e85701fcdadd733e9b055a65a3b1308");
				//Api.submitDisassembly();
			}
		}
	}
}
