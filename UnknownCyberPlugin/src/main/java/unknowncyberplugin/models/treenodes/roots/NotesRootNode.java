package unknowncyberplugin.models.treenodes.roots;

public class NotesRootNode extends BaseRootNode<Object>{

    public NotesRootNode(){
        super("Notes");
        placeholderDisplayName = "No notes to display";
        addPlaceholderNode();
    }

}
