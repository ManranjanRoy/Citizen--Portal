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
import com.manoranjan.citizenportal.Adaptor.FeePaymentListAdaptor;
import com.manoranjan.citizenportal.Adaptor.PendingPaymentListAdaptor;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.PgInvoiceResponse;
import com.manoranjan.citizenportal.model.FeePaymentModel;
import com.manoranjan.citizenportal.model.PendingPaymentModel;
import com.manoranjan.citizenportal.service.CountryService;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FeepaymentActivity extends AppCompatActivity implements PaymentResultListener {
    RecyclerView pendinglistrecycler;
    ImageView nodatafound;
    FeePaymentListAdaptor feePaymentListAdaptor;
    List<FeePaymentModel> feePaymentModels=new ArrayList<>();
    ProgressDialog progressDialog;
    LinearLayout progresslayout;
    String pg_invoice_id="0";
    String TL_Form_Id="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feepayment);
        progressDialog = new ProgressDialog(FeepaymentActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        nodatafound=findViewById(R.id.nodatfoundimg);
        progresslayout=findViewById(R.id.progresslayout);
        pendinglistrecycler = findViewById(R.id.pendinglistrecycler);
        pendinglistrecycler.setHasFixedSize(true);
        pendinglistrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        getpendingpayment();

       /* if (pendingPaymentModelList.size() > 0) {
            nodatafound.setVisibility(View.GONE);
        } else {
            nodatafound.setVisibility(View.VISIBLE);
        }*/
    }

    public void getpendingpayment() {
        JsonObject obj = RequestData.getfeepayment();
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().feepayments(obj).enqueue(new Callback<List<FeePaymentModel>>() {
            @Override
            public void onResponse(Call<List<FeePaymentModel>> call, Response<List<FeePaymentModel>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        nodatafound.setVisibility(View.GONE);
                        feePaymentModels=response.body();
                        // any implementation
                        Collections.reverse(feePaymentModels);
                        feePaymentListAdaptor=new FeePaymentListAdaptor(getApplicationContext(),feePaymentModels);
                        pendinglistrecycler.setAdapter(feePaymentListAdaptor);
                        feePaymentListAdaptor.setonItemClickListner(new FeePaymentListAdaptor.OnitemClickListner() {
                            @Override
                            public void onPayClick(int position) {
                                TL_Form_Id=feePaymentModels.get(position).getTL_Form_Id();
                                //getpginvoice(TL_Form_Id);
                            }
                        });
                    }
                    else {
                        nodatafound.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<FeePaymentModel>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
                getpendingpayment();
            }
        });
    }

    private void startPayment(String pg_invoice_id) {
        // payammount=Integer.parseInt(ammount.getText().toString());
        Float payammount=Float.parseFloat("50.00");
        //Float payammount=payammount1/100;
        Checkout checkout = new Checkout();

        checkout.setImage(R.drawable.logo_haldia);

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Citizen Portal");
            options.put("description", "Reference No - "+pg_invoice_id);
            options.put("theme.color", getResources().getColor(R.color.colorPrimary));
            options.put("currency", "INR");
            options.put("email", StaticData.emailid);
            options.put("contact",StaticData.fnumber);
            options.put("amount", payammount*100);
            options.put("prefill.email", StaticData.emailid);
            options.put("prefill.contact",StaticData.fnumber);
            checkout.open(activity, options);
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {
        updatepginvoice("SUCCESS",s);
        // paymentstatus="1";
        // placeorder("1");

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),"Failed Paid"+s,Toast.LENGTH_SHORT).show();
        updatepginvoice("NOT SUCCESS","NA");

    }

    public void getpginvoice(String tl_form_id) {
        JsonObject obj = RequestData.getpginvoice(tl_form_id);
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().pginvoice(obj).enqueue(new Callback<List<PgInvoiceResponse>>() {
            @Override
            public void onResponse(Call<List<PgInvoiceResponse>> call, Response<List<PgInvoiceResponse>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        pg_invoice_id=response.body().get(0).getPG_Invoice_ID();
                        startPayment(pg_invoice_id);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<PgInvoiceResponse>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
            }
        });
    }

    public void updatepginvoice(String status,String txn_id) {
        JsonObject obj = RequestData.updatepginvoice(status,txn_id,pg_invoice_id,TL_Form_Id);
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().updatepginvoice(obj).enqueue(new Callback<List<PgInvoiceResponse>>() {
            @Override
            public void onResponse(Call<List<PgInvoiceResponse>> call, Response<List<PgInvoiceResponse>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        Toast.makeText(getApplicationContext(),"Sucessfully Paid",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(), PendingpaymentActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<PgInvoiceResponse>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
            }
        });
    }
}
