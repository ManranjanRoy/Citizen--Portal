package com.manoranjan.citizenportal.view;

public interface LoginView {
    void onSucess();
    void onError(String msg);
    boolean validatefiled();
    void Showprogess();
    void dismissproggress();


}

