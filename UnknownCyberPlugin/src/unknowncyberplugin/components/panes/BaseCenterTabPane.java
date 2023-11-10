package unknowncyberplugin.components.panes;

import javax.swing.JScrollPane;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import unknowncyberplugin.components.collections.CenterTree;
import unknowncyberplugin.models.responsedata.FileModel;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;

public abstract class BaseCenterTabPane extends JScrollPane{
    
    protected CenterTree tree;
    protected DefaultMutableTreeNode rootNode;

    protected BaseCenterTabPane(String rootName, String binaryId, String paneType){
        super();

        if (paneType.equalsIgnoreCase("procedure")){
            rootNode = new ProcedureRootNode(rootName, binaryId, rootName);
        } else if (paneType.equalsIgnoreCase("derived procedure")){
            rootNode = new ProcedureRootNode(rootName, binaryId, rootName);
            rootNode.remove(2);
        } else if (paneType.equalsIgnoreCase("file")){
            rootNode = new DerivedFileRootNode(new FileModel(rootName, null, null), rootName);
        }

        tree = new CenterTree(new DefaultTreeModel(rootNode));

        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent ev) throws ExpandVetoException {
                TreePath path = ev.getPath();
                Object subRootNode = path.getLastPathComponent();
                callExpandAction(subRootNode);
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                // This must be overriden. As we have no needed action here, it is left empty.
            }
        });

        setViewportView(tree);
    }

    abstract void callExpandAction(Object subRootNode);

    public CenterTree getTree(){
        return tree;
    }

    public DefaultMutableTreeNode getRootNode(){
        return rootNode;
    }
}
