package com.example.hotelreservationsystem.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hotelreservationsystem.Model.Room;
import com.example.hotelreservationsystem.R;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RoomRecyclerAdapter
            extends RecyclerView.Adapter<RoomRecyclerAdapter.ViewHolder>{
    private Context context;
    private List<Room> items;


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
//        ImageView imageView = (ImageView) cardView.findViewById(R.id.image);
//        Glide.with(context).load(room.getImage()).into(imageView);
        TextView txt = (TextView)cardView.findViewById(R.id.txtName);
        int x=room.getID();
        txt.setText(String.valueOf(x));

        TextView txt2 = (TextView)cardView.findViewById(R.id.txtName2);
        int y=room.getFloor();
        txt2.setText(String.valueOf(y));
        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //
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