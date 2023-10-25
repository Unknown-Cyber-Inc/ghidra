package unknowncyberplugin.components;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import unknowncyberplugin.components.panels.CenterCRUDPanel;

public class CenterTree extends JTree {
    private DefaultTreeModel centerTreeModel;
    private DefaultMutableTreeNode currentSelection;
    private CenterCRUDPanel centerCRUDPanel;
    
    public CenterTree(CenterCRUDPanel centerCRUDPanel, DefaultTreeModel model){
        super(model);
        this.centerCRUDPanel = centerCRUDPanel;
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

        // TODO: Find alternative to using .toString() unless we create models for node types
        if (selected.toString().equalsIgnoreCase("notes")){
            centerCRUDPanel.notesNodeSelected();
        } else if (selected.getParent().toString().equalsIgnoreCase("notes")){
            centerCRUDPanel.noteItemSelected();
        } else if (selected.toString().equalsIgnoreCase("tags")){
            centerCRUDPanel.tagsNodeSelected();
        } else if (selected.getParent().toString().equalsIgnoreCase("tags")){
            centerCRUDPanel.tagItemSelected();
        } else {
            centerCRUDPanel.disableButtons();
        }
    }
}
