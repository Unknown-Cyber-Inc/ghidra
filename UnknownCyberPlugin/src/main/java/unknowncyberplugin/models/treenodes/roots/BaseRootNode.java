package unknowncyberplugin.models.treenodes.roots;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import unknowncyberplugin.models.treenodes.leaves.PlaceholderNode;

public abstract class BaseRootNode<T> extends DefaultMutableTreeNode{
    protected String nodeDisplayName;
    protected String binaryId;
    protected PlaceholderNode placeholderNode;
    protected String placeholderDisplayName;

    protected BaseRootNode(T nodeData, String binaryId){
        super(nodeData);
        this.nodeDisplayName = nodeData.toString();
        this.binaryId = binaryId;
    }

    protected BaseRootNode(T nodeData){
        super(nodeData);
        this.nodeDisplayName = nodeData.toString();
        this.binaryId = getRootBinaryId();
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

    public String getBinaryId(){
        return binaryId;
    }

    @SuppressWarnings("unchecked")
    public String getRootBinaryId(){
        BaseRootNode<T> nodeParent = (BaseRootNode<T>) getParent();

        while (nodeParent.getLevel() != 0) {
            nodeParent = (BaseRootNode<T>) nodeParent.getParent();
        }

        return nodeParent.getBinaryId();
    }

    public void addChildNode(MutableTreeNode child){
        add(child);
    }

    @Override
    public void add(MutableTreeNode child){
        if (placeholderNode != null && placeholderNode.getParent() == this){
            removePlaceholderNode();
            placeholderNode = null;
        }
        super.add(child);
    }

    @Override
    public void remove(int childIndex){
        super.remove(childIndex);

        if (getChildCount() == 0){
            addPlaceholderNode();
        }
    }

    public void removePlaceholderNode(){
        super.remove(placeholderNode);
    }

    public void addPlaceholderNode(){
        placeholderNode = new PlaceholderNode(placeholderDisplayName);
        super.add(placeholderNode);
    }

    @Override
    public String toString(){
        return nodeDisplayName == null ? "No node name set" : nodeDisplayName;
    }
}
