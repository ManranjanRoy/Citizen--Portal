package com.manoranjan.citizenportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeePaymentModel {
    @SerializedName("Form_No")
    @Expose
    private String formNo;
    @SerializedName("Applied_On")
    @Expose
    private String appliedOn;
    @SerializedName("Name_Org")
    @Expose
    private String nameOrg;
    @SerializedName("Ward_No")
    @Expose
    private String wardNo;
    @SerializedName("TL_Form_Id")
    @Expose
    private String TL_Form_Id;

    public String getFormNo() {
        return formNo;
    }
    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }
    public String getAppliedOn() {
        return appliedOn;
    }
    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }
    public String getNameOrg() {
        return nameOrg;
    }

    public void setNameOrg(String nameOrg) {
        this.nameOrg = nameOrg;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public String getTL_Form_Id() {
        return TL_Form_Id;
    }

    public void setTL_Form_Id(String TL_Form_Id) {
        this.TL_Form_Id = TL_Form_Id;
    }
}