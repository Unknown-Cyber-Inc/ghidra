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
import unknowncyberplugin.models.responsedata.Note;
import unknowncyberplugin.models.responsedata.Procedure;
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
        String startEA = rootNode.getStartEA();

        if (node instanceof NoteNode){
            // Note updatedNote = Api.updateProcedureGenomicsNote(binaryId, startEA, ((NoteNode)node).getNodeData().getId(), popupReturnedText);
            // if (updatedNote != null) {
            //     ((NoteNode)node).setNodeData(updatedNote);
            // }
        } else if (node instanceof ProcedureRootNode){
            // Procedure updatedProcedure = Api.updateProcedureName(binaryId, startEA, popupReturnedText);
            // if (updatedProcedure != null) {
            //     ((ProcedureRootNode)node).setNodeData(updatedProcedure);
            // }
        }
    }

    public void processDerivedFileTreeNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode node){
        DerivedFileRootNode rootNode = (DerivedFileRootNode) tabPane.getRootNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        String binaryId = rootNode.getBinaryId();

        if (node instanceof NoteNode){
            // This code does not work, there is no return value for updateFileNote's API call
            // Note updatedNote = Api.updateFileNote(binaryId, ((NoteNode)node).getNodeData().getId(), popupReturnedText);;
            // if (updatedNote != null) {
            //     ((NoteNode)node).setNodeData(updatedNote);
            // }
        }
    }
}
