package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.api;
import unknowncyberplugin.components.popups.FileUploadPopup;


public class FileUploadButton extends BaseButton {
	private UnknownCyberFileProvider fileProvider; 

	public FileUploadButton(UnknownCyberFileProvider fileProvider) {
		super("Upload File");
		this.fileProvider = fileProvider;
	}

	@Override
	protected void runClickedAction(){
		Msg.info("File upload button clicked");
		FileUploadPopup uploadPopup = new FileUploadPopup();
		String uploadType = uploadPopup.displayAndGetResponse();

		if (uploadType.equals("Binary")) {
			Msg.info("Binary upload button clicked");
			api.submitFile(fileProvider);
		} else if (uploadType.equals("Disassembly")) {
			Msg.info("Disassembly upload button clicked");
			api.submitDisassembly(fileProvider);
		} else if (uploadType.equals("GPR")) {
			Msg.info("GPR upload clicked");
			// upload GPR
		}
	}
}
