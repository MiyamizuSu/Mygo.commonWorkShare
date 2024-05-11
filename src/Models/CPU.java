package Models;

import java.util.ArrayList;
import java.util.Iterator;

public class CPU {
    public void createFile( UFD userFiles,String fileName,int isAllowedRU,int isAllowedR,int isAllowedW){
        userFile newFile= new userFile(fileName,isAllowedRU,isAllowedR,isAllowedW);
        userFiles.getUserFiles().add(newFile);
    }
    public void createFile(UFD userFiles,String fileName){
        UFD newDic = new UFD(fileName);
        userFiles.getUserFiles().add(newDic);
    }
    public String deleteFile( UFD userFiles,String fileName){
        Iterator<file> itr = userFiles.getUserFiles().iterator();
        userFile deletedFile=null;
        while(itr.hasNext()){
            file eleE=itr.next();
            if(eleE.getClass().equals(deletedFile.getClass())){
                userFile ele=(userFile)eleE;
                if(fileName.equals(ele.getFileName())){
                    deletedFile=ele;
                }
                else {
                    userFiles.getUserFiles().remove(ele);
                }
            }
        }
        if(deletedFile==null){
            return "没有找到该文件";
        }
        if(deletedFile.nowStatus==userFile.Status.relaxing){
            userFiles.getUserFiles().remove(deletedFile);
        }
        else {
            return "该文件正在被运行，无法被删除";
        }
        return "删除成功";
    }
    public file openFile(UFD ufd,String fileName){
        userFile openedFile=new userFile();
        ArrayList<file> userFiles = ufd.getUserFiles();
        Iterator<file> itr = userFiles.iterator();
        while(itr.hasNext()){
            file eleE=itr.next();
            if(eleE.getClass().equals(openedFile.getClass())){
                userFile ele= (userFile)eleE;
                if(fileName.equals(ele.getFileName())){
                    openedFile=ele;
                }
            }
            else {
                UFD ele=(UFD)eleE;
                if (fileName.equals(ele.getFileName())){
                    return ele;
                }
            }
        }
        return openedFile;
    }
    public file closeFile(UFD ufd,String fileName){
        userFile closedFile=new userFile();
        ArrayList<file> userFiles = ufd.getUserFiles();
        Iterator<file> itr = userFiles.iterator();
        while(itr.hasNext()){
            file eleE=itr.next();
            if (eleE.getClass().equals(closedFile.getClass())){
                userFile ele = (userFile) eleE;
                if(fileName.equals(ele.getFileName())){
                    closedFile=ele;
                }
            }
            else {
                if(eleE.getFileName().equals(fileName)){
                    return eleE;
                }
            }
        }
        return closedFile;
    }
    public String readFile(UFD ufd,String fileName){
        userFile readFile=new userFile();
        ArrayList<file> userFiles = ufd.getUserFiles();
        Iterator<file> itr = userFiles.iterator();
        while(itr.hasNext()){
            file eleE=itr.next();
            if (eleE.getClass().equals(readFile.getClass())){
                userFile ele = (userFile) eleE;
                if(fileName.equals(ele.getFileName())){
                    readFile=ele;
                }
            }
        }
        if(readFile==null){
            return "没有找到该文件";
        }
        if (readFile.nowStatus==userFile.Status.write) {
            return "该文件正在被写入，无法被读取";
        } else if (readFile.nowStatus==userFile.Status.relaxing) {
            return "该文件还未被打开，无法读取";
        }
        else {
            readFile.nowStatus = userFile.Status.read;
            return "文件读取中"+readFile.toString();
        }
    }
    public String writeFile(UFD ufd,String fileName){
        userFile writeFile=new userFile();
        ArrayList<file> userFiles = ufd.getUserFiles();
        Iterator<file> itr = userFiles.iterator();
        while(itr.hasNext()){
            file eleE=itr.next();
            if (eleE.getClass().equals(writeFile.getClass())){
                userFile ele = (userFile) eleE;
                if(fileName.equals(ele.getFileName())){
                    writeFile=ele;
                }
            }
        }
        if(writeFile==null){
            return "没有找到该文件";
        }
        if(writeFile.getProtectedCode().get("allowWrite")==0){
            return "该文件不允许写操作";
        }
        else {
            if (writeFile.nowStatus == userFile.Status.read) {
                return "该文件正在被读取，无法进行写入操作";
            } else if (writeFile.nowStatus == userFile.Status.relaxing) {
                return "该文件还未被打开，无法写入";
            } else {
                writeFile.nowStatus = userFile.Status.write;
                return "该文件正在写入" + writeFile.toString();
            }
        }
    }
}
