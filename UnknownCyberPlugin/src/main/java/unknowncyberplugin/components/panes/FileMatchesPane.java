package unknowncyberplugin.components.panes;

public class FileMatchesPane extends BaseFileListPane{

    public FileMatchesPane(){
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
