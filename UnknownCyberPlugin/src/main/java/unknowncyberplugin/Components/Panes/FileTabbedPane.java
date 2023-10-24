package unknowncyberplugin.Components.Panes;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.api;
import unknowncyberplugin.Components.Panels.FileCRUDPanel;
import unknowncyberplugin.Components.Panels.FilePanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FileTabbedPane extends JTabbedPane {
    UnknownCyberFileProvider fileProvider;
    FileCRUDPanel fileCRUDPanel;
    FileNotesPane notesPane;
    FileTagsPane tagsPane;
    FileMatchesPane matchesPane;
    
    public FileTabbedPane(UnknownCyberFileProvider fileProvider, FileCRUDPanel fileCRUDPanel) {
        super();
        this.fileProvider = fileProvider;
        this.fileCRUDPanel = fileCRUDPanel;

        // create and add panes as tabs
        notesPane = new FileNotesPane("notes", fileCRUDPanel);
		tagsPane = new FileTagsPane("tags", fileCRUDPanel);
		matchesPane = new FileMatchesPane("matches", fileCRUDPanel);
        addTab("Notes", notesPane);
        addTab("Tags", tagsPane);
        addTab("Matches", matchesPane);

        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fetchAndPopulateList();
            }
        });
    }

    private void fetchAndPopulateList(){
        String hash = fileProvider.getHash("sha1");
        BaseFilePane tabComponent = getTabComponent();

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
        tabComponent.populate();
    }

    public BaseFilePane getTabComponent() {
        return (BaseFilePane) getTabComponentAt(getSelectedIndex());
    }

}
