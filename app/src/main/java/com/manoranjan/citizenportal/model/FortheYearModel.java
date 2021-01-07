package com.manoranjan.citizenportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FortheYearModel {


    @SerializedName("Fin_Year_ID")
    @Expose
    private Integer Fin_Year_ID;
    @SerializedName("Fin_Year_Name")
    @Expose
    private String Fin_Year_Name;

    public Integer getFin_Year_ID() {
        return Fin_Year_ID;
    }

    public void setFin_Year_ID(Integer fin_Year_ID) {
        Fin_Year_ID = fin_Year_ID;
    }

    public String getFin_Year_Name() {
        return Fin_Year_Name;
    }

    public void setFin_Year_Name(String fin_Year_Name) {
        Fin_Year_Name = fin_Year_Name;
    }
}

