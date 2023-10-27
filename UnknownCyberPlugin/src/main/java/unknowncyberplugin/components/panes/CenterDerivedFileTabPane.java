package unknowncyberplugin.components.panes;

import unknowncyberplugin.Api;
import unknowncyberplugin.models.treenodes.roots.MatchesRootNode;

public class CenterDerivedFileTabPane extends BaseCenterTabPane{
    private String fileName;
    private MatchesRootNode matchesRoot;
    
    public CenterDerivedFileTabPane(String fileName){
        super(fileName, fileName, "file");
        this.fileName = fileName;

        matchesRoot = new MatchesRootNode();
        
        tree.addNode(rootNode, matchesRoot);
    }

    public MatchesRootNode getMatchesRootNode(){
        return matchesRoot;
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
