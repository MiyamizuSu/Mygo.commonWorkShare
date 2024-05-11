package main;

import Models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class enter {
    public static void main(String[] args) {
        try {
            MDF mdf = new MDF();
            CPU amd=new CPU();
            UFD ufd = new UFD("root");
            int choice;
            UFD backUfd=null;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                boolean isPassed = false;
                System.out.print("请输入用户名：");
                String inputUsername = scanner.nextLine();
                for (HashMap.Entry<String, UFD> entry : mdf.getUserSystem().entrySet()) {
                    if (inputUsername.equals(entry.getKey())) {
                        isPassed = true;
                        ufd = entry.getValue();
                    }
                }
                if (isPassed) {
                    System.out.println("用户存在，可以继续执行其他操作。");
                    break;
                } else {
                    System.out.println("用户不存在，请重新输入正确的用户名。");
                }
            }
            do {
                System.out.println("现在的目录名为"+ufd.getFileName());
                System.out.println("请选择操作：");
                System.out.println("1. 列出文件目录");
                System.out.println("2. 创建文件");
                System.out.println("3. 删除文件");
                System.out.println("4. 打开文件");
                System.out.println("5. 关闭文件");
                System.out.println("6. 读取文件");
                System.out.println("7. 写入文件");
                System.out.println("0. 退出");
                System.out.print("请输入选项：");
                choice = Integer.valueOf(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("文件目录:");
                        if (ufd.getUserFiles().isEmpty()) {
                            System.out.println("该用户目录下暂时没有文件\n");
                            Thread.sleep(2000);
                        }
                        Iterator<file> iterator = ufd.getUserFiles().iterator();
                        while (iterator.hasNext()) {
                            System.out.println(iterator.next().toString() + "\n");
                        }
                        Thread.sleep(2000);
                        break;
                    case 2:
                        System.out.println("您选择了创建文件。");
                        System.out.println("您要创建的是\n1.目录,2.文件");
                        if(scanner.nextLine().equals("1")){
                            System.out.println("请输入文件夹名:");
                            UFD newUfd= new UFD(scanner.nextLine());
                            ufd.getUserFiles().add(newUfd);
                            System.out.println("添加完成");
                        }
                        else {
                            System.out.println("请输入文件名:");
                            String newFileName = scanner.nextLine();
                            System.out.println("请输入文件的运行读写权限");
                            System.out.println("运行权限：");
                            int newFileRU = Integer.valueOf(scanner.nextLine());
                            System.out.println("读权限：");
                            int newFileR = Integer.valueOf(scanner.nextLine());
                            System.out.println("写权限：");
                            int newFileW = Integer.valueOf(scanner.nextLine());
                            amd.createFile(ufd, newFileName, newFileRU, newFileR, newFileW);
                            System.out.println("添加完成");
                        }
                        Thread.sleep(2000);
                        // 添加创建文件逻辑
                        break;
                    case 3:
                        System.out.println("您选择了删除文件。");
                        System.out.println("请输入文件名:");
                        String deletedFileName = scanner.nextLine();
                        System.out.println(amd.deleteFile(ufd,deletedFileName));
                        Thread.sleep(2000);

                        // 添加删除文件逻辑
                        break;
                    case 4:
                        System.out.println("您选择了打开文件。");
                        // 添加打开文件逻辑
                        System.out.println("请输入文件名:");
                        String openedFileName = scanner.nextLine();
                        file openedFile = amd.openFile(ufd,openedFileName);
                        if (openedFile == null) {
                            System.out.println("没有找到该文件");
                        }
                        else {
                            if (openedFile.getClass().equals(ufd.getClass())){
                                backUfd=ufd;
                                ufd=(UFD)openedFile;
                            }
                            else {
                                System.out.println("已打开文件:"+openedFile.getFileName());
                            }
                        }
                        Thread.sleep(2000);
                        break;
                    case 5:
                        System.out.println("您选择了关闭文件。");
                        // 添加关闭文件逻辑
                        boolean isPackage=true;
                        String closedFileName = scanner.nextLine();
                        ArrayList<file> files=backUfd.getUserFiles();
                        Iterator<file> iterator1=files.iterator();
                        if (isPackage) {
                            while (iterator1.hasNext()) {
                                file ele = iterator1.next();
                                if (ele.getFileName().equals(closedFileName)) {
                                    ufd = backUfd;
                                }
                            }
                            isPackage=false;
                        }
                        if(!isPackage){
                            file closedFileE = amd.closeFile(ufd, closedFileName);
                            if (closedFileE == null) {
                                System.out.println("未找到该文件");
                            } else {
                                if (closedFileE.getClass().equals(ufd.getClass())) {
                                    ufd = backUfd;
                                } else {
                                    userFile closedFile = (userFile) amd.closeFile(ufd, closedFileName);
                                    if (closedFile.nowStatus == userFile.Status.write) {
                                        System.out.println("该文件正在写入数据，无法被关闭");
                                    } else if (closedFile.nowStatus == userFile.Status.relaxing) {
                                        System.out.println("该文件还未被打开");
                                    } else {
                                        closedFile.nowStatus = userFile.Status.relaxing;
                                        System.out.println("该文件已经被关闭\n" + closedFile.toString());
                                    }
                                    Thread.sleep(2000);
                                }
                            }
                        }
                        break;
                    case 6:
                        System.out.println("您选择了读取文件。");
                        // 添加读取文件逻辑
                        String readFileName = scanner.nextLine();
                        System.out.println(amd.readFile(ufd, readFileName));
                        Thread.sleep(2000);
                        break;
                    case 7:
                        System.out.println("您选择了写入文件。");
                        // 添加写入文件逻辑
                        String writeFileName = scanner.nextLine();
                        System.out.println(amd.writeFile(ufd, writeFileName));
                        Thread.sleep(2000);
                        break;
                    case 0:
                        System.out.println("感谢使用，再见！");
                        Thread.sleep(2000);
                        break;
                    default:
                        System.out.println("无效选项，请重新输入。");
                        Thread.sleep(2000);
                }
            } while (choice != 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
