package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Adaptor.DocstypesListdataAdaptor;
import com.manoranjan.citizenportal.Api.ApiLinks;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.DocTypeListModel;
import com.manoranjan.citizenportal.model.FileListModel;
import com.manoranjan.citizenportal.model.FileUploadModel;
import com.manoranjan.citizenportal.model.FilesNamepathListModel;
import com.manoranjan.citizenportal.service.CountryService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

public class TradeForm3ctivity extends AppCompatActivity implements View.OnClickListener{
    ImageView img1, img2,img3,img4,img5;
    String  path1 = "", path2 = "",path3 = "",path4 = "",path5 = "";
    Bitmap bitmap1 = null, bitmap2 = null,bitmap3 = null, bitmap4 = null,bitmap5 = null;
    Bitmap icon;
    private int CAMERA = 22, GALLERYDOC = 11;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    Button btn_cancel,btngallery,btncamera;
    BottomSheetDialog dialog;
    CoordinatorLayout coordinatorLayout;
    ProgressDialog progressDialog;
    DocstypesListdataAdaptor docstypesListdataAdaptor;
    RecyclerView doclistrecycler;
    int pos;
    View itemview;
    List<DocTypeListModel> docTypeListModelList=new ArrayList<>();
    List<FileListModel> fileListModelList=new ArrayList<>();
    int imgno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_form3ctivity);
        progressDialog=new ProgressDialog(TradeForm3ctivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        doclistrecycler = findViewById(R.id.doclist);
        doclistrecycler.setHasFixedSize(true);
        doclistrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


         icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_baseline_cloud_upload_24);
         StaticData.bitmap1=icon;
        StaticData.bitmap2=icon;
        StaticData.bitmap3=icon;
        StaticData.bitmap4=icon;
        StaticData.bitmap5=icon;


        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3= findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        init_modal_bottomsheet();
    /*    img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgno=1;
                dialog.show();
                //choosePhotoFromGallaryDoc();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgno=2;
                dialog.show();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgno=3;
                dialog.show();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgno=4;
                dialog.show();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgno=5;
                dialog.show();
            }
        });
*/
        loaddocstypes();
        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TradeForm2Activity.class));
            }
        });
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   /* List<String> paths = new ArrayList<>();
                    paths.add(path1);
                    paths.add(path2);
                    paths.add(path3);
                    paths.add(path4);
                    paths.add(path5);

                    List<File> files = new ArrayList<>();
                    for (int i = 0; i < paths.size(); i++) {
                        if (!paths.get(i).equals("")) {
                            files.add(new File(paths.get(i)));
                        }
                    }
                    StaticData.files=files;
                     if (files.size() >= 1) {
                         startActivity(new Intent(getApplicationContext(),TradeForm4ctivity.class));
                       //loadotherdetailsmulti(files);
                    }else{
                         Toast.makeText(TradeForm3ctivity.this, "Select Any one Document", Toast.LENGTH_SHORT).show();
                     }*/
                   Log.d("docdata", String.valueOf(fileListModelList.size()));
                StaticData.fileslist=fileListModelList;
                if (fileListModelList.size() >= 1) {
                    startActivity(new Intent(getApplicationContext(),TradeForm4ctivity.class));
                    //loadotherdetailsmulti(files);
                }else{
                    Toast.makeText(TradeForm3ctivity.this, "Select Any one Document", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(TradeForm3ctivity.this, fileListModelList.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("docdata", String.valueOf(fileListModelList.size()));
                StaticData.fileslist=fileListModelList;
                finish();
                // startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    public void choosePhotoFromGallaryDoc() {
        String[] mimeTypes = {"image/*", "application/pdf"};

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERYDOC);
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
//            if (mimeTypes.length > 0) {
//                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
//            }
//        } else {
//            String mimeTypesStr = "";
//            for (String mimeType : mimeTypes) {
//                mimeTypesStr += mimeType + "|";
//            }
//            intent.setType(mimeTypesStr.substring(0, mimeTypesStr.length() - 1));
//        }
//        startActivityForResult(intent, GALLERYDOC);
    }

    public void init_modal_bottomsheet() {

        View modalbottomsheet = getLayoutInflater().inflate(R.layout.modal_bottomsheet, null);
        dialog = new BottomSheetDialog(this);
        dialog.setContentView(modalbottomsheet);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        btn_cancel = (Button) modalbottomsheet.findViewById(R.id.btn_cancel);
        btngallery = (Button) modalbottomsheet.findViewById(R.id.btn_gallery);
        btncamera = (Button) modalbottomsheet.findViewById(R.id.btn_camera);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotoFromCamera();
                dialog.hide();
            }
        });
        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhotoFromGallaryDoc();
                dialog.hide();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
       /* path1=StaticData.path1;
        path2=StaticData.path2;
        path3=StaticData.path3;
        path4=StaticData.path4;
        path5=StaticData.path5;*/

        if (StaticData.fileslist!=null && StaticData.fileslist.size()>0){
         docstypesListdataAdaptor.notifyDataSetChanged();
        }
/*
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

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                    if (imgno==1) {
                        img1.setImageBitmap(bitmap);
                        StaticData.uri1=contentURI;
                        path1 = contentURI.getLastPathSegment();
                        bitmap1=bitmap;
                        StaticData.path1=path1;
                        StaticData.bitmap1=bitmap1;
                    }else  if (imgno==2) {
                        img2.setImageBitmap(bitmap);
                        StaticData.uri2=contentURI;
                        path2 = contentURI.getLastPathSegment();
                        bitmap2=bitmap;
                        StaticData.path2=path2;
                        StaticData.bitmap2=bitmap2;
                    }else  if (imgno==3) {
                        StaticData.uri3=contentURI;
                        path3 = contentURI.getLastPathSegment();
                        img3.setImageBitmap(bitmap);
                        bitmap3=bitmap;
                        StaticData.path3=path3;
                        StaticData.bitmap3=bitmap3;
                    }else  if (imgno==4) {
                        StaticData.uri4=contentURI;
                        path4 = contentURI.getLastPathSegment();
                        img4.setImageBitmap(bitmap);
                        bitmap4=bitmap;
                        StaticData.path4=path4;
                        StaticData.bitmap4=bitmap4;
                    }else  if (imgno==5) {
                        StaticData.uri5=contentURI;
                        path5 = contentURI.getLastPathSegment();
                        img5.setImageBitmap(bitmap);
                        bitmap5=bitmap;
                        StaticData.path5=path5;
                        StaticData.bitmap5=bitmap5;
                    }else if (imgno==11){
                       // Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
                        StaticData.uri5=contentURI;
                        path5 = contentURI.getLastPathSegment();
                        bitmap5=bitmap;
                        StaticData.path5=path5;
                        StaticData.bitmap5=bitmap5;
                        showimg();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }else  if (requestCode == CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Log.d("thumbnail...", String.valueOf(bitmap));

            if (imgno==1) {
                StaticData.uri1=Uri.parse(getFileDataFromDrawable(bitmap).toString());
                path1 = saveImage(bitmap);
                img1.setImageBitmap(bitmap);
                bitmap1=bitmap;
                StaticData.path1=path1;
                StaticData.bitmap1=bitmap1;
            }else  if (imgno==2) {
                StaticData.uri2=Uri.parse(getFileDataFromDrawable(bitmap).toString());
                path2 = saveImage(bitmap);
                img2.setImageBitmap(bitmap);
                bitmap2=bitmap;
                StaticData.path2=path2;
                StaticData.bitmap2=bitmap2;

            }else  if (imgno==3) {
                StaticData.uri3=Uri.parse(getFileDataFromDrawable(bitmap).toString());
                path3 =  saveImage(bitmap);
                img3.setImageBitmap(bitmap);
                bitmap3=bitmap;
                StaticData.path3=path3;
                StaticData.bitmap3=bitmap3;
            }else  if (imgno==4) {
                StaticData.uri4=Uri.parse(getFileDataFromDrawable(bitmap).toString());
                path4 =  saveImage(bitmap);
                img4.setImageBitmap(bitmap);
                bitmap4=bitmap;
                StaticData.path4=path4;
                StaticData.bitmap4=bitmap4;
            }else  if (imgno==5) {
                StaticData.uri5=Uri.parse(getFileDataFromDrawable(bitmap).toString());
                path5 =  saveImage(bitmap);
                img5.setImageBitmap(bitmap);
                bitmap5=bitmap;
                StaticData.path5=path5;
                StaticData.bitmap5=bitmap5;
            }else if (imgno==11){
                StaticData.uri5=Uri.parse(getFileDataFromDrawable(bitmap).toString());
                path5 =  saveImage(bitmap);
                bitmap5=bitmap;
                StaticData.path5=path5;
                StaticData.bitmap5=bitmap5;
                showimg();

            }
        }

    }

    public void showimg(){
        ImageView img=itemview.findViewById(R.id.img1);
        img.setImageBitmap(StaticData.bitmap5);
        if (docTypeListModelList.size()>0){
            if (!path5.equals("")) {
                if (fileListModelList.size()>0){
                    int a=0;
                    for (int i=0;i<fileListModelList.size();i++){
                        if (docTypeListModelList.get(pos).getDocumentId()==fileListModelList.get(i).getId()){
                            a=1;
                            fileListModelList.set(i,new FileListModel(docTypeListModelList.get(pos).getDocumentId(), docTypeListModelList.get(pos).getDocumentName(),new File(path5)));
                            break;
                        }
                    }
                    if (a==0) {
                        fileListModelList.add(new FileListModel(docTypeListModelList.get(pos).getDocumentId(),  docTypeListModelList.get(pos).getDocumentName(),new File(path5)));
                    }
                }else{
                    fileListModelList.add(new FileListModel(docTypeListModelList.get(pos).getDocumentId(), docTypeListModelList.get(pos).getDocumentName(), new File(path5)));
                }
            }
        }
    }
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("path",f.getAbsolutePath());
            return f.getAbsolutePath();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public void onClick(View v) {

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
                }
            }
            @Override
            public void onFailure(Call<List<FileUploadModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Server Error"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loaddocstypes(){
        String formtype="N";
        if (StaticData.tapplytype.equals("New")){
            formtype="N";
        }else{
            formtype="R";
        }

        JsonObject obj = RequestData.getdocumentlist(formtype);
        progressDialog.show();
        new CountryService().getAPI().getdoctypeslist(obj).enqueue(new Callback<List<DocTypeListModel>>() {
            @Override
            public void onResponse(Call<List<DocTypeListModel>> call, retrofit2.Response<List<DocTypeListModel>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    docTypeListModelList=response.body();

                    docstypesListdataAdaptor=new DocstypesListdataAdaptor(getApplicationContext(),docTypeListModelList);
                    doclistrecycler.setAdapter(docstypesListdataAdaptor);

                    docstypesListdataAdaptor.setonItemClickListner(new DocstypesListdataAdaptor.OnitemClickListner() {
                        @Override
                        public void onShowClick(int position,View item) {

                            ImageView img=item.findViewById(R.id.img1);
                           // img.setImageBitmap(StaticData.bitmap5);
                            pos=position;
                            itemview=item;
                            imgno=11;
                            dialog.show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<DocTypeListModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Server Error"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
