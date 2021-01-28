package com.manoranjan.citizenportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificatonModel {
    @SerializedName("Form_No")
    @Expose
    private String formNo;
    @SerializedName("Remarks")
    @Expose
    private String Remarks;

    @SerializedName("Followup_Date")
    @Expose
    private String Followup_Date;

    @SerializedName("TL_Form_Id")
    @Expose
    private String TL_Form_Id;
   @SerializedName("Followup_By_User_ID")
   @Expose
   private String Followup_By_User_ID;

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getFollowup_Date() {
        return Followup_Date;
    }

    public void setFollowup_Date(String followup_Date) {
        Followup_Date = followup_Date;
    }

    public String getTL_Form_Id() {
        return TL_Form_Id;
    }

    public void setTL_Form_Id(String TL_Form_Id) {
        this.TL_Form_Id = TL_Form_Id;
    }

    public String getFollowup_By_User_ID() {
        return Followup_By_User_ID;
    }

    public void setFollowup_By_User_ID(String followup_By_User_ID) {
        Followup_By_User_ID = followup_By_User_ID;
    }
}
