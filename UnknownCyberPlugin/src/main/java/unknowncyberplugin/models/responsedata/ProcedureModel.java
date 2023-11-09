package unknowncyberplugin.models.responsedata;

public class ProcedureModel {
    private int count;
    private String status;
    private String startEA;
    private String baseAdjustedStartEA;
    private String procedureName;
    private String binaryId;
    private int tags;
    private int notes;

    public ProcedureModel(String startEA, String procedureName, int count, String status, int notes, int tags, String binaryId){
        this.count = count;
        this.status = status;
        this.startEA = startEA;
        this.procedureName = procedureName;
        this.binaryId = binaryId;
        this.tags = tags;
        this.notes = notes;
    }

    public String getCount(){
        return String.valueOf(count);
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
        // TODO: implement this or delete
        // Logic to adjust startEA with imageBase
    }

    public String getProcedureName(){
        return procedureName;
    }

    public String getBinaryId(){
        return binaryId;
    }

    public String getNotes(){
        return String.valueOf(notes);
    }

    public String getTags(){
        return String.valueOf(tags);
    }

    @Override
    public String toString(){
        return procedureName == null ? startEA : startEA + " - " + procedureName;
    }
}
