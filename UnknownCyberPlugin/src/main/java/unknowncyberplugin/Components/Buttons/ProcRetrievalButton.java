package unknowncyberplugin.Components.Buttons;

public class ProcRetrievalButton extends BaseButton {
    
    public ProcRetrievalButton(){
        super("Get Procedures");
    }

    @Override
    protected void runClickedAction(){
        System.out.println("Proc retrieval button clicked");
        // show/hide proc table component
    }
}
