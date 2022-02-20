package com.example.hotelreservationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelreservationsystem.Admin.MainAdminActivity;
import com.example.hotelreservationsystem.Model.User;
import com.example.hotelreservationsystem.RoomUtilities.RoomRecycler;
import com.example.hotelreservationsystem.RoomUtilities.RoomSearch;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

public class Profile extends AppCompatActivity {

    BottomNavigationView bottomNav;

    private static final String REGISTERED_USER = "REGISTERED_USER";
    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";

    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;

    TextView full_name;
    TextView EMail;
    TextView Phone;

    private static final String USER_INFO = "USER_INFO";
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        Gson gson = new Gson();
        String json = mPrefs.getString(USER_INFO, "");
        Log.e("profile activity json",json);
        user = gson.fromJson(json, User.class);

        if (user == null) {
            Toast.makeText(Profile.this, "Fatal error. Account not found!", Toast.LENGTH_SHORT).show();
            return;
        }

        full_name=findViewById(R.id.tvfullname);
        EMail=findViewById(R.id.tvemail);
        Phone=findViewById(R.id.tvphonenumber);
        String username = user.getName();
        String email = user.getEmail();
        String phone = user.getPhone();
        full_name.setText(username);
        EMail.setText(email);
        Phone.setText(phone);

         bottomNav = findViewById(R.id.btm_nav_bar);
        if(user.getType().equals("admin")){
            bottomNav.setVisibility(View.GONE);
        }
        bottomNav.setSelectedItemId(R.id.home_menu);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case(R.id.home_menu):
                        startActivity(new Intent(getApplicationContext(), RoomRecycler.class));
                        return true;
                    case(R.id.search_menu):
                        startActivity(new Intent(getApplicationContext(), RoomSearch.class));
                        return true;
                    case(R.id.profile_menu):
                        return true;
                }
                return false;
            }
        });
    }

    public void Dologout(View view) {
        prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.apply();
//        finish();
        Intent intent = new Intent(Profile.this,Login.class);
        startActivity(intent);
    }
    public void showMovies(View view) {
        Intent intent = new Intent(Profile.this, MovieActivity.class);
        startActivity(intent);
    }
    public void update(View view) {
    }

    public void deleteprofile(View view) {
    }
}