package unknowncyberplugin.Components.Buttons;

import unknowncyberplugin.Components.Popups.CRUDPopup;

public class ResetButton extends PopupOptionButton {
    
    public ResetButton(CRUDPopup popup){
        super(popup, "Reset");
    }

    public void runClickedAction(){
        popup.setText("");
        popup.setValue(this);
    }
}