package unknowncyberplugin.Components.Panes;

import javax.swing.*;
import unknowncyberplugin.Components.FileList;

public abstract class BaseFilePane extends JScrollPane{

    protected FileList<?> list;
    
    protected BaseFilePane(){
        super();
        list = new FileList<>(new DefaultListModel<>());
        setViewportView(list);
    }

    public FileList<?> getList() {
        return list;
    }

    public abstract void populate();
}
