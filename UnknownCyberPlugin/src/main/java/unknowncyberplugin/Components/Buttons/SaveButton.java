package unknowncyberplugin.Components.Buttons;

import unknowncyberplugin.Components.Popups.CRUDPopup;

public class SaveButton extends PopupOptionButton {

    public SaveButton(CRUDPopup popup){
        super(popup, "Save");
    }

    public void runClickedAction(){
        System.out.println("Saving: " + popup.getText());
        popup.setValue(this);
    }
}