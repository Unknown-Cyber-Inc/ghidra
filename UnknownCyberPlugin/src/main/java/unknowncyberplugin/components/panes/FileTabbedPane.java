package unknowncyberplugin.components.panes;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.api;
import unknowncyberplugin.components.panels.FileCRUDPanel;

import javax.swing.*;

public class FileTabbedPane extends JTabbedPane {
    private UnknownCyberFileProvider fileProvider;
    private FileCRUDPanel fileCRUDPanel;
    
    public FileTabbedPane(UnknownCyberFileProvider fileProvider, FileCRUDPanel fileCRUDPanel) {
        super();
        this.fileProvider = fileProvider;
        this.fileCRUDPanel = fileCRUDPanel;

        // create and add panes as tabs
        BaseFilePane<String> notesPane = new FileNotesPane("notes", fileCRUDPanel);
		BaseFilePane<String> tagsPane = new FileTagsPane("tags", fileCRUDPanel);
		BaseFilePane<String> matchesPane = new FileMatchesPane("matches", fileCRUDPanel);
        addTab("Notes", notesPane);
        addTab("Tags", tagsPane);
        addTab("Matches", matchesPane);

        this.addChangeListener(ev -> {
            fetchAndPopulateList();
        });
    }

    private void fetchAndPopulateList(){
        String hash = fileProvider.getHash("sha1");
        BaseFilePane<?> tabComponent = getActiveTabComponent();

        if (tabComponent instanceof FileNotesPane){
            // response = api.listFileNotes(fileProvider, hash);
            // PROCESS RESPONSE TO GET DATA OUT IF NOT DONE IN REQUEST METHOD

            fileCRUDPanel.notesTabSelected();
            api.listFileNotes(fileProvider, hash);
        } else if (tabComponent instanceof FileTagsPane){
            // response = api.listFileTags(fileProvider, hash);
            // PROCESS RESPONSE TO GET DATA OUT IF NOT DONE IN REQUEST METHOD

            fileCRUDPanel.tagsTabSelected();
            api.listFileTags(fileProvider, hash);
        } else if (tabComponent instanceof FileMatchesPane){
            // response = api.getFileMatches
            // PROCESS RESPONSE TO GET DATA OUT IF NOT DONE IN REQUEST METHOD

            fileCRUDPanel.disableButtons();
            api.getFileMatches(fileProvider, hash);
        }

        // populate respective list
        tabComponent.populate(null);
    }

    public BaseFilePane<?> getActiveTabComponent() {
        return (BaseFilePane<?>) getComponentAt(getSelectedIndex());
    }

}
