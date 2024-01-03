package com.example.filemanage.Model;

import java.util.ArrayList;

public class DirectoryModel {
    private String name;
    private DirectoryModel parent;
    private ArrayList<DirectoryModel> subdirectories;
    private ArrayList<FileModel> files;

    public DirectoryModel(String name, DirectoryModel parent) {
        this.name = name;
        this.parent = parent;
        this.subdirectories = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public DirectoryModel getParent() {
        return parent;
    }

    public ArrayList<DirectoryModel> getSubdirectories() {
        return subdirectories;
    }

    public ArrayList<FileModel> getFiles() {
        return files;
    }

    public void addFile(FileModel file) {
        files.add(file);
    }

    public FileModel getFile(String filename) {
        for (FileModel file : files) {
            if (file.getName().equals(filename)) {
                return file;
            }
        }
        return null;
    }

    public void removeFile(String filename) {
        FileModel fileToRemove = null;
        for (FileModel file : files) {
            if (file.getName().equals(filename)) {
                fileToRemove = file;
                break;
            }
        }
        if (fileToRemove != null) {
            files.remove(fileToRemove);
        }
    }

    public void addSubdirectory(DirectoryModel subdirectory) {
        subdirectories.add(subdirectory);
    }

    public DirectoryModel getSubdirectory(String dirname) {
        for (DirectoryModel subdirectory : subdirectories) {
            if (subdirectory.getName().equals(dirname)) {
                return subdirectory;
            }
        }
        return null;
    }
}