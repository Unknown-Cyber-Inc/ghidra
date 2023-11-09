package unknowncyberplugin.components.panes;

import javax.swing.DefaultListModel;

public class FileMatchesPane extends BaseFileListPane{

    public FileMatchesPane(){
        super();
    }

    @Override
    public void populate(Object[] items){
        DefaultListModel<Object> listModel = (DefaultListModel<Object>)list.getModel();
        listModel.clear();
        if (items != null && items.length >0){
            for (Object item : items) {
                addItem(item);
            }
        }
    }
}
