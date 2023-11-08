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
				// TODO: swap these out for final release
				//Api.submitDisassembly();
				if (Api.getFileMatches("b87a947f3e85701fcdadd733e9b055a65a3b1308")) {
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
