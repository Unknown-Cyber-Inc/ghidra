package unknowncyberplugin.Components.Buttons;

import unknowncyberplugin.Components.Popups.DeleteConfirmationPopup;

public class FileDeleteButton extends BaseButton {

    public FileDeleteButton() {
        super("Delete");
    }

    @Override
    protected void runClickedAction(){
        System.out.println("File delete button clicked");

        // Bring up confirmation popup
        DeleteConfirmationPopup delPopup = new DeleteConfirmationPopup();
        int response = delPopup.displayAndGetResponse();
        System.out.println("Confirmation response: " + response);

        // only if response int is "0"
            // delete currently selected item in list
    }
}
