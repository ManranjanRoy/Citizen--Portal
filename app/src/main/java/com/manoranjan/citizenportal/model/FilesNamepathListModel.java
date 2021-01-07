package com.manoranjan.citizenportal.model;

public class FilesNamepathListModel {

    String name,path;

    public FilesNamepathListModel() {
    }

    public FilesNamepathListModel(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FilesNamepathListModel{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
