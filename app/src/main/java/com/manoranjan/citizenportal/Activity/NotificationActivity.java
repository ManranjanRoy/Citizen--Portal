package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Adaptor.NotificationListAdaptor;
import com.manoranjan.citizenportal.Adaptor.PendingPaymentListAdaptor;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.NotificatonModel;
import com.manoranjan.citizenportal.model.PendingPaymentModel;
import com.manoranjan.citizenportal.service.CountryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    RecyclerView notificationrecycler;
    ImageView nodatafound;
    NotificationListAdaptor notificationListAdaptor;
    List<NotificatonModel> notificatonModelList=new ArrayList<>();
    ProgressDialog progressDialog;
    LinearLayout progresslayout;
    String pg_invoice_id="0";
    String TL_Form_Id="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        progressDialog = new ProgressDialog(NotificationActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        nodatafound=findViewById(R.id.nodatfoundimg);
        progresslayout=findViewById(R.id.progresslayout);
        notificationrecycler = findViewById(R.id.pendinglistrecycler);
        notificationrecycler.setHasFixedSize(true);
        notificationrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getnotification();

    }
    public void getnotification() {
        JsonObject obj = RequestData.getnotificationlist();
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().notificationlist(obj).enqueue(new Callback<List<NotificatonModel>>() {
            @Override
            public void onResponse(Call<List<NotificatonModel>> call, Response<List<NotificatonModel>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        nodatafound.setVisibility(View.GONE);
                        notificatonModelList=response.body();
                        // any implementation
                       // Collections.reverse(notificatonModelList);
                        notificationListAdaptor=new NotificationListAdaptor(getApplicationContext(),notificatonModelList);
                        notificationrecycler.setAdapter(notificationListAdaptor);

                    }
                    else {
                        nodatafound.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<NotificatonModel>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
                getnotification();
            }
        });
    }
}
