package unknowncyberplugin.models.treenodes.roots;

public class TagsRootNode extends BaseRootNode<Object>{
    
    public TagsRootNode(){
        super("Tags");
        placeholderDisplayName = "No tags to display";
        addPlaceholderNode();
    }

}
