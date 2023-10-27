package unknowncyberplugin.models.treenodes.roots;

public class FilesRootNode extends BaseRootNode<Object>{

    public FilesRootNode(Object nodeData){
        super(nodeData);
        // No need for a placeholder here. This class will never be
        // instantiated without files containing the procedure existing.
    }
    
}
