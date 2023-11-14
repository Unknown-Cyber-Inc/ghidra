package unknowncyberplugin.models.responsedata;

import java.util.Map;

public class FileStatusModel {
    private String status;
    private Map<String, String> pipelineStatus;

    public FileStatusModel(String status, Map<String, String> pipelineStatus) {
        this.status = status;
        this.pipelineStatus = pipelineStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPipelineStatus(Map<String, String> pipelineStatus) {
        this.pipelineStatus = pipelineStatus;
    }

    public Map<String, String> getPipelineStatus() {
        return pipelineStatus;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Upload status: ").append(status).append("\n");
        if (pipelineStatus != null) {
            for (Map.Entry<String, String> entry : pipelineStatus.entrySet()) {
                str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        }

        return str.toString();
    }

}
