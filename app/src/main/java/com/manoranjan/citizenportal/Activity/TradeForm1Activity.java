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
import com.manoranjan.citizenportal.Response.RenewNatureoftradeREsponse;
import com.manoranjan.citizenportal.Response.RenewOwnerpartnerResponse;
import com.manoranjan.citizenportal.Response.SingleMyTadeLicense;
import com.manoranjan.citizenportal.model.TradeNatureModel;
import com.manoranjan.citizenportal.model.TypeofBusinessListModel;
import com.manoranjan.citizenportal.model.TypesOfBusinessModel;
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
    List<String> typeofbusinesslist = new ArrayList<>();
    List<TypesOfBusinessModel> typesOfBusinessModelarraylist = new ArrayList<>();
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2,radioButton3;
    String wardnoid = "1", typeofrelationid = "1",Holding_Req="Y";
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
        radioButton3=findViewById(R.id.radionontax);
        formnolayout = findViewById(R.id.formnolayout);
        formno = findViewById(R.id.formno);
        spinnerdata();

        getward();
        gettypesofrelation();
        gettypeofbusiness();
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatefiled()) {
                    StaticData.prevstat=false;

                    final String holdingno1 = holdingno.getText().toString().trim();
                    final String ownername1 = ownername.getText().toString().trim();
                    final String streetaddress1 = streetaddress.getText().toString().trim();
                    final String locality1 = locality.getText().toString().trim();
                    final String ammountpaidfortax1 = ammountpaidfortax.getText().toString().trim();

                    StaticData.tholdingno = holdingno1;
                    StaticData.tholding_req=Holding_Req;
                    StaticData.townername = ownername1;
                    StaticData.tstreetaddress = streetaddress1;
                    StaticData.tlocality = locality1;
                    StaticData.twardno = wardnoid;
                    StaticData.ttypeoftax = typeoftax;
                    StaticData.tammountoftax = ammountpaidfortax1;
                    StaticData.typeofrelation = typeofrelationid;
                    StaticData.tapplytype=applytype;
                    startActivity(new Intent(getApplicationContext(), TradeForm2Activity.class));
                }

            }
        });

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
                StaticData.regx_no = arg0.toString();
                if (arg0.toString().length() >= 1) {
                    getmytradelicense();
                    return;
                }
                //Toast.makeText(getApplicationContext(),"after text change",Toast.LENGTH_LONG).show();
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
                if (StaticData.prevstat==false) {
                    StaticData.tapplytype = applytype;
                }
                //Toast.makeText(TradeForm1Activity.this, item, Toast.LENGTH_SHORT).show();
                checktypeofapply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void checktypeofapply() {
        if (StaticData.prevstat==true &&StaticData.tapplytype.equals("Renew")) {
            applytypespinner.setSelection(1);
            formno.setHint(StaticData.regx_no);
        } else if (StaticData.prevstat==true &&StaticData.tapplytype.equals("New")) {
            applytypespinner.setSelection(0);
        }
        if (applytype.equals("Renew") && StaticData.prevstat==false) {
            formno.setHint(StaticData.regx_no);
            formnolayout.setVisibility(View.VISIBLE);
            holdingno.setEnabled(false);
            ownername.setEnabled(false);
            streetaddress.setEnabled(false);
            locality.setEnabled(false);
            ammountpaidfortax.setEnabled(false);
            typesofrelationspinner.setEnabled(false);
            wardnospinner.setEnabled(false);

          /*  StaticData.tholdingno = "";
            StaticData.townername = "";
            StaticData.tlocality = "";
            StaticData.tstreetaddress = "";
            StaticData.tammountoftax = "";
            StaticData.tlocality ="";
            StaticData.ttypeoftax = "";*/
            onResume();

        } else if (applytype.equals("Renew") && StaticData.prevstat==true) {
            formnolayout.setVisibility(View.VISIBLE);
            holdingno.setEnabled(false);
            ownername.setEnabled(false);
            streetaddress.setEnabled(false);
            locality.setEnabled(false);
            ammountpaidfortax.setEnabled(false);
            typesofrelationspinner.setEnabled(false);
            wardnospinner.setEnabled(false);
            StaticData.prevstat=false;
            onResume();
        } else if (applytype.equals("New") && StaticData.prevstat==true) {

            formnolayout.setVisibility(View.GONE);
            holdingno.setEnabled(true);
            ownername.setEnabled(true);
            streetaddress.setEnabled(true);
            locality.setEnabled(true);
            ammountpaidfortax.setEnabled(true);
            typesofrelationspinner.setEnabled(true);
            wardnospinner.setEnabled(true);
            StaticData.prevstat=false;
            onResume();
        }
        else if (applytype.equals("New") && StaticData.prevstat==false) {
            formnolayout.setVisibility(View.GONE);
            holdingno.setEnabled(true);
            ownername.setEnabled(true);
            streetaddress.setEnabled(true);
            locality.setEnabled(true);
            ammountpaidfortax.setEnabled(true);
            typesofrelationspinner.setEnabled(true);
            wardnospinner.setEnabled(true);


            StaticData.tholdingno = "";
            StaticData.townername = "";
            StaticData.tlocality = "";
            StaticData.tstreetaddress = "";
            StaticData.tammountoftax = "";
            StaticData.tlocality ="";
            StaticData.ttypeoftax = "";
            onResume();

        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        holdingno.setText(StaticData.tholdingno);
        ownername.setText(StaticData.townername);
        streetaddress.setText(StaticData.tstreetaddress);
        locality.setText(StaticData.tlocality);
        ammountpaidfortax.setText(StaticData.tammountoftax);
        //formno.setText(StaticData.regx_no);
        Log.d("testmatch", StaticData.ttypeoftax);
        if (StaticData.ttypeoftax.equals("Property Tax")) {
            radioButton3.setVisibility(View.GONE);
            radioButton1.setChecked(true);
        } else if (StaticData.ttypeoftax.equals("Occupier Tax")) {
            radioButton3.setVisibility(View.GONE);
            radioButton2.setChecked(true);
        }else{
            radioButton3.setVisibility(View.VISIBLE);
            radioButton3.setChecked(true);
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
            case R.id.radionontax:
                typeoftax = "Non Tax";
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


        if (Holding_Req.equals("Y") && TextUtils.isEmpty(holdingno1)) {
            holdingno.setError("Please Enter Holding No");
            return false;
        } else if (Holding_Req.equals("Y") && TextUtils.isEmpty(ownername1)) {
            ownername.setError("Please  Enter Owner Name");
            return false;
        } else if (TextUtils.isEmpty(streetaddress1)) {
            streetaddress.setError("Please Enter Address");
            return false;
        } else if (TextUtils.isEmpty(locality1)) {
            locality.setError("Enter Your Locality");
            return false;
        } else if (Holding_Req.equals("Y") && TextUtils.isEmpty(ammountpaidfortax1)) {
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
                                        Holding_Req=typesOfLandModelArrayList.get(i).getHoldingReq();
                                        if (Holding_Req.equals("Y")){
                                             holdingno.setEnabled(true);
                                             ownername.setEnabled(true);
                                             ammountpaidfortax.setEnabled(true);
                                             radioButton1.setChecked(true);
                                             radioButton3.setVisibility(View.GONE);
                                        }else if (Holding_Req.equals("N")){
                                            holdingno.setEnabled(false);
                                            ownername.setEnabled(false);
                                            ammountpaidfortax.setEnabled(false);
                                            radioButton3.setChecked(true);
                                            radioButton3.setVisibility(View.VISIBLE);
                                        }
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
                                    Holding_Req=typesOfLandModelArrayList.get(i).getHoldingReq();
                                    if (Holding_Req.equals("Y")){
                                        holdingno.setEnabled(true);
                                        ownername.setEnabled(true);
                                        ammountpaidfortax.setEnabled(true);
                                        radioButton1.setChecked(true);
                                        radioButton3.setVisibility(View.GONE);
                                    }else if (Holding_Req.equals("N")){
                                        holdingno.setEnabled(false);
                                        ownername.setEnabled(false);
                                        ammountpaidfortax.setEnabled(false);
                                        radioButton3.setChecked(true);
                                        radioButton3.setVisibility(View.VISIBLE);
                                    }
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
    public void gettypeofbusiness() {
        JsonObject obj = RequestData.gettypeofbusiness();
        progressDialog.show();
        CountryService countryService = new CountryService();
        countryService.getAPI().gettypeofbusiness(obj).enqueue(new Callback<List<TypesOfBusinessModel>>() {
            @Override
            public void onResponse(Call<List<TypesOfBusinessModel>> call, Response<List<TypesOfBusinessModel>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        typesOfBusinessModelarraylist = response.body();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<TypesOfBusinessModel>> call, Throwable t) {
                progressDialog.dismiss();
                gettypeofbusiness();

            }
        });
    }

    public void getmytradelicense() {
        progressDialog.show();
        JsonObject obj = RequestData.getrenewmytradelicense();
        CountryService countryService = new CountryService();
        countryService.getAPI().getsinglemytradelicense(obj).enqueue(new Callback<List<SingleMyTadeLicense>>() {
            @Override
            public void onResponse(Call<List<SingleMyTadeLicense>> call, Response<List<SingleMyTadeLicense>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        singleMyTadeLicense = response.body().get(0);
                        StaticData.tholdingno = singleMyTadeLicense.getAssesseeHoldingNo();
                        StaticData.townername = singleMyTadeLicense.getPropertyOwnerName();
                        StaticData.tlocality = singleMyTadeLicense.getLocalityName();
                        StaticData.tstreetaddress = singleMyTadeLicense.getStreetAddress();
                        StaticData.tammountoftax = String.valueOf(singleMyTadeLicense.getTaxPaidAmount());
                        StaticData.tlocality = singleMyTadeLicense.getLocalityName();
                        StaticData.ttypeoftax = singleMyTadeLicense.getTaxPaidType();
                        StaticData.tl_id= String.valueOf(singleMyTadeLicense.getAppTLID());
                        String item=singleMyTadeLicense.getLandNature();
                        String businessitem=singleMyTadeLicense.getTradeNature();

                        for (int i = 0; i < typesOfLandModelArrayList.size(); i++) {
                            if (item.equals(typesOfLandModelArrayList.get(i).getLandNature())) {
                                typeofrelationid = String.valueOf(typesOfLandModelArrayList.get(i).getLandNatureId());
                                StaticData.typeofrelation=typeofrelationid;
                                break;
                            }
                        }

                        if (typesOfLandModelArrayList.size() > 0) {
                            for (int i = 0; i < typesOfLandModelArrayList.size(); i++) {
                                Log.d("testmatchtype", StaticData.typeofrelation + "/" + typesOfLandModelArrayList.get(i).getLandNatureId());
                                if (StaticData.typeofrelation.trim().equals(typesOfLandModelArrayList.get(i).getLandNatureId().toString())) {
                                    typesofrelationspinner.setSelection(i);
                                    break;
                                }
                            }
                        }
                        if (typesOfBusinessModelarraylist.size() > 0) {
                            for (int i = 0; i < typesOfBusinessModelarraylist.size(); i++) {
                                if (businessitem.equals(typesOfBusinessModelarraylist.get(i).getTrade_Nature())) {
                                    StaticData.ttypeofbusinessid = String.valueOf(typesOfBusinessModelarraylist.get(i).getTrade_Nature_Id());
                                    Log.d("testypetypeof", StaticData.ttypeofbusinessid);

                                    break; }
                            }
                        }
                        getrenemytradelicense_ownerpartner();

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
                        Toast.makeText(getApplicationContext(), "Wrong Form No ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error 11", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SingleMyTadeLicense>> call, Throwable t) {
                progressDialog.dismiss();
               // Toast.makeText(getApplicationContext(), "error 1" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getrenemytradelicense_ownerpartner() {
        progressDialog.show();
        JsonObject obj = RequestData.getRenewSelect_Owner_Partner();
        CountryService countryService = new CountryService();
        countryService.getAPI().getrenewownerpartner(obj).enqueue(new Callback<List<RenewOwnerpartnerResponse>>() {
            @Override
            public void onResponse(Call<List<RenewOwnerpartnerResponse>> call, Response<List<RenewOwnerpartnerResponse>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        List<TypeofBusinessListModel> typeofBusinessListModelList = new ArrayList<>();
                        List<RenewOwnerpartnerResponse> singleownerpartner = response.body();
                        final String noofdirector1 = String.valueOf(singleownerpartner.size());
                        for(int i=0;i<singleownerpartner.size();i++) {
                            final String nameofdirector1 =singleownerpartner.get(i).getOwnerName();
                            final String fathersname1 = singleownerpartner.get(i).getSoDoWo();
                            final String address1 =singleownerpartner.get(i).getAddress();
                            final String contactno1 = singleownerpartner.get(i).getContactMob();
                            final String idproofno1 = singleownerpartner.get(i).getPan();
                            typeofBusinessListModelList.add(new TypeofBusinessListModel(noofdirector1, nameofdirector1, fathersname1, address1, contactno1, "Pan Card", idproofno1));
                        }
                        StaticData.typeofBusinessListModelList = typeofBusinessListModelList;
                        getrenemytradelicense_tradetype();

                        Log.d("sizetradelist",String.valueOf(typeofBusinessListModelList));
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Form No ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error 11", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RenewOwnerpartnerResponse>> call, Throwable t) {
                progressDialog.dismiss();
                // Toast.makeText(getApplicationContext(), "error 1" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getrenemytradelicense_tradetype() {
        progressDialog.show();
        JsonObject obj = RequestData.getRenewSelect_tradetype();
        CountryService countryService = new CountryService();
        countryService.getAPI().getrenewtradetype(obj).enqueue(new Callback<List<RenewNatureoftradeREsponse>>() {
            @Override
            public void onResponse(Call<List<RenewNatureoftradeREsponse>> call, Response<List<RenewNatureoftradeREsponse>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        List<TradeNatureModel> typeoftradeslist = new ArrayList<>();
                        List<RenewNatureoftradeREsponse> singletradetype = response.body();
                        for(int i=0;i<singletradetype.size();i++) {
                            final String Trade_size =singletradetype.get(i).getTrade_size();
                            final String Trade_Type = singletradetype.get(i).getTrade_Type();
                            final String TL_Form_Id =singletradetype.get(i).getTL_Form_Id();
                            typeoftradeslist.add(new TradeNatureModel(TL_Form_Id,  Trade_Type, Trade_size));
                        }
                        StaticData.tradeNatureModels = typeoftradeslist;
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Regx_No", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error 11", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RenewNatureoftradeREsponse>> call, Throwable t) {
                progressDialog.dismiss();
                // Toast.makeText(getApplicationContext(), "error 1" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
