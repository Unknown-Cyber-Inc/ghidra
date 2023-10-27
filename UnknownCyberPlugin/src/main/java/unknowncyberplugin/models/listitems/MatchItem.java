package unknowncyberplugin.models.listitems;

public class MatchItem extends BaseListItem{
    private float matchValue;

    public MatchItem(String fileName, float matchValue){
        super(fileName);
        this.matchValue = matchValue;
    }
    
    public float getMatchValue(){
        return matchValue;
    }
}
