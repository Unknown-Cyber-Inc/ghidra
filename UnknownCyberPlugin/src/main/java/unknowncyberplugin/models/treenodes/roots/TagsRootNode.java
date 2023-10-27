package unknowncyberplugin.models.treenodes.roots;

public class TagsRootNode extends BaseRootNode<Object>{
    
    public TagsRootNode(String binaryId){
        super("Tags", binaryId);
        placeholderDisplayName = "No tags to display";
        addPlaceholderNode();
    }

}
