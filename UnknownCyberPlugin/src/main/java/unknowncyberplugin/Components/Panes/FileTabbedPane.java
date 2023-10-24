package unknowncyberplugin.Components.Panes;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.api;
import unknowncyberplugin.Components.FileList;

import java.awt.Component;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FileTabbedPane extends JTabbedPane {
    UnknownCyberFileProvider fileProvider;
    BaseFilePane notesPane;
    BaseFilePane tagsPane;
    BaseFilePane matchesPane;
    
    public FileTabbedPane(UnknownCyberFileProvider fileProvider) {
        super();
        this.fileProvider = fileProvider;

        // create and add BaseFilePanes as tabs
        notesPane = new FileNotesPane();
		tagsPane = new FileTagsPane();
		matchesPane = new FileMatchesPane();
        addTab("Notes", notesPane);
        addTab("Tags", tagsPane);
        addTab("Matches", matchesPane);

        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = getSelectedIndex();
                String tabTitle = getTitleAt(index);

                System.out.println("Selected tab: " + tabTitle);
                fetchAndPopulateList(tabTitle);
            }
        });
    }

    private void fetchAndPopulateList(String listType){
        String hash = fileProvider.getHash("sha1");
        BaseFilePane tabComponent = (BaseFilePane) getTabComponentAt(getSelectedIndex());

        if (tabComponent instanceof FileNotesPane){
            // response = api.listFileNotes(fileProvider, hash);
            // PROCESS RESPONSE TO GET DATA OUT
            api.listFileNotes(fileProvider, hash);
        } else if (tabComponent instanceof FileTagsPane){
            // response = api.listFileTags(fileProvider, hash);
            // PROCESS RESPONSE TO GET DATA OUT
            api.listFileTags(fileProvider, hash);
        } else if (tabComponent instanceof FileMatchesPane){
            // response = api.getFileMatches
            // PROCESS RESPONSE TO GET DATA OUT
            api.getFileMatches(fileProvider, hash);
        }

        // populate respective list
        tabComponent.populate();
    }

    public String getSelectedTabTitle() {
        return getTitleAt(getSelectedIndex());
    }

}
