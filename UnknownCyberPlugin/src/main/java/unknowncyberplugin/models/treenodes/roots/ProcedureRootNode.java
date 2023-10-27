package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.SimilarProcedureNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;

public class ProcedureRootNode extends BaseRootNode<Object>{
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private SimilaritiesRootNode similaritiesRoot;
    private String address;

    public ProcedureRootNode(Object nodeData, String binaryId){
        super(nodeData, binaryId);

        notesRoot = new NotesRootNode(binaryId);
        notesRoot.add(new NoteNode("TEST NOTE NODE"));
        tagsRoot = new TagsRootNode(binaryId);
        tagsRoot.add(new TagNode("TEST TAG NODE"));
        similaritiesRoot = new SimilaritiesRootNode();
        similaritiesRoot.add(new SimilarProcedureNode("TEST SIMILAR PROC NODE"));

        add(notesRoot);
        add(tagsRoot);
        add(similaritiesRoot);
    }

    public String getAddress(){
        return address;
    }

    public NotesRootNode getNoteRoot(){
        return notesRoot;
    }

    public void populateNotes(Object[] notes){
        for (Object note : notes){
            notesRoot.add(new NoteNode(note));
        }
    }

    public TagsRootNode getTagsRootNode(){
        return tagsRoot;
    }

    public void populateTags(Object[] tags){
        for (Object tag : tags){
            tagsRoot.add(new NoteNode(tag));
        }
    }

    public SimilaritiesRootNode getSimilaritiesRootNode(){
        return similaritiesRoot;
    }

    public void populateSimilarities(Object[] similarities){
        for (Object sim : similarities){
            tagsRoot.add(new NoteNode(sim));
        }
    }
    
}
