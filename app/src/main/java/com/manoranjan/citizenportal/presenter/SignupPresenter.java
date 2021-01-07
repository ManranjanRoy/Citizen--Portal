package com.manoranjan.citizenportal.presenter;

public interface SignupPresenter {
    // void validatefield(String email,String password);
    ////fname,lname,email,monileno,address1,address2,town,pinciode,password,cpassword
    void signup(String fname, String lname, String email, String mobileno, String password, String cpassword, String suburb);
}