package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.responsedata.File;

public class FilesRootNode extends BaseRootNode<Object>{

    public FilesRootNode(File nodeData){
        super(nodeData);
        // No need for a placeholder here. This class will never be
        // instantiated without files containing the procedure existing.
    }
    
}
