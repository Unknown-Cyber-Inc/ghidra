package unknowncyberplugin.models.responsedata;

public class SimilarProcedureModel {
    private String binaryId;
    private String startEA;

    public SimilarProcedureModel(String binaryId, String startEA){
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
