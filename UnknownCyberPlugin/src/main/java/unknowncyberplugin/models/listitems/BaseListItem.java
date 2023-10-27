package unknowncyberplugin.models.listitems;

abstract class BaseListItem {
    protected String displayName;
    protected String userName;
    protected String timeStamp;

    protected BaseListItem(String displayName){
        this.displayName = displayName;
    }

    protected BaseListItem(String displayName, String userName, String timeStamp){
        this.displayName = displayName;
        this.userName = userName;
        this.timeStamp = timeStamp;
    }
    
    public String getDisplayName(){
        return displayName;
    }

    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
