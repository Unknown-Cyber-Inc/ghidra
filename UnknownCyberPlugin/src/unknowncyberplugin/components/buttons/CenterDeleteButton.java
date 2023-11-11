package unknowncyberplugin.components.buttons;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.collections.CenterTree;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.panes.CenterDerivedFileTabPane;
import unknowncyberplugin.components.panes.CenterDerivedProcedureTabPane;
import unknowncyberplugin.components.panes.CenterProcedureTabPane;
import unknowncyberplugin.components.popups.DeleteConfirmationPopup;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;
import unknowncyberplugin.models.treenodes.roots.BaseRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;
import unknowncyberplugin.models.treenodes.roots.SimilaritiesRootNode;

public class CenterDeleteButton extends BaseButton {
    private BaseCenterTabPane tabPane;
    private DefaultMutableTreeNode selectedNode;
    private CenterTree tree;
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private SimilaritiesRootNode simRoot;
    private String binaryId;

    public CenterDeleteButton() {
        super("Delete");
    }
    
    @Override
    protected void runClickedAction(){
        CenterPanel cp = References.getCenterPanel();
        tabPane = cp.getActiveTabComponent();
        selectedNode = cp.getSelectedTreeNode();
        tree = tabPane.getTree();
        DeleteConfirmationPopup delPopup = new DeleteConfirmationPopup();
        int response = delPopup.displayAndGetResponse();

        if (response == 0){
            if (tabPane instanceof CenterProcedureTabPane || tabPane instanceof CenterDerivedProcedureTabPane){
                ProcedureRootNode procRoot = (ProcedureRootNode) tabPane.getRootNode();
                binaryId = procRoot.getBinaryId();
                notesRoot = procRoot.getNoteRoot();
                tagsRoot = procRoot.getTagsRootNode();
                processProcedureTreeNode(procRoot);
            } else if (tabPane instanceof CenterDerivedFileTabPane) {
                DerivedFileRootNode filesRoot = (DerivedFileRootNode) tabPane.getRootNode();
                binaryId = filesRoot.getBinaryId();
                notesRoot = filesRoot.getNoteRoot();
                tagsRoot = filesRoot.getTagsRootNode();
                processDerivedFileTreeNode();
            }
        }
        // Clear node to prevent duplicates upon reopening the tree.
        clearSubRootNodes();
    }

    public void processProcedureTreeNode(ProcedureRootNode procRoot){
        if (tabPane instanceof CenterProcedureTabPane){
            simRoot = procRoot.getSimilaritiesRootNode();
        }
        String startEA = procRoot.getStartEA();

        // If the selected node is NoteNode or TagNode
        if (selectedNode instanceof NoteNode){
            boolean successful = Api.deleteProcedureGenomicsNote(binaryId, startEA, ((NoteNode)selectedNode).getNodeData().getId());
            if (successful) {
                // Closes the tree's every subnode.
                tree.removeNode(selectedNode);
            }
        } else if (selectedNode instanceof TagNode){
            boolean successful = Api.deleteProcedureGenomicsTagById(binaryId, startEA, ((TagNode)selectedNode).getNodeData().getId());
            if (successful) {
                tree.removeNode(selectedNode);
            }
        }
        clearSubRootNodes();
    }

    public void processDerivedFileTreeNode(){
        if (selectedNode instanceof NoteNode){
            
            boolean successful = Api.deleteFileNote(binaryId, ((NoteNode)selectedNode).getNodeData().getId());
            if (successful) {
                tabPane.getTree().removeNode(selectedNode);
            }
        } else if (selectedNode instanceof TagNode){
            boolean successful = Api.removeFileTag(binaryId, ((TagNode)selectedNode).getNodeData().getId());
            if (successful) {
                tabPane.getTree().removeNode(selectedNode);
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
