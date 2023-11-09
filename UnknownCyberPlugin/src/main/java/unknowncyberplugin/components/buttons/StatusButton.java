package unknowncyberplugin.components.buttons;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.popups.StatusPopup;
import unknowncyberplugin.models.responsedata.FileStatusModel;

public class StatusButton extends BaseButton {
    
    public StatusButton() {
        super("Check Upload Status");
    }

    public void runClickedAction(){
        String hash = References.getUploadHash();
        FileStatusModel status = Api.getFileStatus(hash);
        StatusPopup popup = new StatusPopup();
        popup.display(status);
    }
}
