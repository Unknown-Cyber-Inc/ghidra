package unknowncyberplugin.components.panes;

import javax.swing.DefaultListModel;

public class FileMatchesPane extends BaseFileListPane<String>{
    private DefaultListModel<String> model = new DefaultListModel<>();

    public FileMatchesPane(String listType){
        super(listType);
        list.setModel(model);
    }

    @Override
    public void populate(String[] items){
        if (items != null && items.length >0){
            for (String item : items) {
                addItem(item);
            }
        }
    }
    
    public void addItem(String elem){
        model.addElement(elem);
    }
}