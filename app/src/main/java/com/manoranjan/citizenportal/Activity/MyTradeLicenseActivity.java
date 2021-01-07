package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Adaptor.MyTadeLicenseListAdaptor;
import com.manoranjan.citizenportal.Adaptor.PendingPaymentListAdaptor;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.PgInvoiceResponse;
import com.manoranjan.citizenportal.model.MytradeLicenseModel;
import com.manoranjan.citizenportal.model.PendingPaymentModel;
import com.manoranjan.citizenportal.model.TradeNatureModel;
import com.manoranjan.citizenportal.service.CountryService;
import com.razorpay.Checkout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyTradeLicenseActivity extends AppCompatActivity {
    RecyclerView pendinglistrecycler;
    ImageView nodatafound;
    MyTadeLicenseListAdaptor myTadeLicenseListAdaptor;
    List<MytradeLicenseModel> mytradeLicenseModelList=new ArrayList<>();
    ProgressDialog progressDialog;
    LinearLayout progresslayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingpayment);
        progressDialog = new ProgressDialog(MyTradeLicenseActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        nodatafound=findViewById(R.id.nodatfoundimg);
        progresslayout=findViewById(R.id.progresslayout);
        pendinglistrecycler = findViewById(R.id.pendinglistrecycler);
        pendinglistrecycler.setHasFixedSize(true);
        pendinglistrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        getmytradelicense();

    }

    public void getmytradelicense() {
        JsonObject obj = RequestData.getmytradelicense();
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().getmytradelicense(obj).enqueue(new Callback<List<MytradeLicenseModel>>() {
            @Override
            public void onResponse(Call<List<MytradeLicenseModel>> call, Response<List<MytradeLicenseModel>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        nodatafound.setVisibility(View.GONE);
                        mytradeLicenseModelList=response.body();
                        // any implementation
                        Collections.reverse(mytradeLicenseModelList);
                        myTadeLicenseListAdaptor=new MyTadeLicenseListAdaptor(getApplicationContext(),mytradeLicenseModelList);
                        pendinglistrecycler.setAdapter(myTadeLicenseListAdaptor);
                        myTadeLicenseListAdaptor.setonItemClickListner(new MyTadeLicenseListAdaptor.OnitemClickListner() {
                            @Override
                            public void onViewClick(int position) {
                                StaticData.form_id=mytradeLicenseModelList.get(position).getFormNo();
                               startActivity(new Intent(getApplicationContext(),SingleMyTadeLicenseActivity.class));
                            }

                            @Override
                            public void onViewLicenseClick(int position) {

                            }
                        });

                    }
                    else {
                        nodatafound.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<MytradeLicenseModel>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
                getmytradelicense();
            }
        });
    }




}
