package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

public class CenterEditButton extends BaseButton {

    public CenterEditButton() {
        super("Edit");
    }
    
    @Override
    protected void runClickedAction(){
        Msg.info(this, "Center edit button clicked");
        // bring up center popup filled with current data
    }
    
}
