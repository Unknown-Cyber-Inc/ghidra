package unknowncyberplugin.Components.Buttons;

public class FileCreateButton extends BaseButton {

    public FileCreateButton() {
        super("Create");
    }

    @Override
    protected void runClickedAction(){
        System.out.println("File create button clicked");
        // bring up file text popup
    }
}
