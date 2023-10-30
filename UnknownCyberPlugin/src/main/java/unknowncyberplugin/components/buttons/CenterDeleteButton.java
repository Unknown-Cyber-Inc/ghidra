package unknowncyberplugin.components.buttons;

import javax.swing.tree.DefaultMutableTreeNode;

import ghidra.util.Msg;
import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.panes.CenterDerivedFileTabPane;
import unknowncyberplugin.components.panes.CenterDerivedProcedureTabPane;
import unknowncyberplugin.components.panes.CenterProcedureTabPane;
import unknowncyberplugin.components.popups.DeleteConfirmationPopup;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.PlaceholderNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;

public class CenterDeleteButton extends BaseButton {

    public CenterDeleteButton() {
        super("Delete");
    }
    
    @Override
    protected void runClickedAction(){
        CenterPanel cp = References.getCenterPanel();
        Msg.info(this, "Center delete button clicked");

        DeleteConfirmationPopup delPopup = new DeleteConfirmationPopup();
        int response = delPopup.displayAndGetResponse();
        Msg.info(this, ("Confirmation response: " + response));

        if (response == 0){
            processNode(cp.getActiveTabComponent(), cp.getSelectedTreeNode());
        }
    }

    // Based on pane type, call sub-process method for procedure or file. 
    public void processNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode selectedNode){
        if (tabPane instanceof CenterProcedureTabPane || tabPane instanceof CenterDerivedProcedureTabPane){
            processProcedureTreeNode(tabPane, selectedNode);
        } else if (tabPane instanceof CenterDerivedFileTabPane) {
            processDerivedFileTreeNode(tabPane, selectedNode);
        }
    }

    public void processProcedureTreeNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode node){
        ProcedureRootNode rootNode = (ProcedureRootNode) tabPane.getRootNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        String binaryId = rootNode.getBinaryId();
        String startEA = rootNode.getStartEA();

        // If the selected node is NoteNode or TagNode
        if (node instanceof NoteNode){
            // boolean successful = Api.deleteProcedureGenomicsNote(binaryId, startEA, ((NoteNode)node).getNodeData().getId());
            // if (successful) {
            //     parentNode.remove(node);
            // }
        } else if (node instanceof TagNode){
            // boolean successful = Api.deleteProcedureGenomicsTagById(binaryId, startEA, ((TagNode)node).getNodeData().getId());
            // if (successful) {
            //     parentNode.remove(node);
            // }
        }
    }

    public void processDerivedFileTreeNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode node){
        DerivedFileRootNode rootNode = (DerivedFileRootNode) tabPane.getRootNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        String binaryId = rootNode.getBinaryId();

        // If the selected node is NoteNode or TagNode
        if (node instanceof NoteNode){
            // boolean successful = Api.deleteFileNote(binaryId, ((NoteNode)node).getNodeData().getId());
            // if (successful) {
            //     parentNode.remove(node);
            // }
        } else if (node instanceof TagNode){
            // boolean successful = Api.removeFileTag(binaryId, ((TagNode)node).getNodeData().getId());
            // if (successful) {
            //     parentNode.remove(node);
            // }
        }
    }



}
