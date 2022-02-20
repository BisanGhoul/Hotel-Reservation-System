package com.example.hotelreservationsystem.RoomUtilities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotelreservationsystem.Adapters.RoomRecyclerAdapter;
import com.example.hotelreservationsystem.Login;
import com.example.hotelreservationsystem.Model.Room;
import com.example.hotelreservationsystem.Model.User;
import com.example.hotelreservationsystem.MySingleton;
import com.example.hotelreservationsystem.Profile;
import com.example.hotelreservationsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/*
==================================================
==      made by Bisan El Gool - 11181116        ==
==================================================
*/
// TODO: 1/10/2022 button filter doesnt work 
public class RoomRecycler extends AppCompatActivity {
    Button btn;
    private List<Room> roomsList = new ArrayList<>();
    private RecyclerView recycler;
    private static final String BASE_URL = "http://10.0.2.2:80/project_mobile/get_rooms.php";
    private static final String FILTER_URL = "http://10.0.2.2:80/project_mobile/all_rooms.php";

    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;

    String url;

    @Override
    protected void onStart() {
        super.onStart();
//        url = BASE_URL;
//        Intent intent = getIntent();
//        try {
//            url = intent.getStringExtra("SEARCH_URL");
//            Log.e("roomrecycler url", url);
//        }catch (Exception e){
//            Log.e("erorr","null exception");
//        }
//        if(url.equals(null)){
//            url = BASE_URL;
//        }else{
////            url = intent.getStringExtra("SEARCH_URL");
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_recycler);

        url = BASE_URL;
        Intent intent = getIntent();
        try {
            url = intent.getStringExtra("SEARCH_URL");
        }catch (Exception e){
            Log.e("erorr","null exception");
        }
        if(url==null||url.equals(null)||url.equals("")||url.isEmpty()){
            url = BASE_URL;
        }else{
//            url = intent.getStringExtra("SEARCH_URL");
        }

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEditor = mPrefs.edit();
        User user = new Gson().fromJson(mPrefs.getString("USER_INFO", null), User.class);

        btn = (Button) findViewById(R.id.filter_btn);
        recycler = findViewById(R.id.rooms_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems(url);
        RoomRecyclerAdapter adapter = new RoomRecyclerAdapter(RoomRecycler.this,
                roomsList);
        recycler.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.btm_nav_bar);
        bottomNav.setSelectedItemId(R.id.home_menu);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case(R.id.home_menu):
                        return true;

                    case(R.id.search_menu):
                        startActivity(new Intent(getApplicationContext(),RoomSearch.class));
                        return true;

                    case(R.id.profile_menu):
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        return true;
                }
                return false;
            }
        });
    }

    public void loadItems2(View view) {
        Intent intent = new Intent(getBaseContext(), RoomSearch.class);

        startActivity(intent);


    }


    private void loadItems(String url) {
        roomsList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);
                                int id = object.optInt("RNO");
                                double price = object.optDouble("rprice");
                                String isoccupiedStr= object.optString("isOccupied");
                                int floor = object.optInt("RFLOOR");
                                String type= object.optString("RTYPE");
                                String isCleanStr= object.optString("isClean");
                                int numofbeds = object.optInt("bedsNum");
                                String pic=object.optString("link");
                                String wifi= object.optString("hasWifi");
                                String freeBreakfast= object.optString("hasFreeBreakfast");
                                String AC= object.optString("hasAC");
                                String TV= object.optString("hasTV");

                                Log.e("roomrecycler pic", pic);
                                Room room = new Room(id,price,isoccupiedStr,floor,type,isCleanStr,numofbeds,wifi,freeBreakfast,AC,TV);
                                room.setPic(pic);
                                roomsList.add(room);
                            }

                        }catch (Exception e){
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RoomRecycler.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}