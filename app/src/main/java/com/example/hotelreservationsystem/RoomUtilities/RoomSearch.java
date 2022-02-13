package com.example.hotelreservationsystem.RoomUtilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotelreservationsystem.Adapters.RoomRecyclerAdapter;
import com.example.hotelreservationsystem.Model.Room;
import com.example.hotelreservationsystem.MySingleton;
import com.example.hotelreservationsystem.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// TODO: 1/14/2022 all case search
/*
==================================================
==      made by Bisan El Gool - 11181116        ==
==================================================
*/
public class RoomSearch extends AppCompatActivity {

    private static final String BASE_URL = "http://10.0.2.2:80/project_mobile/filter_search.php";
    private List<Room> roomsList = new ArrayList<>();
    private RecyclerView recycler;

    private Button datePicker;
    MaterialDatePicker.Builder<Pair<Long, Long>> builder;
    MaterialDatePicker picker;

    SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor prefsEditor;

    private TextView arrival_date;
    private TextView department_date;
    private EditText minprice_txt;
    private EditText maxprice_txt;

    private String arrivalDate;
    private String departmentDate;
    private String floorStr;
    private String bedsStr;
    private String typeStr;
    private String minPriceStr;
    private String maxPriceStr;
    private String wifiStr = "n";
    private String tvStr = "n";
    private String acStr = "n";
    private String bkStr = "n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_search);

//        String date="Mar 10, 2016 6:30:00 PM";
//        SimpleDateFormat spf=new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");
//        Date newDate= null;
//        try {
//            newDate = spf.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        spf= new SimpleDateFormat("dd MMM yyyy");
//        date = spf.format(newDate);
//        System.out.println(date);


        initViews();
        populateDropDownMenues();
        initDatePicker();



    }

    private void getPriceRange() {
        minPriceStr = String.valueOf(minprice_txt.getText());
        maxPriceStr = String.valueOf(maxprice_txt.getText());
    }

    private void initViews() {
        arrival_date = findViewById(R.id.arrival_date);
        department_date = findViewById(R.id.department_date);
        minprice_txt = findViewById(R.id.minprice_txt);
        maxprice_txt = findViewById(R.id.maxprice_txt);
    }

    private void initDatePicker() {
        datePicker = findViewById(R.id.datepickerSearch_btn);
//        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder = MaterialDatePicker.Builder.dateRangePicker();

        picker = builder.build();
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

//set variables of 'myObject', etc.

        prefsEditor = mPrefs.edit();
        Gson gson = new Gson();

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
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
                arrival_date.setText(arrivalDate);
                SimpleDateFormat simpleFormat2 = new SimpleDateFormat("yyyy-MM-dd");

                Date date2 = new Date(endDate);
                departmentDate = simpleFormat2.format(date2);
                department_date.setText(departmentDate);

                Toast.makeText(RoomSearch.this, "selected date:" + simpleFormat.format(date1), Toast.LENGTH_SHORT).show();

                String json1 = gson.toJson(selection);
                prefsEditor.putString("DATE_PICKER", json1);
                prefsEditor.commit();
            }
        });


    }

    private void populateDropDownMenues() {


        //=========================================== POPULATE MENU FOR BEDS================================================================================

        String bedsNum[] = getResources().getStringArray(R.array.beds);
        ArrayAdapter<String> bedNumAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_beds_num, bedsNum);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.beds_num_auto_searchlayout);
        textView.setAdapter(bedNumAdapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                bedsStr = bedsNum[(position)];
                Toast.makeText(RoomSearch.this, bedsStr, Toast.LENGTH_SHORT).show();

            }

        });

        //=========================================== POPULATE MENU FOR FLOOR================================================================================
        String floor[] = getResources().getStringArray(R.array.floor);
        ArrayAdapter<String> floorAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_floor, floor);
        AutoCompleteTextView textView2 = (AutoCompleteTextView) findViewById(R.id.floor_auto_searchlayout);
        textView2.setAdapter(floorAdapter);

        textView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                floorStr = floor[(position)];
                Toast.makeText(RoomSearch.this, floorStr, Toast.LENGTH_SHORT).show();

            }

        });
        //=========================================== POPULATE MENU FOR ROOM TYPE================================================================================
        String type[] = getResources().getStringArray(R.array.room_type_dropdown);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_beds_num, type);
        AutoCompleteTextView textView3 = (AutoCompleteTextView) findViewById(R.id.room_type_auto_searchlayout);
        textView3.setAdapter(typeAdapter);
        textView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeStr = type[(position)];
                Toast.makeText(RoomSearch.this, typeStr, Toast.LENGTH_SHORT).show();

            }

        });


    }

    public void wifiCheckBox(View v) {
        //code to check if this checkbox is checked!

        CheckBox checkBox = (CheckBox) v;
        switch (getResources().getResourceEntryName(checkBox.getId())) {
            case "wifi_box":
                if (checkBox.isChecked()) {
                    Toast.makeText(RoomSearch.this, getResources().getResourceEntryName(checkBox.getId()) + " y", Toast.LENGTH_SHORT).show();
                    wifiStr = "y";
                } else {
                    wifiStr = "n";
                    Toast.makeText(RoomSearch.this, wifiStr, Toast.LENGTH_SHORT).show();
                }
                break;
            case "tv_box":
                if (checkBox.isChecked()) {
                    Toast.makeText(RoomSearch.this, getResources().getResourceEntryName(checkBox.getId()), Toast.LENGTH_SHORT).show();
                    tvStr = "y";
                } else {
                    tvStr = "n";
                    Toast.makeText(RoomSearch.this, tvStr, Toast.LENGTH_SHORT).show();
                }
                break;
            case "ac_box":
                if (checkBox.isChecked()) {
                    Toast.makeText(RoomSearch.this, getResources().getResourceEntryName(checkBox.getId()), Toast.LENGTH_SHORT).show();
                    acStr = "y";
                } else {
                    acStr = "n";
                    Toast.makeText(RoomSearch.this, acStr, Toast.LENGTH_SHORT).show();
                }
                break;
            case "freebk_box":
                if (checkBox.isChecked()) {
                    Toast.makeText(RoomSearch.this, getResources().getResourceEntryName(checkBox.getId()), Toast.LENGTH_SHORT).show();
                    bkStr = "y";
                } else {
                    bkStr = "n";
                    Toast.makeText(RoomSearch.this, bkStr, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                // code block
        }
    }


    public void loadItems (View view) {
        getPriceRange();
        String url = BASE_URL + "?minprice="+minPriceStr+"&maxprice="+maxPriceStr+"&floor="+floorStr+"&type="+typeStr+"&clean=y"+"&beds="
                +bedsStr+"&wifi="+wifiStr+"&bk="+bkStr+"&ac="+acStr+"&tv="+tvStr+"&arrival="+arrivalDate+"&department="+departmentDate;
        Intent intent = new Intent(getBaseContext(), RoomRecycler.class);
        intent.putExtra("url", url);
        startActivity(intent);

    }



}

//    import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import comp.sondos438.finallogin.R;
//
//    public class room_amenties extends AppCompatActivity {
//        EditText roomn;
//        EditText haswf;
//        EditText hasAc;
//        EditText hasTv;
//        EditText breakfast;
//        Button btnadd;
//
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_room_amenties);
//            roomn=findViewById(R.id.edtroomnumber);
//            haswf=findViewById(R.id.edthaswifi);
//            hasAc=findViewById(R.id.edthasac);
//            hasTv=findViewById(R.id.edthasac);
//            breakfast=findViewById(R.id.edthasbreakfast);
//            btnadd =findViewById(R.id.btnaddroom_am);
//        }
//
//        public void addondatabase(View view) {
//            String roomnu = roomn.getText().toString();
//            int ronum = Integer.parseInt(roomnu);
//            String wifi = haswf.getText().toString();
//            String ac = hasAc.getText().toString();
//            String tv = hasTv.getText().toString();
//            String breakfa = breakfast.getText().toString();
//            room_amAdd(ronum,wifi,ac,tv,breakfa);
//        }
//        public void room_amAdd(int ronum,String wifi ,String ac,String tv,String breakfa){
//            String url = "http://10.0.2.2:80/login-signup/room_amenties.php";
//            RequestQueue queue = Volley.newRequestQueue(room_amenties.this);
//            StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.e("TAG", "RESPONSE IS " + response);
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        // on below line we are displaying a success toast message.
//                        Toast.makeText(room_amenties.this,
//                                jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }, new com.android.volley.Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // method to handle errors.
//                    Toast.makeText(room_amenties.this,
//                            "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//                }
//            }) {
//                @Override
//                public String getBodyContentType() {
//                    // as we are passing data in the form of url encoded
//                    // so we are passing the content type below
//                    return "application/x-www-form-urlencoded; charset=UTF-8";
//                }
//
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("RNO", String.valueOf(ronum));
//                    params.put("hasWifi", wifi);
//                    params.put("hasFreeBreakfast", ac);
//                    params.put("hasAC", tv);
//                    params.put("hasTV", breakfa);
//
//                    // at last we are returning our params.
//                    return params;
//                }
//            /*
//            * @OverRide
//public Map<String, String> getParams() throws AuthFailureError{
//Map<String, String> params = new HashMap<>();
//params.put("Content-Type", "application/json");
//params.put("Authorization", "Bearer " + Utils.readSharedSetting(context, "access_token", ""));
//return params;
//}*/
//            };
//            // below line is to make
//            // a json object request.
//            queue.add(request);
//        }
//    }

