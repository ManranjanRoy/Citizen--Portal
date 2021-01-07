package com.manoranjan.citizenportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WardNoModel {

    @SerializedName("Ward_ID")
    @Expose
    private Integer wardID;
    @SerializedName("Ward_No")
    @Expose
    private String wardNo;
    @SerializedName("Ward_Councillor")
    @Expose
    private String wardCouncillor;
    @SerializedName("Is_Active")
    @Expose
    private String isActive;

    public Integer getWardID() {
        return wardID;
    }

    public void setWardID(Integer wardID) {
        this.wardID = wardID;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public String getWardCouncillor() {
        return wardCouncillor;
    }

    public void setWardCouncillor(String wardCouncillor) {
        this.wardCouncillor = wardCouncillor;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

}
