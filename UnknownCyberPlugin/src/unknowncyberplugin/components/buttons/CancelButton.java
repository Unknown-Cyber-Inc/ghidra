package unknowncyberplugin.components.buttons;

import unknowncyberplugin.components.popups.CRUDPopup;

public class CancelButton extends PopupOptionButton {
    
    public CancelButton(CRUDPopup popup){
        super(popup, "Cancel");
    }

    public void runClickedAction(){
        popup.setValue(this);
    }
}
