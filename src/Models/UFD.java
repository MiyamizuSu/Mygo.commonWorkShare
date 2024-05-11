package Models;

import java.util.ArrayList;

public class UFD implements file{

    private ArrayList<file> userFiles;
    private String ufdName;
    public UFD(String ufdName){
        this.ufdName = ufdName;
        userFiles = new ArrayList<file>();
    }

    public ArrayList<file> getUserFiles() {
        return userFiles;
    }

    public void setUserFiles(ArrayList<file> userFiles) {
        this.userFiles = userFiles;
    }

    public String getUfdName() {
        return ufdName;
    }

    public void setUfdName(String ufdName) {
        this.ufdName = ufdName;
    }
}
