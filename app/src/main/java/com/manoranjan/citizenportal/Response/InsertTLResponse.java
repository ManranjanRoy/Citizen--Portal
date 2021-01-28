package com.manoranjan.citizenportal.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertTLResponse {
    @SerializedName("FormNumber")
    @Expose
    private String formNumber;
    @SerializedName("ReceiptNo")
    @Expose
    private String receiptNo;
    @SerializedName("paymentdate")
    @Expose
    private String paymentdate;
    @SerializedName("Column1")
    @Expose
    private String column1;
    @SerializedName("ReceivedFrom")
    @Expose
    private String receivedFrom;
    @SerializedName("Amount")
    @Expose
    private Double amount;
    @SerializedName("Comp_Logo")
    @Expose
    private String compLogo;
    @SerializedName("TL_form_receipt_sig")
    @Expose
    private String tLFormReceiptSig;
    @SerializedName("CODE")
    @Expose
    private Integer cODE;

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getReceivedFrom() {
        return receivedFrom;
    }

    public void setReceivedFrom(String receivedFrom) {
        this.receivedFrom = receivedFrom;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCompLogo() {
        return compLogo;
    }

    public void setCompLogo(String compLogo) {
        this.compLogo = compLogo;
    }

    public String getTLFormReceiptSig() {
        return tLFormReceiptSig;
    }

    public void setTLFormReceiptSig(String tLFormReceiptSig) {
        this.tLFormReceiptSig = tLFormReceiptSig;
    }

    public Integer getCODE() {
        return cODE;
    }

    public void setCODE(Integer cODE) {
        this.cODE = cODE;
    }

}