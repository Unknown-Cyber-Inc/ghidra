package unknowncyberplugin.Components.Panes;

import javax.swing.DefaultListModel;

import unknowncyberplugin.Components.Panels.FileCRUDPanel;

public class FileMatchesPane extends BaseFilePane{
    DefaultListModel<?> model = (DefaultListModel<?>) list.getModel();

    public FileMatchesPane(String listType, FileCRUDPanel fileCRUDPanel){
        super(listType, fileCRUDPanel);
    }

    public void populate(){
        // for (String item : items) {
        //     model.add(item);
        // }
    }
    
}