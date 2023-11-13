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

    public void setBinaryId(String binaryId){
        this.binaryId = binaryId; 
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
    @SuppressWarnings("unchecked")
    public void add(MutableTreeNode child){
        super.add(child);

        if (child instanceof BaseRootNode){
            ((BaseRootNode<T>)child).setBinaryId(
                ((BaseRootNode<T>)child).getRootBinaryId()
            );
        }

        if (placeholderNode != null){
            removePlaceholderNode();
            placeholderNode = null;
        }
    }

    public void clearNode(){
        removeAllChildren();
        addPlaceholderNode();
    }

    public void removePlaceholderNode(){
        super.remove(0);
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
