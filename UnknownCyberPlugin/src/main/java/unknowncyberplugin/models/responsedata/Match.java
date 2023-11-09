package unknowncyberplugin.models.responsedata;

public class Match {
    private String sha1;
    private float maxSimilarity;

    public Match(String sha1, float maxSimilarity){
        this.sha1 = sha1;
        this.maxSimilarity = maxSimilarity;
    }
}
