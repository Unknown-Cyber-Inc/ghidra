package unknowncyberplugin.components.panes;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

import unknowncyberplugin.components.collections.FileList;

public abstract class BaseFileListPane extends JScrollPane{

    protected FileList list;
    
    protected BaseFileListPane(){
        super();
        list = new FileList(new DefaultListModel<Object>());
        setViewportView(list);
    }

    public FileList getList() {
        return list;
    }

    public abstract void populate(Object[] items);

    public void removeItem(Object item){
        list.removeItem(item);
    }

    public void addItem(Object item){
        list.addItem(item);
    }
}
