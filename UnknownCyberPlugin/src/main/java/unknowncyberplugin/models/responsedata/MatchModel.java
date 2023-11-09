package unknowncyberplugin.models.responsedata;

public class MatchModel {
    private String sha1;
    private Double maxSimilarity;

    public MatchModel(String sha1, Double maxSimilarity){
        this.sha1 = sha1;
        this.maxSimilarity = maxSimilarity;
    }

    public String getSha1(){
        return sha1;
    }

    public Double getMaxSimilarity(){
        return maxSimilarity;
    }

    @Override
    public String toString(){
        return ("hash:" + sha1 + " - sim:" + maxSimilarity);
    }
}
