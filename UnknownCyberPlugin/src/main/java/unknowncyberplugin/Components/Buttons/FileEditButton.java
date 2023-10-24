package unknowncyberplugin.Components.Buttons;

public class FileEditButton extends BaseButton {

    public FileEditButton() {
        super("Edit");
    }

    @Override
    protected void runClickedAction(){
        System.out.println("File edit button clicked");
        // check tab instanceof
        // if note:
            // bring up FileCRUDPopup filled with current data
    }
}
