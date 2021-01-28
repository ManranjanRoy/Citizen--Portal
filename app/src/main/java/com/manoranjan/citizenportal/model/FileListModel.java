package com.manoranjan.citizenportal.model;

import java.io.File;

public class FileListModel {

    int id;
    String name;
    File file;

    public FileListModel() {
    }

    public FileListModel(int id, String name, File file) {
        this.id = id;
        this.name = name;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
