package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Adaptor.NotificationListAdaptor;
import com.manoranjan.citizenportal.Adaptor.PendingPaymentListAdaptor;
import com.manoranjan.citizenportal.Api.Configss;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.Api.StaticData;
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
        notificatonModelList.clear();
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
                        notificationListAdaptor.setonItemClickListner(new NotificationListAdaptor.OnitemClickListner() {
                            @Override
                            public void onForwardClick(int position) {
                                StaticData.form_id=notificatonModelList.get(position).getFormNo();
                                showalert(notificatonModelList.get(position).getTL_Form_Id(),notificatonModelList.get(position).getFollowup_By_User_ID());
                            }
                        });
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

    private void showalert(final String tl_form_id, final String followup_by_user_id){
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String citizenid = sharedPreferences.getString(Configss.citizen_id,"0");
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to Forward data to HMC?");
        alertDialogBuilder.setPositiveButton("Forward",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //Getting out sharedpreferences
                        if(!citizenid.equals("0")) {
                            forwarddata(tl_form_id,followup_by_user_id);
                                  }

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                       // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();


                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void forwarddata(String tl_form_id, String followup_by_user_id) {
        JsonObject obj = RequestData.getfarwarddata(tl_form_id,followup_by_user_id);
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().forwarddata(obj).enqueue(new Callback<List<NotificatonModel>>() {
            @Override
            public void onResponse(Call<List<NotificatonModel>> call, Response<List<NotificatonModel>> response) {
                Log.d("response", response.body().toString());
                //Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                progresslayout.setVisibility(View.GONE);
                getnotification();
                if (response.body() != null) {
                    getnotification();
                    if (response.body().size() >= 0) {
                        Toast.makeText(NotificationActivity.this, "Sucessfully Forwaded", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else {

                    }
                }
            }
            @Override
            public void onFailure(Call<List<NotificatonModel>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);

            }
        });
    }

}
