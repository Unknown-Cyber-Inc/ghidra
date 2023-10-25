package unknowncyberplugin.components.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panes.FileTabbedPane;

public class FilePanel extends JPanel{
    private FileCRUDPanel fileCRUDPanel;
    
    public FilePanel(UnknownCyberFileProvider fileProvider){
        fileCRUDPanel = new FileCRUDPanel();
        FileTabbedPane fileTabs = new FileTabbedPane(fileProvider, fileCRUDPanel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(fileTabs);
        add(fileCRUDPanel);
    }

    public void updateButtons(boolean create, boolean edit, boolean delete){
        fileCRUDPanel.updateButtons(create, edit, delete);
    }
    
}
