package com.example.hotelreservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotelreservationsystem.Model.User;
import com.example.hotelreservationsystem.RoomUtilities.RoomRecycler;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private TextView Signup;
    private EditText edtemail;
    private EditText edtpass;
//    private CheckBox Remember;
    private Button Sign_In;
    private User user;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static final String EMAIL = "EMAIL";
    private static final String PASS = "PASS";
    private static final String FLAG = "FLAG";

    private static final String USER_INFO = "USER_INFO";
    private static final String REGISTERED_USER = "REGISTERED_USER";
    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";

    private static final String PASS_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}";
    private boolean isRegistered;
    User responseUser;
    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;

    String email;
    String password;
    @Override
    protected void onStart() {
        super.onStart();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEditor = mPrefs.edit();
        prefsEditor.putBoolean(IS_LOGGED_IN, false);
        prefsEditor.commit();
        //check is user was redirected from register page
        isRegistered = mPrefs.getBoolean(REGISTERED_USER, false);
        if (isRegistered == false) {
            return;
        } else {
            user = new Gson().fromJson(mPrefs.getString(USER_INFO, null), User.class);
            edtemail.setText(user.getEmail());
            edtpass.setText(user.getPassword());
        }
//        SharedPreferences pref = getSharedPreferences("edtrem",MODE_PRIVATE);
//        String StayLogIN = pref.getString("StayLogIN","");
//        if(StayLogIN.equals("true")){
//               Intent intent = new Intent(this,admin.class);
//               startActivity(intent);
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Signup = findViewById(R.id.Sign_up);
        setUpViews();
    }

    public void setUpViews() {
        edtemail = findViewById(R.id.edtemaillogin);
        edtpass = findViewById(R.id.edtpasslogin);
//        Remember = findViewById(R.id.checkBox);
        Sign_In = findViewById(R.id.SIGN_IN);
    }

    public void GoToRigister(View view) {
        Intent go = new Intent(this, Register.class);
        startActivity(go);
    }


    public void logIn (View view){
//        if(validateInput()){
            signInuser();
//        Log.e("infoprint2","done "+responseUser.getID()+responseUser.getName());

//        if (responseUser==null)
//            {
//                Toast.makeText(this, "Invlaid email or password! please try again.", Toast.LENGTH_SHORT).show();

//            }else {
//                setUpPref();
                goToMain();
//            }
//        }else{
//            Toast.makeText(this, "Invlaid email or password! please try again.", Toast.LENGTH_SHORT).show();

//        }

    }

    private void goToMain() {
        Intent intent = new Intent(getBaseContext(), RoomRecycler.class);
        startActivity(intent);

    }


    private void setUpPref() {
        Gson gson = new Gson();
        String json1 = gson.toJson(responseUser);
        prefsEditor.putString(USER_INFO, json1);
        prefsEditor.putBoolean(IS_LOGGED_IN,true);
        prefsEditor.commit();
    }


    private boolean validateInput() {
         email = edtemail.getText().toString();
        email.trim();
         password = edtpass.getText().toString();
        password.trim();
        boolean valid=true;
        if(email.isEmpty()||email.equals(null)||email.equals("")){
            Toast.makeText(this, "Invlaid email!", Toast.LENGTH_SHORT).show();
            valid=false;
//            return;
        }
        if(password.isEmpty()||password.equals(null)||password.equals("")||!(password.matches(PASS_PATTERN))){
            Toast.makeText(this, "Invlaid email!", Toast.LENGTH_SHORT).show();
            valid=false;
//            return;
        }
        return valid;
    }


    public void signInuser() {
        email = edtemail.getText().toString();
        email.trim();
        password = edtpass.getText().toString();
        password.trim();
            String url = "http://10.0.2.2:80/project_mobile/login.php?email=" + email + "&password=" + password;
            Log.e("urlprint",url);
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String output) {

                    try {
//{","email":"jamal20@gmail.com","password":"123","CNATIONALITY":"Egyptian","CGENDER":"male","CPHONE":"0247168346","type":"client"}
                        JSONObject object = new JSONObject(output);
                        String id = object.optString("CID");
                        String name= object.optString("CNAME");
                        String email= object.optString("email");
                        String password= object.optString("password");
                        String CNATIONALITY= object.optString("CNATIONALITY");
                        String CGENDER= object.optString("CGENDER");
                        String CPHONE= object.optString("CPHONE");
                        String type= object.optString("type");
//    public User(String ID, String name, String email, String password, String nationality, String gender, String phone, String type) {
                        User responseUser = new User(id,name,email,password,CNATIONALITY,CGENDER,CPHONE,type);
                        Log.e("infoprint","done "+id+responseUser.getID()+responseUser.getName());
//                        Toast.makeText(Login.this, "done "+object.toString()+responseUser.getID()+responseUser.getName(),
//                                Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        String json1 = gson.toJson(responseUser);
                        prefsEditor.putString(USER_INFO, json1);
                        prefsEditor.putBoolean(IS_LOGGED_IN,true);
                        prefsEditor.commit();
                    }catch (Exception e){
                    }
                }

            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(Login.this, error.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            MySingleton.getInstance(this).addToRequestQueue(request);
    }
}