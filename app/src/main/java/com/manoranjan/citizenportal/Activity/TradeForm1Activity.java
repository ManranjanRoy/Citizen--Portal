package com.manoranjan.citizenportal.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.SingleMyTadeLicense;
import com.manoranjan.citizenportal.model.TypesOfLandModel;
import com.manoranjan.citizenportal.model.WardNoModel;
import com.manoranjan.citizenportal.service.CountryService;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TradeForm1Activity extends AppCompatActivity {
    TextInputLayout formnolayout;
    EditText formno;
    EditText holdingno, ownername, streetaddress, locality, ammountpaidfortax;
    ProgressDialog progressDialog;
    Spinner applytypespinner, wardnospinner, typesofrelationspinner;
    List<String> wardlist = new ArrayList<>();
    List<String> applytypelist = new ArrayList<>();
    List<String> typesofrelationlist = new ArrayList<>();
    List<WardNoModel> wardNoModelArrayList = new ArrayList<>();
    List<TypesOfLandModel> typesOfLandModelArrayList = new ArrayList<>();
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    String wardnoid = "1", typeofrelationid = "1";
    String typeoftax = "Property Tax";
    String applytype = "New";
    SingleMyTadeLicense singleMyTadeLicense = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_form1);
        progressDialog = new ProgressDialog(TradeForm1Activity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        applytypespinner = findViewById(R.id.applytypespinner);
        wardnospinner = findViewById(R.id.wardno);
        typesofrelationspinner = findViewById(R.id.typesofrelation);
        holdingno = findViewById(R.id.holdingno);
        ownername = findViewById(R.id.ownername);
        streetaddress = findViewById(R.id.streetaddress);
        locality = findViewById(R.id.locality);
        ammountpaidfortax = findViewById(R.id.ammountpaidfortax);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioyes);
        radioButton2 = findViewById(R.id.radiono);
        formnolayout = findViewById(R.id.formnolayout);
        formno = findViewById(R.id.formno);
        spinnerdata();

        getward();
        gettypesofrelation();
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatefiled()) {
                    final String holdingno1 = holdingno.getText().toString().trim();
                    final String ownername1 = ownername.getText().toString().trim();
                    final String streetaddress1 = streetaddress.getText().toString().trim();
                    final String locality1 = locality.getText().toString().trim();
                    final String ammountpaidfortax1 = ammountpaidfortax.getText().toString().trim();

                    StaticData.tholdingno = holdingno1;
                    StaticData.townername = ownername1;
                    StaticData.tstreetaddress = streetaddress1;
                    StaticData.tlocality = locality1;
                    StaticData.twardno = wardnoid;
                    StaticData.ttypeoftax = typeoftax;
                    StaticData.tammountoftax = ammountpaidfortax1;
                    StaticData.typeofrelation = typeofrelationid;
                    startActivity(new Intent(getApplicationContext(), TradeForm2Activity.class));
                }

            }
        });
    }

    private void spinnerdata() {
        //
        List<String> allpytype = new ArrayList<String>();
        allpytype.add("New");
        allpytype.add("Renew");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(TradeForm1Activity.this,
                R.layout.my_spinner_style, allpytype);
        // attaching data adapter to spinner
        applytypespinner.setAdapter(dataAdapter1);
        applytypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                applytype = item.trim();
                //Toast.makeText(TradeForm1Activity.this, item, Toast.LENGTH_SHORT).show();
                checktypeofapply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void checktypeofapply() {
        if (applytype.equals("Renew")) {
            formnolayout.setVisibility(View.VISIBLE);
            holdingno.setFocusable(false);
            ownername.setFocusable(false);
            streetaddress.setFocusable(false);
            locality.setFocusable(false);
            ammountpaidfortax.setFocusable(false);
        } else {
            formnolayout.setVisibility(View.GONE);
            holdingno.setFocusable(true);
            ownername.setFocusable(true);
            streetaddress.setFocusable(true);
            locality.setFocusable(true);
            ammountpaidfortax.setFocusable(true);
            StaticData.tholdingno = "";
            StaticData.townername = "";
            StaticData.tlocality = "";
            StaticData.tstreetaddress = "";
            StaticData.tammountoftax = "";
            StaticData.tlocality ="";
            StaticData.ttypeoftax = "";
        }

        formno.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //Toast.makeText(getApplicationContext(),"before text change",Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                StaticData.form_id = arg0.toString();
                if (arg0.toString().length() >= 21) {
                    getmytradelicense();
                }
                //Toast.makeText(getApplicationContext(),"after text change",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getmytradelicense() {
        progressDialog.show();
        JsonObject obj = RequestData.getsinglemytradelicense();
        CountryService countryService = new CountryService();
        countryService.getAPI().getsinglemytradelicense(obj).enqueue(new Callback<List<SingleMyTadeLicense>>() {
            @Override
            public void onResponse(Call<List<SingleMyTadeLicense>> call, Response<List<SingleMyTadeLicense>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        Toast.makeText(getApplicationContext(), "error 1", Toast.LENGTH_SHORT).show();
                        singleMyTadeLicense = response.body().get(0);
                        StaticData.tholdingno = singleMyTadeLicense.getAssesseeHoldingNo();
                        StaticData.townername = singleMyTadeLicense.getPropertyOwnerName();
                        StaticData.tlocality = singleMyTadeLicense.getLocalityName();
                        StaticData.tstreetaddress = singleMyTadeLicense.getStreetAddress();
                        StaticData.tammountoftax = String.valueOf(singleMyTadeLicense.getTaxPaidAmount());
                        StaticData.tlocality = singleMyTadeLicense.getLocalityName();
                        StaticData.ttypeoftax = singleMyTadeLicense.getTaxPaidType();

                      /*  StaticData.ttypeofbusinessid = singleMyTadeLicense.getTradeNature();
                        StaticData.tfortheyearid = singleMyTadeLicense.getFinYearShtName();
                        StaticData.tnameoffirm = singleMyTadeLicense.getNameOrg();
                        StaticData.tgstin = singleMyTadeLicense.getGSTIN();
                        StaticData.tdateofcommenence = singleMyTadeLicense.getAppliedOn();
                        StaticData.tinvestmentcapital = String.valueOf(singleMyTadeLicense.getCapital());
                       // StaticData.ttradetype = tradetypeid;
                        StaticData.tcompanypancard = singleMyTadeLicense.getCompanyPAN();
                        StaticData.tcontactnofirst = singleMyTadeLicense.getConNo();
                        StaticData.tcontactaddress = singleMyTadeLicense.getConAdd();
                        StaticData.tworkshopaddress = singleMyTadeLicense.getWorkshop();
                        StaticData.tgodownaddress = singleMyTadeLicense.getGodown();*/
                        onResume();
                    } else {
                        Toast.makeText(getApplicationContext(), "error 1", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error 11", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SingleMyTadeLicense>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "error 1" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        holdingno.setText(StaticData.tholdingno);
        ownername.setText(StaticData.townername);
        streetaddress.setText(StaticData.tstreetaddress);
        locality.setText(StaticData.tlocality);
        ammountpaidfortax.setText(StaticData.tammountoftax);
        Log.d("testmatch", StaticData.ttypeoftax);
        if (StaticData.ttypeoftax.equals("Property Tax")) {
            radioButton1.setChecked(true);
        } else {
            radioButton2.setChecked(true);
        }


    }

    public void radioclick(View view) {
        int checkedId = view.getId();
        switch (checkedId) {
            case R.id.radioyes:
                typeoftax = "Property Tax";
                break;
            case R.id.radiono:
                typeoftax = "Occupier Tax";
                break;
        }
    }

    public boolean validatefiled() {
        ////holdingno,ownername,streetaddress,locality,ammountpaidfortax
        final String holdingno1 = holdingno.getText().toString().trim();
        final String ownername1 = ownername.getText().toString().trim();
        final String streetaddress1 = streetaddress.getText().toString().trim();
        final String locality1 = locality.getText().toString().trim();
        final String ammountpaidfortax1 = ammountpaidfortax.getText().toString().trim();


        if (TextUtils.isEmpty(holdingno1)) {
            holdingno.setError("Please Enter Holding No");
            return false;
        } else if (TextUtils.isEmpty(ownername1)) {
            ownername.setError("Please  Enter Owner Name");
            return false;
        } else if (TextUtils.isEmpty(streetaddress1)) {
            streetaddress.setError("Please Enter Address");
            return false;
        } else if (TextUtils.isEmpty(locality1)) {
            locality.setError("Enter Your Locality");
            return false;
        } else if (TextUtils.isEmpty(ammountpaidfortax1)) {
            ammountpaidfortax.setError("Enter Your Ammount Paid For Tax ");
            return false;
        }
        return true;
    }

    public void getward() {
        JsonObject obj = RequestData.getward();
        progressDialog.show();
        CountryService countryService = new CountryService();
        countryService.getAPI().getwardno(obj).enqueue(new Callback<List<WardNoModel>>() {
            @Override
            public void onResponse(Call<List<WardNoModel>> call, Response<List<WardNoModel>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        wardNoModelArrayList = response.body();
                        for (int i = 0; i < wardNoModelArrayList.size(); i++) {
                            wardlist.add(wardNoModelArrayList.get(i).getWardNo());
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(TradeForm1Activity.this,
                                R.layout.my_spinner_style, wardlist);
                        wardnospinner.setAdapter(dataAdapter);
                        wardnospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                                String item = parent.getItemAtPosition(position).toString();
                                for (int i = 0; i < wardNoModelArrayList.size(); i++) {
                                    if (item.equals(wardNoModelArrayList.get(i).getWardNo())) {
                                        wardnoid = String.valueOf(wardNoModelArrayList.get(i).getWardID());
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        if (wardNoModelArrayList.size() > 0) {
                            for (int i = 0; i < wardNoModelArrayList.size(); i++) {
                                Log.d("testmatchtype", StaticData.twardno + "/" + wardNoModelArrayList.get(i).getWardID());
                                if (StaticData.twardno.trim().equals(wardNoModelArrayList.get(i).getWardID().toString())) {
                                    wardnospinner.setSelection(i);
                                    break;
                                }
                            }
                        }
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<List<WardNoModel>> call, Throwable t) {
                progressDialog.dismiss();
                getward();
            }
        });
    }

    public void gettypesofrelation() {

        JsonObject obj = RequestData.gettypesofland();
        progressDialog.show();
        CountryService countryService = new CountryService();
        countryService.getAPI().gettypesofland(obj).enqueue(new Callback<List<TypesOfLandModel>>() {
            @Override
            public void onResponse(Call<List<TypesOfLandModel>> call, Response<List<TypesOfLandModel>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        typesOfLandModelArrayList = response.body();
                        for (int i = 0; i < typesOfLandModelArrayList.size(); i++) {
                            typesofrelationlist.add(typesOfLandModelArrayList.get(i).getLandNature());
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(TradeForm1Activity.this,
                                R.layout.my_spinner_style, typesofrelationlist);
                        typesofrelationspinner.setAdapter(dataAdapter);
                        typesofrelationspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                                String item = parent.getItemAtPosition(position).toString();
                                for (int i = 0; i < typesOfLandModelArrayList.size(); i++) {
                                    if (item.equals(typesOfLandModelArrayList.get(i).getLandNature())) {
                                        typeofrelationid = String.valueOf(typesOfLandModelArrayList.get(i).getLandNatureId());
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        if (typesOfLandModelArrayList.size() > 0) {
                            for (int i = 0; i < typesOfLandModelArrayList.size(); i++) {
                                Log.d("testmatchtype", StaticData.typeofrelation + "/" + typesOfLandModelArrayList.get(i).getLandNatureId());
                                if (StaticData.typeofrelation.trim().equals(typesOfLandModelArrayList.get(i).getLandNatureId().toString())) {
                                    typesofrelationspinner.setSelection(i);
                                    break;
                                }
                            }
                        }

                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<List<TypesOfLandModel>> call, Throwable t) {
                progressDialog.dismiss();
                gettypesofrelation();
            }
        });
    }

}
