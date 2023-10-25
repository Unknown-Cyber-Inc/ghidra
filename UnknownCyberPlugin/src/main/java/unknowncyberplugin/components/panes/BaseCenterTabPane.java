package unknowncyberplugin.components.panes;

import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.CenterTree;
import unknowncyberplugin.components.panels.CenterCRUDPanel;

public abstract class BaseCenterTabPane extends JScrollPane{
    
    protected CenterTree tree;
    protected DefaultMutableTreeNode rootNode;
    protected DefaultMutableTreeNode notesNode;
    protected DefaultMutableTreeNode tagsNode;
    protected UnknownCyberFileProvider fileProvider;

    protected BaseCenterTabPane(String rootNodeName, CenterCRUDPanel centerCRUDPanel){
        super();

        rootNode = new DefaultMutableTreeNode(rootNodeName);
        notesNode = new DefaultMutableTreeNode("Notes");
        tagsNode = new DefaultMutableTreeNode("Tags");
        rootNode.add(notesNode);
        rootNode.add(tagsNode);

        tree = new CenterTree(centerCRUDPanel, new DefaultTreeModel(rootNode));
    }

    protected BaseCenterTabPane(String rootNodeName, UnknownCyberFileProvider fileProvider, CenterCRUDPanel centerCRUDPanel){
        super();
        this.fileProvider = fileProvider;

        rootNode = new DefaultMutableTreeNode(rootNodeName);
        notesNode = new DefaultMutableTreeNode("Notes");
        tagsNode = new DefaultMutableTreeNode("Tags");
        rootNode.add(notesNode);
        rootNode.add(tagsNode);

        tree = new CenterTree(centerCRUDPanel, new DefaultTreeModel(rootNode));
    }

    public CenterTree getTree(){
        return tree;
    }

    public DefaultMutableTreeNode getRootNode(){
        return rootNode;
    }

    public void addNode(DefaultMutableTreeNode node){
        rootNode.add(node);
    }

    public abstract void populateNotes();

    public abstract void populateTags();
}
