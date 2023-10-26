package unknowncyberplugin.components.panes;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.Api;
import unknowncyberplugin.components.panels.CenterCRUDPanel;

public class CenterDerivedProcedurePane extends BaseCenterTabPane{
    private String procName;
    private DefaultMutableTreeNode filesNode;
    private DefaultMutableTreeNode similaritiesNode;

    public CenterDerivedProcedurePane(String procName, CenterCRUDPanel centerCRUDPanel){
        super(procName, centerCRUDPanel);
        this.procName = procName;

        filesNode = new DefaultMutableTreeNode("Containing Files");
        similaritiesNode = new DefaultMutableTreeNode("Similar Procedure Locations");
        tree.addNode(rootNode, filesNode);
        tree.addNode(rootNode, similaritiesNode);
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
