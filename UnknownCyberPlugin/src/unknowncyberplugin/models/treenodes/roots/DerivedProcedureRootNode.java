package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.responsedata.FileModel;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.TagModel;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.SimilarProcedureNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;

public class DerivedProcedureRootNode extends BaseRootNode<Object>{
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private String startEA;

    public DerivedProcedureRootNode(Object nodeData, String binaryId, String startEA){
        super(nodeData, binaryId);
        this.startEA = startEA;

        notesRoot = new NotesRootNode();
        tagsRoot = new TagsRootNode();

        add(notesRoot);
        add(tagsRoot);
    }

    public String getStartEA(){
        return startEA;
    }

    public NotesRootNode getNoteRoot(){
        return notesRoot;
    }

    public void populateNotes(NoteModel[] notes){
        for (NoteModel note : notes){
            notesRoot.add(new NoteNode(note));
        }
    }

    public TagsRootNode getTagsRootNode(){
        return tagsRoot;
    }

    public void populateTags(TagModel[] tags){
        for (TagModel tag : tags){
            tagsRoot.add(new TagNode(tag));
        }
    }
    
}
