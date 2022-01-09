package com.example.hotelreservationsystem.RoomUtilities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotelreservationsystem.Adapters.RoomRecyclerAdapter;
import com.example.hotelreservationsystem.Model.Room;
import com.example.hotelreservationsystem.MySingleton;
import com.example.hotelreservationsystem.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RoomRecycler extends AppCompatActivity {

    private List<Room> roomsList = new ArrayList<>();
    private RecyclerView recycler;
    private static  final String BASE_URL = "http://10.0.2.2:80/project_mobile/get_rooms.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_recycler);

        recycler = findViewById(R.id.rooms_recycler);


        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();
    }

    private void loadItems() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
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


                                Room room = new Room(id,price,isoccupiedStr,floor,type,isCleanStr,numofbeds,wifi,freeBreakfast,AC,TV);
                                roomsList.add(room);
                            }

                        }catch (Exception e){
                        }
                        RoomRecyclerAdapter adapter = new RoomRecyclerAdapter(RoomRecycler.this,
                                roomsList);
                        recycler.setAdapter(adapter);

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