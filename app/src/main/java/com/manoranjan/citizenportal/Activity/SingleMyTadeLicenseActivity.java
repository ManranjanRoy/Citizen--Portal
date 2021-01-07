package com.manoranjan.citizenportal.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Adaptor.DocumentsListAdaptor;
import com.manoranjan.citizenportal.Adaptor.OwnerofTradeAdaptor;
import com.manoranjan.citizenportal.Adaptor.SingleTradeTypesAdaptor;
import com.manoranjan.citizenportal.Adaptor.TimelineAdapter;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.DocumentListResponse;
import com.manoranjan.citizenportal.Response.SingleMyTadeLicense;
import com.manoranjan.citizenportal.Response.SingleTradetypeResponse;
import com.manoranjan.citizenportal.model.ListItem;
import com.manoranjan.citizenportal.model.TradeNatureModel;
import com.manoranjan.citizenportal.model.TypeofBusinessListModel;
import com.manoranjan.citizenportal.service.CountryService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleMyTadeLicenseActivity extends AppCompatActivity {
    LinearLayout progresslayout;
    TextView holdingno, ownername, street, locality, ward, typeoftax, propertytax, status, typeofland, issueddate, typeofbusiness, fortheyear, companyname;
    TextView applieddate, investmentofcapital, gst, pan, contact, address, workshop, godown;
    SingleMyTadeLicense singleMyTadeLicense = null;
    RecyclerView tradetyperecycle, ownerrecycler, documentrecycler;
    SingleTradeTypesAdaptor singleTradeTypesAdaptor;
    OwnerofTradeAdaptor ownerofTradeAdaptor;
    DocumentsListAdaptor documentsListAdaptor;
    List<TradeNatureModel> tradeNatureModelList = new ArrayList<>();
    List<TypeofBusinessListModel> typeofBusinessListModelList = new ArrayList<>();
    List<DocumentListResponse> documentListResponses = new ArrayList<>();

    public static final String ORIENTATION = "orientation";
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_my_tade_license);
        progresslayout = findViewById(R.id.progresslayout);
        holdingno = findViewById(R.id.sholdingno);
        ownername = findViewById(R.id.sownername);
        street = findViewById(R.id.sstreetname);
        locality = findViewById(R.id.slocalityname);
        ward = findViewById(R.id.swardno);
        typeoftax = findViewById(R.id.stypeoftax);
        propertytax = findViewById(R.id.spropertytax);
        status = findViewById(R.id.sstatus);
        typeofland = findViewById(R.id.sland);
        issueddate = findViewById(R.id.sissuedate);
        typeofbusiness = findViewById(R.id.stypeofbusiness);
        fortheyear = findViewById(R.id.sfortheyear);
        companyname = findViewById(R.id.snameofcompany);
        applieddate = findViewById(R.id.sapplieddate);
        investmentofcapital = findViewById(R.id.sinvestmentofcapital);
        gst = findViewById(R.id.sgst);
        pan = findViewById(R.id.span);
        contact = findViewById(R.id.scontact);
        address = findViewById(R.id.sconatctaddress);
        workshop = findViewById(R.id.sworkshop);
        godown = findViewById(R.id.sgodown);
        tradetyperecycle = findViewById(R.id.tradelist);
        tradetyperecycle.setHasFixedSize(true);
        tradetyperecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        documentrecycler = findViewById(R.id.documentlist);
        documentrecycler.setHasFixedSize(true);
        documentrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        /*ownerrecycler = findViewById(R.id.ownerlist);
        ownerrecycler.addItemDecoration(new DividerItemDecoration(SingleMyTadeLicenseActivity.this, DividerItemDecoration.VERTICAL));
        ownerrecycler.setHasFixedSize(true);
        ownerrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));*/

        getmytradelicense();


    }

    void showdata() {
        //TextView holdingno, ownername, street, locality, ward, typeoftax, propertytax, status, typeofland, issueddate,
        // typeofbusiness, fortheyear, companyname;
        //    TextView applieddate, investmentofcapital, gst, pan, contact, address, workshop, godown;
        holdingno.setText(singleMyTadeLicense.getAssesseeHoldingNo());
        ownername.setText(singleMyTadeLicense.getPropertyOwnerName());
        street.setText(singleMyTadeLicense.getStreet());
        locality.setText(singleMyTadeLicense.getLocalityName());
        ward.setText(singleMyTadeLicense.getWardNo() + " / " + singleMyTadeLicense.getWardCouncillor());
        typeoftax.setText(singleMyTadeLicense.getTaxPaidType());
        propertytax.setText(String.valueOf(singleMyTadeLicense.getTaxPaidAmount()));
        status.setText(String.valueOf(singleMyTadeLicense.getStatus()));
        typeofland.setText(singleMyTadeLicense.getLandNature());
        issueddate.setText("----");
        typeofbusiness.setText(singleMyTadeLicense.getTradeNature());
        fortheyear.setText(singleMyTadeLicense.getFinYearShtName());
        companyname.setText(singleMyTadeLicense.getNameOrg());
        applieddate.setText(String.valueOf(singleMyTadeLicense.getAppliedOn()));
        investmentofcapital.setText(String.valueOf(singleMyTadeLicense.getCapital()));
        gst.setText(singleMyTadeLicense.getGSTIN());
        pan.setText(singleMyTadeLicense.getCompanyPAN());
        contact.setText(singleMyTadeLicense.getConNo());
        address.setText(singleMyTadeLicense.getConAdd());
        workshop.setText(singleMyTadeLicense.getWorkshop());
        godown.setText(singleMyTadeLicense.getGodown());
    }

    public void getmytradelicense() {
        JsonObject obj = RequestData.getsinglemytradelicense();
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().getsinglemytradelicense(obj).enqueue(new Callback<List<SingleMyTadeLicense>>() {
            @Override
            public void onResponse(Call<List<SingleMyTadeLicense>> call, Response<List<SingleMyTadeLicense>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        Toast.makeText(SingleMyTadeLicenseActivity.this, "error 1", Toast.LENGTH_SHORT).show();
                        singleMyTadeLicense = response.body().get(0);
                        getmytradetypes(String.valueOf(singleMyTadeLicense.getAppTLID()));
                        showdata();
                    } else {
                        Toast.makeText(SingleMyTadeLicenseActivity.this, "error 1", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SingleMyTadeLicenseActivity.this, "error 11", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<SingleMyTadeLicense>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
                Toast.makeText(SingleMyTadeLicenseActivity.this, "error 1" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getmytradetypes(String appTLID) {
        JsonObject obj = RequestData.getsinglemytradetype(appTLID);
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().getsinglemytradetypes(obj).enqueue(new Callback<List<SingleTradetypeResponse>>() {
            @Override
            public void onResponse(Call<List<SingleTradetypeResponse>> call, Response<List<SingleTradetypeResponse>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        singleTradeTypesAdaptor = new SingleTradeTypesAdaptor(getApplicationContext(), response.body());
                        tradetyperecycle.setAdapter(singleTradeTypesAdaptor);
                        getmydocuments();
                    } else {
                        Toast.makeText(SingleMyTadeLicenseActivity.this, "error 2", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<SingleTradetypeResponse>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
            }
        });
    }

    public void getmydocuments() {
        JsonObject obj = RequestData.getsingledocumentlist();
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().getsingledocumentlist(obj).enqueue(new Callback<List<DocumentListResponse>>() {
            @Override
            public void onResponse(Call<List<DocumentListResponse>> call, Response<List<DocumentListResponse>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        documentListResponses = response.body();
                        documentsListAdaptor = new DocumentsListAdaptor(getApplicationContext(), documentListResponses);
                        documentrecycler.setAdapter(documentsListAdaptor);
                        documentsListAdaptor.setonItemClickListner(new DocumentsListAdaptor.OnitemClickListner() {
                            @Override
                            public void onShowClick(int position) {
                                showalert1(position);
                            }
                        });

                    } else {
                        Toast.makeText(SingleMyTadeLicenseActivity.this, "error 2", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SingleMyTadeLicenseActivity.this, "error 22", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DocumentListResponse>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
                Toast.makeText(SingleMyTadeLicenseActivity.this, "error 1" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showalert1(final int position) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.SheetDialog1)
                .setView(R.layout.item_imagedialog)
                .create();
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                final ImageView cancel = dialog.findViewById(R.id.cancel);
                ImageView adsimage = dialog.findViewById(R.id.adsimage);
                Picasso.with(getApplicationContext())
                        .load(documentListResponses.get(position).getFileUrl())
                        .noFade()
                        .into(adsimage);
                adsimage.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // finish();
                    dialog.dismiss();
                }
                return true;
            }
        });
        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.follow_up_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.graph:
               startActivity(new Intent(getApplicationContext(),FollowUpGraphActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
