package com.manoranjan.citizenportal.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("E_Cityzen_ID")
    @Expose
    private Integer eCityzenID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Company_Name")
    @Expose
    private String companyName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Pass")
    @Expose
    private String pass;
    @SerializedName("User_Type")
    @Expose
    private String userType;
    @SerializedName("Reg_OTP")
    @Expose
    private Integer regOTP;
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
    @SerializedName("User_Hash")
    @Expose
    private String userHash;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getRegOTP() {
        return regOTP;
    }

    public void setRegOTP(Integer regOTP) {
        this.regOTP = regOTP;
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

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    public Integer getCODE() {
        return cODE;
    }

    public void setCODE(Integer cODE) {
        this.cODE = cODE;
    }

}
