package com.example.filemanage.Model;

// FileModel.java
public class FileModel {
    private String name;
    private String content;

    public FileModel(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}