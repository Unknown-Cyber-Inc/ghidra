package unknowncyberplugin.models.responsedata;

public class Note {
    private String content;
    private String userName;
    private String timeStamp;
    private String noteId;

    public Note(String content, String userName, String timeStamp, String noteId){
        this.content = content;
        this.userName = userName;
        this.timeStamp = timeStamp;
        this.noteId = noteId;
    }

    public String getContent(){
        return content;
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
}
