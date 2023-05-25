package com.example.androidprojectversion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public  class MarketAdapter extends ArrayAdapter<Market> {
    private ArrayList<Market> handlerList;
    Context context;

    public MarketAdapter(ArrayList<Market> data , Context context){
        super(context,R.layout.item,data);
        this.handlerList = data;
        this.context = context;
    }


    private static class ViewHolder {
        TextView title,price;
        ImageView image;
        Button info;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        Market market = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null){

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item,parent,false);

            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.price = convertView.findViewById(R.id.price);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.info = convertView.findViewById(R.id.info);



            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(market.getTitle());
        viewHolder.price.setText(market.getPrice()+"$");
        //viewHolder.info.setText(market.getId());
        Picasso.get().load(market.getImage()).into(viewHolder.image);

        viewHolder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    viewHolder.info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(view.getContext(),Product.class);
                            intent.putExtra("Title",market.getTitle());
                            intent.putExtra("Price",viewHolder.price.getText());
                            intent.putExtra("Image",market.getImage());
                            intent.putExtra("ID",market.getId());
                            view.getContext().startActivity(intent);
                            Toast.makeText(getContext().getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
                        }
                    });
                }catch (Exception e){
                    Log.e("ERROR",e.toString());
                }
            }
        });


        return convertView;
    }

}
