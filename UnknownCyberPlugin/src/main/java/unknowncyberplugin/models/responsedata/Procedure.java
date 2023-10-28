package unknowncyberplugin.models.responsedata;

public class Procedure {
    private int count;
    private String status;
    private String startEA;
    private String procedureName;

    public Procedure(int count, String status, String startEA, String procedureName){
        this.count = count;
        this.status = status;
        this.startEA = startEA;
        this.procedureName = procedureName;
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

    public String getProcedureName(){
        return procedureName;
    }
    
}
