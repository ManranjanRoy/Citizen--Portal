package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;

import java.util.Stack;

public class Form2Activity extends AppCompatActivity {
EditText raodname,localityname,distric,state,pincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
        raodname=findViewById(R.id.roadname);
        localityname=findViewById(R.id.localityname);
        distric=findViewById(R.id.districname);
        state=findViewById(R.id.statename);
        pincode=findViewById(R.id.pincode);
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatefiled()) {
                    final String roadname1 = raodname.getText().toString().trim();
                    final String localityname1 = localityname.getText().toString().trim();
                    final String distric1 = distric.getText().toString().trim();
                    final String state1 = state.getText().toString().trim();
                    final String pincode1 = pincode.getText().toString().trim();
                    StaticData.froadname=roadname1;
                    StaticData.flocalityname=localityname1;
                    StaticData.fdistrict=distric1;
                    StaticData.fstate=state1;
                    StaticData.fpincode=pincode1;
                    startActivity(new Intent(getApplicationContext(), Form3Activity.class));
                }
            }
        });
        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Form1Activity.class));
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
    public boolean validatefiled() {
        ////fname,lname,email,monileno,address1,address2,town,pinciode,password,cpassword
        final String roadname1 = raodname.getText().toString().trim();
        final String localityname1 = localityname.getText().toString().trim();
        final String distric1 = distric.getText().toString().trim();
        final String state1 = state.getText().toString().trim();
        final String pincode1 = pincode.getText().toString().trim();

        if (TextUtils.isEmpty(roadname1)) {
            raodname.setError("Please Enter Road Name");
            return false;
        } else if (TextUtils.isEmpty(localityname1)) {
            localityname.setError("Please  Enter Locality");
            return false;
        } else if (TextUtils.isEmpty(distric1)) {
            distric.setError("Please Enter your District");
            return false;
        } else if (TextUtils.isEmpty(pincode1)) {
            pincode.setError("Enter Your Pincode");
            return false;
        } else if (pincode1.length() > 6 || pincode1.length() < 6) {
            pincode.setError("Enter your correct pincode");
            return false;
        } else if (TextUtils.isEmpty(state1)) {
            state.setError("Enter Your State ");
            return false;

        }/*else if (!checkBox.isChecked()) {
            Toast.makeText(getApplicationContext(), "Aggree to terms and privacy policy to continue", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        showdata();
    }

    private void showdata() {
        raodname.setText(StaticData.froadname);
        localityname.setText(StaticData.flocalityname);
        distric.setText(StaticData.fdistrict);
        state.setText(StaticData.fstate);
        pincode.setText(StaticData.fpincode);
    }
}
