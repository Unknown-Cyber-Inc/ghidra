package unknowncyberplugin.components.buttons;

import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.FilePanel;

public class FileToggleButton extends BaseButton {
    private static final String HIDE_TEXT = "Hide File Section";
    private static final String SHOW_TEXT = "Show File Section";

    public FileToggleButton(){
        super(HIDE_TEXT);
    }

    @Override
    protected void runClickedAction(){
        FilePanel fp = References.getFilePanel();
        if (HIDE_TEXT.equals(getText())){
            fp.setVisible(false);
            setText(SHOW_TEXT);
        } else {
            fp.setVisible(true);
            setText(HIDE_TEXT);
        }
    }
}
