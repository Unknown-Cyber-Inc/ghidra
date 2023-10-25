package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

import unknowncyberplugin.components.popups.DeleteConfirmationPopup;

public class FileDeleteButton extends BaseButton {

    public FileDeleteButton() {
        super("Delete");
    }

    @Override
    protected void runClickedAction(){
        Msg.info(this, "File delete button clicked");

        // Bring up confirmation popup
        DeleteConfirmationPopup delPopup = new DeleteConfirmationPopup();
        int response = delPopup.displayAndGetResponse();
        Msg.info(this, ("Confirmation response: " + response));

        // only if response int is "0"
            // delete currently selected item in list
    }
}
