package unknowncyberplugin.components.buttons;

import unknowncyberplugin.components.popups.CRUDPopup;

public class SaveButton extends PopupOptionButton {

    public SaveButton(CRUDPopup popup){
        super(popup, "Save");
    }

    public void runClickedAction(){
        popup.setValue(this);
    }
}