package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Adaptor.OwnerofTradeAdaptor;
import com.manoranjan.citizenportal.Adaptor.SingleTradeTypesforForm4Adaptor;
import com.manoranjan.citizenportal.Adaptor.natureofTradeAdaptor;
import com.manoranjan.citizenportal.Api.ApiLinks;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.InsertTLResponse;
import com.manoranjan.citizenportal.Response.UpdateProfile;
import com.manoranjan.citizenportal.model.FileUploadModel;
import com.manoranjan.citizenportal.model.FilesNamepathListModel;
import com.manoranjan.citizenportal.model.FortheYearModel;
import com.manoranjan.citizenportal.model.NatureoftradeModel;
import com.manoranjan.citizenportal.model.TradeNatureModel;
import com.manoranjan.citizenportal.model.TypeofBusinessListModel;
import com.manoranjan.citizenportal.model.TypesOfBusinessModel;
import com.manoranjan.citizenportal.model.TypesOfLandModel;
import com.manoranjan.citizenportal.model.WardNoModel;
import com.manoranjan.citizenportal.service.CountryService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

public class TradeForm4ctivity extends AppCompatActivity {
String radioyesno="0";
    EditText holdingno,ownername,streetaddress,locality,ammountpaidfortax;
    ProgressDialog progressDialog;
    Spinner wardnospinner,typesofrelationspinner,businesstype,tradetype;
    List<String> wardlist=new ArrayList<>();
    List<String> typesofrelationlist=new ArrayList<>();
    List<WardNoModel> wardNoModelArrayList=new ArrayList<>();
    List<TypesOfLandModel> typesOfLandModelArrayList=new ArrayList<>();

    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2;

    String wardnoid="1",typeofrelationid="1";
    String typeoftax="Property Tax";
    String businessid,fortheyearid,tradetypeid;


    List<String> natureoftrade=new ArrayList<>();
    private int mYear, mMonth, mDay;
    TextView Dateofcommen;
    List<TradeNatureModel>  tradeNatureModelList=new ArrayList<>();
    RecyclerView tradetyperecycle,ownerrecycler;
    SingleTradeTypesforForm4Adaptor singleTradeTypesforForm4Adaptor;
    OwnerofTradeAdaptor ownerofTradeAdaptor;

    List<String> typeofbusinesslist=new ArrayList<>();
    List<TypesOfBusinessModel> typesOfBusinessModelarraylist=new ArrayList<>();
    List<String> fortheyearlist=new ArrayList<>();
    List<FortheYearModel> fortheYearModelArrayList=new ArrayList<>();
    Spinner fortheyearspinner;
    String[] cardtypes = {"PAN CARD"};
    TextInputLayout totaldirectorlayout;
    EditText nameofrirm,investmentofcapital,gstin,companypancard,contactnofirst,contactaddress,workshopaddress,godownaddress;
    EditText noofdirector, nameofdirector, fathersname, address, contactno, idproofno;

    List<TypeofBusinessListModel> typeofBusinessListModelList=new ArrayList<>();

    ImageView img1, img2,img3,img4,img5;
    String  path1 = "", path2 = "",path3 = "",path4 = "",path5 = "";
    Bitmap bitmap = null, bitmap2 = null;
    List<FilesNamepathListModel> filesNamepathListModels=new ArrayList<>();
    List<FilesNamepathListModel> filesNamepathListModelsnew=new ArrayList<>();
    LinearLayout rlayoutforowner,rlayoutforpartner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_form4ctivity);

        progressDialog=new ProgressDialog(TradeForm4ctivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        progressDialog=new ProgressDialog(TradeForm4ctivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        wardnospinner=findViewById(R.id.wardno);
        typesofrelationspinner=findViewById(R.id.typesofrelation);
        holdingno=findViewById(R.id.holdingno);
        ownername=findViewById(R.id.ownername);
        streetaddress=findViewById(R.id.streetaddress);
        locality=findViewById(R.id.locality);
        ammountpaidfortax=findViewById(R.id.ammountpaidfortax);
        radioGroup=findViewById(R.id.radioGroup);
        radioButton1=findViewById(R.id.radioyes);
        radioButton2=findViewById(R.id.radiono);
        businesstype=findViewById(R.id.typeofbusiness);
        tradetype=findViewById(R.id.tradetype);

        rlayoutforowner = findViewById(R.id.rlayoutforowner);
        rlayoutforpartner = findViewById(R.id.rlayoutforpartner);
        noofdirector = findViewById(R.id.noofdirector);
        nameofdirector = findViewById(R.id.nameofdirector);
        fathersname = findViewById(R.id.fathersname);
        address = findViewById(R.id.address);
        contactno = findViewById(R.id.contactno);
        idproofno = findViewById(R.id.idproofno);


        fortheyearspinner =findViewById(R.id.fortheyear);
        Dateofcommen=findViewById(R.id.Dateofcommen);
        Dateofcommen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }
        });

        //EditText nameofrirm,investmentofcapital,gstin,companypancard,contactnofirst,contactaddress,workshopaddress,godownaddress;
        nameofrirm=findViewById(R.id.nameofrirm);
        investmentofcapital=findViewById(R.id.investmentofcapital);
        gstin=findViewById(R.id.gstin);
        companypancard=findViewById(R.id.companypancard);
        contactnofirst=findViewById(R.id.contactnofirst);
        contactaddress=findViewById(R.id.contactaddress);
        workshopaddress=findViewById(R.id.workshopaddress);
        godownaddress=findViewById(R.id.godownaddress);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3= findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        //recycler for tradetypelist
        tradetyperecycle = findViewById(R.id.tradelist);
        tradetyperecycle.setHasFixedSize(true);
        tradetyperecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        ownerrecycler = findViewById(R.id.ownerlist);
        ownerrecycler.addItemDecoration(new DividerItemDecoration(TradeForm4ctivity.this, DividerItemDecoration.VERTICAL));
        ownerrecycler.setHasFixedSize(true);
        ownerrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ownerofTradeAdaptor = new OwnerofTradeAdaptor(getApplicationContext(), StaticData.typeofBusinessListModelList);
        ownerrecycler.setAdapter(ownerofTradeAdaptor);

        singleTradeTypesforForm4Adaptor = new SingleTradeTypesforForm4Adaptor(getApplicationContext(), tradeNatureModelList);
        tradetyperecycle.setAdapter(singleTradeTypesforForm4Adaptor);

        getward();
        gettypesofrelation();
        gettypeofbusiness();
        getfortheyear();
        getnatureoftrade();
        spinnerdata();

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startActivity(new Intent(getApplicationContext(),MainActivity.class));
                filesNamepathListModels.add(new FilesNamepathListModel("RENT AGREEMENT",path1));
                filesNamepathListModels.add(new FilesNamepathListModel("PROPERTY TAX RECIEPT",path2));
                filesNamepathListModels.add(new FilesNamepathListModel("FIRE LICENSE",path3));
                filesNamepathListModels.add(new FilesNamepathListModel("PARTNERSHIP DEED",path4));
                filesNamepathListModels.add(new FilesNamepathListModel("ASSOCIATION OF MEMORUNDUM",path5));

                List<File> files = new ArrayList<>();
                List<File> paths=StaticData.files;
                for (int i = 0; i < paths.size(); i++) {
                    if (!paths.get(i).equals("")) {
                        files.add(paths.get(i));
                    }
                }
                loadotherdetailsmulti(files);
               /* List<File> paths=StaticData.files;
                for (int i = 0; i < paths.size(); i++) {
                    if (!paths.get(i).getPath().equals("")) {
                        loadotherdetails(new File(paths.get(i).getPath()),i);
                    }
                }*/

                //submitdata();

                //Log.d("filestest",filesNamepathListModelsnew.toString());


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        wardnospinner.setEnabled(false);
        typesofrelationspinner.setEnabled(false);
        holdingno.setText(StaticData.tholdingno);
        ownername.setText(StaticData.townername);
        streetaddress.setText(StaticData.tstreetaddress);
        locality.setText(StaticData.tlocality);
        ammountpaidfortax.setText(StaticData.tammountoftax);
        Log.d("testmatch",StaticData.ttypeoftax);
        if (StaticData.ttypeoftax.equals("Property Tax")){
            radioButton1.setChecked(true);
        }else{
            radioButton2.setChecked(true);
        }
        nameofrirm.setText(StaticData.tnameoffirm);
        investmentofcapital.setText(StaticData.tinvestmentcapital);
        gstin.setText(StaticData.tgstin);
        companypancard.setText(StaticData.tcompanypancard);
        contactnofirst.setText(StaticData.tcontactnofirst);
        contactaddress.setText(StaticData.tcontactaddress);
        workshopaddress.setText(StaticData.tworkshopaddress);
        godownaddress.setText(StaticData.tgodownaddress);
        Dateofcommen.setText(StaticData.tdateofcommenence);
        tradeNatureModelList.clear();
        if (StaticData.tradeNatureModels!=null &&StaticData.tradeNatureModels.size()>0 ){
            Log.d("sizeoftrade", String.valueOf(StaticData.tradeNatureModels.size()));
            tradeNatureModelList=StaticData.tradeNatureModels;
            singleTradeTypesforForm4Adaptor = new SingleTradeTypesforForm4Adaptor(getApplicationContext(), tradeNatureModelList);
            tradetyperecycle.setAdapter(singleTradeTypesforForm4Adaptor);
        }
        if (StaticData.typeofBusinessListModelList != null && StaticData.typeofBusinessListModelList.size() > 0) {
            Log.d("sizeoftrade", String.valueOf(StaticData.typeofBusinessListModelList.size()));
            typeofBusinessListModelList = StaticData.typeofBusinessListModelList;
            ownerofTradeAdaptor = new OwnerofTradeAdaptor(getApplicationContext(), typeofBusinessListModelList);
            ownerrecycler.setAdapter(ownerofTradeAdaptor);
        }
        if (!StaticData.path1.isEmpty()){
            img1.setImageBitmap(StaticData.bitmap1);
        }
        if (!StaticData.path2.isEmpty()){
            img2.setImageBitmap(StaticData.bitmap2);
        }
        if (!StaticData.path3.isEmpty()){
            img3.setImageBitmap(StaticData.bitmap3);
        }
        if (!StaticData.path4.isEmpty()){
            img4.setImageBitmap(StaticData.bitmap4);
        }
        if (!StaticData.path5.isEmpty()){
            img5.setImageBitmap(StaticData.bitmap5);
        }
        if (StaticData.ttypeofbusinessid.equals("1") || StaticData.ttypeofbusinessid.equals("2")) {
            rlayoutforpartner.setVisibility(View.VISIBLE);
            rlayoutforowner.setVisibility(View.GONE);
            if (StaticData.typeofBusinessListModelList!=null &&StaticData.typeofBusinessListModelList.size()>0) {
                nameofdirector.setText(StaticData.typeofBusinessListModelList.get(0).getName());
                fathersname.setText(StaticData.typeofBusinessListModelList.get(0).getFathername());
                address.setText(StaticData.typeofBusinessListModelList.get(0).getAddress());
                contactno.setText(StaticData.typeofBusinessListModelList.get(0).getContactno());
                idproofno.setText(StaticData.typeofBusinessListModelList.get(0).getIdproof());
            }

        }else{
            rlayoutforowner.setVisibility(View.VISIBLE);
            rlayoutforpartner.setVisibility(View.GONE);
        }
    }
    public void radioclick(View view) {
        int checkedId=view.getId();
        switch(checkedId){
            case R.id.radioyes:
                radioyesno="1";
                break;
            case R.id.radiono:
                radioyesno="0";
                break;
        }
    }

    public void getward() {
        JsonObject obj= RequestData.getward();
        progressDialog.show();
        CountryService countryService=new CountryService();
        countryService.getAPI().getwardno(obj).enqueue(new Callback<List<WardNoModel>>() {
            @Override
            public void onResponse(Call<List<WardNoModel>> call, Response<List<WardNoModel>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body()!=null) {
                    if (response.body().size()>0){
                        wardNoModelArrayList=response.body();
                        for (int i=0;i<wardNoModelArrayList.size();i++){
                            wardlist.add(wardNoModelArrayList.get(i).getWardNo());
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(TradeForm4ctivity.this,
                                R.layout.my_spinner_style, wardlist);
                        wardnospinner.setAdapter(dataAdapter);
                        wardnospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                                String item = parent.getItemAtPosition(position).toString();
                                for (int i=0;i<wardNoModelArrayList.size();i++){
                                    if (item.equals(wardNoModelArrayList.get(i).getWardNo())){
                                        wardnoid= String.valueOf(wardNoModelArrayList.get(i).getWardID());
                                        break;
                                    }
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        if (wardNoModelArrayList.size()>0) {
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

        JsonObject obj= RequestData.gettypesofland();
        progressDialog.show();
        CountryService countryService=new CountryService();
        countryService.getAPI().gettypesofland(obj).enqueue(new Callback<List<TypesOfLandModel>>() {
            @Override
            public void onResponse(Call<List<TypesOfLandModel>> call, Response<List<TypesOfLandModel>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body()!=null) {
                    if (response.body().size()>0){
                        typesOfLandModelArrayList=response.body();
                        for (int i=0;i<typesOfLandModelArrayList.size();i++){
                            typesofrelationlist.add(typesOfLandModelArrayList.get(i).getLandNature());
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(TradeForm4ctivity.this,
                                R.layout.my_spinner_style, typesofrelationlist);
                        typesofrelationspinner.setAdapter(dataAdapter);
                        typesofrelationspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                                String item = parent.getItemAtPosition(position).toString();
                                for (int i=0;i<typesOfLandModelArrayList.size();i++){
                                    if (item.equals(typesOfLandModelArrayList.get(i).getLandNature())){
                                        typeofrelationid= String.valueOf(typesOfLandModelArrayList.get(i).getLandNatureId());
                                        break;
                                    }
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        if (typesOfLandModelArrayList.size()>0) {
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
    private void spinnerdata() {
        //
        List<String> tradetypes = new ArrayList<String>();
        tradetypes.add("Small");
        tradetypes.add("Medium");
        tradetypes.add("Large");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(TradeForm4ctivity.this,
                R.layout.my_spinner_style, tradetypes);
        // attaching data adapter to spinner
        tradetype.setAdapter(dataAdapter1);
        tradetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                tradetypeid = item.trim();
                // Showing selected spinner item
              //  Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       /* ArrayAdapter ad = new ArrayAdapter(this,R.layout.my_spinner_style,cardtypes);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        cardspinner.setAdapter(ad);
        cardspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StaticData.fcardtype=cardtypes[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/

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
                        Dateofcommen.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        StaticData.fdob=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
    }
    public void gettypeofbusiness() {
        JsonObject obj= RequestData.gettypeofbusiness();
        progressDialog.show();
        CountryService countryService=new CountryService();
        countryService.getAPI().gettypeofbusiness(obj).enqueue(new Callback<List<TypesOfBusinessModel>>() {
            @Override
            public void onResponse(Call<List<TypesOfBusinessModel>> call, Response<List<TypesOfBusinessModel>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body()!=null) {
                    if (response.body().size()>0){
                        typesOfBusinessModelarraylist=response.body();
                        for (int i=0;i<typesOfBusinessModelarraylist.size();i++){
                            typeofbusinesslist.add(typesOfBusinessModelarraylist.get(i).getTrade_Nature());
                        }
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(TradeForm4ctivity.this,
                                R.layout.my_spinner_style, typeofbusinesslist);
                        businesstype.setAdapter(dataAdapter);
                        businesstype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                                String item = parent.getItemAtPosition(position).toString();
                                for (int i=0;i<typesOfBusinessModelarraylist.size();i++){
                                    if (item.equals(typesOfBusinessModelarraylist.get(i).getTrade_Nature())){
                                        businessid= String.valueOf(typesOfBusinessModelarraylist.get(i).getTrade_Nature_Id());
                                        break;
                                    }
                                }
                                //checkformtype();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        if (typesOfBusinessModelarraylist.size()>0) {
                            for (int i = 0; i < typesOfBusinessModelarraylist.size(); i++) {
                                Log.d("testmatchtype", StaticData.ttypeofbusinessid + "/" + typesOfBusinessModelarraylist.get(i).getTrade_Nature_Id());
                                if (StaticData.ttypeofbusinessid.trim().equals(typesOfBusinessModelarraylist.get(i).getTrade_Nature_Id().toString())) {
                                    businesstype.setSelection(i);
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
    public void getfortheyear() {
        JsonObject obj= RequestData.getfortheyear();
        progressDialog.show();
        CountryService countryService=new CountryService();
        countryService.getAPI().getfortheyear(obj).enqueue(new Callback<List<FortheYearModel>>() {
            @Override
            public void onResponse(Call<List<FortheYearModel>> call, Response<List<FortheYearModel>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body()!=null) {
                    if (response.body().size()>0){
                        fortheYearModelArrayList=response.body();
                        for (int i=0;i<fortheYearModelArrayList.size();i++){
                            fortheyearlist.add(fortheYearModelArrayList.get(i).getFin_Year_Name());
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(TradeForm4ctivity.this,
                                R.layout.my_spinner_style, fortheyearlist);
                        fortheyearspinner.setAdapter(dataAdapter);
                        fortheyearspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                                String item = parent.getItemAtPosition(position).toString();
                                for (int i=0;i<fortheYearModelArrayList.size();i++){
                                    if (item.equals(fortheYearModelArrayList.get(i).getFin_Year_Name())){
                                        fortheyearid= String.valueOf(fortheYearModelArrayList.get(i).getFin_Year_ID());
                                        break;
                                    }
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        if (fortheYearModelArrayList.size()>0) {
                            for (int i = 0; i < fortheYearModelArrayList.size(); i++) {
                                Log.d("testmatchtype", StaticData.tfortheyearid + "/" + fortheYearModelArrayList.get(i).getFin_Year_ID());
                                if (StaticData.tfortheyearid.trim().equals(fortheYearModelArrayList.get(i).getFin_Year_ID().toString())) {
                                    fortheyearspinner.setSelection(i);
                                    break;
                                }
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
        JsonObject obj= RequestData.getnatureoftrade();
        progressDialog.show();
        CountryService countryService=new CountryService();
        countryService.getAPI().getnatureoftrade(obj).enqueue(new Callback<List<NatureoftradeModel>>() {
            @Override
            public void onResponse(Call<List<NatureoftradeModel>> call, Response<List<NatureoftradeModel>> response) {
                Log.d("response", response.body().toString());
                progressDialog.dismiss();
                if (response.body()!=null) {
                    if (response.body().size()>0){
                        //fortheYearModelArrayList=response.body();
                        List<NatureoftradeModel> natureoftradeModels=response.body();
                        for (int i=0;i<natureoftradeModels.size();i++){
                            natureoftrade.add(natureoftradeModels.get(i).getTrade_Type());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (TradeForm4ctivity.this, R.layout.my_spinner_style, natureoftrade);
                        //Getting the instance of AutoCompleteTextView
                        final AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
                        actv.setThreshold(1);//will start working from first character
                        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                parent.getSelectedItem();
                                tradeNatureModelList.add(new TradeNatureModel("0",actv.getText().toString(),tradetypeid));
                                actv.setText("");
                                singleTradeTypesforForm4Adaptor.notifyDataSetChanged();
                                // Toast.makeText(TradeForm2Activity.this, "", Toast.LENGTH_SHORT).show();
                            }
                        });
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
    public Bitmap getbitmap(Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private void loadotherdetails(File files,final  int i){
        progressDialog.show();
        MultipartBody.Part bdy=MultipartBody.Part.createFormData("image",files.getName(),
                RequestBody.create(MediaType.parse("image/*"), files));
        new CountryService().getAPI().uploadfiles(ApiLinks.upload_file,bdy).enqueue(new Callback<List<FileUploadModel>>() {
            @Override
            public void onResponse(Call<List<FileUploadModel>> call, retrofit2.Response<List<FileUploadModel>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("filepath",response.body().get(0).getFilepath());
                    filesNamepathListModelsnew.add(new FilesNamepathListModel(filesNamepathListModels.get(i).getName(),response.body().get(0).getFilepath()));

                }
            }
            @Override
            public void onFailure(Call<List<FileUploadModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Server Error"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Showprogess() {
        progressDialog.show();
    }
    public void dismissproggress() {
        progressDialog.dismiss();
    }

    private void loadotherdetailsmulti(List<File> files){
        MultipartBody.Part[] bodys = new MultipartBody.Part[files.size()];
        for(int j=0;j<files.size();j++){
            bodys[j]=MultipartBody.Part.createFormData("image[]",files.get(j).getName(),
                    RequestBody.create(MediaType.parse("image/*"), files.get(j)));
        }
        progressDialog.show();
        new CountryService().getAPI().uploadmultifiles(ApiLinks.upload_file,bodys).enqueue(new Callback<List<FileUploadModel>>() {
            @Override
            public void onResponse(Call<List<FileUploadModel>> call, retrofit2.Response<List<FileUploadModel>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    List<FileUploadModel> fileUploadModels=response.body();
                    Log.d("filepath",response.body().get(0).getFilepath());
                    List<File> paths=StaticData.files;
                    int j=0;
                    for (int i = 0; i < paths.size(); i++) {
                        if (!paths.get(i).getPath().equals("")) {
                        filesNamepathListModelsnew.add(new FilesNamepathListModel(filesNamepathListModels.get(i).getName(),fileUploadModels.get(j).getFilepath()));
                          j=j+1;
                        }
                    }

                    Log.d("Sixedoc", String.valueOf(filesNamepathListModelsnew.size()));
                    if (filesNamepathListModelsnew.size()==0){
                        Toast.makeText(TradeForm4ctivity.this, "no docs", Toast.LENGTH_SHORT).show();
                    }else{
                        submitdata();
                        //JsonObject obj= RequestData.inserttradeform(filesNamepathListModelsnew);
                    }
                    //StaticData.ffileurl=response.body().get(0).getFilepath();
                    //startActivity(new Intent(getApplicationContext(), Form4Activity.class));
                }
            }
            @Override
            public void onFailure(Call<List<FileUploadModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Server Error"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void submitdata(){
        JsonObject obj= RequestData.inserttradeform(filesNamepathListModelsnew);
        //  Showprogess();
        CountryService countryService=new CountryService();
        countryService.getAPI().inserttlform(obj).enqueue(new Callback<List<InsertTLResponse>>() {
            @Override
            public void onResponse(Call<List<InsertTLResponse>> call, Response<List<InsertTLResponse>> response) {
                Log.d("response", response.body().toString());
                dismissproggress();
                if (response.body()!=null) {
                    if(response.body().get(0).getCODE()==1){
                        InsertTLResponse insertTLResponse=response.body().get(0);
                        StaticData.insertTLResponse=insertTLResponse;
                        Toast.makeText(TradeForm4ctivity.this, "Sucessfully Inserted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),InsertTLFInalPageActivity.class));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<InsertTLResponse>> call, Throwable t) {
                dismissproggress();
            }
        });
    }

}
