package unknowncyberplugin.models.responsedata;

public class File {
    private String sha1;
    private String sha256;
    private String fileName;

    public File(String sha1, String sha256, String fileName) {
        this.sha1 = sha1;
        this.sha256 = sha256;
        this.fileName = fileName;
    }

    public String getSha1(){
        return sha1;
    }

    public String getSha256(){
        return sha256;
    }

    public String getFileName(){
        return fileName;
    }
}
