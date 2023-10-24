package unknowncyberplugin.Components.Buttons;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.Components.Popups.FileUploadPopup;
import unknowncyberplugin.api;


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
		} else if (uploadType.equals("GPR")) {
			System.out.println("GPR upload clicked");
			// upload GPR
		}
	}
}
