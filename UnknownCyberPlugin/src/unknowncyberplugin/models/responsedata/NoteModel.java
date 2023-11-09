package unknowncyberplugin.models.responsedata;

public class NoteModel {
    private String noteText;
    private String userName;
    private String timeStamp;
    private String noteId;

    public NoteModel(String noteText, String noteId, String userName, String timeStamp){
        this.noteText = noteText;
        this.userName = userName;
        this.timeStamp = timeStamp;
        this.noteId = noteId;
    }

    public String getNoteText(){
        return noteText;
    }

    public String getUserName(){
        return userName;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public String getId(){
        return noteId;
    }

    public void updateItemData(NoteModel note){
        this.noteText = note.getNoteText();
        this.userName = note.getUserName();
        this.timeStamp = note.getTimeStamp();
        this.noteId = note.getId();
    }

    @Override
    public String toString(){
        return noteText == null ? "No note text set" : noteText;
    }
}
