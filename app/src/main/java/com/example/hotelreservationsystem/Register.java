package com.example.hotelreservationsystem;
// TODO: 1/15/2022 validate 
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotelreservationsystem.Model.User;
import com.example.hotelreservationsystem.RoomUtilities.RoomSearch;
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
    String id;
    int userid;
    String NameC;
    String email;
    String password;
    String National;
    String G;
    String phone;
    String Type;

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
        email = edtemail.getText().toString();
        email.trim();
        password = edtpass.getText().toString();
        password.trim();
        boolean valid=true;
        if(email.isEmpty()||email.equals(null)||email.equals("")||!(email.matches("^(.+)@(.+)$"))){
            Toast.makeText(this, "Invlaid email!", Toast.LENGTH_SHORT).show();
            valid=false;
//            return;
        }
        if(password.isEmpty()||password.equals(null)||password.equals("")||!(password.matches(PASS_PATTERN))){
            Toast.makeText(this, "Invlaid email!", Toast.LENGTH_SHORT).show();
            valid=false;
//            return;
        }
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
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
//                R.array.type, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        type.setAdapter(adapter2);

    }

    private void AddUser(int id,String NameC,String Email,String password,String National,String G,String phone,String Type)
    {
//
        String url = "http://10.0.2.2:80/login-signup/signup.php";
        RequestQueue queue = Volley.newRequestQueue(Register.this);
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

                // at last we are returning our params.
                return params;
            }
            /*
            * @OverRide
public Map<String, String> getParams() throws AuthFailureError{
Map<String, String> params = new HashMap<>();
params.put("Content-Type", "application/json");
params.put("Authorization", "Bearer " + Utils.readSharedSetting(context, "access_token", ""));
return params;
}*/
        };
        // below line is to make
        // a json object request.
        MySingleton.getInstance(this).addToRequestQueue(request);
    }



    public void GoToRegister(View view) {

         id = ID.getText().toString();
         userid = Integer.parseInt(id);
         NameC = Name.getText().toString();
//         Email = email.getText().toString();
//         Password = password.getText().toString();
         National = Nationality.getText().toString();
         G = gender.getSelectedItem().toString();
         phone = phoneNumber.getText().toString();
//         Type = type.getSelectedItem().toString();
        AddUser(userid,NameC,email,password,National,G,phone,"client");
//   public User(String ID, String name, String email, String password, String nationality, String gender, String phone, String type) {

        Gson gson = new Gson();

            User user= new User(id,NameC,email,password,National,G,phone,"client");

        String json1 = gson.toJson(user);
        prefsEditor.putString(USER_INFO, json1);
        prefsEditor.putBoolean(REGISTERED_USER,true);
        prefsEditor.commit();
        Intent intent = new Intent(getBaseContext(), Login.class);

        startActivity(intent);

//        if(String.valueOf(id).length()!=10){
//            Toast.makeText(Register.this, "not valid ID",
//                    Toast.LENGTH_SHORT).show();
//        }
//        else if(phone.length()!=10){
//            Toast.makeText(Register.this, "not valid phone number",
//                    Toast.LENGTH_SHORT).show();
//        }
//        else{
//
//        }


    }

}
