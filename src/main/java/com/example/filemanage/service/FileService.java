package com.example.filemanage.service;

public interface FileService {
    void createFile(String filename);// 创建文件
    void deleteFile(String filename);// 删除文件
    void changeDirectory(String directoryName);// 切换目录
    void listFiles();// 列出当前目录下的文件
    void readFile(String filename);// 读取文件内容
    void writeFile(String filename, String content);// 写入文件内容

}
