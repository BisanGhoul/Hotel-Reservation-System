package com.example.hotelreservationsystem.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotelreservationsystem.Adapters.UsersRecyclerAdapter;
import com.example.hotelreservationsystem.Model.User;
import com.example.hotelreservationsystem.MySingleton;
import com.example.hotelreservationsystem.R;
import com.example.hotelreservationsystem.RoomUtilities.RoomSearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CheckInOUTCustomer extends AppCompatActivity {

    private List<User> usersList = new ArrayList<>();
    private static final String BASE_URL = "http://10.0.2.2:80/project_mobile/get_users.php";

//    String IDs[];
    String user_id;
    private TextView customer_name_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_out_customer);
        customer_name_txt=findViewById(R.id.customer_name_txt);
        loadItems(BASE_URL);
//        populateDropDownMenues();

    }

    public void CheckIn(View view) {
String url="http://10.0.2.2:80/project_mobile/check_in.php?id="+user_id;
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
                Toast.makeText(com.example.hotelreservationsystem.Admin.CheckInOUTCustomer.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void CheckOut(View view) {
        String url="http://10.0.2.2:80/project_mobile/check_out.php?id="+user_id;
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
                Toast.makeText(com.example.hotelreservationsystem.Admin.CheckInOUTCustomer.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void loadItems(String url) {
//        usersList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                           String IDs[]=new String[array.length()];
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);
                                String id = object.optString("CID");
                                IDs[i]=id;
                                String isoccupiedStr= object.optString("CNAME");
                                //    public User(double ID, String name, String email, String password, String nationality, String gender, String phone, String type) {

                                User room = new User(id,isoccupiedStr);
                                usersList.add(room);
                                populateDropDownMenues(IDs);
                                Log.e("print size", String.valueOf(usersList.size()));
                            }

                        }catch (Exception e){
                        }

//                        UsersRecyclerAdapter adapter = new UsersRecyclerAdapter(com.example.hotelreservationsystem.Admin.UsersRecycler.this,
//                                usersList);
//                        recycler.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                Toast.makeText(com.example.hotelreservationsystem.Admin.UsersRecycler.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        //Volley.newRequestQueue(RoomRecycler.this).add(stringRequest);
    }
    private void populateDropDownMenues(String[] IDs) {
        Log.e("userprint1", String.valueOf(usersList.size()));
        //=========================================== POPULATE MENU FOR USER IDs================================================================================

//        for(int i=0;i<usersList.size();i++){
//            IDs[i]=String.valueOf(usersList.get(i).getID());
//            Log.e("userprint",IDs[i]);
//        }

        ArrayAdapter<String> bedNumAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_beds_num, IDs);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.user_id_auto_searchlayout);
        textView.setAdapter(bedNumAdapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user_id = IDs[(position)];
                for (User u: usersList) {
                    if(u.getID()==(user_id)){
                        customer_name_txt.setText(u.getName());

                    }
                }


                Toast.makeText(CheckInOUTCustomer.this, user_id, Toast.LENGTH_SHORT).show();

            }

        });
    }
}