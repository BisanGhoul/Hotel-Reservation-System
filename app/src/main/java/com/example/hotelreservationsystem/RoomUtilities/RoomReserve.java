package com.example.hotelreservationsystem.RoomUtilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotelreservationsystem.Model.User;
import com.example.hotelreservationsystem.MySingleton;
import com.example.hotelreservationsystem.Profile;
import com.example.hotelreservationsystem.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: 2/20/2022 go back to previous activity after booking 
// TODO: 2/19/2022 remove set isoccuied in web service, it should be changed in check in
/*
==================================================
==      made by Bisan El Gool - 1181116        ==
==================================================
*/
public class RoomReserve extends AppCompatActivity {

    User user;
    SharedPreferences mPrefs;

    private static final String USER_INFO = "USER_INFO";
    private static final String BASE_URL = "http://10.0.2.2:80/project_mobile/get_rooms.php";
    String room_id;
    String user_id;// TODO: 1/14/2022 shared preference to book room 
    String departmentDate;
    String arrivalDate;

    private Button datePicker;
    MaterialDatePicker.Builder<Pair<Long, Long>> builder;
    MaterialDatePicker picker;

    private TextView arrival_date2;
    private TextView department_date2;

    private static final String ROOM_NUM = "1";


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reserve);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        initViews();

//        boolean fromSearch = mPrefs.getBoolean("FROM_SEARCH", false);
//        if (fromSearch == true) {
//            setDatePicker();
//        }

        initDatePicker();
        Intent intent = getIntent();
        // TODO: 2/19/2022 change it back to shared prefs
        room_id = intent.getStringExtra("ROOM_RNO");

        Gson gson = new Gson();
        String json = mPrefs.getString(USER_INFO, "");
        user = gson.fromJson(json, User.class);

        if (user == null) {
            Toast.makeText(RoomReserve.this, "Fatal error. Account not found!", Toast.LENGTH_SHORT).show();
            return;
        }
        user_id = user.getID();

    }

    private void initViews() {
        arrival_date2 = findViewById(R.id.arrival_date2);
        department_date2 = findViewById(R.id.department_date2);

    }

    public void setDatePicker() {

        Gson gson = new Gson();
        String json = mPrefs.getString("DATE_PICKER", "");

        androidx.core.util.Pair<Long, Long> selectionObj = gson.fromJson(json, androidx.core.util.Pair.class);
        if (selectionObj == (null)) {
            return;
        }
        Long startDate = (selectionObj.first);
        Long endDate = selectionObj.second;

        //2022-01-12 -> sql date format
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date(startDate);
        arrivalDate = simpleFormat.format(date1);
        arrival_date2.setText(arrivalDate);
        SimpleDateFormat simpleFormat2 = new SimpleDateFormat("yyyy-MM-dd");

        Date date2 = new Date(endDate);
        departmentDate = simpleFormat2.format(date2);
        department_date2.setText(departmentDate);
    }


    public void initDatePicker() {
        datePicker = findViewById(R.id.datepickerSearch_btn3);
//        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder = MaterialDatePicker.Builder.dateRangePicker();

        picker = builder.build();
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });


        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<androidx.core.util.Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {

                androidx.core.util.Pair<Long, Long> selectionObj;
                selectionObj = selection;
                Long startDate = selection.first;
                Long endDate = selection.second;

                //2022-01-12 -> sql date format
                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = new Date(startDate);
                arrivalDate = simpleFormat.format(date1);
                arrival_date2.setText(arrivalDate);
                SimpleDateFormat simpleFormat2 = new SimpleDateFormat("yyyy-MM-dd");

                Date date2 = new Date(endDate);
                departmentDate = simpleFormat2.format(date2);
                department_date2.setText(departmentDate);

                Toast.makeText(RoomReserve.this, "selected date:" + simpleFormat.format(date1), Toast.LENGTH_SHORT).show();


            }
        });

    }
public void bookRoom(View view){
    bookRoomAPI();
    redirect();
}
    public void bookRoomAPI() {// TODO: 1/15/2022 add user id from shared preference
        String url = "http://10.0.2.2:80/project_mobile/book_room.php?id=" + user_id + "&rno=" + room_id + "&arrival=" + arrivalDate + "&depart=" + departmentDate;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
//                            Toast.makeText(com.example.hotelreservationsystem.RoomUtilities.RoomReserve.this,"Room was booked successfully!!", Toast.LENGTH_LONG).show();
//
//                            Intent intent = new Intent(getBaseContext(), RoomRecycler.class);
//                            startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(com.example.hotelreservationsystem.RoomUtilities.RoomReserve.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
void redirect(){
                                Toast.makeText(com.example.hotelreservationsystem.RoomUtilities.RoomReserve.this,"Room was booked successfully!!", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getBaseContext(), RoomRecycler.class);
                            startActivity(intent);
}
}