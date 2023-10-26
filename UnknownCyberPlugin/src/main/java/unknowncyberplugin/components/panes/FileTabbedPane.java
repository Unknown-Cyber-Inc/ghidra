package unknowncyberplugin.components.panes;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.FileCRUDPanel;

import javax.swing.*;

public class FileTabbedPane extends JTabbedPane {
    
    public FileTabbedPane() {
        super();

        // create and add panes as tabs
        BaseFileListPane<String> notesPane = new FileNotesPane("notes");
		BaseFileListPane<String> tagsPane = new FileTagsPane("tags");
		BaseFileListPane<String> matchesPane = new FileMatchesPane("matches");
        addTab("Notes", notesPane);
        addTab("Tags", tagsPane);
        addTab("Matches", matchesPane);

        this.addChangeListener(ev -> {
            fetchAndPopulateList();
        });
    }

    private void fetchAndPopulateList(){
        FileCRUDPanel fcp = References.getFileCRUDPanel();
        String hash = Api.getFileProvider().getHash("sha1");
        BaseFileListPane<?> tabComponent = getActiveTabComponent();

        if (tabComponent instanceof FileNotesPane){
            // PROCESS RESPONSE TO GET DATA OUT IF NOT DONE IN REQUEST METHOD

            fcp.notesTabSelected();
            Api.listFileNotes(hash);
            // notesPane.populate(API RESPONSE DATA);
        } else if (tabComponent instanceof FileTagsPane){
            // PROCESS RESPONSE TO GET DATA OUT IF NOT DONE IN REQUEST METHOD

            fcp.tagsTabSelected();
            Api.listFileTags(hash);
            // tagsPane.populate(API RESPONSE DATA);
        } else if (tabComponent instanceof FileMatchesPane){
            // PROCESS RESPONSE TO GET DATA OUT IF NOT DONE IN REQUEST METHOD

            fcp.disableButtons();
            Api.getFileMatches(hash);
            // matchesPane.populate(API RESPONSE DATA);
        }

        // populate respective list
        tabComponent.populate(null);
    }

    public BaseFileListPane<?> getActiveTabComponent() {
        return (BaseFileListPane<?>) getComponentAt(getSelectedIndex());
    }

}
