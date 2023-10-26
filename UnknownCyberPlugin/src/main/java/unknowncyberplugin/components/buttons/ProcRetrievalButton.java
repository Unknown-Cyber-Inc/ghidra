package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

import unknowncyberplugin.Api;

public class ProcRetrievalButton extends BaseButton {
    
    public ProcRetrievalButton(){
        super("Get Procedures");
    }

    @Override
    protected void runClickedAction(){
        Msg.info(this, "Proc retrieval button clicked");
        // Api.listFileGenomics
    }
}
