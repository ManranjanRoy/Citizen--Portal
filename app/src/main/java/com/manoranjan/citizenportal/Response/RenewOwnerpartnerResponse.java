package com.manoranjan.citizenportal.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RenewOwnerpartnerResponse {
    @SerializedName("Owner_Partner_Id")
    @Expose
    private Integer ownerPartnerId;
    @SerializedName("TL_ID")
    @Expose
    private Integer tLID;
    @SerializedName("Owner_Name")
    @Expose
    private String ownerName;
    @SerializedName("Pan")
    @Expose
    private String pan;
    @SerializedName("Contact_Mob")
    @Expose
    private String contactMob;
    @SerializedName("So_Do_Wo")
    @Expose
    private String soDoWo;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Form_No")
    @Expose
    private String formNo;

    public Integer getOwnerPartnerId() {
        return ownerPartnerId;
    }

    public void setOwnerPartnerId(Integer ownerPartnerId) {
        this.ownerPartnerId = ownerPartnerId;
    }

    public Integer getTLID() {
        return tLID;
    }

    public void setTLID(Integer tLID) {
        this.tLID = tLID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getContactMob() {
        return contactMob;
    }

    public void setContactMob(String contactMob) {
        this.contactMob = contactMob;
    }

    public String getSoDoWo() {
        return soDoWo;
    }

    public void setSoDoWo(String soDoWo) {
        this.soDoWo = soDoWo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

}
