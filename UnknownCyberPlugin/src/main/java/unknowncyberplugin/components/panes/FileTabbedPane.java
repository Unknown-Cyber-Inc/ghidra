package unknowncyberplugin.components.panes;

import javax.swing.JTabbedPane;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.collections.FileList;
import unknowncyberplugin.components.panels.FileCRUDPanel;
import unknowncyberplugin.models.responsedata.File;
import unknowncyberplugin.models.responsedata.Note;
import unknowncyberplugin.models.responsedata.Tag;

public class FileTabbedPane extends JTabbedPane {
    private FileList shownList;
    
    public FileTabbedPane() {
        super();

        BaseFileListPane notesPane = new FileNotesPane();
        notesPane.addItem(new Note("TEST NOTE ITEM", null, null, null));
		BaseFileListPane tagsPane = new FileTagsPane();
        tagsPane.addItem(new Tag("TEST TAG ITEM", null, null, null));
		BaseFileListPane matchesPane = new FileMatchesPane();
        matchesPane.addItem(new File("TEST MATCH ITEM", null, null));
        addTab("Notes", notesPane);
        addTab("Tags", tagsPane);
        addTab("Matches", matchesPane);

        this.addChangeListener(ev -> {
            if (shownList != null){
                shownList.clearSelection();
            }

            fetchAndPopulateList();

            shownList = getActiveTabComponent().getList();
        });
    }

    private void fetchAndPopulateList(){
        FileCRUDPanel fcp = References.getFileCRUDPanel();
        String hash = References.getFileProvider().getOriginalSha1();
        BaseFileListPane tabComponent = getActiveTabComponent();
        Object[] items = null;

        if (tabComponent instanceof FileNotesPane){
            fcp.notesTabSelected();
            items = Api.listFileNotes(hash);
        } else if (tabComponent instanceof FileTagsPane){
            fcp.tagsTabSelected();
            items = Api.listFileTags(hash);
        } else if (tabComponent instanceof FileMatchesPane){
            fcp.disableButtons();
            // items = Api.getFileMatches(hash);
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

}
