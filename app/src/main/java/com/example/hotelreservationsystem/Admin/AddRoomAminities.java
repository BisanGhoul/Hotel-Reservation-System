package com.example.hotelreservationsystem.Admin;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotelreservationsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class AddRoomAminities extends AppCompatActivity {
    EditText roomn;
    EditText haswf;
    EditText hasAc;
    EditText hasTv;
    EditText breakfast;
    Button btnadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room_aminities);
        roomn=findViewById(R.id.edtroomnumber);
        haswf=findViewById(R.id.edthaswifi);
        hasAc=findViewById(R.id.edthasac);
        hasTv=findViewById(R.id.edthasac);
        breakfast=findViewById(R.id.edthasbreakfast);
        btnadd =findViewById(R.id.btnaddroom_am);
    }

    public void addondatabase(View view) {
        String roomnu = roomn.getText().toString();
        int ronum = Integer.parseInt(roomnu);
        String wifi = haswf.getText().toString();
        String ac = hasAc.getText().toString();
        String tv = hasTv.getText().toString();
        String breakfa = breakfast.getText().toString();
        room_amAdd(ronum,wifi,ac,tv,breakfa);
    }
    public void room_amAdd(int ronum,String wifi ,String ac,String tv,String breakfa){
        String url = "http://10.0.2.2:80/project_mobile/add_ameneties.php";
        RequestQueue queue = Volley.newRequestQueue(AddRoomAminities.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(AddRoomAminities.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(AddRoomAminities.this,
                        "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("RNO", String.valueOf(ronum));
                params.put("hasWifi", wifi);
                params.put("hasFreeBreakfast", ac);
                params.put("hasAC", tv);
                params.put("hasTV", breakfa);

                return params;
            }

        };

        queue.add(request);
    }
}