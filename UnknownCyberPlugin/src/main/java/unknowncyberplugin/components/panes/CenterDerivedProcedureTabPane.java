package unknowncyberplugin.components.panes;

import unknowncyberplugin.Api;
import unknowncyberplugin.models.treenodes.roots.SimilaritiesRootNode;

public class CenterDerivedProcedureTabPane extends BaseCenterTabPane{
    private String procName;
    private SimilaritiesRootNode similaritiesRoot;

    public CenterDerivedProcedureTabPane(String procName, String binaryId){
        super(procName, binaryId, "procedure");
        this.procName = procName;

        similaritiesRoot = new SimilaritiesRootNode();

        tree.addNode(rootNode, similaritiesRoot);
    }

    public SimilaritiesRootNode getSimilaritiesRootNode(){
        return similaritiesRoot;
    }

    public void populateNotes(){
        // populate notesNode with notes
        // Api.listProcedureGenomicsNotes
    }

    public void populateTags(){
        // populate tagsNode with tags
        // Api.listProcedureGenomicsTags
    }

    public void populateContainingFiles(){
        // populate filesNode with containing files
        // Api.listProcedureFiles
    }

    public void populateSimilarities(){
        // populate similaritiesNode with procedure similarities
        // Api.listProcedureSimilarities
    }
    
}
