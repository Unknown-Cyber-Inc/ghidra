package unknowncyberplugin.models.responsedata;

import java.util.List;

import com.unknowncyber.magic.model.BlockSchema;

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
    private int blockCount;
    private int codeCount;
    private List<BlockSchema> blocks;

    public ProcedureModel(String startEA, String procedureName, int count, String status, int notes, int tags,
            String binaryId, String hardHash, int blockCount, int codeCount, List<BlockSchema> blocks) {
        this.count = count;
        this.status = status;
        this.startEA = startEA;
        this.procedureName = procedureName;
        this.binaryId = binaryId;
        this.hardHash = hardHash;
        this.tags = tags;
        this.notes = notes;
        this.blockCount = blockCount;
        this.codeCount = codeCount;
        this.blocks = blocks;
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
        return String.valueOf(blockCount);
    }

    public String getCodeCount() {
        return String.valueOf(codeCount);
    }

    public List<BlockSchema> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        return procedureName == null ? startEA : startEA + " - " + procedureName;
    }
}
