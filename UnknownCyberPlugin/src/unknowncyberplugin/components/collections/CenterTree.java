package unknowncyberplugin.components.collections;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.CenterCRUDPanel;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcGroupNotesRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcGroupTagsRootNode;

public class CenterTree extends JTree {
    private DefaultTreeModel centerTreeModel;
    private DefaultMutableTreeNode currentSelection;
    
    public CenterTree(DefaultTreeModel model){
        super(model);
        centerTreeModel = model;
        setEditable(false);
        setToggleClickCount(0);

        TreeSelectionModel centerSelectionModel = getSelectionModel();
        centerSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        centerSelectionModel.addTreeSelectionListener(ev -> {
            currentSelection = (DefaultMutableTreeNode) getLastSelectedPathComponent();
            if(currentSelection != null) {
                updateButtons();
            }
        });
    }

    public void addNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode child) {
        centerTreeModel.insertNodeInto(child, parent, parent.getChildCount());
        centerTreeModel.reload();
    }

    public void removeNode(DefaultMutableTreeNode node) {
        centerTreeModel.removeNodeFromParent(node);
        centerTreeModel.reload();
    }

    public void editNode(DefaultMutableTreeNode node, Object userObject) {
        node.setUserObject(userObject);
        centerTreeModel.reload();
    }

    public DefaultMutableTreeNode getCurrentSelection() {
        return currentSelection;
    }

    public void updateButtons(){
        DefaultMutableTreeNode selected = getCurrentSelection();
        CenterCRUDPanel ccp = References.getCenterCRUDPanel();

        if (selected instanceof NotesRootNode){
            ccp.notesRootSelected();
        } else if (selected instanceof ProcGroupNotesRootNode){
            ccp.procGroupNotesRootSelected();
        } else if (selected instanceof NoteNode){
            ccp.noteSelected();
        } else if (selected instanceof TagsRootNode){
            ccp.tagsRootSelected();
        } else if (selected instanceof ProcGroupTagsRootNode){
            ccp.procGroupTagsRootSelected();
        } else if (selected instanceof TagNode){
            ccp.tagSelected();
        } else if (selected instanceof ProcedureRootNode) {
            ccp.procedureRootSelected();
        } else {
            ccp.disableButtons();
        }
    }
}
