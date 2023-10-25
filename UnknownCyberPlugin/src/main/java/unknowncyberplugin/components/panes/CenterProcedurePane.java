package unknowncyberplugin.components.panes;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panels.CenterCRUDPanel;

public class CenterProcedurePane extends BaseCenterTabPane{
    private String procName;
    private UnknownCyberFileProvider fileProvider;

    public CenterProcedurePane(String procName, CenterCRUDPanel centerCRUDPanel, UnknownCyberFileProvider fileProvider){
        super(procName, centerCRUDPanel);
        this.procName = procName;
        this.fileProvider = fileProvider;

        DefaultMutableTreeNode filesNode = new DefaultMutableTreeNode("Containing Files");
        DefaultMutableTreeNode similaritiesNode = new DefaultMutableTreeNode("Similar Procedure Locations");

        tree.addNode(rootNode, filesNode);
        tree.addNode(rootNode, similaritiesNode);
    }

    public void populateNotes(){
        // populate notes
        // api.listProcedureGenomicsNotes
    }

    public void populateTags(){
        // populate tags
        // api.listProcedureGenomicsTags
    }

    public void populateContainingFiles(){
        // populate containing files
        // api.listProcedureFiles
    }

    public void populateSimilarities(){
        // populate procedure similarities
        // api.listProcedureSimilarities
    }
    
}
