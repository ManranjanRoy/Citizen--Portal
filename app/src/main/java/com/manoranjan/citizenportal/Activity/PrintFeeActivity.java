package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.UserDictionary;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Adaptor.TimelineAdapter;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.BuildConfig;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.PrintdataResponse;
import com.manoranjan.citizenportal.Response.SingleFollowupGraphResponse;
import com.manoranjan.citizenportal.service.CountryService;
import com.manoranjan.citizenportal.service.NumberToWordsConverter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintFeeActivity extends AppCompatActivity {
    private LinearLayout llPdf;
    LinearLayout progresslayout;
TextView datetop,regno,mbrno,certificateno,foryear,ownername,farmname,holdingno,plotno;
TextView location,wardno,tradename,feeammount,feeammountintext,validtill,currentfee,activefee,totalfee,datebottom,computerno;
ImageView signature,toplogo;
String foryearstatic="Year For which the certificate of enlistment of its renewal relates ";
String holdingnostatic="residing and or carrying on a instructing is vary on business in Holding No :- ";
String sigurl="https://municipadoc.blob.core.windows.net/printlogo/16_12_2020_12_43_391.jpeg?sv=2018-03-28&sr=b&sig=Ox48805aPuRNv7zXrq0QpIRhpV9W4E%2FgTiNrWJLlOOc%3D&se=2025-05-04T07%3A13%3A38Z&sp=rl";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_fee);
        progresslayout=findViewById(R.id.progresslayout);
        datetop=findViewById(R.id.datetop);
        regno=findViewById(R.id.regno);
        mbrno=findViewById(R.id.mbrno);
        certificateno=findViewById(R.id.certificateno);
        foryear=findViewById(R.id.foryear);
        ownername=findViewById(R.id.ownername);
        farmname=findViewById(R.id.farmname);
        holdingno=findViewById(R.id.holdingno);
        plotno=findViewById(R.id.plotno);
        location=findViewById(R.id.location);
        wardno=findViewById(R.id.wardno);
        tradename=findViewById(R.id.tradename);
        feeammount=findViewById(R.id.feeammount);
        feeammountintext=findViewById(R.id.feeammountintext);
        validtill=findViewById(R.id.validtill);
        currentfee=findViewById(R.id.currentfee);
        activefee=findViewById(R.id.activefee);
        totalfee=findViewById(R.id.totalfee);
        datebottom=findViewById(R.id.datebottom);
        computerno=findViewById(R.id.computerno);
        signature=findViewById(R.id.signature);
        toplogo=findViewById(R.id.toplogo);
        llPdf = findViewById(R.id.llpdf);
        llPdf.setVisibility(View.GONE);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        getprintdata();
    }

    public void getprintdata() {
        JsonObject obj = RequestData.getprintdatafortradelicense();
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().getprintdatafortradedetails(obj).enqueue(new Callback<List<PrintdataResponse>>() {
            @Override
            public void onResponse(Call<List<PrintdataResponse>> call, Response<List<PrintdataResponse>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);

                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        llPdf.setVisibility(View.VISIBLE);
                        PrintdataResponse printdataResponse=response.body().get(0);
                        regno.setText(printdataResponse.getRegn118());
                        certificateno.setText(printdataResponse.getRegn118());
                        String sourceString = holdingnostatic+"<b>" + printdataResponse.getAssesseeHoldingNo() + "</b> " ;
                        String fortheyaertext = foryearstatic+"<b>" +"2020-2021" + "</b> " ;
                        holdingno.setText(Html.fromHtml(sourceString));
                        foryear.setText(Html.fromHtml(fortheyaertext));
                        ownername.setText(printdataResponse.getPropertyOwnerName());
                        farmname.setText(printdataResponse.getNameOrg());
                        wardno.setText(printdataResponse.getWardNo());
                        feeammount.setText("Rs. "+(int)Math.round(printdataResponse.getNetPayable()));
                        try {
                            final int number = (int)Math.round(printdataResponse.getNetPayable());
                            String returnz = numToWords(number);
                            feeammountintext.setText("( Rupee "+returnz+" )");
                        } catch ( NumberFormatException e) {
                            Toast.makeText(getApplicationContext(),"illegal number or empty number" , Toast.LENGTH_SHORT).show();
                        }
                        currentfee.setText(String.valueOf(printdataResponse.getTotalFee118()));
                        activefee.setText(String.valueOf(printdataResponse.getTotalFee201()));
                        totalfee.setText(String.valueOf(printdataResponse.getNetPayable()));
                        Picasso.with(getApplicationContext())
                                .load(sigurl)
                                .noFade()
                                .into(signature);


                    } else {
                        Toast.makeText(getApplicationContext(), "error 2", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error 22", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PrintdataResponse>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "error 1" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String numToWords (int n){ //optional
        NumberToWordsConverter ntw = new NumberToWordsConverter(); // directly implement this
        return ntw.convert(n);
    }



}
