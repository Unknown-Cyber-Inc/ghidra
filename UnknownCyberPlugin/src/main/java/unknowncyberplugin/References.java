package unknowncyberplugin;

import unknowncyberplugin.components.panels.CenterCRUDPanel;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panels.FileButtonsPanel;
import unknowncyberplugin.components.panels.FileCRUDPanel;
import unknowncyberplugin.components.panels.FilePanel;
import unknowncyberplugin.components.panels.ProcButtonsPanel;
import unknowncyberplugin.components.panels.ProcTablePanel;
import unknowncyberplugin.components.panes.FileNotesPane;
import unknowncyberplugin.components.panes.FileTagsPane;
import unknowncyberplugin.components.panes.FileMatchesPane;

public class References {
    // Panel references
    private static FileButtonsPanel fileButtonsPanel;
    private static FilePanel filePanel;
    private static FileCRUDPanel fileCRUDPanel;
    private static CenterPanel centerPanel;
    private static CenterCRUDPanel centerCRUDPanel;
    private static ProcButtonsPanel procButtonsPanel;
    private static ProcTablePanel procTablePanel;

    // Pane references
    private static FileNotesPane fileNotesPane;
    private static FileTagsPane fileTagsPane;
    private static FileMatchesPane fileMatchesPane;

    private References(){
        throw new UnsupportedOperationException(
            "This is a utility class and cannot be instantiated"
        );
    }

    public static void enableFullPlugin(boolean accessGranted){
        fileButtonsPanel.getToggleButton().setVisible(accessGranted);
        filePanel.setVisible(accessGranted);
        centerPanel.setVisible(accessGranted);
        procButtonsPanel.setVisible(accessGranted);
        procTablePanel.setVisible(accessGranted);
    }

    // Panel getters/setters

    public static FileButtonsPanel getFileButtonsPanel(){
        return fileButtonsPanel;
    }

    public static void setFileButtonsPanel(FileButtonsPanel fbp){
        fileButtonsPanel = fbp;
    }

    public static FilePanel getFilePanel(){
        return filePanel;
    }

    public static void setFilePanel(FilePanel fp){
        filePanel = fp; 
    }

    public static FileCRUDPanel getFileCRUDPanel(){
        return fileCRUDPanel;
    }

    public static void setFileCRUDPanel(FileCRUDPanel fcp){
        fileCRUDPanel = fcp;
    }

    public static CenterPanel getCenterPanel(){
        return centerPanel;
    }

    public static void setCenterPanel(CenterPanel cp){
        centerPanel = cp; 
    }

    public static CenterCRUDPanel getCenterCRUDPanel(){
        return centerCRUDPanel;
    }

    public static void setCenterCRUDPanel(CenterCRUDPanel ccp){
        centerCRUDPanel = ccp; 
    }

    public static ProcButtonsPanel getProcButtonsPanel(){
        return procButtonsPanel;
    }

    public static void setProcButtonsPanel(ProcButtonsPanel pbp){
        procButtonsPanel = pbp; 
    }

    public static ProcTablePanel getProcTablePanel(){
        return procTablePanel;
    }

    public static void setProcTablePanel(ProcTablePanel ptp){
        procTablePanel = ptp; 
    }

    // Pane getters/setters

    public static FileNotesPane getFileNotesPane(){
        return fileNotesPane;
    }

    public static void setFileNotesPane(FileNotesPane fnp){
        fileNotesPane = fnp;
    }

    public static FileTagsPane getFileTagsPane(){
        return fileTagsPane;
    }

    public static void setFileTagsPane(FileTagsPane ftp){
        fileTagsPane = ftp;
    }

    public static FileMatchesPane getFileMatchesPane(){
        return fileMatchesPane;
    }

    public static void setFileMatchesPane(FileMatchesPane fmp){
        fileMatchesPane = fmp;
    }
}
