package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

public class FileCreateButton extends BaseButton {

    public FileCreateButton() {
        super("Create");
    }

    @Override
    protected void runClickedAction(){
        Msg.info(this, "File create button clicked");

        // create FileCRUDPopup
        // capture user input

        // check tab instanceof
        // if note:
            // make create file note request
        // elif tags:
            // make create file tag request
    }
}
