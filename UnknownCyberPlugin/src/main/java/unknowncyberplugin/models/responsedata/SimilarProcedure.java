package unknowncyberplugin.models.responsedata;

public class SimilarProcedure {
    private String binaryId;
    private String startEA;

    public SimilarProcedure(String binaryId, String startEA){
        this.binaryId = binaryId;
        this.startEA = startEA;
    }

    public String getBinaryId(){
        return binaryId;
    }

    public String getStartEA(){
        return startEA;
    }
    
}
