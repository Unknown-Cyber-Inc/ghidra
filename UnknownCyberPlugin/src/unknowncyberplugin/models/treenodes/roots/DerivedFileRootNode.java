package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.responsedata.FileModel;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.TagModel;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;

public class DerivedFileRootNode extends BaseRootNode<Object>{
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;

    public DerivedFileRootNode(FileModel nodeData, String binaryId){
        super(nodeData, binaryId);

        notesRoot = new NotesRootNode();
        tagsRoot = new TagsRootNode();

        add(notesRoot);
        add(tagsRoot);
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
