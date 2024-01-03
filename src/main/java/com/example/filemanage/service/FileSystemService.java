package com.example.filemanage.service;

import com.example.filemanage.Model.DirectoryModel;
import com.example.filemanage.Model.FileModel;

// FileSystemService.java
public class FileSystemService {
    private DirectoryModel currentDirectory;

    public FileSystemService() {
        this.currentDirectory = new DirectoryModel("root", null);
    }

    public void createFile(String filename, String content) {
        FileModel newFile = new FileModel(filename, content);
        currentDirectory.addFile(newFile);
    }

    public FileModel openFile(String filename) {
        return currentDirectory.getFile(filename);
    }

    public String readFile(FileModel file) {
        return file.getContent();
    }

    public void writeFile(FileModel file, String content) {
        file.setContent(content);
    }

    public void deleteFile(String filename) {
        currentDirectory.removeFile(filename);
    }

    public void createDirectory(String dirname) {
        DirectoryModel newDirectory = new DirectoryModel(dirname, currentDirectory);
        currentDirectory.addSubdirectory(newDirectory);
    }

    public void deleteDirectory(String dirname) {
        DirectoryModel directoryToRemove = null;
        for (DirectoryModel directory : currentDirectory.getSubdirectories()) {
            if (directory.getName().equals(dirname)) {
                directoryToRemove = directory;
                break;
            }
        }
        if (directoryToRemove != null) {
            currentDirectory.getSubdirectories().remove(directoryToRemove);
        }
    }

    public void listFilesAndDirectories() {
        System.out.println("Files:");
        for (FileModel file : currentDirectory.getFiles()) {
            System.out.println("- " + file.getName());
        }

        System.out.println("Directories:");
        for (DirectoryModel directory : currentDirectory.getSubdirectories()) {
            System.out.println("- " + directory.getName());
        }
    }

    public void changeDirectory(String dirname) {
        DirectoryModel newCurrentDirectory = currentDirectory.getSubdirectory(dirname);
        if (newCurrentDirectory != null) {
            currentDirectory = newCurrentDirectory;
        } else {
            System.out.println("Directory not found: " + dirname);
        }
    }

    public DirectoryModel getCurrentDirectory() {
        return currentDirectory;
    }
}
