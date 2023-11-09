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
				// TODO: remove this for final release
				/*
				Api.listFileMatches("b87a947f3e85701fcdadd733e9b055a65a3b1308");
				int dummy = 5;
				if (dummy == 5) {
					return;
				}
				//*/

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
