package unknowncyberplugin.Components.Panes;

import javax.swing.DefaultListModel;

public class FileMatchesPane extends BaseFilePane{

    DefaultListModel<?> model = (DefaultListModel<?>) list.getModel();

    public void populate(){
        // for (String item : items) {
        //     model.add(item);
        // }
    }
    
}