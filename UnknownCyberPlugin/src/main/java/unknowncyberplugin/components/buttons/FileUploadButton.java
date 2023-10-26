package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.Api;
import unknowncyberplugin.components.popups.FileUploadPopup;


public class FileUploadButton extends BaseButton {
	private UnknownCyberFileProvider fileProvider; 

	public FileUploadButton(UnknownCyberFileProvider fileProvider) {
		super("Upload File");
		this.fileProvider = fileProvider;
	}

	@Override
	protected void runClickedAction(){
		Msg.info(this, "File upload button clicked");
		FileUploadPopup uploadPopup = new FileUploadPopup();
		String uploadType = uploadPopup.displayAndGetResponse();

		if (uploadType.equals("Binary")) {
			Msg.info(this, "Binary upload button clicked");
			Api.submitFile();
		} else if (uploadType.equals("Disassembly")) {
			Msg.info(this, "Disassembly upload button clicked");
			Api.isFileAccessible("b87a947f3e85701fcdadd733e9b055a65a3b1308");
			//Api.submitDisassembly();
		} else if (uploadType.equals("GPR")) {
			Msg.info(this, "GPR upload clicked");
			// upload GPR
		}
	}
}
