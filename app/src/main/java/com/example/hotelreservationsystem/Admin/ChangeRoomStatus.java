package com.example.hotelreservationsystem.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotelreservationsystem.Model.Room;
import com.example.hotelreservationsystem.MySingleton;
import com.example.hotelreservationsystem.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChangeRoomStatus extends AppCompatActivity {
    // TODO: 1/14/2022 refresh list
    private List<Room> roomsList = new ArrayList<>();
    private static final String CLEAN_ROOMS_URL = "http://10.0.2.2:80/project_mobile/get_dirty_rooms.php";

    String room_id;
    private TextView room_status_txt;
    private TextView room_floor_txt_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_room_status);
        room_status_txt = findViewById(R.id.room_status_txt);
        room_floor_txt_status = findViewById(R.id.room_floor_txt_status);
        loadDirtyRooms(CLEAN_ROOMS_URL);
//        populateDropDownMenues();

    }

    public void makeClean(View view) {
        loadDirtyRooms(CLEAN_ROOMS_URL);
        String url = "http://10.0.2.2:80/project_mobile/make_room_clean.php?id=" + room_id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                        } catch (Exception e) {
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(com.example.hotelreservationsystem.Admin.ChangeRoomStatus.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void loadDirtyRooms(String url) {
        roomsList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            String IDs[] = new String[array.length()];

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);
                                //Room(int ID, double price, boolean isOccupied, int floor, String type, boolean isClean, int numOfBeds, int pic, boolean wifi, boolean freeBreakfast, boolean AC, boolean TV) {
//[{"RA_ID":"1","RNO":"1","hasWifi":"y","hasFreeBreakfast":"y","hasAC":"y","hasTV":"y","rprice":"100","isOccupied":"y","RFLOOR":"2","RTYPE":"single","isClean":"y","bedsNum":"1"},
                                int id = object.optInt("RNO");
                                IDs[i] = String.valueOf(id);
                                double price = object.optDouble("rprice");
                                String isoccupiedStr = object.optString("isOccupied");
                                int floor = object.optInt("RFLOOR");
                                String type = object.optString("RTYPE");
                                String isCleanStr = object.optString("isClean");
                                int numofbeds = object.optInt("bedsNum");
                                //int pic=object.optInt("RNO");
                                String wifi = object.optString("hasWifi");
                                String freeBreakfast = object.optString("hasFreeBreakfast");
                                String AC = object.optString("hasAC");
                                String TV = object.optString("hasTV");

                                Log.e("id room", String.valueOf(id));
                                Room room = new Room(id, price, isoccupiedStr, floor, type, isCleanStr, numofbeds, wifi, freeBreakfast, AC, TV);
                                roomsList.add(room);
                                initDirtyRoomsMenu(IDs);
                            }

                        } catch (Exception e) {
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                Toast.makeText(com.example.hotelreservationsystem.Admin.UsersRecycler.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void initDirtyRoomsMenu(String[] IDs) {
        Log.e("userprint1", String.valueOf(roomsList.size()));
        //=========================================== POPULATE MENU FOR ROOM IDs================================================================================


        ArrayAdapter<String> roomIdAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_beds_num, IDs);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.room_id_auto_changestatus);
        textView.setAdapter(roomIdAdapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                room_id = IDs[(position)];
                for (Room u : roomsList) {
                    if (u.getID() == (Integer.valueOf(room_id))) {
                        Toast.makeText(ChangeRoomStatus.this, u.isClean(), Toast.LENGTH_SHORT).show();

                        if (u.isClean().equals("y")) {
                            room_status_txt.setText("This Room is Clean");
                            room_floor_txt_status.setText("on floor " + u.getFloor());
                        } else {
                            room_status_txt.setText("This Room Needs Cleaning");
                            room_floor_txt_status.setText("on floor " + u.getFloor());

                        }


                    }
                }


                Toast.makeText(ChangeRoomStatus.this, room_id, Toast.LENGTH_SHORT).show();

            }

        });
    }
}