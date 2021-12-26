package com.example.hotelreservationsystem.RoomUtilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hotelreservationsystem.R;

public class RoomDetails extends AppCompatActivity {
    // TODO: 12/26/2021 centralize styling for amenities 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        Intent intent = getIntent();
        String wifi = intent.getStringExtra("wifi");
        String ac = intent.getStringExtra("ac");
        String tv = intent.getStringExtra("tv");
        String breakfast = intent.getStringExtra("breakfast");
        String price = intent.getStringExtra("price");
        String beds = intent.getStringExtra("beds");
        String type = intent.getStringExtra("type");
        String floor = intent.getStringExtra("floor");
    }
}