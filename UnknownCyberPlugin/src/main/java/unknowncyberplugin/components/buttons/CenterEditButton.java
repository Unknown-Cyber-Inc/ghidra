package unknowncyberplugin.components.buttons;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.popups.CenterCRUDPopup;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.ProcedureModel;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;

public class CenterEditButton extends BaseButton {
    private String popupReturnedText;

    public CenterEditButton() {
        super("Edit");
    }
    
    @Override
    protected void runClickedAction(){
        CenterPanel cp = References.getCenterPanel();
        String currentDisplayName = cp.getSelectedTreeNode().toString();

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
        String binaryId = rootNode.getBinaryId();
        String startEA = rootNode.getStartEA();

        if (node instanceof NoteNode){
            if (Api.updateProcedureGenomicsNote(binaryId, startEA, ((NoteNode)node).getNodeData().getId(), popupReturnedText)) {
                NoteModel note = new NoteModel(popupReturnedText, ((NoteNode)node).getNodeData().getId(), ((NoteNode)node).getNodeData().getUserName(), ((NoteNode)node).getNodeData().getTimeStamp());
                ((NoteNode)node).getNodeData().updateItemData(note);
            } else {
                References.getFileProvider().announce(
                    "Failed to Update",
                    "An error occurred while updating the procedure genomics note, see the User Log for more information.",
                    true
                );
            }
        } else if (node instanceof ProcedureRootNode){
            // TODO: waiting on creation at API side
            // ProcedureModel updatedProcedure = Api.updateProcedureName(binaryId, startEA, popupReturnedText);
            // if (updatedProcedure != null) {
            //     ((ProcedureRootNode)node).setNodeData(updatedProcedure);
            // }
        }
    }

    public void processDerivedFileTreeNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode node){
        DerivedFileRootNode rootNode = (DerivedFileRootNode) tabPane.getRootNode();
        String binaryId = rootNode.getBinaryId();

        if (node instanceof NoteNode){
            if (Api.updateFileNote(binaryId, ((NoteNode)node).getNodeData().getId(), popupReturnedText)) {
                NoteModel note = new NoteModel(popupReturnedText, ((NoteNode)node).getNodeData().getId(), ((NoteNode)node).getNodeData().getUserName(), ((NoteNode)node).getNodeData().getTimeStamp());
                ((NoteNode)node).getNodeData().updateItemData(note);
            } else {
                References.getFileProvider().announce(
                    "Failed to Update",
                    "An error occurred while updating the file note, see the User Log for more information.",
                    true
                );
            }
        }
    }
}
