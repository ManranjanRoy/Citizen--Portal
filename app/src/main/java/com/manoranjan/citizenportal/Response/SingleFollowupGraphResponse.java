package com.manoranjan.citizenportal.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleFollowupGraphResponse {

    @SerializedName("Followup_Id")
    @Expose
    private Integer followupId;
    @SerializedName("TL_Form_Id")
    @Expose
    private Integer tLFormId;
    @SerializedName("Form_Status")
    @Expose
    private String formStatus;
    @SerializedName("Followup_By_E_Cityzen_ID")
    @Expose
    private Integer followupByECityzenID;
    @SerializedName("Followup_By_User_Type")
    @Expose
    private String followupByUserType;
    @SerializedName("Followup_Date")
    @Expose
    private String followupDate;
    @SerializedName("Sent_To_Date")
    @Expose
    private Object sentToDate;
    @SerializedName("Name")
    @Expose
    private Object name;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Followup_by_user_name")
    @Expose
    private String followupByUserName;
    @SerializedName("Followup_by_e_cityzen_name")
    @Expose
    private String followupByECityzenName;
    @SerializedName("Next_Followup_by_user_name")
    @Expose
    private String nextFollowupByUserName;
    @SerializedName("Next_Followup_by_e_cityzen_name")
    @Expose
    private String nextFollowupByECityzenName;

    public Integer getFollowupId() {
        return followupId;
    }

    public void setFollowupId(Integer followupId) {
        this.followupId = followupId;
    }

    public Integer getTLFormId() {
        return tLFormId;
    }

    public void setTLFormId(Integer tLFormId) {
        this.tLFormId = tLFormId;
    }

    public String getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(String formStatus) {
        this.formStatus = formStatus;
    }

    public Integer getFollowupByECityzenID() {
        return followupByECityzenID;
    }

    public void setFollowupByECityzenID(Integer followupByECityzenID) {
        this.followupByECityzenID = followupByECityzenID;
    }

    public String getFollowupByUserType() {
        return followupByUserType;
    }

    public void setFollowupByUserType(String followupByUserType) {
        this.followupByUserType = followupByUserType;
    }

    public String getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(String followupDate) {
        this.followupDate = followupDate;
    }

    public Object getSentToDate() {
        return sentToDate;
    }

    public void setSentToDate(Object sentToDate) {
        this.sentToDate = sentToDate;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFollowupByUserName() {
        return followupByUserName;
    }

    public void setFollowupByUserName(String followupByUserName) {
        this.followupByUserName = followupByUserName;
    }

    public String getFollowupByECityzenName() {
        return followupByECityzenName;
    }

    public void setFollowupByECityzenName(String followupByECityzenName) {
        this.followupByECityzenName = followupByECityzenName;
    }

    public String getNextFollowupByUserName() {
        return nextFollowupByUserName;
    }

    public void setNextFollowupByUserName(String nextFollowupByUserName) {
        this.nextFollowupByUserName = nextFollowupByUserName;
    }

    public String getNextFollowupByECityzenName() {
        return nextFollowupByECityzenName;
    }

    public void setNextFollowupByECityzenName(String nextFollowupByECityzenName) {
        this.nextFollowupByECityzenName = nextFollowupByECityzenName;
    }

}
