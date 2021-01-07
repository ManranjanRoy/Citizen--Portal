package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manoranjan.citizenportal.Api.ApiLinks;
import com.manoranjan.citizenportal.Api.Configss;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.Profile;
import com.manoranjan.citizenportal.service.CountryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MyProfileActivity extends AppCompatActivity {
    ImageView beditnamee,beditlname,beditphonenumberr,beditcardnumberr,beditemaill,beditaddresss,beditpincodee;
    TextView textname,textlname,textphonenumberr,textcardnumberr,textemaill,textaddresss,textpincodee;
    EditText editname,editlname,editphonenumberr,editcardnumberr,editemaill,editaddresss,editpincodee;
    String tokencode;
    ProgressDialog progressDialog;
    TextView headname,heademail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        progressDialog=new ProgressDialog(MyProfileActivity.this);
        progressDialog.setMessage("Please wait while Loading...");
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching the boolean value form sharedpreferences
        tokencode = sharedPreferences.getString(Configss.tokencode, "default");


        beditnamee=findViewById(R.id.beditname);
        beditlname=findViewById(R.id.beditlname);
        beditphonenumberr=findViewById(R.id.beditphonenumber);
        beditcardnumberr=findViewById(R.id.beditcardnumber);
        beditemaill=findViewById(R.id.beditemail);
        beditaddresss=findViewById(R.id.beditaddress);
        beditpincodee=findViewById(R.id.beditpincode);


        beditnamee.setTag(R.drawable.edit_icon);
        beditlname.setTag(R.drawable.edit_icon);
        beditphonenumberr.setTag(R.drawable.edit_icon);
        beditcardnumberr.setTag(R.drawable.edit_icon);
        beditemaill.setTag(R.drawable.edit_icon);
        beditaddresss.setTag(R.drawable.edit_icon);
        beditpincodee.setTag(R.drawable.edit_icon);

        textname=findViewById(R.id.textname);
        textlname=findViewById(R.id.textlname);
        textphonenumberr=findViewById(R.id.textphonenumber);
        textcardnumberr=findViewById(R.id.textcardnumber);
        textemaill=findViewById(R.id.textemail);
        textaddresss=findViewById(R.id.textaddress);
        textpincodee=findViewById(R.id.textpincode);

        editname=findViewById(R.id.editname);
        editlname=findViewById(R.id.editlname);
        editphonenumberr=findViewById(R.id.editphonenumber);
        editcardnumberr=findViewById(R.id.editcardnumber);
        editemaill=findViewById(R.id.editemail);
        editaddresss=findViewById(R.id.editaddress);
        editpincodee=findViewById(R.id.editpincode);
        headname=findViewById(R.id.headname);
        heademail=findViewById(R.id.heademail);
        loadProfile();
        // printdata();

       /* beditnamee.setOnClickListener(this);
        beditlname.setOnClickListener(this);
        beditphonenumberr.setOnClickListener(this);
        beditcardnumberr.setOnClickListener(this);
        beditemaill.setOnClickListener(this);
        beditaddresss.setOnClickListener(this);
        beditpincodee.setOnClickListener(this);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        //printdata();
    }

    private void printdata() {

        textname.setText(StaticData.profile.getName());
        textlname.setText(StaticData.profile.getUserType());
        textphonenumberr.setText(StaticData.profile.getMobileNo());
        textcardnumberr.setText(StaticData.profile.getCompanyName());
        textemaill.setText(StaticData.profile.getEmailId());
        // textpincodee.setText(StaticData.profile.getId());

    }


    private int getDrawableId(ImageView iv) {
        return (Integer) iv.getTag();
    }


    public void loadProfile() {
        progressDialog.show();
        JsonObject obj = new JsonObject();
        final JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("email", tokencode);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(paramObject);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "getProfile2");
            jsonObject.put("spname", "USP_LOGIN");
            jsonObject.put("json", jsonArray);
            Log.d("jsondata",jsonObject.toString());

            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        new CountryService().getAPI().profile(obj).enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                progressDialog.dismiss();
                if (response.body()!=null) {

                    if (response.body().size()>0) {
                        Profile profiles = response.body().get(0);
                        Log.d("error",profiles.getEmailId());
                        StaticData.profile = profiles;
                        printdata();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                progressDialog.dismiss();
                loadProfile();
            }
        });

    }
}
