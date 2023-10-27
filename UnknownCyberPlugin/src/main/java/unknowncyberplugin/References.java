package unknowncyberplugin;

import unknowncyberplugin.components.panels.CenterCRUDPanel;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panels.FileCRUDPanel;
import unknowncyberplugin.components.panels.FilePanel;

public class References {
    private static FileCRUDPanel fileCRUDPanel;
    private static FilePanel filePanel;
    private static CenterCRUDPanel centerCRUDPanel;
    private static CenterPanel centerPanel;

    private References(){
        throw new UnsupportedOperationException(
            "This is a utility class and cannot be instantiated"
        );
    }

    public static FileCRUDPanel getFileCRUDPanel(){
        return fileCRUDPanel;
    }

    public static void setFileCRUDPanel(FileCRUDPanel fcp){
        fileCRUDPanel = fcp;
    }

    public static FilePanel getFilePanel(){
        return filePanel;
    }

    public static void setFilePanel(FilePanel fp){
        filePanel = fp; 
    }

    public static CenterCRUDPanel getCenterCRUDPanel(){
        return centerCRUDPanel;
    }

    public static void setCenterCRUDPanel(CenterCRUDPanel ccp){
        centerCRUDPanel = ccp; 
    }

    public static CenterPanel getCenterPanel(){
        return centerPanel;
    }

    public static void setCenterPanel(CenterPanel cp){
        centerPanel = cp; 
    }
    
}
