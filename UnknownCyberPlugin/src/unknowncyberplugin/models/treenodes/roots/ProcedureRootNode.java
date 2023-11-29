package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.responsedata.FileModel;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.TagModel;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.SimilarProcedureNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;

public class ProcedureRootNode extends BaseRootNode<Object>{
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private SimilaritiesRootNode similaritiesRoot;
    private ProcGroupNotesRootNode procGroupNotesRoot;
    private ProcGroupTagsRootNode procGroupTagsRoot;
    private String startEA;

    public ProcedureRootNode(Object nodeData, String binaryId, String startEA){
        super(nodeData, binaryId);
        this.startEA = startEA;

        notesRoot = new NotesRootNode();
        tagsRoot = new TagsRootNode();
        similaritiesRoot = new SimilaritiesRootNode();
        procGroupNotesRoot = new ProcGroupNotesRootNode();
        procGroupTagsRoot = new ProcGroupTagsRootNode();

        add(notesRoot);
        add(tagsRoot);
        add(procGroupNotesRoot);
        add(procGroupTagsRoot);
        add(similaritiesRoot);
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

    public ProcGroupNotesRootNode getProcGroupNoteRoot(){
        return procGroupNotesRoot;
    }

    public void populateProcGroupNotes(NoteModel[] notes){
        for (NoteModel note : notes){
            procGroupNotesRoot.add(new NoteNode(note));
        }
    }

    public ProcGroupTagsRootNode getProcGroupTagsRootNode(){
        return procGroupTagsRoot;
    }

    public void populateProcGroupTags(TagModel[] tags){
        for (TagModel tag : tags){
            procGroupTagsRoot.add(new TagNode(tag));
        }
    }

    public SimilaritiesRootNode getSimilaritiesRootNode(){
        return similaritiesRoot;
    }
    
}
