package unknowncyberplugin.components.buttons;

import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.ProcTablePanel;

public class ProcToggleButton extends BaseButton {
    private static final String HIDE_TEXT = "Hide Procedures Section";
    private static final String SHOW_TEXT = "Show Procedures Section";

    public ProcToggleButton(){
        super(HIDE_TEXT);
    }

    @Override
    protected void runClickedAction(){
        ProcTablePanel ptp = References.getProcTablePanel();
        if (HIDE_TEXT.equals(getText())){
            ptp.setVisible(false);
            setText(SHOW_TEXT);
        } else {
            ptp.setVisible(true);
            setText(HIDE_TEXT);
        }
    }
}
