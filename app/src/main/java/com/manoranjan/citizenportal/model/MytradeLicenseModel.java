package com.manoranjan.citizenportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MytradeLicenseModel {
    @SerializedName("Form_No")
    @Expose
    private String formNo;
    @SerializedName("Name_Org")
    @Expose
    private String nameOrg;
    @SerializedName("Applied_On")
    @Expose
    private String appliedOn;
    @SerializedName("Status_Name")
    @Expose
    private String statusName;
    @SerializedName("TL_Type")
    @Expose
    private String tLType;
    @SerializedName("Ward_No")
    @Expose
    private String wardNo;
    @SerializedName("Fin_Year_Name")
    @Expose
    private String finYearName;

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getNameOrg() {
        return nameOrg;
    }

    public void setNameOrg(String nameOrg) {
        this.nameOrg = nameOrg;
    }

    public String getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTLType() {
        return tLType;
    }

    public void setTLType(String tLType) {
        this.tLType = tLType;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public String getFinYearName() {
        return finYearName;
    }

    public void setFinYearName(String finYearName) {
        this.finYearName = finYearName;
    }

}
