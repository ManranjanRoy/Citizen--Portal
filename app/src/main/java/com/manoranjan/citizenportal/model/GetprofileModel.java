package com.manoranjan.citizenportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetprofileModel {
    @SerializedName("E_Cityzen_ID")
    @Expose
    private Integer eCityzenID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("EmailId")
    @Expose
    private String emailId;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("UserType")
    @Expose
    private String userType;
    @SerializedName("OTP")
    @Expose
    private Integer oTP;
    @SerializedName("Father_Husband_Company_Name")
    @Expose
    private String fatherHusbandCompanyName;
    @SerializedName("DOB")
    @Expose
    private String dOB;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Road_Name")
    @Expose
    private String roadName;
    @SerializedName("Locality_Name")
    @Expose
    private String localityName;
    @SerializedName("District")
    @Expose
    private String district;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Pin_Code")
    @Expose
    private String pinCode;
    @SerializedName("IDProof_Name")
    @Expose
    private String iDProofName;
    @SerializedName("IDProof_Number")
    @Expose
    private String iDProofNumber;
    @SerializedName("IDProofFile_Path")
    @Expose
    private String iDProofFilePath;
    @SerializedName("IS_Active")
    @Expose
    private String iSActive;
    @SerializedName("HashMap")
    @Expose
    private String hashMap;
    @SerializedName("CODE")
    @Expose
    private Integer cODE;

    public Integer getECityzenID() {
        return eCityzenID;
    }

    public void setECityzenID(Integer eCityzenID) {
        this.eCityzenID = eCityzenID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getOTP() {
        return oTP;
    }

    public void setOTP(Integer oTP) {
        this.oTP = oTP;
    }

    public String getFatherHusbandCompanyName() {
        return fatherHusbandCompanyName;
    }

    public void setFatherHusbandCompanyName(String fatherHusbandCompanyName) {
        this.fatherHusbandCompanyName = fatherHusbandCompanyName;
    }

    public String getDOB() {
        return dOB;
    }

    public void setDOB(String dOB) {
        this.dOB = dOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getIDProofName() {
        return iDProofName;
    }

    public void setIDProofName(String iDProofName) {
        this.iDProofName = iDProofName;
    }

    public String getIDProofNumber() {
        return iDProofNumber;
    }

    public void setIDProofNumber(String iDProofNumber) {
        this.iDProofNumber = iDProofNumber;
    }

    public String getIDProofFilePath() {
        return iDProofFilePath;
    }

    public void setIDProofFilePath(String iDProofFilePath) {
        this.iDProofFilePath = iDProofFilePath;
    }

    public String getISActive() {
        return iSActive;
    }

    public void setISActive(String iSActive) {
        this.iSActive = iSActive;
    }

    public String getHashMap() {
        return hashMap;
    }

    public void setHashMap(String hashMap) {
        this.hashMap = hashMap;
    }

    public Integer getCODE() {
        return cODE;
    }

    public void setCODE(Integer cODE) {
        this.cODE = cODE;
    }

}
