package unknowncyberplugin.components.buttons;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.collections.CenterTree;
import unknowncyberplugin.components.collections.ProcTable;
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
    private String hardHash;
    private String binaryId;
    private String popupReturnedText;
    private ProcTable table;
    private int rowNumber;

    public CenterEditButton() {
        super("Edit");
    }
    
    @Override
    protected void runClickedAction(){
        CenterPanel cp = References.getCenterPanel();
        tabPane = cp.getActiveTabComponent();
        selectedNode = cp.getSelectedTreeNode();
        tree = tabPane.getTree();
        table = References.getProcTablePane().getProcTable();
        String textToEdit = selectedNode.toString();
        if (selectedNode instanceof ProcedureRootNode){
            for (int i=0; i < table.getRowCount(); i++){
                if (table.getValueAt(i, 0).equals(textToEdit)){
                    rowNumber = i;
                    textToEdit = (String) table.getValueAt(rowNumber, 1);
                }
            }
        }

        popupReturnedText = null;

        // bring up center popup filled with current data
        CenterCRUDPopup popup = new CenterCRUDPopup();
        popupReturnedText = popup.displayAndGetResponse(textToEdit);

        if (popupReturnedText != null && !(popupReturnedText.equals(textToEdit))){
            // determine api call based on tab pane and node type
            if (tabPane.getRootNode() instanceof ProcedureRootNode){
                ProcedureRootNode procRoot = (ProcedureRootNode) tabPane.getRootNode();
                binaryId = procRoot.getBinaryId();
                notesRoot = procRoot.getNoteRoot();
                tagsRoot = procRoot.getTagsRootNode();
                if (selectedNode.getParent() instanceof ProcGroupNotesRootNode){
                    hardHash = (CenterProcedureTabPane) tabPane.getHardHash();
                }
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
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();

        if (parentNode instanceof NotesRootNode){
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
            clearSubRootNodes();
        } else if (parentNode instanceof ProcGroupNotesRootNode){

            if (Api.updateProcedureGroupNote(hardHash, ((NoteNode)selectedNode).getNodeData().getId(), popupReturnedText)) {
                NoteModel note = new NoteModel(
                    popupReturnedText, ((NoteNode)selectedNode).getNodeData().getId(),
                    ((NoteNode)selectedNode).getNodeData().getUserName(),
                    ((NoteNode)selectedNode).getNodeData().getTimeStamp());
                tree.editNode(selectedNode, note);
            } else {
                References.getFileProvider().announce(
                    "Failed to Update",
                    "An error occurred while updating the procedure group note, see the User Log for more information.",
                    true
                );
            }
        } else if (selectedNode instanceof ProcedureRootNode){
            if (Api.updateProcedureName(binaryId, startEA, popupReturnedText)) {
                tree.editNode(selectedNode, new ProcedureModel(
                    (String) table.getValueAt(rowNumber, 0), popupReturnedText,
                    Integer.parseInt((String)table.getValueAt(rowNumber, 2)), (String) table.getValueAt(rowNumber, 3),
                    Integer.parseInt((String)table.getValueAt(rowNumber, 4)), Integer.parseInt((String)table.getValueAt(rowNumber, 5)),
                    binaryId
                ));
            }
            table.setValueAt(popupReturnedText, rowNumber, 1);
        }
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
