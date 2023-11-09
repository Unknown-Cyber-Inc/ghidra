package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.responsedata.FileModel;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.ProcedureModel;
import unknowncyberplugin.models.responsedata.TagModel;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.SimilarProcedureNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;

public class ProcedureRootNode extends BaseRootNode<Object>{
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private SimilaritiesRootNode similaritiesRoot;
    private String startEA;

    public ProcedureRootNode(Object nodeData, String binaryId){
        super(nodeData, binaryId);

        notesRoot = new NotesRootNode();
        notesRoot.add(new NoteNode(new NoteModel("TEST NOTE NODE", null, null, null)));
        tagsRoot = new TagsRootNode();
        tagsRoot.add(new TagNode(new TagModel("TEST TAG NODE", null, null, null)));
        similaritiesRoot = new SimilaritiesRootNode();
        FilesRootNode procHoldingFileRoot = new FilesRootNode(new FileModel(binaryId, binaryId, binaryId), binaryId);
        similaritiesRoot.add(procHoldingFileRoot);
        SimilarProcedureNode similarProcNode = new SimilarProcedureNode(new ProcedureModel("0x1000", "badMal", 10, "TEST PROC", 0, 0, binaryId));
        procHoldingFileRoot.add(similarProcNode);

        add(notesRoot);
        add(tagsRoot);
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

    public SimilaritiesRootNode getSimilaritiesRootNode(){
        return similaritiesRoot;
    }
    
}
