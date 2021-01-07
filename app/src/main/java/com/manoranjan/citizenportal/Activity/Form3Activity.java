package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.manoranjan.citizenportal.Api.ApiLinks;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.FileUploadModel;
import com.manoranjan.citizenportal.service.CountryService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Form3Activity extends AppCompatActivity {
    ImageView adharfront, adharback;
    String path;
    Bitmap bitmap = null, bitmap2 = null;
    private int CAMERA = 22, GALLERYDOC = 11;
    Spinner cardspinner;
    String[] cardtypes = {"PAN CARD"};
    EditText cardnumber;
    ProgressDialog progressDialog;
    Pattern patternforpan,patternforgst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);
        progressDialog=new ProgressDialog(Form3Activity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        cardspinner = findViewById(R.id.cardspinner);
        cardnumber=findViewById(R.id.cardnumber);
        patternforpan = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

        ArrayAdapter ad = new ArrayAdapter(this,R.layout.my_spinner_style,cardtypes);
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
        });
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matcher matcherpan = patternforpan.matcher(cardnumber.getText().toString());
                if (TextUtils.isEmpty(cardnumber.getText().toString())){
                    cardnumber.setError("Enter Card Number");
                    return;
                } else if (!matcherpan.matches()) {
                    cardnumber.setError("Please Enter Valid Pan Number");
                    return;
                } else if (TextUtils.isEmpty(path)){
                    Toast.makeText(Form3Activity.this, "Select Image", Toast.LENGTH_SHORT).show();
                }else{
                    StaticData.fcardnumber=cardnumber.getText().toString();
                    List<String> paths=new ArrayList<>();
                    paths.add(path);
                    List<File> files=new ArrayList<>();
                    for (int i=0;i<paths.size();i++){
                        if (!paths.get(i).equals("")){
                            files.add(new File(paths.get(i)));
                        }
                    }
                    if (files.size()>=1){
                        loadotherdetails(files);
                    }
                   // startActivity(new Intent(getApplicationContext(), Form4Activity.class));
                }
            }
        });
        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Form2Activity.class));
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        adharfront = findViewById(R.id.aadharfront);
        adharfront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhotoFromGallaryDoc();
            }
        });
    }
    public void choosePhotoFromGallaryDoc() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERYDOC);
       /* if (path1.equals("")){
            Toast.makeText(getApplicationContext(), "Select  Image1", Toast.LENGTH_SHORT).show();
        }*/
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }  else if (requestCode == GALLERYDOC) {
            if (data != null) {
                Uri contentURI = data.getData();
                path = contentURI.getLastPathSegment();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                        path = contentURI.getLastPathSegment();
                        adharfront.setImageBitmap(bitmap);
                        this.bitmap = bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        StaticData.fcardnumber=cardnumber.getText().toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cardnumber.setText(StaticData.fcardnumber);
    }
    private void loadotherdetails(List<File> files){
       /* MultipartBody.Part[] bodys = new MultipartBody.Part[files.size()];
        for(int j=0;j<files.size();j++){
            bodys[j]=MultipartBody.Part.createFormData("image[]",files.get(j).getName(),
                    RequestBody.create(MediaType.parse("image/*"), files.get(j)));
        }*/
       progressDialog.show();
        MultipartBody.Part bdy=MultipartBody.Part.createFormData("image",files.get(0).getName(),
                RequestBody.create(MediaType.parse("image/*"), files.get(0)));
        new CountryService().getAPI().uploadfiles(ApiLinks.upload_file,bdy).enqueue(new Callback<List<FileUploadModel>>() {
            @Override
            public void onResponse(Call<List<FileUploadModel>> call, retrofit2.Response<List<FileUploadModel>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("filepath",response.body().get(0).getFilepath());
                    StaticData.ffileurl=response.body().get(0).getFilepath();
                    startActivity(new Intent(getApplicationContext(), Form4Activity.class));
                }
            }
            @Override
            public void onFailure(Call<List<FileUploadModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Server Error"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
