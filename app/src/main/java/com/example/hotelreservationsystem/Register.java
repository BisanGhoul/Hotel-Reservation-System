package com.example.hotelreservationsystem;
// TODO: 1/15/2022 validate 
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hotelreservationsystem.Model.User;
import com.google.gson.Gson;


public class Register extends AppCompatActivity {

    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;

    private static final String USER_INFO = "USER_INFO";
    private static final String REGISTERED_USER = "REGISTERED_USER";
    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    private static final String PASS_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}";

    private EditText ID;
    private EditText Name;
    private EditText edtemail;
    private EditText edtpass;
    private Spinner gender;
    private EditText Nationality;
    private EditText phoneNumber;
    private Spinner type;
    private Button Register;
    private TextView txtResult;
//REGISTERED_USER
    String idStr;
    int userid;
    String NameCStr;
    String emailStr;
    String passwordStr;
    String NationalStr;
    String GStr;
    String phoneStr;
    String TypeStr;

    @Override
    protected void onStart() {
        super.onStart();
    prefsEditor.putBoolean(IS_LOGGED_IN,false);
    prefsEditor.putBoolean(REGISTERED_USER,false);
    prefsEditor.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEditor = mPrefs.edit();
        setUpViews();

    }

    public void setUpViews() {
        ID = findViewById(R.id.edtID);
        Name = findViewById(R.id.edtName);
        edtemail = findViewById(R.id.edtemail);
        edtpass = findViewById(R.id.edtpassword);
        gender = findViewById(R.id.spinnergender);
        Nationality = findViewById(R.id.edtnationality);
        phoneNumber = findViewById(R.id.edtphonenumber);
//        type = findViewById(R.id.spinnertype);
        Register = findViewById(R.id.btnregDone);
        txtResult = findViewById(R.id.txtResult);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

    }

    private void setValues(){
        idStr = ID.getText().toString();
        userid = Integer.valueOf(idStr);
        NameCStr = Name.getText().toString();
        emailStr = edtemail.getText().toString();
        emailStr.trim();
        passwordStr = edtpass.getText().toString();
        passwordStr.trim();
        NationalStr = Nationality.getText().toString();
        GStr = gender.getSelectedItem().toString();
        phoneStr = phoneNumber.getText().toString();
    }

    public void signUp(View v){
        setValues();
        AddUser(userid, NameCStr, emailStr, passwordStr, NationalStr, GStr, phoneStr,"client");
        goToLogin();
    }
    private void AddUser(int id,String NameC,String Email,String password,String National,String G,String phone,String Type)
    {
//
        String url = "http://10.0.2.2:80/project_mobile/signup.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(Register.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(Register.this,
                        "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("CID", String.valueOf(id));
                params.put("CNAME", NameC);
                params.put("email", Email);
                params.put("password", password);
                params.put("CNATIONALITY", National);
                params.put("CGENDER", G);
                params.put("CPHONE", phone);
                params.put("type", Type);

                User responseUser = new User(String.valueOf(id),NameC,Email,password,NationalStr,G,phone,Type);
                Gson gson = new Gson();
                String json1 = gson.toJson(responseUser);
                prefsEditor.putString(USER_INFO, json1);
                prefsEditor.putBoolean(IS_LOGGED_IN,false);
                prefsEditor.putBoolean(REGISTERED_USER,true);
                prefsEditor.commit();
                // at last we are returning our params.
                return params;
            }

        };
        MySingleton.getInstance(this).addToRequestQueue(request);
    }



    public void goToLogin(View view){
        Intent intent = new Intent(getBaseContext(), Login.class);

        startActivity(intent);
    }
    public void goToLogin(){
        Intent intent = new Intent(getBaseContext(), Login.class);

        startActivity(intent);
    }

//    public void GoToRegister(View view) {
//
//         idStr = ID.getText().toString();
//         userid = Integer.parseInt(idStr);
//         NameCStr = Name.getText().toString();
////         Email = email.getText().toString();
////         Password = password.getText().toString();
//         NationalStr = Nationality.getText().toString();
//         GStr = gender.getSelectedItem().toString();
//         phoneStr = phoneNumber.getText().toString();
////         Type = type.getSelectedItem().toString();
//        AddUser(userid, NameCStr, emailStr, passwordStr, NationalStr, GStr, phoneStr,"client");
////   public User(String ID, String name, String email, String password, String nationality, String gender, String phone, String type) {
//
//        Gson gson = new Gson();
//
//            User user= new User(idStr, NameCStr, emailStr, passwordStr, NationalStr, GStr, phoneStr,"client");
//
//        String json1 = gson.toJson(user);
//        prefsEditor.putString(USER_INFO, json1);
//        prefsEditor.putBoolean(REGISTERED_USER,true);
//        prefsEditor.commit();
//        Intent intent = new Intent(getBaseContext(), Login.class);
//
//        startActivity(intent);
//
////        if(String.valueOf(id).length()!=10){
////            Toast.makeText(Register.this, "not valid ID",
////                    Toast.LENGTH_SHORT).show();
////        }
////        else if(phone.length()!=10){
////            Toast.makeText(Register.this, "not valid phone number",
////                    Toast.LENGTH_SHORT).show();
////        }
////        else{
////
////        }
//
//
//    }

}
