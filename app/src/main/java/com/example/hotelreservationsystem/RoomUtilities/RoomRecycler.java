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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_recycler);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEditor = mPrefs.edit();
        User user = new Gson().fromJson(mPrefs.getString("USER_INFO", null), User.class);


        Toast.makeText(RoomRecycler.this, user.getName(),
                Toast.LENGTH_SHORT).show();

        btn = (Button) findViewById(R.id.filter_btn);
        recycler = findViewById(R.id.rooms_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
//        String sessionId = getIntent().getStringExtra("url");
//        if(sessionId==(null)) {
//            loadItems(BASE_URL);
//        }else{
//            loadItems(sessionId);
        loadItems(BASE_URL);
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
                }
                return false;
            }
        });
//        }
    }

//    btn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            //Perform your action here
//        }
//    });
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
                                //Room(int ID, double price, boolean isOccupied, int floor, String type, boolean isClean, int numOfBeds, int pic, boolean wifi, boolean freeBreakfast, boolean AC, boolean TV) {
//[{"RA_ID":"1","RNO":"1","hasWifi":"y","hasFreeBreakfast":"y","hasAC":"y","hasTV":"y","rprice":"100","isOccupied":"y","RFLOOR":"2","RTYPE":"single","isClean":"y","bedsNum":"1"},
                                int id = object.optInt("RNO");
                                double price = object.optDouble("rprice");
                                String isoccupiedStr= object.optString("isOccupied");
                                int floor = object.optInt("RFLOOR");
                                String type= object.optString("RTYPE");
                                String isCleanStr= object.optString("isClean");
                                int numofbeds = object.optInt("bedsNum");
                                //int pic=object.optInt("RNO");
                                String wifi= object.optString("hasWifi");
                                String freeBreakfast= object.optString("hasFreeBreakfast");
                                String AC= object.optString("hasAC");
                                String TV= object.optString("hasTV");

                                Log.e("id room", String.valueOf(id));
                                Room room = new Room(id,price,isoccupiedStr,floor,type,isCleanStr,numofbeds,wifi,freeBreakfast,AC,TV);
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
        //Volley.newRequestQueue(RoomRecycler.this).add(stringRequest);
    }
}