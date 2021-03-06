package com.example.hotelreservationsystem.RoomUtilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelreservationsystem.R;
import com.squareup.picasso.Picasso;

/*
==================================================
==      made by Bisan El Gool - 11181116        ==
==================================================
*/
public class RoomDetails extends AppCompatActivity {
    // TODO: 12/26/2021 centralize styling for amenities


    TextView roomtypeDetail_txt;
    TextView descDetail_txt;
    TextView priceDet_txt;
    TextView wifi_txt;
    TextView tv_txt;
    TextView ac_txt;
    TextView bk_txt_det;

    String roomNum;
    String wifi;
    String ac;
    String tv;
    String breakfast;
    double price;
    int beds;
    String type;
    int floor;
    String pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        getIntents();
        setViews();
        populateViews();


    }

    public void getIntents() {

        Intent intent = getIntent();
        wifi = intent.getStringExtra("wifi");
        ac = intent.getStringExtra("ac");
        roomNum = (intent.getStringExtra("rno"));
        tv = intent.getStringExtra("tv");
        breakfast = intent.getStringExtra("breakfast");
        price = intent.getDoubleExtra("price", price);
        beds = intent.getIntExtra("beds", beds);
        type = intent.getStringExtra("type");
        floor = intent.getIntExtra("floor", floor);
        pic = intent.getStringExtra("pic");
//        Log.e("details price", price);
    }

    private void populateViews() {

        ImageView imageView = (ImageView)findViewById(R.id.roomimgpic);
        Picasso.with(RoomDetails.this).load(pic).into(imageView);
        if (type.equals("suite")) {
            roomtypeDetail_txt.setText(type);
            descDetail_txt.setText("this " + type + " is on floor " + floor + ", & it has " + beds + " beds.");

        } else {
            roomtypeDetail_txt.setText(type + " Room");
            descDetail_txt.setText("this " + type + " is on floor " + floor + ", & it has " + beds + " beds.");
            // TODO: 12/26/2021 capitalize first letter
        }
        //fill price
        priceDet_txt.setText(String.valueOf(price) + "$");


        //show or hide (wifi,tv,ac,breakfast) icons
        if (ac.equals("n")) {

            ac_txt.setVisibility(View.GONE);
        }
        if (tv.equals("n")) {
            tv_txt.setVisibility(View.GONE);
        }
        if (wifi.equals("n")) {
            wifi_txt.setVisibility(View.GONE);
        }
        if (breakfast.equals("n")) {
            bk_txt_det.setVisibility(View.GONE);
        }
    }

    private void setViews() {
        roomtypeDetail_txt = findViewById(R.id.roomtypeDetail_txt);
        descDetail_txt = findViewById(R.id.descDetail_txt);
        priceDet_txt = findViewById(R.id.priceDet_txt);
        wifi_txt = findViewById(R.id.wifi_txt);
        tv_txt = findViewById(R.id.tv_txt);
        ac_txt = findViewById(R.id.ac_txt);
        bk_txt_det = findViewById(R.id.bk_txt_det);
    }


    public void goToReserveRoom(View view) {
        Intent intent1 = new Intent(this, RoomReserve.class);
        intent1.putExtra("ROOM_RNO", roomNum);
        startActivity(intent1);
    }
}