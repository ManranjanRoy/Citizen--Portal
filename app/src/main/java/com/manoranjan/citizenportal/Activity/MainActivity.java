package com.manoranjan.citizenportal.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Api.Configss;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.FilesNamepathListModel;
import com.manoranjan.citizenportal.model.NotificatonModel;
import com.manoranjan.citizenportal.model.PendingPaymentModel;
import com.manoranjan.citizenportal.model.TradeNatureModel;
import com.manoranjan.citizenportal.model.TypeofBusinessListModel;
import com.manoranjan.citizenportal.service.CountryService;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    boolean loggedIn, profile;
    String id, tokencode,name;
    int notisize;

    LinearLayout progresslayout,llpendingpayment;
    LinearLayout pendingpaymentlayout;
    TextView pendingpaymenttext;

    TextView navname,navemail;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Configss.LOGGEDIN_SHARED_PREF, false);
        profile = sharedPreferences.getBoolean(Configss.Profile_status, false);
        id = sharedPreferences.getString(Configss.citizen_id, "default");
        tokencode = sharedPreferences.getString(Configss.tokencode, "default");
        name = sharedPreferences.getString(Configss.name, "default");
        /*SharedPreferences preferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Configss.notisize, "0");
        editor.commit();*/
        notisize = Integer.parseInt(sharedPreferences.getString(Configss.notisize, "0"));
        //If we will get true
        if (!loggedIn) {
            //We will start the Profile Activity
            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (loggedIn && !profile) {
            Intent intent = new Intent(this, Form1Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            StaticData.citizen_id = id;
            StaticData.emailid = tokencode;

            navemail.setText(tokencode);
            navname.setText(name);
            getpendingpayment();
            getnotification();

            StaticData.tapplytype = "New";
            StaticData.tholdingno = "";
            StaticData.townername = "";
            StaticData.tstreetaddress="";
            StaticData.tlocality="";
            StaticData.twardno = "1";
            StaticData.ttypeoftax = "Property Tax";
            StaticData.tammountoftax="";
            StaticData.typeofrelation = "1";
            StaticData.ttypeofbusinessid = "1";
            StaticData. tfortheyearid = "1";StaticData.tnameoffirm="";
            StaticData. tdateofcommenence="";
            StaticData.tinvestmentcapital="";
            StaticData.ttradetype = "Small";
            StaticData.tgstin="";
            StaticData. tcompanypancard="";
            StaticData. tcontactnofirst="";
            StaticData. tcontactaddress="";
            StaticData.tworkshopaddress="";
            StaticData.tgodownaddress="";
            StaticData.tradeNatureModels = null;
            StaticData.typeofBusinessListModelList = null;
            StaticData.files = null;
            StaticData.finalfilesNamepathListModelList = null;
            StaticData.path1 = "";StaticData.path2 = "";StaticData.path3 = "";StaticData.path4 = "";StaticData.path5 = "";
            if (StaticData.tradeNatureModels!=null && StaticData.tradeNatureModels.size()>0) {
                StaticData.tradeNatureModels.clear();
            }
            if (StaticData.fileslist!=null && StaticData.fileslist.size()>0) {
                StaticData.fileslist.clear();
            }
            if (StaticData.finalfilesNamepathListModelList!=null && StaticData.finalfilesNamepathListModelList.size()>0) {
                StaticData.finalfilesNamepathListModelList.clear();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toasty.error(getApplicationContext(), "Success!", Toast.LENGTH_SHORT, true).show();
        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle("our24mart");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        progresslayout = findViewById(R.id.progresslayout);
       /* SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching the boolean value form sharedpreferences
        tokencode = sharedPreferences.getString(Configss.tokencode, "default");
*/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.nav_home);
        View headerView = navigationView.getHeaderView(0);

        navname = (TextView) headerView.findViewById(R.id.navname);
        navemail=(TextView)headerView.findViewById(R.id.navemail);

        navigationView.setNavigationItemSelectedListener(this);

        pendingpaymentlayout = findViewById(R.id.llpendingpayment);
        pendingpaymenttext = findViewById(R.id.pendingpaymentcount);

        llpendingpayment=findViewById(R.id.llpendingpayment);
        llpendingpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PendingpaymentActivity.class));
            }
        });
        findViewById(R.id.noti).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.notificationcount = 0;
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String roleid = sharedPreferences.getString(Configss.login_role, "Not Available");
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //Getting out sharedpreferences
                            SharedPreferences preferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            //Getting editor
                            SharedPreferences.Editor editor = preferences.edit();
                            //Puting the value false for loggedin
                            editor.putBoolean(Configss.LOGGEDIN_SHARED_PREF, false);
                            //Putting blank value to email
                            editor.putString(Configss.EMAIL_SHARED_PREF, "");
                            editor.putString(Configss.tokencode, "");
                            editor.putString(Configss.citizen_id, "");
                            editor.putString(Configss.name, "0");
                            editor.putBoolean(Configss.Profile_status, false);
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        //Starting login activity

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplicationContext(), "For Logout Click on Yes", Toast.LENGTH_SHORT).show();

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_home) {
           /* Intent i=new Intent(getApplicationContext(), MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);*/
            // Handle the camera action
        } else if (id == R.id.nav_trade) {
            Intent i = new Intent(getApplicationContext(), TradeForm1Activity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_pendingpayment) {
            Intent i = new Intent(getApplicationContext(), PendingpaymentActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_my_trade_license) {
            Intent i = new Intent(getApplicationContext(), MyTradeLicenseActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_feepayment) {
            Intent i = new Intent(getApplicationContext(), FeepaymentActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_logout) {
            /*Intent i=new Intent(getApplicationContext(), EditProfile.class);
            startActivity(i);*/
            logout();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getpendingpayment() {
        progresslayout.setVisibility(View.VISIBLE);
        JsonObject obj = RequestData.getpendingpayment();
        CountryService countryService = new CountryService();
        countryService.getAPI().pendingpayments(obj).enqueue(new Callback<List<PendingPaymentModel>>() {
            @Override
            public void onResponse(Call<List<PendingPaymentModel>> call, Response<List<PendingPaymentModel>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        pendingpaymenttext.setText(String.valueOf(response.body().size()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PendingPaymentModel>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
                getpendingpayment();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.notification_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.graph:
                // startActivity(new Intent(getApplicationContext(),FollowUpGraphActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getnotification() {
        JsonObject obj = RequestData.getnotificationlist();
        CountryService countryService = new CountryService();
        countryService.getAPI().notificationlist(obj).enqueue(new Callback<List<NotificatonModel>>() {
            @Override
            public void onResponse(Call<List<NotificatonModel>> call, Response<List<NotificatonModel>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        TextView notification = findViewById(R.id.cartcount);
                        Log.d("sizze", response.body().size() + "/" + notisize);
                        if (response.body().size() > notisize) {
                            addNotification(response.body().get(0).getFormNo(),response.body().get(0).getRemarks());
                            notification.setVisibility(View.VISIBLE);
                            int a = response.body().size() - notisize;
                            SharedPreferences preferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString(Configss.notisize, String.valueOf(response.body().size()));
                            editor.commit();
                            StaticData.notificationcount = a;
                            notification.setText(String.valueOf(a));
                        } else if(response.body().size() == notisize) {
                            notification.setVisibility(View.VISIBLE);
                            notification.setText(String.valueOf(StaticData.notificationcount));
                        } else {
                            notification.setVisibility(View.GONE);
                        }
                    } else {

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
    private void addNotification(String title,String remark) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(remark)
                .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        // Add as notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }
}
