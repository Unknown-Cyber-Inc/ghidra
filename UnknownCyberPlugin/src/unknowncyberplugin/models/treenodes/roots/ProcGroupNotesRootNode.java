package unknowncyberplugin.models.treenodes.roots;

public class ProcGroupNotesRootNode extends BaseRootNode<Object>{

    public ProcGroupNotesRootNode(){
        super("Procedure Group Notes");
        placeholderDisplayName = "No procedure group notes to display";
        addPlaceholderNode();
    }

}
