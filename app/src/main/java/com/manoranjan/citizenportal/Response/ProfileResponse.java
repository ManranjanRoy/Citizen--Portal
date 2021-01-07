package com.manoranjan.citizenportal.Response;

import com.google.gson.annotations.SerializedName;
import com.manoranjan.citizenportal.model.Profile;

public class ProfileResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String messages;

    @SerializedName("data")
    private Profile data;

    public Profile getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessages() {
        return messages;
    }
}
