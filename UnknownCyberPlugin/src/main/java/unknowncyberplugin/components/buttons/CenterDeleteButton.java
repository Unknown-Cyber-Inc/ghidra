package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

import unknowncyberplugin.components.popups.DeleteConfirmationPopup;

public class CenterDeleteButton extends BaseButton {

    public CenterDeleteButton() {
        super("Delete");
    }
    
    @Override
    protected void runClickedAction(){
        Msg.info("Center delete button clicked");

        // bring up delete confirmation popup
        DeleteConfirmationPopup delPopup = new DeleteConfirmationPopup();
        int response = delPopup.displayAndGetResponse();
        Msg.info("Confirmation response: " + response);

        // make delete api call
    }

}
