package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

import unknowncyberplugin.api;

public class ProcRetrievalButton extends BaseButton {
    
    public ProcRetrievalButton(){
        super("Get Procedures");
    }

    @Override
    protected void runClickedAction(){
        Msg.info("Proc retrieval button clicked");
        // api.listFileGenomics
    }
}
