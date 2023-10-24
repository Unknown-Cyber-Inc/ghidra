package unknowncyberplugin.Components.Panes;

import javax.swing.DefaultListModel;

public class FileTagsPane extends BaseFilePane{

    DefaultListModel<?> model = (DefaultListModel<?>) list.getModel();

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
}
