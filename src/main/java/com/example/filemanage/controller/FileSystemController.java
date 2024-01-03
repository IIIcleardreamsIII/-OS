package com.example.filemanage.controller;

import com.example.filemanage.Model.FileModel;
import com.example.filemanage.service.FileSystemService;

import java.util.Scanner;

public class FileSystemController {
    private FileSystemService fileSystemService;

    public FileSystemService getFileSystemService() {
        return fileSystemService;
    }

    public void setFileSystemService(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n当前目录：" + fileSystemService.getCurrentDirectory().getName());
            System.out.println("1. 创建文件");
            System.out.println("2. 打开文件");
            System.out.println("3. 读取文件");
            System.out.println("4. 写入文件");
            System.out.println("5. 删除文件");
            System.out.println("6. 创建目录");
            System.out.println("7. 删除目录");
            System.out.println("8. 列出文件和目录");
            System.out.println("9. 切换目录");
            System.out.println("0. 退出");

            System.out.print("请输入您的选择：");
            int choice = scanner.nextInt();
            scanner.nextLine();  // 消耗换行符

            switch (choice) {
                case 1:
                    System.out.print("请输入文件名：");
                    String createFilename = scanner.nextLine();
                    System.out.print("请输入文件内容：");
                    String createContent = scanner.nextLine();
                    fileSystemService.createFile(createFilename, createContent);
                    break;
                case 2:
                    System.out.print("请输入要打开的文件名：");
                    String openFilename = scanner.nextLine();
                    FileModel openedFile = fileSystemService.openFile(openFilename);
                    if (openedFile != null) {
                        System.out.println("文件内容：" + fileSystemService.readFile(openedFile));
                    } else {
                        System.out.println("文件未找到：" + openFilename);
                    }
                    break;
                case 3:
                    System.out.print("请输入要读取的文件名：");
                    String readFilename = scanner.nextLine();
                    FileModel readFile = fileSystemService.openFile(readFilename);
                    if (readFile != null) {
                        System.out.println("文件内容：" + fileSystemService.readFile(readFile));
                    } else {
                        System.out.println("文件未找到：" + readFilename);
                    }
                    break;
                case 4:
                    System.out.print("请输入要写入的文件名：");
                    String writeFilename = scanner.nextLine();
                    FileModel writeFile = fileSystemService.openFile(writeFilename);
                    if (writeFile != null) {
                        System.out.print("请输入新内容：");
                        String newContent = scanner.nextLine();
                        fileSystemService.writeFile(writeFile, newContent);
                        System.out.println("文件内容已更新。");
                    } else {
                        System.out.println("文件未找到：" + writeFilename);
                    }
                    break;
                case 5:
                    System.out.print("请输入要删除的文件名：");
                    String deleteFilename = scanner.nextLine();
                    fileSystemService.deleteFile(deleteFilename);
                    System.out.println("文件已删除。");
                    break;
                case 6:
                    System.out.print("请输入要创建的目录名：");
                    String createDirname = scanner.nextLine();
                    fileSystemService.createDirectory(createDirname);
                    System.out.println("目录已创建。");
                    break;
                case 7:
                    System.out.print("请输入要删除的目录名：");
                    String deleteDirname = scanner.nextLine();
                    fileSystemService.deleteDirectory(deleteDirname);
                    System.out.println("目录已删除。");
                    break;
                case 8:
                    fileSystemService.listFilesAndDirectories();
                    break;
                case 9:
                    System.out.print("请输入要切换的目录名：");
                    String changeDirname = scanner.nextLine();
                    fileSystemService.changeDirectory(changeDirname);
                    break;
                case 0:
                    System.out.println("正在退出...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效的选择。请输入 0 到 9 之间的数字。");
            }
        }
    }

    public static void main(String[] args) {
        FileSystemService fileSystemService = new FileSystemService();
        FileSystemController controller = new FileSystemController();
        controller.setFileSystemService(fileSystemService);
        controller.run();
    }
}
