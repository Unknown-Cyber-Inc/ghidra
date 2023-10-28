package unknowncyberplugin.models.responsedata;

public class Tag {
    private String tagName;
    private String userName;
    private String timeStamp;
    private String tagId;

    public Tag(String tagName, String userName, String timeStamp, String tagId){
        this.tagName = tagName;
        this.userName = userName;
        this.timeStamp = timeStamp;
        this.tagId = tagId;
    }

    public String getTagName(){
        return tagName;
    }

    public String getUserName(){
        return userName;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public String getId(){
        return tagId;
    }
}
