package com.example.filemanage.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import com.example.filemanage.service.FileSystemService;
public class FileSystemController {
    private FileSystemService fileSystemService;

    public FileSystemController(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n当前目录: " + fileSystemService.getCurrentDirectory());
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

            System.out.print("请输入您的选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // 消耗换行符

            switch (choice) {
                case 1:
                    System.out.print("请输入文件名: ");
                    String createFilename = scanner.nextLine();
                    System.out.print("请输入文件内容: ");
                    String createContent = scanner.nextLine();
                    fileSystemService.createFile(createFilename, createContent);
                    break;
                case 2:
                    System.out.print("请输入文件名打开: ");
                    String openFilename = scanner.nextLine();
                    String openedFileContent = fileSystemService.readFile(openFilename);
                    if (openedFileContent != null) {
                        System.out.println("文件内容: " + openedFileContent);
                    } else {
                        System.out.println("文件未找到: " + openFilename);
                    }
                    break;
                case 3:
                    System.out.print("请输入文件名读取: ");
                    String readFilename = scanner.nextLine();
                    String readFileContent = fileSystemService.readFile(readFilename);
                    if (readFileContent != null) {
                        System.out.println("文件内容: " + readFileContent);
                    } else {
                        System.out.println("文件未找到: " + readFilename);
                    }
                    break;
                case 4:
                    System.out.print("请输入文件名写入: ");
                    String writeFilename = scanner.nextLine();
                    System.out.print("请输入新内容: ");
                    String newContent = scanner.nextLine();
                    fileSystemService.writeFile(writeFilename, newContent);
                    System.out.println("文件内容已更新.");
                    break;
                case 5:
                    System.out.print("请输入文件名删除: ");
                    String deleteFilename = scanner.nextLine();
                    fileSystemService.deleteFile(deleteFilename);
                    System.out.println("文件已删除.");
                    break;
                case 6:
                    System.out.print("请输入目录名创建: ");
                    String createDirname = scanner.nextLine();
                    fileSystemService.createDirectory(createDirname);
                    System.out.println("目录已创建.");
                    break;
                case 7:
                    System.out.print("请输入目录名删除: ");
                    String deleteDirname = scanner.nextLine();
                    fileSystemService.deleteDirectory(deleteDirname);
                    System.out.println("目录已删除.");
                    break;
                case 8:
                    List<String> filesAndDirs = fileSystemService.listFilesAndDirectories();
                    System.out.println("文件和目录列表:");
                    for (String item : filesAndDirs) {
                        System.out.println("- " + item);
                    }
                    break;
                case 9:
                    System.out.print("请输入目录名切换: ");
                    String changeDirname = scanner.nextLine();
                    fileSystemService.changeDirectory(changeDirname);
                    break;
                case 0:
                    System.out.println("退出...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效选择，请输入0-9之间的数字.");
            }
        }
    }

    public static void main(String[] args) {
        FileSystemService fileSystemService = new FileSystemService();
        FileSystemController controller = new FileSystemController(fileSystemService);
        controller.run();
    }
}
