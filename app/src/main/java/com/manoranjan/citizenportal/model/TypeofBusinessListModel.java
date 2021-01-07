package com.manoranjan.citizenportal.model;

public class TypeofBusinessListModel {
    String noofdirector,name,fathername,address,contactno,idproof,idproofno;

    public TypeofBusinessListModel() {
    }

    public TypeofBusinessListModel(String noofdirector, String name, String fathername, String address, String contactno, String idproof, String idproofno) {
        this.noofdirector = noofdirector;
        this.name = name;
        this.fathername = fathername;
        this.address = address;
        this.contactno = contactno;
        this.idproof = idproof;
        this.idproofno = idproofno;
    }

    public String getNoofdirector() {
        return noofdirector;
    }

    public void setNoofdirector(String noofdirector) {
        this.noofdirector = noofdirector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getIdproof() {
        return idproof;
    }

    public void setIdproof(String idproof) {
        this.idproof = idproof;
    }

    public String getIdproofno() {
        return idproofno;
    }

    public void setIdproofno(String idproofno) {
        this.idproofno = idproofno;
    }

    @Override
    public String toString() {
        return "TypeofBusinessListModel{" +
                "noofdirector='" + noofdirector + '\'' +
                ", name='" + name + '\'' +
                ", fathername='" + fathername + '\'' +
                ", address='" + address + '\'' +
                ", contactno='" + contactno + '\'' +
                ", idproof='" + idproof + '\'' +
                ", idproofno='" + idproofno + '\'' +
                '}';
    }
}
