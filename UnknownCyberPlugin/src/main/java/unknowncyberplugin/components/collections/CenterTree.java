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

public class CenterTree extends JTree {
    private DefaultTreeModel centerTreeModel;
    private DefaultMutableTreeNode currentSelection;
    
    public CenterTree(DefaultTreeModel model){
        super(model);
        centerTreeModel = model;
        setEditable(false);

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
        centerTreeModel.reload(node);
    }

    public DefaultMutableTreeNode getCurrentSelection() {
        return currentSelection;
    }

    public void updateButtons(){
        DefaultMutableTreeNode selected = getCurrentSelection();
        CenterCRUDPanel ccp = References.getCenterCRUDPanel();

        if (selected instanceof NotesRootNode){
            ccp.notesNodeSelected();
        } else if (selected instanceof NoteNode){
            ccp.noteItemSelected();
        } else if (selected instanceof TagsRootNode){
            ccp.tagsNodeSelected();
        } else if (selected instanceof TagNode){
            ccp.tagItemSelected();
        } else if (selected instanceof ProcedureRootNode) {
            ccp.procedureRootSelected();
        } else {
            ccp.disableButtons();
        }
    }
}
