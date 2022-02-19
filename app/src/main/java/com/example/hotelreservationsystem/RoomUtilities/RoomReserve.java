package com.example.hotelreservationsystem.RoomUtilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.example.hotelreservationsystem.MySingleton;
import com.example.hotelreservationsystem.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: 2/19/2022 remove set isoccuied in web service, it should be changed in check in
/*
==================================================
==      made by Bisan El Gool - 1181116        ==
==================================================
*/
public class RoomReserve extends AppCompatActivity {
    SharedPreferences mPrefs;

    private Toolbar toolbar;


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
        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

//        setTitle("Reserve Room");
//        getSupportActionBar().setIcon(R.drawable.wifi);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        initViews();
        setDatePicker();
//        setDatePicker();
        initDatePicker();
        Intent intent = getIntent();
        // TODO: 2/19/2022 change it back to shared prefs
       // room_id = intent.getStringExtra("ROOM_RNO");
        room_id = ROOM_NUM;
        user_id = "211867844";

    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                Toast.makeText(RoomReserve.this, "hello", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.drawer_menu, menu);
    MenuItem item = menu.findItem(R.id.search_menu_btn);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_menu_btn) {
                            Toast.makeText(RoomReserve.this, "hello", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void initViews() {
        arrival_date2 = findViewById(R.id.arrival_date2);
        department_date2 = findViewById(R.id.department_date2);

    }
    public void setDatePicker(){


        Gson gson = new Gson();
        String json = mPrefs.getString("DATE_PICKER", "");

        androidx.core.util.Pair<Long,Long> selectionObj = gson.fromJson(json, androidx.core.util.Pair.class);
        if(selectionObj==(null)){
            return;
        }
        Long startDate = selectionObj.first;
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

                androidx.core.util.Pair<Long,Long> selectionObj;
                selectionObj=selection;
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
    public void bookRoom(View view) {// TODO: 1/15/2022 add user id from shared preference
        String url="http://10.0.2.2:80/project_mobile/book_room.php?id="+user_id+"&rno="+room_id+"&arrival="+arrivalDate+"&depart="+departmentDate;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                        }catch (Exception e){
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(com.example.hotelreservationsystem.RoomUtilities.RoomReserve.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}