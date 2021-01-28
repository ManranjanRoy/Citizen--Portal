package com.manoranjan.citizenportal.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrintdataResponse {

    @SerializedName("regn_118")
    @Expose
    private String regn118;
    @SerializedName("Assessee_Holding_No")
    @Expose
    private String assesseeHoldingNo;
    @SerializedName("Property_Owner_Name")
    @Expose
    private String propertyOwnerName;
    @SerializedName("Name_Org")
    @Expose
    private String nameOrg;
    @SerializedName("Holding_No")
    @Expose
    private String holdingNo;
    @SerializedName("Total_Fee_118")
    @Expose
    private Double totalFee118;
    @SerializedName("Total_Fee_201")
    @Expose
    private Double totalFee201;
    @SerializedName("Total_Arrear")
    @Expose
    private Double totalArrear;
    @SerializedName("Adjustment")
    @Expose
    private Double adjustment;
    @SerializedName("Net_Payable")
    @Expose
    private Double netPayable;
    @SerializedName("Ward_No")
    @Expose
    private String wardNo;

    public String getRegn118() {
        return regn118;
    }

    public void setRegn118(String regn118) {
        this.regn118 = regn118;
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

    public String getNameOrg() {
        return nameOrg;
    }

    public void setNameOrg(String nameOrg) {
        this.nameOrg = nameOrg;
    }

    public String getHoldingNo() {
        return holdingNo;
    }

    public void setHoldingNo(String holdingNo) {
        this.holdingNo = holdingNo;
    }

    public Double getTotalFee118() {
        return totalFee118;
    }

    public void setTotalFee118(Double totalFee118) {
        this.totalFee118 = totalFee118;
    }

    public Double getTotalFee201() {
        return totalFee201;
    }

    public void setTotalFee201(Double totalFee201) {
        this.totalFee201 = totalFee201;
    }

    public Double getTotalArrear() {
        return totalArrear;
    }

    public void setTotalArrear(Double totalArrear) {
        this.totalArrear = totalArrear;
    }

    public Double getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(Double adjustment) {
        this.adjustment = adjustment;
    }

    public Double getNetPayable() {
        return netPayable;
    }

    public void setNetPayable(Double netPayable) {
        this.netPayable = netPayable;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

}
