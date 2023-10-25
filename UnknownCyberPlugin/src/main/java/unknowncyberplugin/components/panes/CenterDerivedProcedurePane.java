package unknowncyberplugin.components.panes;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.api;
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
        // api.listProcedureGenomicsNotes
    }

    public void populateTags(){
        // populate tagsNode with tags
        // api.listProcedureGenomicsTags
    }

    public void populateContainingFiles(){
        // populate filesNode with containing files
        // api.listProcedureFiles
    }

    public void populateSimilarities(){
        // populate similaritiesNode with procedure similarities
        // api.listProcedureSimilarities
    }
    
}
