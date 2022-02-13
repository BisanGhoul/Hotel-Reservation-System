package com.example.hotelreservationsystem.Adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelreservationsystem.Model.Room;
import com.example.hotelreservationsystem.Model.User;
import com.example.hotelreservationsystem.R;
import com.example.hotelreservationsystem.RoomUtilities.RoomDetails;

import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hotelreservationsystem.Model.Room;
import com.example.hotelreservationsystem.R;
import com.example.hotelreservationsystem.RoomUtilities.RoomDetails;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<User> items;

    public UsersRecyclerAdapter(){

    }
    public UsersRecyclerAdapter(Context context, List<User> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_users_recycler_adapter,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User room = items.get(position);
        CardView cardView = holder.cardView;
        //  ImageView imageView = (ImageView) cardView.findViewById(R.id.room_pic);
//        Glide.with(context).load(room.getImage()).into(imageView);
        //get views
        TextView user_txt = (TextView)cardView.findViewById(R.id.user_name);
        TextView user_id_txt = (TextView)cardView.findViewById(R.id.user_id);

        user_txt.setText(room.getName());
        user_id_txt.setText(String.valueOf(room.getID()));


        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){

//                Intent roomDetail = new Intent(context, RoomDetails.class);
////                roomDetail.putextra("room object", room);
//                roomDetail.putExtra("wifi",room.getWifi());
//                roomDetail.putExtra("ac",room.getAC());
//                roomDetail.putExtra("tv",room.getTV());
//                roomDetail.putExtra("breakfast",room.getFreeBreakfast());
//                roomDetail.putExtra("price",room.getPrice());
//                roomDetail.putExtra("beds",room.getNumOfBeds());
//                roomDetail.putExtra("type",room.getType());
//                roomDetail.putExtra("floor",room.getFloor());
//
//                context.startActivity(roomDetail);
//                Log.e("price",String.valueOf(room.getPrice()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
}