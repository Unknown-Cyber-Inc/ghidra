package unknowncyberplugin.components.panes;

import javax.swing.DefaultListModel;

import unknowncyberplugin.components.panels.FileCRUDPanel;

public class FileTagsPane extends BaseFilePane<String>{
    private DefaultListModel<String> model = new DefaultListModel<>();

    public FileTagsPane(String listType, FileCRUDPanel fileCRUDPanel){
        super(listType, fileCRUDPanel);
        list.setModel(model);
    }

    public void populate(String[] items){
        for (String item : items) {
            addItem(item);
        }
    }
    
    public void removeItem(int index){
        model.remove(index);
    }

    public void addItem(String elem){
        model.addElement(elem);
    }
}
