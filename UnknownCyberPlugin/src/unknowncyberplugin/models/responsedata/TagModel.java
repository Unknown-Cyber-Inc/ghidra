package unknowncyberplugin.models.responsedata;

public class TagModel {
    private String tagName;
    private String userName;
    private String timeStamp;
    private String tagId;

    public TagModel(String tagName, String userName, String timeStamp, String tagId){
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

    @Override
    public String toString(){
        return tagName == null ? "No tag name set" : tagName;
    }
}
