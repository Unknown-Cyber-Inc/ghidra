package unknowncyberplugin.Components.Buttons;

public class FileCreateButton extends BaseButton {

    public FileCreateButton() {
        super("Create");
    }

    @Override
    protected void runClickedAction(){
        System.out.println("File create button clicked");

        // create FileCRUDPopup
        // capture user input

        // check tab instanceof
        // if note:
            // make create file note request
        // elif tags:
            // make create file tag request
    }
}
