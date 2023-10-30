package unknowncyberplugin.models.responsedata;

public class Procedure {
    private int count;
    private String status;
    private String startEA;
    private String baseAdjustedStartEA;
    private String procedureName;
    private String binaryId;

    public Procedure(int count, String status, String startEA, String procedureName, String binaryId){
        this.count = count;
        this.status = status;
        this.startEA = startEA;
        this.procedureName = procedureName;
        this.binaryId = binaryId;
    }

    public int getCount(){
        return count;
    }

    public String getStatus(){
        return status;
    }

    public String getStartEA(){
        return startEA;
    }

    public String getBaseAdjustedStartEA(){
        return baseAdjustedStartEA;
    }

    public void setAdjustedStartEA(String imageBase){
        // Logic to adjust startEA with imageBase
    }

    public String getProcedureName(){
        return procedureName;
    }

    public String getBinaryId(){
        return binaryId;
    }

    @Override
    public String toString(){
        return procedureName == null ? startEA : startEA + " - " + procedureName;
    }
}
