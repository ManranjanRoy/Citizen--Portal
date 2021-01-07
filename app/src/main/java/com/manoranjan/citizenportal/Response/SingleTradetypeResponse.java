package com.manoranjan.citizenportal.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleTradetypeResponse {
    @SerializedName("Trade_id")
    @Expose
    private Integer tradeId;
    @SerializedName("Trade_size")
    @Expose
    private String tradeSize;
    @SerializedName("Trade_Type")
    @Expose
    private String tradeType;

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeSize() {
        return tradeSize;
    }

    public void setTradeSize(String tradeSize) {
        this.tradeSize = tradeSize;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

}
