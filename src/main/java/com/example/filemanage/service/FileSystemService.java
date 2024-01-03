package com.example.filemanage.service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FileSystemService {
    private Path rootPath;
    private Path currentDirectory;
    private Path previousDirectory;

    public FileSystemService() {
        // 根目录设置为 D 盘的 "111" 文件夹
        this.rootPath = Paths.get("D:\\111");
        this.currentDirectory = rootPath;
        this.previousDirectory = rootPath;
        initRootDirectory();
    }

    private void initRootDirectory() {
        if (!Files.exists(rootPath)) {
            try {
                Files.createDirectory(rootPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createFile(String filename, String content) {
        Path filePath = currentDirectory.resolve(filename);
        try {
            Files.write(filePath, content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String filename) {
        Path filePath = currentDirectory.resolve(filename);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeFile(String filename, String content) {
        Path filePath = currentDirectory.resolve(filename);
        try {
            Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String filename) {
        Path filePath = currentDirectory.resolve(filename);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDirectory(String dirname) {
        Path dirPath = currentDirectory.resolve(dirname);
        try {
            Files.createDirectory(dirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteDirectory(String dirname) {
        Path dirPath = currentDirectory.resolve(dirname);
        try {
            Files.walkFileTree(dirPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> listFilesAndDirectories() {
        try {
            return Files.list(currentDirectory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public void changeDirectory(String dirname) {
        Path newDirPath;

        if (dirname.equals("..")) {
            // 切换到上一个目录
            newDirPath = previousDirectory;
        } else {
            // 切换到指定目录
            newDirPath = currentDirectory.resolve(dirname);
        }

        if (Files.isDirectory(newDirPath)) {
            previousDirectory = currentDirectory;
            currentDirectory = newDirPath;
        } else {
            System.out.println("目录未找到: " + dirname);
        }
    }

    public Path getCurrentDirectory() {
        return currentDirectory;
    }
}
