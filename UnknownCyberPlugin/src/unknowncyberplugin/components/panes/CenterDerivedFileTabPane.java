package unknowncyberplugin.components.panes;

import unknowncyberplugin.Api;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.TagModel;
import unknowncyberplugin.models.treenodes.roots.MatchesRootNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;

public class CenterDerivedFileTabPane extends BaseCenterTabPane{
    private String fileName;
    
    public CenterDerivedFileTabPane(String fileName){
        super(fileName, fileName, "file");
        this.fileName = fileName;
    }

    @Override
    protected void callExpandAction(Object subRootNode){
        if (subRootNode instanceof NotesRootNode){
            NoteModel[] notes = Api.listFileNotes(fileName);
            ((DerivedFileRootNode)getRootNode()).populateNotes(notes);
        } else if (subRootNode instanceof TagsRootNode){
            TagModel[] tags = Api.listFileTags(fileName);
            ((DerivedFileRootNode)getRootNode()).populateTags(tags);
        }
    }
}
