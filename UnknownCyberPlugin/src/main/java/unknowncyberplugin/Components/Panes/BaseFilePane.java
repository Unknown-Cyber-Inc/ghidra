package unknowncyberplugin.Components.Panes;

import javax.swing.*;
import unknowncyberplugin.Components.FileList;
import unknowncyberplugin.Components.Panels.FileCRUDPanel;

public abstract class BaseFilePane extends JScrollPane{

    protected FileList<?> list;
    
    protected BaseFilePane(String listType, FileCRUDPanel fileCRUDPanel){
        super();
        list = new FileList<>(listType, fileCRUDPanel, new DefaultListModel<>());
        setViewportView(list);
    }

    public FileList<?> getList() {
        return list;
    }

    public abstract void populate();
}
