package com.manoranjan.citizenportal.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleMyTadeLicense {
    @SerializedName("app_TL_ID")
    @Expose
    private Integer appTLID;
    @SerializedName("Assessee_Holding_No")
    @Expose
    private String assesseeHoldingNo;
    @SerializedName("Property_Owner_Name")
    @Expose
    private String propertyOwnerName;
    @SerializedName("Ward")
    @Expose
    private Integer ward;
    @SerializedName("Ward_No")
    @Expose
    private String wardNo;
    @SerializedName("Ward_Councillor")
    @Expose
    private String wardCouncillor;
    @SerializedName("Street_Address")
    @Expose
    private String streetAddress;
    @SerializedName("Locality_Name")
    @Expose
    private String localityName;
    @SerializedName("Tax_Paid_Type")
    @Expose
    private String taxPaidType;
    @SerializedName("Tax_Paid_Amount")
    @Expose
    private Double taxPaidAmount;
    @SerializedName("Land_Nature")
    @Expose
    private String landNature;
    @SerializedName("Trade_Nature")
    @Expose
    private String tradeNature;
    @SerializedName("Name_Org")
    @Expose
    private String nameOrg;
    @SerializedName("GST_IN")
    @Expose
    private String gSTIN;
    @SerializedName("Company_PAN")
    @Expose
    private String companyPAN;
    @SerializedName("Capital")
    @Expose
    private Double capital;
    @SerializedName("Con_add")
    @Expose
    private String conAdd;
    @SerializedName("Con_No")
    @Expose
    private String conNo;
    @SerializedName("Workshop")
    @Expose
    private String workshop;
    @SerializedName("Godown")
    @Expose
    private String godown;
    @SerializedName("Regnx")
    @Expose
    private Object regnx;
    @SerializedName("Ptax_no")
    @Expose
    private Object ptaxNo;
    @SerializedName("Remarks")
    @Expose
    private Object remarks;
    @SerializedName("User_Name")
    @Expose
    private String userName;
    @SerializedName("Appl_By")
    @Expose
    private String applBy;
    @SerializedName("regn_118")
    @Expose
    private Integer regn118;
    @SerializedName("regn_201")
    @Expose
    private Object regn201;
    @SerializedName("Form_No")
    @Expose
    private String formNo;
    @SerializedName("Fin_Year_Sht_Name")
    @Expose
    private String finYearShtName;
    @SerializedName("Street")
    @Expose
    private String street;
    @SerializedName("Holding_No")
    @Expose
    private String holdingNo;
    @SerializedName("Tax_Status")
    @Expose
    private Object taxStatus;
    @SerializedName("Applied_On")
    @Expose
    private String appliedOn;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Entry_Type_User")
    @Expose
    private String entryTypeUser;

    public Integer getAppTLID() {
        return appTLID;
    }

    public void setAppTLID(Integer appTLID) {
        this.appTLID = appTLID;
    }

    public String getAssesseeHoldingNo() {
        return assesseeHoldingNo;
    }

    public void setAssesseeHoldingNo(String assesseeHoldingNo) {
        this.assesseeHoldingNo = assesseeHoldingNo;
    }

    public String getPropertyOwnerName() {
        return propertyOwnerName;
    }

    public void setPropertyOwnerName(String propertyOwnerName) {
        this.propertyOwnerName = propertyOwnerName;
    }

    public Integer getWard() {
        return ward;
    }

    public void setWard(Integer ward) {
        this.ward = ward;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public String getWardCouncillor() {
        return wardCouncillor;
    }

    public void setWardCouncillor(String wardCouncillor) {
        this.wardCouncillor = wardCouncillor;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getTaxPaidType() {
        return taxPaidType;
    }

    public void setTaxPaidType(String taxPaidType) {
        this.taxPaidType = taxPaidType;
    }

    public Double getTaxPaidAmount() {
        return taxPaidAmount;
    }

    public void setTaxPaidAmount(Double taxPaidAmount) {
        this.taxPaidAmount = taxPaidAmount;
    }

    public String getLandNature() {
        return landNature;
    }

    public void setLandNature(String landNature) {
        this.landNature = landNature;
    }

    public String getTradeNature() {
        return tradeNature;
    }

    public void setTradeNature(String tradeNature) {
        this.tradeNature = tradeNature;
    }

    public String getNameOrg() {
        return nameOrg;
    }

    public void setNameOrg(String nameOrg) {
        this.nameOrg = nameOrg;
    }

    public String getGSTIN() {
        return gSTIN;
    }

    public void setGSTIN(String gSTIN) {
        this.gSTIN = gSTIN;
    }

    public String getCompanyPAN() {
        return companyPAN;
    }

    public void setCompanyPAN(String companyPAN) {
        this.companyPAN = companyPAN;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public String getConAdd() {
        return conAdd;
    }

    public void setConAdd(String conAdd) {
        this.conAdd = conAdd;
    }

    public String getConNo() {
        return conNo;
    }

    public void setConNo(String conNo) {
        this.conNo = conNo;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getGodown() {
        return godown;
    }

    public void setGodown(String godown) {
        this.godown = godown;
    }

    public Object getRegnx() {
        return regnx;
    }

    public void setRegnx(Object regnx) {
        this.regnx = regnx;
    }

    public Object getPtaxNo() {
        return ptaxNo;
    }

    public void setPtaxNo(Object ptaxNo) {
        this.ptaxNo = ptaxNo;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApplBy() {
        return applBy;
    }

    public void setApplBy(String applBy) {
        this.applBy = applBy;
    }

    public Integer getRegn118() {
        return regn118;
    }

    public void setRegn118(Integer regn118) {
        this.regn118 = regn118;
    }

    public Object getRegn201() {
        return regn201;
    }

    public void setRegn201(Object regn201) {
        this.regn201 = regn201;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getFinYearShtName() {
        return finYearShtName;
    }

    public void setFinYearShtName(String finYearShtName) {
        this.finYearShtName = finYearShtName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHoldingNo() {
        return holdingNo;
    }

    public void setHoldingNo(String holdingNo) {
        this.holdingNo = holdingNo;
    }

    public Object getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(Object taxStatus) {
        this.taxStatus = taxStatus;
    }

    public String getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEntryTypeUser() {
        return entryTypeUser;
    }

    public void setEntryTypeUser(String entryTypeUser) {
        this.entryTypeUser = entryTypeUser;
    }

}