package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

import unknowncyberplugin.components.popups.CRUDPopup;

public class SaveButton extends PopupOptionButton {

    public SaveButton(CRUDPopup popup){
        super(popup, "Save");
    }

    public void runClickedAction(){
        Msg.info(this, ("Saving: " + popup.getText()));
        popup.setValue(this);
    }
}