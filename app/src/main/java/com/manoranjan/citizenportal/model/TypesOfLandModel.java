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

}
