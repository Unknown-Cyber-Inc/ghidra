package unknowncyberplugin.Components.Buttons;

public class CenterEditButton extends BaseButton {

    public CenterEditButton() {
        super("Edit");
    }
    
    @Override
    protected void runClickedAction(){
        System.out.println("Center edit button clicked");
        // bring up center text popup filled with current data
    }
    
}
