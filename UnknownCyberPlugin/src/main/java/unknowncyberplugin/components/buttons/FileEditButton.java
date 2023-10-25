package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

public class FileEditButton extends BaseButton {

    public FileEditButton() {
        super("Edit");
    }

    @Override
    protected void runClickedAction(){
        Msg.info("File edit button clicked");
        // check tab instanceof
        // if note:
            // bring up FileCRUDPopup filled with current data
    }
}
