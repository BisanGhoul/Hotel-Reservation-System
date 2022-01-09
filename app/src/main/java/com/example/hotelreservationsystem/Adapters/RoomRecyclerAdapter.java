package com.example.hotelreservationsystem.Adapters;
// TODO: 12/26/2021 sql query for search
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

public class RoomRecyclerAdapter
            extends RecyclerView.Adapter<RoomRecyclerAdapter.ViewHolder>{
    private Context context;
    private List<Room> items;

    public RoomRecyclerAdapter(){

    }
    public RoomRecyclerAdapter(Context context, List<Room> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_room_recycler_adapter,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Room room = items.get(position);
        CardView cardView = holder.cardView;
     //  ImageView imageView = (ImageView) cardView.findViewById(R.id.room_pic);
//        Glide.with(context).load(room.getImage()).into(imageView);
        //get views
        TextView type_txt = (TextView)cardView.findViewById(R.id.roomtype_txt);
        TextView desc_txt = (TextView)cardView.findViewById(R.id.description_txt);
        TextView price_txt = (TextView)cardView.findViewById(R.id.price_txt);
        ImageView ac_img = (ImageView) cardView.findViewById(R.id.ac_img);
        ImageView tv_img = (ImageView) cardView.findViewById(R.id.tv_img);
        ImageView wifi_img = (ImageView) cardView.findViewById(R.id.wifi_img);
        ImageView breakfast_img = (ImageView) cardView.findViewById(R.id.breakfast_img);

        //fill description and title
        if(room.getType().equals("suite")){
            type_txt.setText(room.getType());
            desc_txt.setText("this "+room.getType()+" is on floor "+room.getFloor()+", & it has "+room.getNumOfBeds()+" beds.");

        }else{
            type_txt.setText(room.getType()+ " Room");
            // TODO: 12/26/2021 capitalize first letter
            desc_txt.setText("this "+room.getType()+" room is on floor "+room.getFloor()+", & it has "+room.getNumOfBeds()+" beds.");
        }
        //fill price
        price_txt.setText(String.valueOf(room.getPrice())+"$");

        //show or hide (wifi,tv,ac,breakfast) icons
        if(room.getAC().equals("n")){
            ac_img.setVisibility(View.GONE);
        }
        if(room.getTV().equals("n")){
            tv_img.setVisibility(View.GONE);
        }
        if(room.getWifi().equals("n")){
            wifi_img.setVisibility(View.GONE);
        }
        if(room.getFreeBreakfast().equals("n")){
            breakfast_img.setVisibility(View.GONE);
        }



        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // TODO: 12/26/2021 detailed activity

                Intent roomDetail = new Intent(context, RoomDetails.class);
//                roomDetail.putextra("room object", room);
                roomDetail.putExtra("wifi",room.getWifi());
                roomDetail.putExtra("ac",room.getAC());
                roomDetail.putExtra("tv",room.getTV());
                roomDetail.putExtra("breakfast",room.getFreeBreakfast());
                roomDetail.putExtra("price",room.getPrice());
                roomDetail.putExtra("beds",room.getNumOfBeds());
                roomDetail.putExtra("type",room.getType());
                roomDetail.putExtra("floor",room.getFloor());

                context.startActivity(roomDetail);
                Log.e("price",String.valueOf(room.getPrice()));

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