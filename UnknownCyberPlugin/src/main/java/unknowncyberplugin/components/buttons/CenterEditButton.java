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
import unknowncyberplugin.components.popups.CenterCRUDPopup;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;

public class CenterEditButton extends BaseButton {
    private String popupReturnedText;

    public CenterEditButton() {
        super("Edit");
    }
    
    @Override
    protected void runClickedAction(){
        CenterPanel cp = References.getCenterPanel();
        String currentDisplayName = cp.getSelectedTreeNode().toString();

        Msg.info(this, "Center edit button clicked");
        popupReturnedText = null;

        // bring up center popup filled with current data
        CenterCRUDPopup popup = new CenterCRUDPopup();
        popupReturnedText = popup.displayAndGetResponse(currentDisplayName);

        if (popupReturnedText != null && !(popupReturnedText.equals(currentDisplayName))){
            // determine api call based on tab pane and node type
            processNode(cp.getActiveTabComponent(), cp.getSelectedTreeNode());
        }
    }

    public void processNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode selectedNode){
        if (tabPane.getRootNode() instanceof ProcedureRootNode){
            processProcedureTreeNode(tabPane, selectedNode);
        } else if (tabPane.getRootNode() instanceof DerivedFileRootNode) {
            processDerivedFileTreeNode(tabPane, selectedNode);
        }
    }
    
    public void processProcedureTreeNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode node){
        ProcedureRootNode rootNode = (ProcedureRootNode) tabPane.getRootNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        String binaryId = rootNode.getBinaryId();
        String address = "PLACEHOLDER ADDRESS";

        if (node instanceof NoteNode){
            // Object result = Api.updateProcedureGenomicsNote(binaryId, address, node.getId(), popupReturnedText);
            // if (200 <= result.getStatus() <=300) {
            //     ((NoteNode)node).setNodeData(result);
            // }
        } else if (node instanceof ProcedureRootNode){
            // Object result = Api.updateProcedureName(binaryId, address, popupReturnedText);
            // if (200 <= result.getStatus() <=300) {
            //     (ProcedureRootNode) node.setNodeData(result);
            // }
        }
    }

    public void processDerivedFileTreeNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode node){
        DerivedFileRootNode rootNode = (DerivedFileRootNode) tabPane.getRootNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        String binaryId = rootNode.getBinaryId();

        // If the selected node is NoteNode or TagNode
        if (node instanceof NoteNode){
            // Object result = Api.updateFileNote(binaryId, node.getId(), popupReturnedText);;
            // if (200 <= response.getStatus() <=300) {
            //     ((NoteNode)node).setNodeData(result);
            // }
        }
    }
}
