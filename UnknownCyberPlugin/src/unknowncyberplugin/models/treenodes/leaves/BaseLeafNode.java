package unknowncyberplugin.models.treenodes.leaves;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.models.treenodes.roots.BaseRootNode;

public abstract class BaseLeafNode<T> extends DefaultMutableTreeNode{
    protected String nodeDisplayName;

    protected BaseLeafNode(T nodeData){
        super(nodeData);
        nodeDisplayName = nodeData.toString();
    }

    @SuppressWarnings("unchecked")
    public T getNodeData(){
        return (T) getUserObject();
    }

    public void setNodeData(T newData){
        setUserObject(newData);
        setNodeDisplayName(newData.toString());
    }

    public String getNodeDisplayName(){
        return nodeDisplayName;
    }

    public void setNodeDisplayName(String newName){
        nodeDisplayName = newName;
    }

    @SuppressWarnings("unchecked")
    public String getRootBinaryId(){
        BaseRootNode<T> nodeParent = (BaseRootNode<T>) getParent();

        while (nodeParent.getLevel() != 0) {
            nodeParent = (BaseRootNode<T>) nodeParent.getParent();
        }

        return nodeParent.getBinaryId();
    }

    @Override
    public String toString(){
        return nodeDisplayName == null ? "No node name set" : nodeDisplayName;
    }
    
}
