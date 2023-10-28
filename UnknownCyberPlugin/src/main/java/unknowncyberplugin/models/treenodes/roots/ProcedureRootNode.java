package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.responsedata.File;
import unknowncyberplugin.models.responsedata.Note;
import unknowncyberplugin.models.responsedata.Tag;
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
        notesRoot.add(new NoteNode(new Note("TEST NOTE NODE", null, null, null)));
        tagsRoot = new TagsRootNode();
        tagsRoot.add(new TagNode(new Tag("TEST TAG NODE", null, null, null)));
        similaritiesRoot = new SimilaritiesRootNode();
        similaritiesRoot.add(new SimilarProcedureNode(new File("TEST SIMILAR PROC NODE", null, null)));

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

    public void populateNotes(Note[] notes){
        for (Note note : notes){
            notesRoot.add(new NoteNode(note));
        }
    }

    public TagsRootNode getTagsRootNode(){
        return tagsRoot;
    }

    public void populateTags(Tag[] tags){
        for (Tag tag : tags){
            tagsRoot.add(new TagNode(tag));
        }
    }

    public SimilaritiesRootNode getSimilaritiesRootNode(){
        return similaritiesRoot;
    }

    public void populateSimilarities(File[] similarities){
        for (File sim : similarities){
            tagsRoot.add(new SimilarProcedureNode(sim));
        }
    }
    
}
