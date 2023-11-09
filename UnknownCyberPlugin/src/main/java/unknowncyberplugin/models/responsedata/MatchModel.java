package unknowncyberplugin.models.responsedata;

public class MatchModel {
    private String sha1;
    private float maxSimilarity;

    public MatchModel(String sha1, float maxSimilarity){
        this.sha1 = sha1;
        this.maxSimilarity = maxSimilarity;
    }

    public String getSha1(){
        return sha1;
    }

    public float getMaxSimilarity(){
        return maxSimilarity;
    }
}
