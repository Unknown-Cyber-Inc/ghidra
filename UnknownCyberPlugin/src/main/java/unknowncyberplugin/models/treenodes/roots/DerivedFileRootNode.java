package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.responsedata.File;
import unknowncyberplugin.models.responsedata.Note;
import unknowncyberplugin.models.responsedata.Tag;
import unknowncyberplugin.models.treenodes.leaves.MatchNode;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;

public class DerivedFileRootNode extends BaseRootNode<Object>{
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private MatchesRootNode matchesRoot;

    public DerivedFileRootNode(File nodeData, String binaryId){
        super(nodeData, binaryId);

        notesRoot = new NotesRootNode();
        tagsRoot = new TagsRootNode();
        matchesRoot = new MatchesRootNode();

        add(notesRoot);
        add(tagsRoot);
        add(matchesRoot);
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

    public MatchesRootNode getMatchesRootNode(){
        return matchesRoot;
    }

    public void populateMatches(File[] matches){
        for (File match : matches){
            matchesRoot.add(new MatchNode(match));
        }
    }
    
}
