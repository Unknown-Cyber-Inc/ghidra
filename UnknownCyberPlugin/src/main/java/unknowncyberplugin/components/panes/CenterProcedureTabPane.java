package unknowncyberplugin.components.panes;

import unknowncyberplugin.Api;
import unknowncyberplugin.models.treenodes.roots.SimilaritiesRootNode;

public class CenterProcedureTabPane extends BaseCenterTabPane{
    private String procName;
    private SimilaritiesRootNode similaritiesRoot;

    public CenterProcedureTabPane(String procName){
        super(procName, Api.getFileProvider().getHash("sha1"), "procedure");
        this.procName = procName;

        similaritiesRoot = new SimilaritiesRootNode();

        tree.addNode(rootNode, similaritiesRoot);
    }

    public SimilaritiesRootNode getSimilaritiesRootNode(){
        return similaritiesRoot;
    }

    public void populateNotes(){
        // populate notes
        // Api.listProcedureGenomicsNotes
    }

    public void populateTags(){
        // populate tags
        // Api.listProcedureGenomicsTags
    }

    public void populateContainingFiles(){
        // populate containing files
        // Api.listProcedureFiles
    }

    public void populateSimilarities(){
        // populate procedure similarities
        // Api.listProcedureSimilarities
    }
    
}
