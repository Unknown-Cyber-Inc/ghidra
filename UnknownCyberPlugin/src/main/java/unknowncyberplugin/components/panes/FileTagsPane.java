package unknowncyberplugin.components.panes;

import javax.swing.DefaultListModel;

public class FileTagsPane extends BaseFileListPane<String>{
    private DefaultListModel<String> model = new DefaultListModel<>();

    public FileTagsPane(String listType){
        super(listType);
        list.setModel(model);
    }

    public void populate(String[] items){
        if (items != null && items.length >0){
            for (String item : items) {
                addItem(item);
            }
        }
    }
    
    public void removeItem(int index){
        model.remove(index);
    }

    public void addItem(String elem){
        model.addElement(elem);
    }
}
