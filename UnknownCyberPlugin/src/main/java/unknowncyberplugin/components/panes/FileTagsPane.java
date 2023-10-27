package unknowncyberplugin.components.panes;

public class FileTagsPane extends BaseFileListPane{

    public FileTagsPane(){
        super();
    }

    public void populate(Object[] items){
        if (items != null && items.length >0){
            for (Object item : items) {
                addItem(item);
            }
        }
    }
}
