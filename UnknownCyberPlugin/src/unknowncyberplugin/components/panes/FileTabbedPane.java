package unknowncyberplugin.components.panes;

import javax.swing.JTabbedPane;
import javax.swing.DefaultListModel;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.collections.FileList;
import unknowncyberplugin.components.panels.FileCRUDPanel;
import unknowncyberplugin.components.panels.FileMatchesPaginationControls;

public class FileTabbedPane extends JTabbedPane {
    private FileList shownList;
    private BaseFileListPane mPane;
    
    public FileTabbedPane() {
        super();

        BaseFileListPane notesPane = new FileNotesPane();
		BaseFileListPane tagsPane = new FileTagsPane();
		BaseFileListPane matchesPane = new FileMatchesPane();
        mPane = matchesPane;
        addTab("Notes", notesPane);
        addTab("Tags", tagsPane);
        addTab("Matches", matchesPane);

        References.setFileNotesPane((FileNotesPane)notesPane);
        References.setFileTagsPane((FileTagsPane)tagsPane);
        References.setFileMatchesPane((FileMatchesPane)matchesPane);

        this.addChangeListener(ev -> {
            this.shownList = getActiveTabComponent().getList();
            DefaultListModel<Object> listModel = (DefaultListModel<Object>)shownList.getModel();
            listModel.clear();
            if (listModel != null){
                fetchAndPopulateList();
            }
        });
    }

    private void fetchAndPopulateList(){
        FileCRUDPanel fcp = References.getFileCRUDPanel();
        String hash = References.getFileProvider().getProgram().getExecutableMD5();
        BaseFileListPane tabComponent = getActiveTabComponent();
        Object[] items = null;

        if (tabComponent instanceof FileNotesPane){
            fcp.notesTabSelected();
            References.getFilePanel().getPageControls().setVisible(false);
            items = Api.listFileNotes(hash);
        } else if (tabComponent instanceof FileTagsPane){
            fcp.tagsTabSelected();
            References.getFilePanel().getPageControls().setVisible(false);
            items = Api.listFileTags(hash);
        } else if (tabComponent instanceof FileMatchesPane){
            fcp.disableButtons();
            References.getFilePanel().getPageControls().setVisible(true);
            FileMatchesPaginationControls pc = References.getFileMatchesPaginationControls();
            items = Api.listFileMatches(hash, pc.getCurrentPage());

            if (items != null){
                pc.setCurrentPageSize(items.length);
            }
        }

        if (items != null){
            tabComponent.populate(items);
        }
    }

    public BaseFileListPane getActiveTabComponent() {
        if (getSelectedIndex() == -1){
            return null;
        }
        return (BaseFileListPane) getComponentAt(getSelectedIndex());
    }

    public BaseFileListPane getMatchesPane(){
        return mPane;
    }

}
