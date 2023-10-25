package unknowncyberplugin.components.panes;

import java.io.Serializable;

import javax.swing.*;

import unknowncyberplugin.components.FileList;
import unknowncyberplugin.components.panels.FileCRUDPanel;

public abstract class BaseFilePane<E extends Serializable> extends JScrollPane{

    protected FileList<E> list;
    
    protected BaseFilePane(String listType, FileCRUDPanel fileCRUDPanel){
        super();
        list = new FileList<>(listType, fileCRUDPanel, new DefaultListModel<>());
        setViewportView(list);
    }

    public FileList<E> getList() {
        return list;
    }

    public abstract void populate(E[] items);
}
