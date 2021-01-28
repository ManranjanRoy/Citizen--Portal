package com.manoranjan.citizenportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocTypeListModel {
    @SerializedName("Document_name")
    @Expose
    private String documentName;
    @SerializedName("Document_Id")
    @Expose
    private Integer documentId;

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

}
