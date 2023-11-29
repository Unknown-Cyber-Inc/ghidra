package unknowncyberplugin.models.responsedata;

public class ProcedureModel {
    private int count;
    private String status;
    private String startEA;
    private String baseAdjustedStartEA;
    private String procedureName;
    private String binaryId;
    private String hardHash;
    private int tags;
    private int notes;
    private int block_count;
    private int code_count;

    public ProcedureModel(String startEA, String procedureName, int count, String status, int notes, int tags,
            String binaryId, String hardHash, int block_count, int code_count) {
        this.count = count;
        this.status = status;
        this.startEA = startEA;
        this.procedureName = procedureName;
        this.binaryId = binaryId;
        this.hardHash = hardHash;
        this.tags = tags;
        this.notes = notes;
        this.block_count = block_count;
        this.code_count = code_count;
    }

    public String getCount() {
        return String.valueOf(count);
    }

    public String getStatus() {
        return status;
    }

    public String getStartEA() {
        return startEA;
    }

    public String getBaseAdjustedStartEA() {
        return baseAdjustedStartEA;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public String getBinaryId() {
        return binaryId;
    }

    public String getHardHash() {
        return hardHash;
    }

    public String getNotes() {
        return String.valueOf(notes);
    }

    public String getTags() {
        return String.valueOf(tags);
    }

    public String getBlockCount() {
        return String.valueOf(block_count);
    }

    public String getCodeCount() {
        return String.valueOf(code_count);
    }

    @Override
    public String toString() {
        return procedureName == null ? startEA : startEA + " - " + procedureName;
    }
}
