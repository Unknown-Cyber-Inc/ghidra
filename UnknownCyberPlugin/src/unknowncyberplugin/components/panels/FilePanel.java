package unknowncyberplugin.components.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.collections.FileList;
import unknowncyberplugin.components.panes.BaseFileListPane;
import unknowncyberplugin.components.panes.FileMatchesPane;
import unknowncyberplugin.components.panes.FileTabbedPane;
import unknowncyberplugin.models.responsedata.MatchModel;

public class FilePanel extends JPanel{
    private FileCRUDPanel fileCRUDPanel;
    private FileTabbedPane fileTabs;
    private FileMatchesPaginationControls pageControls;
    
    public FilePanel(){
        fileCRUDPanel = new FileCRUDPanel();
        fileTabs = new FileTabbedPane();
        pageControls = new FileMatchesPaginationControls(this::changeMatchesPage);
        pageControls.setVisible(false);

        References.setFileCRUDPanel(fileCRUDPanel);
        References.setFileMatchesPaginationControls(pageControls);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(fileTabs);
        add(pageControls);
        add(fileCRUDPanel);
    }

    public void updateButtons(boolean create, boolean edit, boolean delete){
        fileCRUDPanel.updateButtons(create, edit, delete);
    }

    public BaseFileListPane getActiveTabComponent(){
        return fileTabs.getActiveTabComponent();
    }

    public void setMatchesTabToActive(boolean fileAccessible){
        if (fileAccessible) {
            fileTabs.setSelectedComponent(fileTabs.getMatchesPane());
        }
    }

    public Object getSelectedListItem(){
        BaseFileListPane tab = fileTabs.getActiveTabComponent();
        FileList list = tab.getList();
        return list.getSelection();
    }

    private void changeMatchesPage(int pageCount){
        MatchModel[] matchList= Api.listFileMatches(
            References.getFileProvider().getOriginalSha1(), pageCount);
        FileMatchesPane matchesPane = (FileMatchesPane)fileTabs.getMatchesPane();
        matchesPane.populate(matchList);
        pageControls.setCurrentPageSize(matchList.length);
    }

    public FileMatchesPaginationControls getPageControls(){
        return pageControls;
    }
    
}
