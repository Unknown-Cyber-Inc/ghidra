package unknowncyberplugin.components.panes;

import javax.swing.DefaultListModel;

import unknowncyberplugin.components.panels.FileCRUDPanel;

public class FileMatchesPane extends BaseFilePane<String>{
    private DefaultListModel<String> model = new DefaultListModel<>();

    public FileMatchesPane(String listType, FileCRUDPanel fileCRUDPanel){
        super(listType, fileCRUDPanel);
        list.setModel(model);
    }

    @Override
    public void populate(String[] items){
        for (String item : items) {
            addItem(item);
        }
    }
    
    public void addItem(String elem){
        model.addElement(elem);
    }
}