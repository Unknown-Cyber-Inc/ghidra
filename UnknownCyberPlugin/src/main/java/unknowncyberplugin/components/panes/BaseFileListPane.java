package unknowncyberplugin.components.panes;

import java.io.Serializable;

import javax.swing.*;

import unknowncyberplugin.components.collections.FileList;

public abstract class BaseFileListPane<E extends Serializable> extends JScrollPane{

    protected FileList<E> list;
    
    protected BaseFileListPane(String listType){
        super();
        list = new FileList<>(listType, new DefaultListModel<>());
        setViewportView(list);
    }

    public FileList<E> getList() {
        return list;
    }

    public abstract void populate(E[] items);
}
