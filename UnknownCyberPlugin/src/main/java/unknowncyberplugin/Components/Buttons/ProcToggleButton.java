package unknowncyberplugin.Components.Buttons;

public class ProcToggleButton extends BaseButton {

    public ProcToggleButton(){
        super("Hide Procedure Section");
    }

    @Override
    protected void runClickedAction(){
        System.out.println("Proc toggle button clicked");
        setText(
            "Hide Procedure Section".equals(getText())
            ? "Show Procedure Section"
            : "Hide Procedure Section"
        );
        // show/hide proc table component
    }
}
