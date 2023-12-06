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
        FileMatchesPaginationControls pc = References.getFileMatchesPaginationControls();
        String hash = References.getFileProvider().getProgram().getExecutableMD5();
        BaseFileListPane tabComponent = getActiveTabComponent();
        Object[] items = null;

        if (tabComponent instanceof FileNotesPane){
            pc.hideControls();
            items = Api.listFileNotes(hash);

            if (items != null){
                tabComponent.populate(items);
                shownList.clearSelection();
            }
            fcp.notesTabSelected();
        } else if (tabComponent instanceof FileTagsPane){
            pc.hideControls();
            items = Api.listFileTags(hash);

            if (items != null){
                tabComponent.populate(items);
                shownList.clearSelection();
            }
            fcp.tagsTabSelected();
        } else if (tabComponent instanceof FileMatchesPane){
            pc.showControls();
            items = Api.listFileMatches(hash, pc.getCurrentPage());

            if (items != null){
                pc.setCurrentPageSize(items.length);
                tabComponent.populate(items);
                shownList.clearSelection();
            }
            fcp.disableButtons();
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
