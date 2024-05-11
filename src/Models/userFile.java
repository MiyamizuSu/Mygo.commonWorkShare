package Models;

import java.util.HashMap;

public class userFile  implements file{
    private String FileName;
    private HashMap<String,Integer> ProtectedCode;
    private int FileLength;
    public Status nowStatus;
    public int usedNumber;
    public userFile(String fileName,int isAllowedRU,int isAllowedR,int isAllowedW) {
        FileName = fileName;
        FileLength = 0;
        ProtectedCode = new HashMap<>();
        ProtectedCode.put("allowRunning", isAllowedRU);
        ProtectedCode.put("allowRead", isAllowedR);
        ProtectedCode.put("allowWrite", isAllowedW);
        usedNumber = 0;
        nowStatus=Status.relaxing;
    }
    @Override
    public String toString() {
        return "文件的名字为"+FileName+"\n文件的长度为"+FileLength;
    }
    public String getFileName() {
        return FileName;
    }
    public void setFileName(String fileName) {
        FileName = fileName;
    }
    public HashMap<String, Integer> getProtectedCode() {
        return ProtectedCode;
    }
    public void setProtectedCode(HashMap<String, Integer> protectedCode) {
        this.ProtectedCode = protectedCode;
    }

    public int getFileLength() {
        return FileLength;
    }

    public void setFileLength(int fileLength) {
        FileLength = fileLength;
    }
    enum Status {
        relaxing,running,read,write
    }

}
