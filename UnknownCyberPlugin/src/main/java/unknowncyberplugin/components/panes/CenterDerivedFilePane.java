package unknowncyberplugin.components.panes;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.api;
import unknowncyberplugin.components.panels.CenterCRUDPanel;

public class CenterDerivedFilePane extends BaseCenterTabPane{
    private String fileName;
    
    public CenterDerivedFilePane(String fileName, UnknownCyberFileProvider fileProvider, CenterCRUDPanel centerCRUDPanel){
        super(fileName, fileProvider, centerCRUDPanel);
        this.fileName = fileName;

        DefaultMutableTreeNode matchesNode = new DefaultMutableTreeNode("Matches");
        tree.addNode(rootNode, matchesNode);
    }

    public void populateNotes(){
        api.listFileNotes(fileProvider, fileName);
    }

    public void populateTags(){
        api.listFileTags(fileProvider, fileName);
    }

    public void populateMatches(){
        api.getFileMatches(fileProvider, fileName);
    }
}
