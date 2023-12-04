package unknowncyberplugin.models.responsedata;

public class SimilarProcedureModel {
    private String binaryId;
    private String startEA;
    private int blockCount;
    private int codeCount;

    public SimilarProcedureModel(String binaryId, String startEA, int blockCount, int codeCount){
        this.binaryId = binaryId;
        this.startEA = startEA;
        this.blockCount = blockCount;
        this.codeCount = codeCount;
    }

    public String getBinaryId(){
        return binaryId;
    }

    public String getStartEA(){
        return startEA;
    }

    public String getBlockCount(){
        return String.valueOf(blockCount);
    }

    public String getCodeCount(){
        return String.valueOf(codeCount);
    }
    
}
