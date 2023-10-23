package unknowncyberplugin.Components.Buttons;

public class FileToggleButton extends BaseButton {

    public FileToggleButton(){
        super("Hide File Section");
    }

    @Override
    protected void runClickedAction(){
        System.out.println("File toggle button clicked");
        setText(
            "Hide File Section".equals(getText())
            ? "Show File Section"
            : "Hide File Section"
        );
        // show/hide file list component
    }
}
