package unknowncyberplugin.Components.Buttons;

import unknowncyberplugin.Components.Popups.DeleteConfirmationPopup;

public class CenterDeleteButton extends BaseButton {

    public CenterDeleteButton() {
        super("Delete");
    }
    
    @Override
    protected void runClickedAction(){
        System.out.println("Center delete button clicked");

        // bring up delete confirmation popup
        DeleteConfirmationPopup delPopup = new DeleteConfirmationPopup();
        int response = delPopup.displayAndGetResponse();
        System.out.println("Confirmation response: " + response);

        // make delete api call
    }

}
