package unknowncyberplugin.models.listitems;

abstract class BaseListItem {
    protected String displayName;
    protected String userName;
    protected String timeStamp;
    protected String itemId;

    protected BaseListItem(String displayName){
        this.displayName = displayName;
    }

    protected BaseListItem(String displayName, String userName, String timeStamp, String itemId){
        this.displayName = displayName;
        this.userName = userName;
        this.timeStamp = timeStamp;
        this.itemId = itemId;
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

    public String getId(){
        return itemId;
    }

    @Override
    public String toString(){
        return displayName == null ? "No item name set" : displayName;
    }
}
