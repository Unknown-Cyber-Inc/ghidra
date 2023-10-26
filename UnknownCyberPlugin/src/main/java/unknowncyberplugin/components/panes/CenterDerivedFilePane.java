package unknowncyberplugin.components.panes;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.Api;
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
        Api.listFileNotes(fileName);
    }

    public void populateTags(){
        Api.listFileTags(fileName);
    }

    public void populateMatches(){
        Api.getFileMatches(fileName);
    }
}
