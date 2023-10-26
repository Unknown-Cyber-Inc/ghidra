package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.treenodes.leaves.NoteNode;

public class ProcedureRootNode extends BaseRootNode<Object>{
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private SimilaritiesRootNode similaritiesRoot;
    private String address;

    public ProcedureRootNode(Object nodeData, String binaryId){
        super(nodeData, binaryId);

        notesRoot = new NotesRootNode();
        tagsRoot = new TagsRootNode();
        similaritiesRoot = new SimilaritiesRootNode();

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
