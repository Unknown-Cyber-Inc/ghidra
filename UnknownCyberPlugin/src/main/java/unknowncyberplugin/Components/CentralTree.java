package unknowncyberplugin.Components;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class CentralTree extends JTree {
    private DefaultTreeModel centerTreeModel;
    private transient TreeSelectionModel centerSelectionModel;
    private DefaultMutableTreeNode currentSelection;
    
    public CentralTree(DefaultTreeModel model){
        super(model);
        centerTreeModel = model;
        setEditable(false);

        centerSelectionModel = getSelectionModel();
        centerSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        centerSelectionModel.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent ev){
                currentSelection = (DefaultMutableTreeNode) getLastSelectedPathComponent();
                if(currentSelection != null) {
                    updateButtons();
                }
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
        // update CRUD buttons
    }
}

// centralPanel = new JPanel();
// centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
//     centralTabs = new JTabbedPane();
//         DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("0x12");
//         DefaultMutableTreeNode notesRootNode = new DefaultMutableTreeNode("Notes");
//         DefaultMutableTreeNode noteNode = new DefaultMutableTreeNode("Note1");
//         DefaultMutableTreeNode tagsRootNode = new DefaultMutableTreeNode("Tags");
//         DefaultMutableTreeNode tagNode = new DefaultMutableTreeNode("Tag1");
//         rootNode.add(notesRootNode);
//         rootNode.add(tagsRootNode);
//         notesRootNode.add(noteNode);
//         tagsRootNode.add(tagNode);
//         DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
//         centralTree = new JTree(treeModel);
//         centralTree.setEditable(false);
//         centralScroll = new JScrollPane(centralTree);
//         DefaultMutableTreeNode rootNode2 = new DefaultMutableTreeNode("0x34");
//         DefaultMutableTreeNode notesRootNode2 = new DefaultMutableTreeNode("Notes");
//         DefaultMutableTreeNode noteNode2 = new DefaultMutableTreeNode("Note2");
//         DefaultMutableTreeNode tagsRootNode2 = new DefaultMutableTreeNode("Tags");
//         DefaultMutableTreeNode tagNode2 = new DefaultMutableTreeNode("Tag2");
//         rootNode2.add(notesRootNode2);
//         rootNode2.add(tagsRootNode2);
//         notesRootNode2.add(noteNode2);
//         tagsRootNode2.add(tagNode2);
//         DefaultTreeModel treeModel2 = new DefaultTreeModel(rootNode2);
//         JTree centralTree2 = new JTree(treeModel2);
//         centralTree2.setEditable(false);
//         JScrollPane centralScroll2 = new JScrollPane(centralTree2);
//     centralTabs.addTab("0x12", centralScroll);
//     centralTabs.addTab("0x34", centralScroll2);
// centralPanel.add(centralTabs);
//     centralCRUDPanel = new JPanel();
//     centralCRUDPanel.setLayout(new FlowLayout());
//         centralCreate = new JButton("Create");
//         centralEdit = new JButton("Edit");
//         centralDelete = new JButton("Delete");
//     centralCRUDPanel.add(centralCreate);
//     centralCRUDPanel.add(centralEdit);
//     centralCRUDPanel.add(centralDelete);
// centralPanel.add(centralCRUDPanel);