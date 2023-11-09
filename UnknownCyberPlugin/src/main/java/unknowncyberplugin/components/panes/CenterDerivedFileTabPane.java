package unknowncyberplugin.components.panes;

import unknowncyberplugin.Api;
import unknowncyberplugin.models.responsedata.MatchModel;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.TagModel;
import unknowncyberplugin.models.treenodes.roots.MatchesRootNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;

public class CenterDerivedFileTabPane extends BaseCenterTabPane{
    private String fileName;
    private MatchesRootNode matchesRoot;
    
    public CenterDerivedFileTabPane(String fileName){
        super(fileName, fileName, "file");
        this.fileName = fileName;

        matchesRoot = new MatchesRootNode();
        
        tree.addNode(rootNode, matchesRoot);
    }

    @Override
    protected void callExpandAction(Object subRootNode){
        if (subRootNode instanceof NotesRootNode){
            NoteModel[] notes = Api.listFileNotes(fileName);
            ((DerivedFileRootNode)getRootNode()).populateNotes(notes);
        } else if (subRootNode instanceof TagsRootNode){
            TagModel[] tags = Api.listFileTags(fileName);
            ((DerivedFileRootNode)getRootNode()).populateTags(tags);
        } else if (subRootNode instanceof MatchesRootNode){
            MatchModel[] matches = Api.listFileMatches(fileName);
            // TODO: fix this
            //((DerivedFileRootNode)getRootNode()).populateMatches(matches);
        }
    }

    public MatchesRootNode getMatchesRootNode(){
        return matchesRoot;
    }
}
