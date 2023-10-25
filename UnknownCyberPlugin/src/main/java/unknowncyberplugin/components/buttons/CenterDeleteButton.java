package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

import unknowncyberplugin.components.popups.DeleteConfirmationPopup;

public class CenterDeleteButton extends BaseButton {

    public CenterDeleteButton() {
        super("Delete");
    }
    
    @Override
    protected void runClickedAction(){
        Msg.info(this, "Center delete button clicked");

        // bring up delete confirmation popup
        DeleteConfirmationPopup delPopup = new DeleteConfirmationPopup();
        int response = delPopup.displayAndGetResponse();
        Msg.info(this, ("Confirmation response: " + response));

        // make delete api call
    }

}
