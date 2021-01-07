package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Api.Configss;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.UpdateProfile;
import com.manoranjan.citizenportal.model.GetprofileModel;
import com.manoranjan.citizenportal.service.CountryService;

import java.util.Calendar;
import java.util.List;

public class Form4Activity extends AppCompatActivity {
    Spinner companytypespinner,genderspinner,idtypespinner;
    String[] companytype = {"SELF", "COMPANY"};
    String[] idtype = {"PAN CARD"};
    String[] gender = {"MALE", "FEMALE"};
    TextView dateofbirth;
    EditText fname, companyname, email, mobileno;
    EditText raodname,localityname,distric,state,pincode;
    EditText cardnumber;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ProgressDialog progressDialog;
    String tokencode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form4);
        progressDialog=new ProgressDialog(Form4Activity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        tokencode = sharedPreferences.getString(Configss.tokencode, "default");

        companytypespinner = findViewById(R.id.coursesspinner);
        genderspinner = findViewById(R.id.genderspinner);
        idtypespinner=findViewById(R.id.idtypesspinner);
        fname = findViewById(R.id.fname);
        companyname = findViewById(R.id.companyname);
        email = findViewById(R.id.emailid);
        mobileno = findViewById(R.id.phoneno);
        dateofbirth=findViewById(R.id.Dateofbirth);
        raodname=findViewById(R.id.roadname);
        localityname=findViewById(R.id.localityname);
        distric=findViewById(R.id.districname);
        state=findViewById(R.id.statename);
        pincode=findViewById(R.id.pincode);
        cardnumber=findViewById(R.id.cardnumber);
        // Get Current Date
       /* dateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }
        });*/

        ArrayAdapter ad = new ArrayAdapter(this,R.layout.my_spinner_style,companytype);
       // ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        companytypespinner.setAdapter(ad);
        companytypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter ad1 = new ArrayAdapter(this,R.layout.my_spinner_style,gender);
        //ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(ad1);
        genderspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter ad2 = new ArrayAdapter(this,R.layout.my_spinner_style,idtype);
        idtypespinner.setAdapter(ad2);
        idtypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        showdata();


        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitdata();
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Form3Activity.class));
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

    void datepicker(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        dateofbirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    void showdata(){
        if (StaticData.fcompanytype.equals("SELF")){
            companytypespinner.setSelection(0);
            companyname.setVisibility(View.GONE);
        }else{
            companytypespinner.setSelection(1);
            companyname.setVisibility(View.VISIBLE);
        }
        if (StaticData.fgender.equals("MALE")){
            genderspinner.setSelection(0);
        }else{
            genderspinner.setSelection(1);
        }
        companytypespinner.setEnabled(false);
        genderspinner.setEnabled(false);
        idtypespinner.setEnabled(false);

        fname.setText(StaticData.fname);
        companyname.setText(StaticData.fcompanyname);
        mobileno.setText(StaticData.fnumber);
        email.setText(StaticData.femail);
        dateofbirth.setText(StaticData.fdob);
        raodname.setText(StaticData.froadname);
        localityname.setText(StaticData.flocalityname);
        distric.setText(StaticData.fdistrict);
        state.setText(StaticData.fstate);
        pincode.setText(StaticData.fpincode);
        cardnumber.setText(StaticData.fcardnumber);
    }
    public void Showprogess() {
        progressDialog.show();
    }
    public void dismissproggress() {
        progressDialog.dismiss();
        //
    }
    public void submitdata(){
        JsonObject obj= RequestData.updateprofile();
        Showprogess();
        CountryService countryService=new CountryService();
        countryService.getAPI().updateprofile(obj).enqueue(new Callback<List<UpdateProfile>>() {
            @Override
            public void onResponse(Call<List<UpdateProfile>> call, Response<List<UpdateProfile>> response) {
                Log.d("response", response.body().toString());
                dismissproggress();
                if (response.body()!=null) {
                    if (response.body().size()>0){
                           if (response.body().get(0).getCODE().equals("1")){
                               SharedPreferences sharedPreferences = getSharedPreferences
                                       (Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                               //Creating editor to store values to shared preferences
                               SharedPreferences.Editor editor = sharedPreferences.edit();
                               editor.putBoolean(Configss.Profile_status,true);
                               editor.commit();
                               Toast.makeText(getApplicationContext(),"Sucessfully Updated Your Profile",Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(Form4Activity.this, MainActivity.class);
                               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                               startActivity(intent);
                               finish();
                           }else{
                               Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                           }
                    } else {

                    }
                }
            }
            @Override
            public void onFailure(Call<List<UpdateProfile>> call, Throwable t) {
                dismissproggress();
            }
        });

    }
}
