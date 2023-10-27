package unknowncyberplugin.models.listitems;

import unknowncyberplugin.models.responsedata.Note;

public class NoteItem extends BaseListItem{

    public NoteItem(String note, String userName, String timeStamp, String noteId){
        super(note, userName, timeStamp, noteId);
    }

    public void updateItemData(Note note){
        this.displayName = note.getContent();
        this.userName = note.getUserName();
        this.timeStamp = note.getTimeStamp();
        this.itemId = note.getId();
    }
    
}
