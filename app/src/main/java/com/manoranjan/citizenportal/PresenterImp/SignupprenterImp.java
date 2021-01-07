package com.manoranjan.citizenportal.PresenterImp;



import android.content.Context;

import com.manoranjan.citizenportal.presenter.SignupPresenter;
import com.manoranjan.citizenportal.service.CountryService;
import com.manoranjan.citizenportal.view.SignupView;

public class SignupprenterImp  implements SignupPresenter {
    SignupView signupView;
    Context context;
    public SignupprenterImp(SignupView signupView, Context applicationContext) {
        this.signupView = signupView;
        this.context=applicationContext;
    }

    @Override
    public void signup(String fname, String lname,String mobileno, String email, String password, String cpassword,String suburb) {

              }
}
