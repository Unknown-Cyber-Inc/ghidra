package unknowncyberplugin.models.treenodes.roots;

public class MatchesRootNode extends BaseRootNode<Object>{

    public MatchesRootNode(){
        super("Matches");
        placeholderDisplayName = "No matches to display";
        addPlaceholderNode();
    }
    
}
