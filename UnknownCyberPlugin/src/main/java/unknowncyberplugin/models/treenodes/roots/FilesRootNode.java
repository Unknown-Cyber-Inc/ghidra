package unknowncyberplugin.models.treenodes.roots;

import unknowncyberplugin.models.responsedata.FileModel;

public class FilesRootNode extends BaseRootNode<Object>{

    public FilesRootNode(FileModel nodeData, String binaryId){
        super(nodeData, binaryId);
        // No need for a placeholder here. This class will never be
        // instantiated without files containing the procedure existing.
    }
    
}
