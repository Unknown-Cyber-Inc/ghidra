package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.treenodes.leaves.NoteNode;

public class DerivedFileRootNode extends BaseRootNode<Object>{
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private MatchesRootNode matchesRoot;

    public DerivedFileRootNode(Object nodeData, String binaryId){
        super(nodeData, binaryId);

        notesRoot = new NotesRootNode(binaryId);
        tagsRoot = new TagsRootNode(binaryId);
        matchesRoot = new MatchesRootNode();

        add(notesRoot);
        add(tagsRoot);
        add(matchesRoot);
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

    public MatchesRootNode getMatchesRootNode(){
        return matchesRoot;
    }

    public void populateMatches(Object[] matches){
        for (Object match : matches){
            matchesRoot.add(new NoteNode(match));
        }
    }
    
}
