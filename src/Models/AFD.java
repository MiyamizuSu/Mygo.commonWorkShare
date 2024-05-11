package Models;

import java.util.ArrayList;
import java.util.Iterator;

public class AFD {
   ArrayList<userFile> openingFile=new ArrayList<>();
   public AFD(){
       this.openingFile= new ArrayList<userFile>();
   }
   @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
       Iterator<userFile> iterator = openingFile.iterator();
       int count=0;
       while(iterator.hasNext()){
           userFile file=iterator.next();
           builder.append(count+"  "+"运行中的文件为"+file.getFileName()+"该文件的长度为"+file.getFileLength()+"读写锁为"+file.nowStatus);
           builder.append("\n");
       }
       return builder.toString();
   }
}
