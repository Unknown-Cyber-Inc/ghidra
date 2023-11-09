package unknowncyberplugin.components.buttons;

import unknowncyberplugin.Api;
import unknowncyberplugin.components.popups.FileUploadPopup;
import unknowncyberplugin.References;


public class FileUploadButton extends BaseButton {

	public FileUploadButton() {
		super("Upload File");
	}

	@Override
	protected void runClickedAction(){
		FileUploadPopup uploadPopup = new FileUploadPopup();
		String uploadType = uploadPopup.displayAndGetResponse();

		if (uploadType != null) {
			if (uploadType.equals("Binary")) {
				if (Api.submitFile()) {
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
				// TODO: fix this
			}
		}
	}
}
