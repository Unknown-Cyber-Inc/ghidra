package unknowncyberplugin.Components.Buttons;

public class FileEditButton extends BaseButton {

    public FileEditButton() {
        super("Edit");
    }

    @Override
    protected void runClickedAction(){
        System.out.println("File edit button clicked");
        // bring up file text popup filled with current data
    }
}
