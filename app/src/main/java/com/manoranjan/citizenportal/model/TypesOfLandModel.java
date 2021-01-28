package com.manoranjan.citizenportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypesOfLandModel {
    @SerializedName("Land_Nature_Id")
    @Expose
    private Integer landNatureId;
    @SerializedName("Land_Nature")
    @Expose
    private String landNature;
    @SerializedName("Holding_Req")
    @Expose
    private String holdingReq;
    @SerializedName("Is_Active")
    @Expose
    private String isActive;

    public Integer getLandNatureId() {
        return landNatureId;
    }

    public void setLandNatureId(Integer landNatureId) {
        this.landNatureId = landNatureId;
    }

    public String getLandNature() {
        return landNature;
    }

    public void setLandNature(String landNature) {
        this.landNature = landNature;
    }

    public String getHoldingReq() {
        return holdingReq;
    }

    public void setHoldingReq(String holdingReq) {
        this.holdingReq = holdingReq;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

}