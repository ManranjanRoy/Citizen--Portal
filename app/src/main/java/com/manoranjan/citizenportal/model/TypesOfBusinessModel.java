package com.manoranjan.citizenportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypesOfBusinessModel {

    @SerializedName("Trade_Nature_Id")
    @Expose
    private Integer Trade_Nature_Id;
    @SerializedName("Trade_Nature")
    @Expose
    private String Trade_Nature;

    public Integer getTrade_Nature_Id() {
        return Trade_Nature_Id;
    }

    public void setTrade_Nature_Id(Integer trade_Nature_Id) {
        Trade_Nature_Id = trade_Nature_Id;
    }

    public String getTrade_Nature() {
        return Trade_Nature;
    }

    public void setTrade_Nature(String trade_Nature) {
        Trade_Nature = trade_Nature;
    }
}
