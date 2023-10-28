package unknowncyberplugin.components.panes;

import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import unknowncyberplugin.components.collections.CenterTree;
import unknowncyberplugin.models.responsedata.File;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;

public abstract class BaseCenterTabPane extends JScrollPane{
    
    protected CenterTree tree;
    protected DefaultMutableTreeNode rootNode;

    protected BaseCenterTabPane(String rootName, String binaryId, String paneType){
        super();

        if (paneType.equalsIgnoreCase("procedure")){
            rootNode = new ProcedureRootNode(rootName, binaryId);
        } else if (paneType.equalsIgnoreCase("file")){
            rootNode = new DerivedFileRootNode(new File(rootName, null, null), rootName);
        }

        tree = new CenterTree(new DefaultTreeModel(rootNode));
        setViewportView(tree);
    }

    public CenterTree getTree(){
        return tree;
    }

    public DefaultMutableTreeNode getRootNode(){
        return rootNode;
    }
}
