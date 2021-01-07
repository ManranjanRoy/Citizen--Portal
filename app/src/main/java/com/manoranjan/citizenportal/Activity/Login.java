package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manoranjan.citizenportal.Api.ApiLinks;
import com.manoranjan.citizenportal.Api.Configss;
import com.manoranjan.citizenportal.Api.CryptUtil;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.PresenterImp.LoginPresenterImp;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.Example;
import com.manoranjan.citizenportal.model.GetprofileModel;
import com.manoranjan.citizenportal.presenter.LoginPresenter;
import com.manoranjan.citizenportal.service.CountryService;
import com.manoranjan.citizenportal.view.LoginView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Login extends AppCompatActivity  implements LoginView {
    TextView signup;
    TextInputLayout emaillayout,passwordlayput;
    private EditText editTextEmail;
    private EditText editTextPassword;
    TextView linkforgotpass;
    private Button buttonLogin;
    LoginPresenter loginPresenter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup = findViewById(R.id.linkSignupadmin);
        loginPresenter=new LoginPresenterImp(this);
        loginPresenter.getcontext(getApplicationContext());
        progressDialog=new ProgressDialog(Login.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        emaillayout=findViewById(R.id.emaillayout);
        passwordlayput=findViewById(R.id.passwordlayout);
        editTextEmail = (EditText) findViewById(R.id.edittextmobileno);
        editTextPassword = findViewById(R.id.editTextPassword);
        //linkforgotpass = (TextView) findViewById(R.id.linkforgotpass);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
                signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUpActivity.class));
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatefiled()){
                    loginfunction(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                    //startActivity(new Intent(Login.this, Form1Activity.class));
                }
                //loginfunction(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                //startActivity(new Intent(Login.this, MainActivity.class));
            }
        });
        TextView forgottext=findViewById(R.id.forgot_txt);
        String html = "<u>Forgot password?</u>";
        forgottext.setText(Html.fromHtml(html));
    }
    @Override
    public void onSucess() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }



    @Override
    public void onError(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean validatefiled() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        /*Intent intent = new Intent(Login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
        // finish();
        if (email.isEmpty()){
            emaillayout.setError("Enter Your Email");
            return false;
        } else if (!TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emaillayout.setError("Enter Correct Email");
            return false;
        }else if (password.isEmpty()) {
            passwordlayput.setError("Enter your Password");
            return false;
        }
        emaillayout.setError(null);
        passwordlayput.setError(null);

        return true;

    }

    @Override
    public void Showprogess() {
        progressDialog.show();
    }

    @Override
    public void dismissproggress() {
        progressDialog.dismiss();
        //
    }
    public void loginfunction(final String email, final String password) {
        // loginView.onError();
       /* JsonObject obj = new JsonObject();
        final JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("username", email);
           paramObject.put("password", password);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(paramObject);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag", "signin2");
        jsonObject.put("spname", "USP_LOGIN");
        jsonObject.put("json", jsonArray);
            Log.d("jsondata",jsonObject.toString());

            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

      JsonObject obj= RequestData.login(email,password);

        if (validatefiled()){
            Showprogess();
            CountryService countryService=new CountryService();
            countryService.getAPI().login(obj).enqueue(new Callback<List<Example>>() {
                @Override
                public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                    Log.d("response", response.body().toString());
                    dismissproggress();
                    if (response.body()!=null) {
                       // Log.d("dataarray",response.body().get(0).getECityzenID().toString());
                        if (response.body().size()>0){
                            Example e=response.body().get(0);
                            Log.d("response", response.body().toString());
                            List<Example>  loginResponse = response.body();
                        boolean test=false;
                            for (int i=0;i<loginResponse.size();i++) {
                                try {
                                    Log.d("passd", CryptUtil.decrypt(loginResponse.get(i).getPass()));
                                    String pass=CryptUtil.decrypt(loginResponse.get(i).getPass());
                                    if (password.equals(pass)) {
                                        test=true;
                                        Toast.makeText(getApplicationContext(), "Sucessfully Login", Toast.LENGTH_SHORT).show();
                                        SharedPreferences sharedPreferences = getSharedPreferences
                                                (Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                        //Creating editor to store values to shared preferences
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        //Adding values to editor
                                        editor.putBoolean(Configss.LOGGEDIN_SHARED_PREF, true);
                                        editor.putBoolean(Configss.Profile_status, false);
                                        editor.putString(Configss.login_role, "0");
                                        editor.putString(Configss.tokencode, response.body().get(0).getEmail());
                                        editor.putString(Configss.citizen_id, String.valueOf(response.body().get(0).getECityzenID()));
                                        editor.commit();
                                        getprofile(email);
                                        break;
                                    }
                                } catch (NoSuchPaddingException ex) {
                                    ex.printStackTrace();
                                } catch (NoSuchAlgorithmException ex) {
                                    ex.printStackTrace();
                                } catch (InvalidAlgorithmParameterException ex) {
                                    ex.printStackTrace();
                                } catch (InvalidKeyException ex) {
                                    ex.printStackTrace();
                                } catch (BadPaddingException ex) {
                                    ex.printStackTrace();
                                } catch (IllegalBlockSizeException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            if (!test){
                                onError("Error ");
                            }

                        } else {
                            onError("Error enter Correct email password");
                        }


                    }

                }

                @Override
                public void onFailure(Call<List<Example>> call, Throwable t) {
                    dismissproggress();
                }
            });
        }
        // loginView.onSucess();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }

    public void getprofile(final String email1) {

        JsonObject obj= RequestData.getprofile(email1);
        if (validatefiled()){
            Showprogess();
            CountryService countryService=new CountryService();
            countryService.getAPI().getprofile(obj).enqueue(new Callback<List<GetprofileModel>>() {
                @Override
                public void onResponse(Call<List<GetprofileModel>> call, Response<List<GetprofileModel>> response) {
                    Log.d("response", response.body().toString());
                    dismissproggress();
                    if (response.body()!=null) {
                        if (response.body().size()>0){
                            GetprofileModel e=response.body().get(0);
                            if (e.getISActive().equals("Y")){
                                SharedPreferences sharedPreferences = getSharedPreferences
                                        (Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean(Configss.Profile_status, true);
                                editor.commit();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }else{
                                SharedPreferences sharedPreferences = getSharedPreferences
                                        (Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean(Configss.Profile_status, false);
                                editor.commit();
                                Intent intent = new Intent(Login.this, Form1Activity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }

                        } else {

                        }
                    }
                }
                @Override
                public void onFailure(Call<List<GetprofileModel>> call, Throwable t) {
                    dismissproggress();
                }
            });
        }
    }

}
