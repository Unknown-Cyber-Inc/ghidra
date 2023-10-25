package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

public class CenterCreateButton extends BaseButton {

    public CenterCreateButton() {
        super("Create");
    }
    
    @Override
    protected void runClickedAction(){
        Msg.info(this, "Center create button clicked");
        // bring up center popup
    }
}
