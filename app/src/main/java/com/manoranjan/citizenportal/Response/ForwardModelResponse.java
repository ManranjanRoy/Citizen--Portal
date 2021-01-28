package com.manoranjan.citizenportal.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForwardModelResponse {
    @SerializedName("Form_No")
    @Expose
    private String formNo;
    @SerializedName("Remarks")
    @Expose
    private String Remarks;

    @SerializedName("Followup_Date")
    @Expose
    private String Followup_Date;

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
}
