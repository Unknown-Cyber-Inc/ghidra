package unknowncyberplugin.Components.Panes;

import javax.swing.DefaultListModel;

import unknowncyberplugin.Components.Panels.FileCRUDPanel;

public class FileNotesPane extends BaseFilePane{
    DefaultListModel<?> model = (DefaultListModel<?>) list.getModel();

    public FileNotesPane(String listType, FileCRUDPanel fileCRUDPanel){
        super(listType, fileCRUDPanel);
    }

    public void populate(){
        // for (String item : items) {
        //     model.add(item);
        // }
    }

    public void removeItem(int index){
        model.remove(index);
    }

    public void addItem(E elem){
        model.addElement(elem);
    }

    public void updateItem(int index, E elem){
        model.set(index, elem);
    }
    
}
