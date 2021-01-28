package com.manoranjan.citizenportal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class InsertTLFInalPageActivity extends AppCompatActivity {

    TextView recieprno, recievedfrom, sumofrs, onaccountof, rs, remark, enteredby, applicationno,date;
    ImageView signature,qrcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_t_l_f_inal_page);
        recieprno = findViewById(R.id.recieprno);
        recievedfrom = findViewById(R.id.recievedfrom);
        sumofrs = findViewById(R.id.sumofrs);
        onaccountof = findViewById(R.id.onaccountof);
        rs = findViewById(R.id.rs);
        remark = findViewById(R.id.remark);
        enteredby = findViewById(R.id.enteredby);
        applicationno = findViewById(R.id.applicationno);
        signature=findViewById(R.id.signature);
        qrcode=findViewById(R.id.qrcode);
        date=findViewById(R.id.date);

        if (StaticData.insertTLResponse != null) {
            recieprno.setText(StaticData.insertTLResponse.getReceiptNo());
            recievedfrom.setText(StaticData.insertTLResponse.getReceivedFrom());
            sumofrs.setText(StaticData.insertTLResponse.getReceiptNo());
           // onaccountof.setText(StaticData.insertTLResponse.getReceivedFrom());
            rs.setText(String.valueOf(StaticData.insertTLResponse.getAmount()));
            remark.setText("Dummy data");
            enteredby.setText(StaticData.insertTLResponse.getReceivedFrom());
            applicationno.setText(StaticData.insertTLResponse.getFormNumber());
            date.setText(StaticData.insertTLResponse.getPaymentdate());

            Picasso.with(getApplicationContext())
                    .load(StaticData.insertTLResponse.getTLFormReceiptSig())
                    .noFade()
                    .into(signature);

          /*  Picasso.with(getApplicationContext())
                    .load(StaticData.insertTLResponse.getCompLogo())
                    .noFade()
                    .into(qrcode);*/

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i=new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
