package com.manoranjan.citizenportal.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Api.Configss;
import com.manoranjan.citizenportal.Api.CryptUtil;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.PresenterImp.SignupprenterImp;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.Example;
import com.manoranjan.citizenportal.presenter.SignupPresenter;
import com.manoranjan.citizenportal.service.CountryService;
import com.manoranjan.citizenportal.view.SignupView;

import org.json.JSONException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SignupView {
    String[] courses = {"SELF", "COMPANY"};
    EditText fname, companyname, email, mobileno, password, otp, hashvalue;
    TextInputLayout textcompany, namelayout, mobilelayout, emaillayout;
    //EditText address1;
    Button buttonsignup;
    SignupPresenter signupPresenter;
    ProgressDialog progressDialog;
    CheckBox checkBox;
    String companytype = "SELF";
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String ABc = "0123456789";
    EditText confirmpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        Spinner spino = findViewById(R.id.coursesspinner);
        spino.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(ad);
        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Please Wait...");
        textcompany = findViewById(R.id.textcompany);
        hashvalue = findViewById(R.id.hashmap);
        fname = findViewById(R.id.fname);
        companyname = findViewById(R.id.lname);
        email = findViewById(R.id.emailid);
        mobileno = findViewById(R.id.phoneno);
        // address1=findViewById(R.id.addressline1);
        password = findViewById(R.id.password);
        otp = findViewById(R.id.otp);
        buttonsignup = findViewById(R.id.buttonsignup);
        checkBox = findViewById(R.id.checkbox);
        confirmpass = findViewById(R.id.confirmpass);
        SecureRandom rnd = new SecureRandom();

        signupPresenter = new SignupprenterImp(this, getApplicationContext());
        findViewById(R.id.buttonsignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (validatefiled()) {
                        showalert1();
                    }

            }
        });
        findViewById(R.id.signup_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });//

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        String b = generateString();
        hashvalue.setText(b);
        String a = randomString(7);
        String c = randomnumber(4);
        password.setText(a);
        otp.setText(c);
    }

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    String randomString(int len) {
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        sb.append("2");
        return sb.toString();
    }

    String randomnumber(int len) {
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(ABc.charAt(rnd.nextInt(ABc.length())));
        return sb.toString();
    }

    @Override
    public void onSucess() {
        Intent intent = new Intent(SignUpActivity.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean validatefiled() {
        ////fname,lname,email,monileno,address1,address2,town,pinciode,password,cpassword
        final String fname1 = fname.getText().toString().trim();
        final String companyname1 = companyname.getText().toString().trim();
        final String email1 = email.getText().toString().trim();
        final String mob = mobileno.getText().toString().trim();
        //final String address11 = address1.getText().toString().trim();
        final String password1 = password.getText().toString().trim();


        if (TextUtils.isEmpty(fname1)) {
            fname.setError("Please Enter Your Name");
            return false;
        } else if (companytype.equals("COMPANY") && TextUtils.isEmpty(companyname1)) {
            companyname.setError("Please  Your Company Name");
            return false;
        } else if (TextUtils.isEmpty(email1)) {
            email.setError("Please Enter your Email");
            return false;
        } else if (!TextUtils.isEmpty(email1) && !Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("Enter Valid Email");
            return false;
        } else if (TextUtils.isEmpty(mob)) {
            mobileno.setError("Enter Your Mobile No");
            return false;
        } else if (mob.length() > 10 || mob.length() < 10) {
            mobileno.setError("Enter Your Mobile No");
            return false;
        } else if (TextUtils.isEmpty(password1)) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            password.setError("Enter Your Password ");
            return false;

        }/*else if (!checkBox.isChecked()) {
            Toast.makeText(getApplicationContext(), "Aggree to terms and privacy policy to continue", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return true;
    }

    @Override
    public void Showprogess() {
        progressDialog.show();
    }

    @Override
    public void dismissproggress() {
          progressDialog.dismiss();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        companytype = courses[position];
        if (courses[position].equals("SELF")) {
            textcompany.setVisibility(View.GONE);
        } else {
            textcompany.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void showalert1() {

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialoglayout1)
                .setPositiveButton("Proceed", null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView pass = dialog.findViewById(R.id.textpass);
                final TextView otp1 = dialog.findViewById(R.id.textotp);
                pass.setText("Password : " + password.getText().toString());
                otp1.setText("OTP :" + otp.getText().toString());
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        // showalert();
                        checkemail(email.getText().toString());
                    }
                });

                Button cancel = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
    }

    private void showalert() {

        //  final EditText input = new EditText(ProductDetailsActivity.this);
        //   input.setHint("    Enter Pincode");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialoglayout)
                .setTitle("Enter Your Pincode")
                .setPositiveButton("Check", null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    EditText input = dialog.findViewById(R.id.input);

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        if (input.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_LONG).show();

                        } else {

                            if (input.getText().toString().trim().equals(otp.getText().toString().trim())) {
                                try {
                                    signup(fname.getText().toString(), companyname.getText().toString(),
                                            mobileno.getText().toString(),
                                            email.getText().toString(),
                                            password.getText().toString(),
                                            otp.getText().toString(), "1");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Enter Correct OTP", Toast.LENGTH_LONG).show();

                            }
                            /*if (verifycode(input.getText().toString())){
                                dialog.dismiss();
                            }else{dialog.dismiss();}*/
                        }

                        //Dismiss once everything is OK.

                    }
                });

                Button cancel = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something

                        //Dismiss once everything is OK.
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
    }


    public void signup(String fname, String cname, String mobileno, final String email, String password, String otp, String ctype) throws JSONException {
         String pass = null;
        try {
             pass=CryptUtil.encrypt(password);
            Log.d("passe", CryptUtil.encrypt(password));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        //Log.d("passd", CryptUtil.decrypt(CryptUtil.encrypt(password)));
        String ctypes;
        if (companytype.equals("SELF")){
            ctypes="0";
        }else{
            ctypes=companytype;
        }

        JsonObject obj = RequestData.signup(fname, cname, mobileno, email, pass, otp, ctypes);

        Showprogess();
        CountryService countryService = new CountryService();
        countryService.getAPI().createUser(obj)
                .enqueue(new Callback<List<Example>>() {
                    @Override
                    public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                        if (response.isSuccessful()) {
                            if (response.body().size()>0) {
                                Toast.makeText(getApplicationContext(), "Registerd Sucessfully", Toast.LENGTH_LONG).show();
                                onSucess();
                            }else{
                                dismissproggress();
                                Toast.makeText(getApplicationContext(), "Registerd Faild", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            dismissproggress();
                            Toast.makeText(getApplicationContext(), "Registerd Faild", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Example>> call, Throwable t) {
                        dismissproggress();
                    }
                });

    }
    public void checkemail(final String email1) {

        JsonObject obj= RequestData.login(email1,"1");
        if (validatefiled()){
            Showprogess();
            CountryService countryService=new CountryService();
            countryService.getAPI().login(obj).enqueue(new Callback<List<Example>>() {
                @Override
                public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                    Log.d("response", response.body().toString());
                    dismissproggress();
                    if (response.body()!=null) {
                        if (response.body().size()>0){
                            Toast.makeText(SignUpActivity.this, "Email id Already Exists", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                signup(fname.getText().toString(), companyname.getText().toString(),
                                        mobileno.getText().toString(),
                                        email.getText().toString(),
                                        password.getText().toString(),
                                        otp.getText().toString(), "1");
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Example>> call, Throwable t) {
                    dismissproggress();
                }
            });
        }
        }

}
