package com.manoranjan.citizenportal.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Adaptor.OwnerofTradeAdaptor;
import com.manoranjan.citizenportal.Adaptor.natureofTradeAdaptor;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.FortheYearModel;
import com.manoranjan.citizenportal.model.NatureoftradeModel;
import com.manoranjan.citizenportal.model.TradeNatureModel;
import com.manoranjan.citizenportal.model.TypeofBusinessListModel;
import com.manoranjan.citizenportal.model.TypesOfBusinessModel;
import com.manoranjan.citizenportal.service.CountryService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TradeForm2Activity extends AppCompatActivity {
    Spinner businesstype, tradetype;
    String businessid, fortheyearid, tradetypeid;
    List<String> natureoftrade = new ArrayList<>();
    private int mYear, mMonth, mDay;
    TextView Dateofcommen;
    List<TradeNatureModel> tradeNatureModelList = new ArrayList<>();
    List<TypeofBusinessListModel> typeofBusinessListModelList = new ArrayList<>();
    RecyclerView tradetyperecycle, ownerrecycler;
    natureofTradeAdaptor schedulelistAdaptor;
    OwnerofTradeAdaptor ownerofTradeAdaptor;
    ProgressDialog progressDialog;
    List<String> typeofbusinesslist = new ArrayList<>();
    List<TypesOfBusinessModel> typesOfBusinessModelarraylist = new ArrayList<>();
    List<String> fortheyearlist = new ArrayList<>();
    List<FortheYearModel> fortheYearModelArrayList = new ArrayList<>();
    Spinner cardspinner, fortheyearspinner;
    String[] cardtypes = {"PAN CARD"};

    TextInputLayout totaldirectorlayout;
    EditText noofdirector, nameofdirector, fathersname, address, contactno, idproofno;
    EditText nameofrirm, investmentofcapital, gstin, companypancard, contactnofirst, contactaddress, workshopaddress, godownaddress;
    Button add;
    LinearLayout rlayoutforowner;

    Pattern patternforpan,patternforgst;
     AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_form2);
        patternforpan = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
        patternforgst=Pattern.compile("^([0][1-9]|[1-2][0-9]|[3][0-7])([a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9a-zA-Z]{1}[zZ]{1}[0-9a-zA-Z]{1})+$");
        progressDialog = new ProgressDialog(TradeForm2Activity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        businesstype = findViewById(R.id.typeofbusiness);
        tradetype = findViewById(R.id.tradetype);
        add = findViewById(R.id.btn_add);
        rlayoutforowner = findViewById(R.id.rlayoutforowner);

        cardspinner = findViewById(R.id.idtype);
        fortheyearspinner = findViewById(R.id.fortheyear);
        Dateofcommen = findViewById(R.id.Dateofcommen);
        Dateofcommen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }
        });

        totaldirectorlayout = findViewById(R.id.totaldirectorlayout);
        noofdirector = findViewById(R.id.noofdirector);
        nameofdirector = findViewById(R.id.nameofdirector);
        fathersname = findViewById(R.id.fathersname);
        address = findViewById(R.id.address);
        contactno = findViewById(R.id.contactno);
        idproofno = findViewById(R.id.idproofno);
        //EditText nameofrirm,investmentofcapital,gstin,companypancard,contactnofirst,contactaddress,workshopaddress,godownaddress;
        nameofrirm = findViewById(R.id.nameofrirm);
        investmentofcapital = findViewById(R.id.investmentofcapital);
        gstin = findViewById(R.id.gstin);
        companypancard = findViewById(R.id.companypancard);
        contactnofirst = findViewById(R.id.contactnofirst);
        contactaddress = findViewById(R.id.contactaddress);
        workshopaddress = findViewById(R.id.workshopaddress);
        godownaddress = findViewById(R.id.godownaddress);
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        gettypeofbusiness();
        getfortheyear();
        getnatureoftrade();
        spinnerdata();

        //recycler for tradetypelist
        tradetyperecycle = findViewById(R.id.tradelist);
        tradetyperecycle.setHasFixedSize(true);
        tradetyperecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ownerrecycler = findViewById(R.id.ownerlist);
        ownerrecycler.addItemDecoration(new DividerItemDecoration(TradeForm2Activity.this, DividerItemDecoration.VERTICAL));
        ownerrecycler.setHasFixedSize(true);
        ownerrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        if(StaticData.tapplytype.equals("Renew")){
            actv.setEnabled(false);
            schedulelistAdaptor = new natureofTradeAdaptor(getApplicationContext(), StaticData.tradeNatureModels);
            tradetyperecycle.setAdapter(schedulelistAdaptor);
        }else{
            actv.setEnabled(true);
            schedulelistAdaptor = new natureofTradeAdaptor(getApplicationContext(), tradeNatureModelList);
            tradetyperecycle.setAdapter(schedulelistAdaptor);
        }
        schedulelistAdaptor.setonItemClickListner(new natureofTradeAdaptor.OnitemClickListner() {
            @Override
            public void onDeleteClick(int position) {
                if (StaticData.tapplytype.equals("New")) {
                    if (tradeNatureModelList.size() <= 1) {
                        tradeNatureModelList.clear();
                    } else {
                        tradeNatureModelList.remove(position);
                    }
                    schedulelistAdaptor.notifyDataSetChanged();
                }
            }
        });

        ownerofTradeAdaptor = new OwnerofTradeAdaptor(getApplicationContext(), typeofBusinessListModelList);
        ownerrecycler.setAdapter(ownerofTradeAdaptor);
        ownerofTradeAdaptor.setonItemClickListner(new OwnerofTradeAdaptor.OnitemClickListner() {
            @Override
            public void onDeleteClick(int position) {
                if (typeofBusinessListModelList.size() <= 1) {
                    typeofBusinessListModelList.clear();
                } else {
                    typeofBusinessListModelList.remove(position);
                }
                ownerofTradeAdaptor.notifyDataSetChanged();
            }
        });
        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.prevstat = true;
                startActivity(new Intent(getApplicationContext(), TradeForm1Activity.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatefiledforbottmform()) {
                    boolean a = true;
                    final String noofdirector1 = noofdirector.getText().toString().trim();
                    if (typeofBusinessListModelList.size()<Integer.parseInt(noofdirector1)) {
                        final String nameofdirector1 = nameofdirector.getText().toString().trim();
                        final String fathersname1 = fathersname.getText().toString().trim();
                        final String address1 = address.getText().toString().trim();
                        final String contactno1 = contactno.getText().toString().trim();
                        final String idproofno1 = idproofno.getText().toString().trim();
                        for (int i = 0; i < typeofBusinessListModelList.size(); i++) {
                            String pan = typeofBusinessListModelList.get(i).getIdproofno();
                            if (pan.equals(idproofno1)) {
                                a = false;
                                idproofno.setError("Pan No already added");
                                break;
                            }
                        }
                        if (a) {
                            typeofBusinessListModelList.add(new TypeofBusinessListModel(noofdirector1, nameofdirector1, fathersname1, address1, contactno1, "Pan Card", idproofno1));
                        }// Toast.makeText(TradeForm2Activity.this, "Added", Toast.LENGTH_SHORT).show();
                        ownerofTradeAdaptor.notifyDataSetChanged();
                    }else{
                        Toast.makeText(TradeForm2Activity.this, "Please Enter "+noofdirector1 +" Partner or Director", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (businessid.equals("3") || businessid.equals("6") || businessid.equals("4") || businessid.equals("5")) {
                    if (validatefiled() &&typeofBusinessListModelList.size()>0){
                        final String noofdirector1 = noofdirector.getText().toString().trim();
                        if (Integer.parseInt(noofdirector1) ==typeofBusinessListModelList.size()) {
                            final String nameofrirm1 = nameofrirm.getText().toString().trim();
                            final String investmentofcapital1 = investmentofcapital.getText().toString().trim();
                            final String gstin1 = gstin.getText().toString().trim();
                            final String companypancard1 = companypancard.getText().toString().trim();
                            final String contactnofirst1 = contactnofirst.getText().toString().trim();
                            final String contactaddress1 = contactaddress.getText().toString().trim();
                            final String workshopaddress1 = workshopaddress.getText().toString().trim();
                            final String godownaddress1 = godownaddress.getText().toString().trim();

                            StaticData.ttypeofbusinessid = businessid;
                            StaticData.tfortheyearid = fortheyearid;
                            StaticData.tnameoffirm = nameofrirm1;
                            StaticData.tgstin = gstin1;
                            StaticData.tdateofcommenence = Dateofcommen.getText().toString();
                            StaticData.tinvestmentcapital = investmentofcapital1;
                            StaticData.ttradetype = tradetypeid;
                            StaticData.tcompanypancard = companypancard1;
                            StaticData.tcontactnofirst = contactnofirst1;
                            StaticData.tcontactaddress = contactaddress1;
                            StaticData.tworkshopaddress = workshopaddress1;
                            StaticData.tgodownaddress = godownaddress1;

                            StaticData.typeofBusinessListModelList = typeofBusinessListModelList;
                            StaticData.tradeNatureModels = tradeNatureModelList;

                            Log.d("sizeoflist", String.valueOf(StaticData.typeofBusinessListModelList.size()));
                            startActivity(new Intent(getApplicationContext(), TradeForm3ctivity.class));
                        }else{
                            Toast.makeText(TradeForm2Activity.this, "Please Enter "+noofdirector1 +" Partner or Director", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(TradeForm2Activity.this, "sizeerror", Toast.LENGTH_SHORT).show();
                    }
                }
                if (businessid.equals("1") || businessid.equals("2")) {
                    if (validatefiled() && validatefiledforbottmform()) {
                        // public static String ttypeofbusinessid,tfortheyearid,tnameoffirm,tdateofcommenence,tinvestmentcapital,
                        //            ttradetype,tgstin,tcompanypancard,tcontactnofirst,tcontactaddress,tworkshopaddress,tgodownaddress;
                        final String nameofrirm1 = nameofrirm.getText().toString().trim();
                        final String investmentofcapital1 = investmentofcapital.getText().toString().trim();
                        final String gstin1 = gstin.getText().toString().trim();
                        final String companypancard1 = companypancard.getText().toString().trim();
                        final String contactnofirst1 = contactnofirst.getText().toString().trim();
                        final String contactaddress1 = contactaddress.getText().toString().trim();
                        final String workshopaddress1 = workshopaddress.getText().toString().trim();
                        final String godownaddress1 = godownaddress.getText().toString().trim();

                        StaticData.ttypeofbusinessid = businessid;
                        StaticData.tfortheyearid = fortheyearid;
                        StaticData.tnameoffirm = nameofrirm1;
                        StaticData.tgstin = gstin1;
                        StaticData.tdateofcommenence = Dateofcommen.getText().toString();
                        StaticData.tinvestmentcapital = investmentofcapital1;
                        StaticData.ttradetype = tradetypeid;
                        StaticData.tcompanypancard = companypancard1;
                        StaticData.tcontactnofirst = contactnofirst1;
                        StaticData.tcontactaddress = contactaddress1;
                        StaticData.tworkshopaddress = workshopaddress1;
                        StaticData.tgodownaddress = godownaddress1;

                        final String noofdirector1 = noofdirector.getText().toString().trim();
                        final String nameofdirector1 = nameofdirector.getText().toString().trim();
                        final String fathersname1 = fathersname.getText().toString().trim();
                        final String address1 = address.getText().toString().trim();
                        final String contactno1 = contactno.getText().toString().trim();
                        final String idproofno1 = idproofno.getText().toString().trim();
                        typeofBusinessListModelList.add(new TypeofBusinessListModel("1", nameofdirector1, fathersname1, address1, contactno1, "Pan Card", idproofno1));


                        StaticData.typeofBusinessListModelList = typeofBusinessListModelList;
                        StaticData.tradeNatureModels = tradeNatureModelList;

                        Log.d("sizeoflist", String.valueOf(StaticData.typeofBusinessListModelList.size()));

                        startActivity(new Intent(getApplicationContext(), TradeForm3ctivity.class));

                    }
                }
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tradeNatureModelList.add(new TradeNatureModel("0", actv.getText().toString(), tradetypeid));
                actv.setText("");
                schedulelistAdaptor.notifyDataSetChanged();
                // Toast.makeText(TradeForm2Activity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void spinnerdata() {

        List<String> tradetypes = new ArrayList<String>();
        tradetypes.add("Small");
        tradetypes.add("Medium");
        tradetypes.add("Large");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(TradeForm2Activity.this,
                R.layout.my_spinner_style, tradetypes);
        // attaching data adapter to spinner
        tradetype.setAdapter(dataAdapter1);
        tradetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                tradetypeid = item.trim();
                // Showing selected spinner item
                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter ad = new ArrayAdapter(this, R.layout.my_spinner_style, cardtypes);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        cardspinner.setAdapter(ad);
        cardspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StaticData.fcardtype = cardtypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        nameofrirm.setText(StaticData.tnameoffirm);
        investmentofcapital.setText(StaticData.tinvestmentcapital);
        gstin.setText(StaticData.tgstin);
        companypancard.setText(StaticData.tcompanypancard);
        contactnofirst.setText(StaticData.tcontactnofirst);
        contactaddress.setText(StaticData.tcontactaddress);
        workshopaddress.setText(StaticData.tworkshopaddress);
        godownaddress.setText(StaticData.tgodownaddress);
        Dateofcommen.setText(StaticData.tdateofcommenence);
        businessid=StaticData.ttypeofbusinessid;


        if (StaticData.tapplytype.equals("New") &&StaticData.tradeNatureModels != null && StaticData.tradeNatureModels.size() > 0) {
            tradeNatureModelList.clear();
            Log.d("sizeoftrade", String.valueOf(StaticData.tradeNatureModels.size()));
            tradeNatureModelList = StaticData.tradeNatureModels;
            schedulelistAdaptor = new natureofTradeAdaptor(getApplicationContext(), tradeNatureModelList);
            tradetyperecycle.setAdapter(schedulelistAdaptor);
            schedulelistAdaptor.setonItemClickListner(new natureofTradeAdaptor.OnitemClickListner() {
                @Override
                public void onDeleteClick(int position) {
                    Log.d("sizeoftrade", String.valueOf(StaticData.tradeNatureModels.size()));
                     if (tradeNatureModelList.size() <= 1) {
                        tradeNatureModelList.clear();
                    } else {
                        tradeNatureModelList.remove(position);
                    }
                    schedulelistAdaptor.notifyDataSetChanged();
                }
            });
        }
        if (StaticData.tapplytype.equals("Renew") &&StaticData.tradeNatureModels != null && StaticData.tradeNatureModels.size() > 0) {
            Log.d("sizeoftraderesume", String.valueOf(StaticData.tradeNatureModels.size()));
            schedulelistAdaptor = new natureofTradeAdaptor(getApplicationContext(),  StaticData.tradeNatureModels);
            tradetyperecycle.setAdapter(schedulelistAdaptor);
            schedulelistAdaptor.notifyDataSetChanged();
            tradeNatureModelList = StaticData.tradeNatureModels;

        }

        typesOfBusinessModelarraylist.clear();
        if (StaticData.typeofBusinessListModelList != null && StaticData.typeofBusinessListModelList.size() > 0) {
            Log.d("sizeoftrade", String.valueOf(StaticData.typeofBusinessListModelList.size()));
            typeofBusinessListModelList = StaticData.typeofBusinessListModelList;
            ownerofTradeAdaptor = new OwnerofTradeAdaptor(getApplicationContext(), typeofBusinessListModelList);
            ownerrecycler.setAdapter(ownerofTradeAdaptor);
            ownerofTradeAdaptor.setonItemClickListner(new OwnerofTradeAdaptor.OnitemClickListner() {
                @Override
                public void onDeleteClick(int position) {
                    if (typeofBusinessListModelList.size() <= 1) {
                        typeofBusinessListModelList.clear();
                    } else {
                        typeofBusinessListModelList.remove(position);
                    }
                    ownerofTradeAdaptor.notifyDataSetChanged();
                }
            });
        }

        if (businessid.equals("1") || businessid.equals("2")) {
            if (StaticData.typeofBusinessListModelList!=null &&StaticData.typeofBusinessListModelList.size()>0) {
                nameofdirector.setText(StaticData.typeofBusinessListModelList.get(0).getName());
                fathersname.setText(StaticData.typeofBusinessListModelList.get(0).getFathername());
                address.setText(StaticData.typeofBusinessListModelList.get(0).getAddress());
                contactno.setText(StaticData.typeofBusinessListModelList.get(0).getContactno());
                idproofno.setText(StaticData.typeofBusinessListModelList.get(0).getIdproofno());
            }
        }else if(StaticData.tapplytype.equals("Renew") &&(!businessid.equals("1") ||! businessid.equals("2"))){
            if (StaticData.typeofBusinessListModelList!=null &&StaticData.typeofBusinessListModelList.size()>0) {
                noofdirector.setText(String.valueOf(StaticData.typeofBusinessListModelList.size()));
                nameofdirector.setText(StaticData.typeofBusinessListModelList.get(0).getName());
                fathersname.setText(StaticData.typeofBusinessListModelList.get(0).getFathername());
                address.setText(StaticData.typeofBusinessListModelList.get(0).getAddress());
                contactno.setText(StaticData.typeofBusinessListModelList.get(0).getContactno());
                idproofno.setText(StaticData.typeofBusinessListModelList.get(0).getIdproofno());
            }
        }
    }

    void datepicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Dateofcommen.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        StaticData.fdob = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
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
                        for (int i = 0; i < typesOfBusinessModelarraylist.size(); i++) {
                            typeofbusinesslist.add(typesOfBusinessModelarraylist.get(i).getTrade_Nature());
                        }
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(TradeForm2Activity.this,
                                R.layout.my_spinner_style, typeofbusinesslist);
                        businesstype.setAdapter(dataAdapter);
                        businesstype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                                String item = parent.getItemAtPosition(position).toString();
                                for (int i = 0; i < typesOfBusinessModelarraylist.size(); i++) {
                                    if (item.equals(typesOfBusinessModelarraylist.get(i).getTrade_Nature())) {
                                        businessid = String.valueOf(typesOfBusinessModelarraylist.get(i).getTrade_Nature_Id());
                                        break;
                                    }
                                }
                                checkformtype();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        if (typesOfBusinessModelarraylist.size() > 0) {
                            for (int i = 0; i < typesOfBusinessModelarraylist.size(); i++) {
                                Log.d("testmatchtype", StaticData.ttypeofbusinessid + "/" + typesOfBusinessModelarraylist.get(i).getTrade_Nature_Id());
                                if (StaticData.ttypeofbusinessid.trim().equals(typesOfBusinessModelarraylist.get(i).getTrade_Nature_Id().toString())) {
                                    businesstype.setSelection(i);
                                    if (StaticData.tapplytype.equals("Renew")){
                                        onResume();
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
            public void onFailure(Call<List<TypesOfBusinessModel>> call, Throwable t) {
                progressDialog.dismiss();
                gettypeofbusiness();
            }
        });
    }

    public boolean validatefiled() {
        //EditText nameofrirm,investmentofcapital,gstin,companypancard,contactnofirst,contactaddress,workshopaddress,godownaddress;
        final String nameofrirm1 = nameofrirm.getText().toString().trim();
        final String investmentofcapital1 = investmentofcapital.getText().toString().trim();
        final String gstin1 = gstin.getText().toString().trim();
        final String companypancard1 = companypancard.getText().toString().trim();
        final String contactnofirst1 = contactnofirst.getText().toString().trim();
        final String contactaddress1 = contactaddress.getText().toString().trim();
        final String workshopaddress1 = workshopaddress.getText().toString().trim();
        final String godownaddress1 = godownaddress.getText().toString().trim();
        Matcher matcher = patternforgst.matcher(gstin1);
        Matcher matcherpan = patternforpan.matcher(companypancard1);

        if (TextUtils.isEmpty(nameofrirm1)) {
            nameofrirm.setError("Please Enter Name of firm/Company");
            return false;
        } else if (TextUtils.isEmpty(investmentofcapital1)) {
            investmentofcapital.setError("Please  Enter Investment of Capital");
            return false;
        } else if (!TextUtils.isEmpty(gstin1) &&!matcher.matches()) {
            gstin.setError("Please Enter Valid GST IN");
            return false;
        } else if (TextUtils.isEmpty(companypancard1)) {
            companypancard.setError("Enter Company Pan Card Number");
            return false;
        } else if (!matcherpan.matches()) {
            companypancard.setError("Please Enter Valid Pan Number");
            return false;
        }else if (TextUtils.isEmpty(contactnofirst1)) {
            contactnofirst.setError("Enter Your Contact No ");
            return false;
            // //EditText contactaddress,workshopaddress,godownaddress;
        } else if (contactnofirst1.length() < 10) {
            contactnofirst.setError("Please Enter Correct No");
            return false;
        } else if (TextUtils.isEmpty(contactaddress1)) {
            contactaddress.setError("Please Enter Contact Address");
            return false;
        } /*else if (TextUtils.isEmpty(workshopaddress1)) {
            workshopaddress.setError("Please  Enter Workshop Address");
            return false;
        } else if (TextUtils.isEmpty(godownaddress1)) {
            godownaddress.setError("Please Enter Godown Address");
            return false;
        } */else if (tradeNatureModelList.size() == 0) {
            Toast.makeText(this, "Add atleat one Nature of trade", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean validatefiledforbottmform() {
        // EditText noofdirector,nameofdirector,fathersname,address,contactno,idproofno;
        final String noofdirector1 = noofdirector.getText().toString().trim();
        final String nameofdirector1 = nameofdirector.getText().toString().trim();
        final String fathersname1 = fathersname.getText().toString().trim();
        final String address1 = address.getText().toString().trim();
        final String contactno1 = contactno.getText().toString().trim();
        final String idproofno1 = idproofno.getText().toString().trim();
        Matcher matcher = patternforpan.matcher(idproofno1);
       /* if ((!businessid.equals("1") ||!businessid.equals("2")) &&TextUtils.isEmpty(noofdirector1)) {
            noofdirector.setError("Please Enter No");
            return false;
        } else*/
        if (TextUtils.isEmpty(nameofdirector1)) {
            nameofdirector.setError("Please  Enter name");
            return false;
        } else if (TextUtils.isEmpty(fathersname1)) {
            fathersname.setError("Please Enter Father/Husband Name");
            return false;
        } else if (TextUtils.isEmpty(address1)) {
            address.setError("Enter Address");
            return false;
        } else if (TextUtils.isEmpty(contactno1)) {
            contactno.setError("Enter Your Contact No ");
            return false;
            // //EditText contactaddress,workshopaddress,godownaddress;
        } else if (contactno1.length() < 10) {
            contactno.setError("Please Enter Correct No");
            return false;
        } else if (TextUtils.isEmpty(idproofno1)) {
            idproofno.setError("Please Enter Pan No");
            return false;
        }else if (!matcher.matches()) {
            idproofno.setError("Please Enter Valid Pan No");
            return false;
        }
        return true;
    }

    private void checkformtype() {
        noofdirector.setHint("");
        nameofdirector.setHint("");
        // TextInputLayout totaldirectorlayout;
        //    EditText noofdirector,nameofdirector,fathersname,address,contactno,idproofno;
        if (businessid.equals("1") || businessid.equals("2")) {
            add.setVisibility(View.GONE);
            rlayoutforowner.setVisibility(View.GONE);
            noofdirector.setHint("");
            nameofdirector.setHint("");
            totaldirectorlayout.setVisibility(View.GONE);
            nameofdirector.setHint("Name of Proprietor");
            return;
        } else if (businessid.equals("3") || businessid.equals("6")) {
            noofdirector.setHint("");
            nameofdirector.setHint("");
            noofdirector.setHint("No of partners");
            totaldirectorlayout.setVisibility(View.VISIBLE);
            add.setVisibility(View.VISIBLE);
            rlayoutforowner.setVisibility(View.VISIBLE);
            nameofdirector.setHint("Name of partner");
            return;
        } else if (businessid.equals("4") || businessid.equals("5")) {
            noofdirector.setHint("");
            nameofdirector.setHint("");
            noofdirector.setHint("No of director");
            totaldirectorlayout.setVisibility(View.VISIBLE);
            add.setVisibility(View.VISIBLE);
            rlayoutforowner.setVisibility(View.VISIBLE);
            nameofdirector.setHint("Name of director");
            return;
        }
    }

    public void getfortheyear() {
        JsonObject obj = RequestData.getfortheyear();
        progressDialog.show();
        CountryService countryService = new CountryService();
        countryService.getAPI().getfortheyear(obj).enqueue(new Callback<List<FortheYearModel>>() {
            @Override
            public void onResponse(Call<List<FortheYearModel>> call, Response<List<FortheYearModel>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        fortheYearModelArrayList = response.body();
                        for (int i = 0; i < fortheYearModelArrayList.size(); i++) {
                            fortheyearlist.add(fortheYearModelArrayList.get(i).getFin_Year_Name());
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(TradeForm2Activity.this,
                                R.layout.my_spinner_style, fortheyearlist);
                        fortheyearspinner.setAdapter(dataAdapter);
                        fortheyearspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                                String item = parent.getItemAtPosition(position).toString();
                                for (int i = 0; i < fortheYearModelArrayList.size(); i++) {
                                    if (item.equals(fortheYearModelArrayList.get(i).getFin_Year_Name())) {
                                        fortheyearid = String.valueOf(fortheYearModelArrayList.get(i).getFin_Year_ID());
                                        Dateofcommen.setText("1-4-20"+item.substring(0,2));
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        if (fortheYearModelArrayList.size() > 0) {
                            for (int i = 0; i < fortheYearModelArrayList.size(); i++) {
                                Log.d("testmatchtype", StaticData.tfortheyearid + "/" + fortheYearModelArrayList.get(i).getFin_Year_ID());
                                if (StaticData.tfortheyearid.trim().equals(fortheYearModelArrayList.get(i).getFin_Year_ID().toString())) {
                                    fortheyearspinner.setSelection(i);
                                    Dateofcommen.setText("1-4-20"+fortheYearModelArrayList.get(i).getFin_Year_Name().substring(0,2));
                                    break;
                                }
                            }
                            if (StaticData.tapplytype.equals("Renew")){
                                fortheyearspinner.setSelection(0);
                            }
                        }
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<List<FortheYearModel>> call, Throwable t) {
                progressDialog.dismiss();
                getfortheyear();
            }
        });
    }

    public void getnatureoftrade() {
        JsonObject obj = RequestData.getnatureoftrade();
        progressDialog.show();
        CountryService countryService = new CountryService();
        countryService.getAPI().getnatureoftrade(obj).enqueue(new Callback<List<NatureoftradeModel>>() {
            @Override
            public void onResponse(Call<List<NatureoftradeModel>> call, Response<List<NatureoftradeModel>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        //fortheYearModelArrayList=response.body();
                        List<NatureoftradeModel> natureoftradeModels = response.body();
                        for (int i = 0; i < natureoftradeModels.size(); i++) {
                            natureoftrade.add(natureoftradeModels.get(i).getTrade_Type());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (TradeForm2Activity.this, R.layout.my_spinner_style, natureoftrade);
                        actv.setThreshold(1);//will start working from first character
                        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<List<NatureoftradeModel>> call, Throwable t) {
                progressDialog.dismiss();
                getfortheyear();
            }
        });
    }
}
