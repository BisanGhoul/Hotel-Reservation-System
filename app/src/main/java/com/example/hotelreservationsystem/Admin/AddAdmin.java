package com.example.hotelreservationsystem.Admin;
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

import com.example.hotelreservationsystem.Login;
import com.example.hotelreservationsystem.Model.User;
import com.example.hotelreservationsystem.MySingleton;
import com.example.hotelreservationsystem.R;
import com.google.gson.Gson;


public class AddAdmin extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        setUpViews();

    }

    public void setUpViews() {
        ID = findViewById(R.id.edtAdminID);
        Name = findViewById(R.id.edtAdminName);
        edtemail = findViewById(R.id.edtAdminemail);
        edtpass = findViewById(R.id.edtAdminpassword);
        gender = findViewById(R.id.spinnerAdmingender);
        Nationality = findViewById(R.id.edtAdminnationality);
        phoneNumber = findViewById(R.id.edtAdminphonenumber);
        Register = findViewById(R.id.btnAdminregDone);
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
        AddUser(userid, NameCStr, emailStr, passwordStr, NationalStr, GStr, phoneStr,"admin");
        Toast.makeText(AddAdmin.this,
                "Admin has been added succesfully!", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(AddAdmin.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(AddAdmin.this,
                        "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
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
                return params;
            }

        };

        MySingleton.getInstance(this).addToRequestQueue(request);
    }


}
