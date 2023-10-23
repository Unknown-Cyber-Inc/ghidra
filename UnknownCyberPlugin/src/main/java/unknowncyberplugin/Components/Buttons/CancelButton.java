package unknowncyberplugin.Components.Buttons;

import unknowncyberplugin.Components.Popups.CRUDPopup;

public class CancelButton extends PopupOptionButton {
    
    public CancelButton(CRUDPopup popup){
        super(popup, "Cancel");
    }

    public void runClickedAction(){
        popup.setValue(this);
    }
}
