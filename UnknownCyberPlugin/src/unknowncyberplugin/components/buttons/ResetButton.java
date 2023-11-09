package unknowncyberplugin.components.buttons;

import unknowncyberplugin.components.popups.CRUDPopup;

public class ResetButton extends PopupOptionButton {
    
    public ResetButton(CRUDPopup popup){
        super(popup, "Reset");
    }

    public void runClickedAction(){
        popup.setText("");
        popup.setValue(this);
    }
}