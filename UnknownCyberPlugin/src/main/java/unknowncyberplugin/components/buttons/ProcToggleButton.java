package unknowncyberplugin.components.buttons;

import unknowncyberplugin.components.panels.ProcTablePanel;

public class ProcToggleButton extends BaseButton {
    private static final String HIDE_TEXT = "Hide Procedure Section";
    private static final String SHOW_TEXT = "Show Procedure Section";
    private ProcTablePanel table;

    public ProcToggleButton(ProcTablePanel table){
        super(HIDE_TEXT);
        this.table = table;
    }

    @Override
    protected void runClickedAction(){
        if (HIDE_TEXT.equals(getText())){
            table.setVisible(false);
            setText(SHOW_TEXT);
        } else {
            table.setVisible(true);
            setText(HIDE_TEXT);
        }
    }
}
