package unknowncyberplugin.components.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import unknowncyberplugin.References;
import unknowncyberplugin.components.collections.FileList;
import unknowncyberplugin.components.panes.BaseFileListPane;
import unknowncyberplugin.components.panes.FileTabbedPane;

public class FilePanel extends JPanel{
    private FileCRUDPanel fileCRUDPanel;
    private FileTabbedPane fileTabs;
    
    public FilePanel(){
        fileCRUDPanel = new FileCRUDPanel();
        fileTabs = new FileTabbedPane();

        References.setFileCRUDPanel(fileCRUDPanel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(fileTabs);
        add(fileCRUDPanel);
    }

    public void updateButtons(boolean create, boolean edit, boolean delete){
        fileCRUDPanel.updateButtons(create, edit, delete);
    }

    public BaseFileListPane getActiveTabComponent(){
        return fileTabs.getActiveTabComponent();
    }

    public Object getSelectedListItem(){
        BaseFileListPane tab = fileTabs.getActiveTabComponent();
        FileList list = tab.getList();
        return list.getSelection();
    }
    
}
