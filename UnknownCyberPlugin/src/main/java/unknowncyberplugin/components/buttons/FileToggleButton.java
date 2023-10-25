package unknowncyberplugin.components.buttons;

import unknowncyberplugin.components.panels.FilePanel;

public class FileToggleButton extends BaseButton {
    private static final String HIDE_TEXT = "Hide File Section";
    private static final String SHOW_TEXT = "Show File Section";
    private FilePanel filePanel;

    public FileToggleButton(FilePanel filePanel){
        super(HIDE_TEXT);
        this.filePanel = filePanel;
    }

    @Override
    protected void runClickedAction(){
        if (HIDE_TEXT.equals(getText())){
            filePanel.setVisible(false);
            setText(SHOW_TEXT);
        } else {
            filePanel.setVisible(true);
            setText(HIDE_TEXT);
        }
    }
}
