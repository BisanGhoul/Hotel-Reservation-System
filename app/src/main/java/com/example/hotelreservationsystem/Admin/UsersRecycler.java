package com.example.hotelreservationsystem.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotelreservationsystem.Adapters.RoomRecyclerAdapter;
import com.example.hotelreservationsystem.Adapters.UsersRecyclerAdapter;
import com.example.hotelreservationsystem.Model.Room;
import com.example.hotelreservationsystem.Model.User;
import com.example.hotelreservationsystem.MySingleton;
import com.example.hotelreservationsystem.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsersRecycler extends AppCompatActivity {

    private List<User> usersList = new ArrayList<>();
    private RecyclerView recycler;
    private static final String BASE_URL = "http://10.0.2.2:80/project_mobile/get_users.php";
    private static final String FILTER_URL = "http://10.0.2.2:80/project_mobile/all_rooms.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_recycler);
        recycler = findViewById(R.id.users_recycler);


        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems(BASE_URL);
    }

    //    btn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            //Perform your action here
//        }
//    });
    public void loadItems2(View view) {
        loadItems(FILTER_URL);


    }


    private void loadItems(String url) {
        usersList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){
// TODO: 1/15/2022 add the ret of attributes 
                                JSONObject object = array.getJSONObject(i);
                                String id = object.optString("CID");
                                String isoccupiedStr= object.optString("CNAME");
                                //    public User(double ID, String name, String email, String password, String nationality, String gender, String phone, String type) {

                                User room = new User(id,isoccupiedStr);
                                usersList.add(room);
                            }

                        }catch (Exception e){
                        }
                        UsersRecyclerAdapter adapter = new UsersRecyclerAdapter(com.example.hotelreservationsystem.Admin.UsersRecycler.this,
                                usersList);
                        recycler.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(com.example.hotelreservationsystem.Admin.UsersRecycler.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        //Volley.newRequestQueue(RoomRecycler.this).add(stringRequest);
    }
}