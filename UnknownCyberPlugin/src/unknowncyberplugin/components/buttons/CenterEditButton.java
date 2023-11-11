package unknowncyberplugin.components.buttons;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.collections.CenterTree;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.popups.CenterCRUDPopup;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.ProcedureModel;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;
import unknowncyberplugin.models.treenodes.roots.SimilaritiesRootNode;

public class CenterEditButton extends BaseButton {
    private BaseCenterTabPane tabPane;
    private DefaultMutableTreeNode selectedNode;
    private CenterTree tree;
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private SimilaritiesRootNode simRoot;
    private String binaryId;
    private String popupReturnedText;

    public CenterEditButton() {
        super("Edit");
    }
    
    @Override
    protected void runClickedAction(){
        CenterPanel cp = References.getCenterPanel();
        tabPane = cp.getActiveTabComponent();
        selectedNode = cp.getSelectedTreeNode();
        tree = tabPane.getTree();
        String currentDisplayName = selectedNode.toString();

        popupReturnedText = null;

        // bring up center popup filled with current data
        CenterCRUDPopup popup = new CenterCRUDPopup();
        popupReturnedText = popup.displayAndGetResponse(currentDisplayName);

        if (popupReturnedText != null && !(popupReturnedText.equals(currentDisplayName))){
            // determine api call based on tab pane and node type
            if (tabPane.getRootNode() instanceof ProcedureRootNode){
                ProcedureRootNode procRoot = (ProcedureRootNode) tabPane.getRootNode();
                binaryId = procRoot.getBinaryId();
                notesRoot = procRoot.getNoteRoot();
                tagsRoot = procRoot.getTagsRootNode();
                processProcedureTreeNode(procRoot);
            } else if (tabPane.getRootNode() instanceof DerivedFileRootNode) {
                DerivedFileRootNode filesRoot = (DerivedFileRootNode) tabPane.getRootNode();
                notesRoot = filesRoot.getNoteRoot();
                tagsRoot = filesRoot.getTagsRootNode();
                processDerivedFileTreeNode();
            }
        }
    }
    
    public void processProcedureTreeNode(ProcedureRootNode procRoot){
        String startEA = procRoot.getStartEA();

        if (selectedNode instanceof NoteNode){
            if (Api.updateProcedureGenomicsNote(binaryId, startEA, ((NoteNode)selectedNode).getNodeData().getId(), popupReturnedText)) {
                NoteModel note = new NoteModel(
                    popupReturnedText, ((NoteNode)selectedNode).getNodeData().getId(),
                    ((NoteNode)selectedNode).getNodeData().getUserName(),
                    ((NoteNode)selectedNode).getNodeData().getTimeStamp());
                tree.editNode(selectedNode, note);
            } else {
                References.getFileProvider().announce(
                    "Failed to Update",
                    "An error occurred while updating the procedure genomics note, see the User Log for more information.",
                    true
                );
            }
        } else if (selectedNode instanceof ProcedureRootNode){
            // TODO: finish getting counts and status
            // ProcedureModel updatedProcedure = Api.updateProcedureName(binaryId, startEA, popupReturnedText);
            // if (updatedProcedure != null) {
            //     tree.editNode(selectedNode, updatedProcedure);
            // }
            /*
            if (Api.updateProcedureName(binaryId, startEA, popupReturnedText)) {
                ProcedureModel updatedProcedure = new ProcedureModel(((ProcedureRootNode)selectedNode).getNodeData().getCount(), ((ProcedureRootNode)selectedNode).getNodeData().getStatus(), startEA, popupReturnedText, binaryId);
            } else {
                References.getFileProvider().announce(
                    "Failed to Update",
                    "An error occurred while updating the procedure name, see the User Log for more information.",
                    true
                );
            }
            //*/
        }
        clearSubRootNodes();
    }

    public void processDerivedFileTreeNode(){
        if (selectedNode instanceof NoteNode){
            if (Api.updateFileNote(binaryId, ((NoteNode)selectedNode).getNodeData().getId(), popupReturnedText)) {
                NoteModel note = new NoteModel(
                    popupReturnedText, ((NoteNode)selectedNode).getNodeData().getId(),
                    ((NoteNode)selectedNode).getNodeData().getUserName(),
                    ((NoteNode)selectedNode).getNodeData().getTimeStamp());
                tree.editNode(selectedNode, note);
            } else {
                References.getFileProvider().announce(
                    "Failed to Update",
                    "An error occurred while updating the file note, see the User Log for more information.",
                    true
                );
            }
        }
        clearSubRootNodes();
    }

    public void clearSubRootNodes(){
        notesRoot.clearNode();
        tagsRoot.clearNode();
        if (simRoot != null){
            simRoot.clearNode();
        }
        simRoot = null;
    }
}
