package unknowncyberplugin.models.treenodes.roots;

public class NotesRootNode extends BaseRootNode<Object>{

    public NotesRootNode(String binaryId){
        super("Notes", binaryId);
        placeholderDisplayName = "No notes to display";
        addPlaceholderNode();
    }

}
