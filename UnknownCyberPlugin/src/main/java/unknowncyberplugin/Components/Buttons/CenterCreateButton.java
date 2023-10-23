package unknowncyberplugin.Components.Buttons;

public class CenterCreateButton extends BaseButton {

    public CenterCreateButton() {
        super("Create");
    }
    
    @Override
    protected void runClickedAction(){
        System.out.println("Center create button clicked");
        // bring up center text popup
    }
}
