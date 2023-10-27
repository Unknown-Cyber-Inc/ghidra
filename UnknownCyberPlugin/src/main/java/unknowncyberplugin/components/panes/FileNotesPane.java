package unknowncyberplugin.components.panes;

public class FileNotesPane extends BaseFileListPane{

    public FileNotesPane(){
        super();
    }

    @Override
    public void populate(Object[] items){
        if (items != null && items.length >0){
            for (Object item : items) {
                addItem(item);
            }
        }
    }
}
