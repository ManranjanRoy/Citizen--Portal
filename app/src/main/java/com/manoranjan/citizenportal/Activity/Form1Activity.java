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
import com.manoranjan.citizenportal.model.Example;
import com.manoranjan.citizenportal.model.GetprofileModel;
import com.manoranjan.citizenportal.service.CountryService;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Form1Activity extends AppCompatActivity {
TextView dateofbirth;
    Spinner companytypespinner,genderspinner;
    EditText fname, companyname, email, mobileno;
    String[] companytypes = {"SELF", "COMPANY"};
    String[] gender = {"MALE", "FEMALE"};
    private int mYear, mMonth, mDay, mHour, mMinute;
    String tokencode;
    ProgressDialog progressDialog;
    String gendertext,companytype="SELF";
    List<GetprofileModel> getprofileModelList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        progressDialog=new ProgressDialog(Form1Activity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        tokencode = sharedPreferences.getString(Configss.tokencode, "default");
        fname = findViewById(R.id.fname);
        companyname = findViewById(R.id.companyname);
        email = findViewById(R.id.emailid);
        mobileno = findViewById(R.id.phoneno);
        dateofbirth=findViewById(R.id.Dateofbirth);
        // Get Current Date
        dateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }
        });
        companytypespinner = findViewById(R.id.coursesspinner);
        genderspinner = findViewById(R.id.genderspinner);

        ArrayAdapter ad = new ArrayAdapter(this,R.layout.my_spinner_style,companytypes);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        companytypespinner.setAdapter(ad);
        companytypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               /* companytype = companytypes[position];
                if (companytypes[position].equals("SELF")) {
                    companyname.setVisibility(View.GONE);
                } else {
                    companyname.setVisibility(View.VISIBLE);
                }*/
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter ad1 = new ArrayAdapter(this,R.layout.my_spinner_style,gender);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        genderspinner.setAdapter(ad1);
        genderspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             StaticData.fgender=gender[position];
               // Toast.makeText(Form1Activity.this, StaticData.fgender, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dateofbirth.getText().toString().isEmpty()){
                    Toast.makeText(Form1Activity.this, "Select Date of Birth", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(getApplicationContext(),Form2Activity.class));
                }
            }
        });
        getprofile(tokencode);

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
                        dateofbirth.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        StaticData.fdob=year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("","");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getprofileModelList.size()>0){
        showdata();}
        else{
            getprofile(tokencode);
        }
    }

    public void Showprogess() {
        progressDialog.show();
    }


    public void dismissproggress() {
        progressDialog.dismiss();
        //
    }
    public void getprofile(final String email1) {

        JsonObject obj= RequestData.getprofile(email1);
            Showprogess();
            CountryService countryService=new CountryService();
            countryService.getAPI().getprofile(obj).enqueue(new Callback<List<GetprofileModel>>() {
                @Override
                public void onResponse(Call<List<GetprofileModel>> call, Response<List<GetprofileModel>> response) {
                    Log.d("response", response.body().toString());
                    dismissproggress();
                    if (response.body()!=null) {
                        if (response.body().size()>0){
                            getprofileModelList=response.body();
                            GetprofileModel getprofileModel=getprofileModelList.get(0);
                            StaticData.fcompanytype=getprofileModel.getUserType();
                            StaticData.fcompanyname=getprofileModel.getCompanyName();
                            StaticData.fname=getprofileModel.getName();
                            StaticData.fnumber=getprofileModel.getMobileNo();
                            StaticData.femail=getprofileModel.getEmailId();
                            StaticData.fdob=getprofileModel.getDOB();
                           showdata();
                        } else {

                        }
                    }
                }
                @Override
                public void onFailure(Call<List<GetprofileModel>> call, Throwable t) {
                    dismissproggress();
                }
            });

    }

    void showdata(){
        GetprofileModel getprofileModel=getprofileModelList.get(0);
        if (getprofileModel.getUserType().equals("SELF")){
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
        fname.setText(getprofileModel.getName());
        companyname.setText(getprofileModel.getCompanyName());
        mobileno.setText(getprofileModel.getMobileNo());
        email.setText(getprofileModel.getEmailId());
        dateofbirth.setText(StaticData.fdob);

    }
}
