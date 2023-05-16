package com.example.androidprojectversion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MarketAdapter extends ArrayAdapter<Handler> {
    private ArrayList<Handler> handlerList;
    Context context;

    public MarketAdapter(ArrayList<Handler> data , Context context){
        super(context,R.layout.item,data);
        this.handlerList = data;
        this.context = context;
    }

    private static class ViewHolder {
        TextView title,price;
        ImageView image;
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

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(market.getTitle());
        viewHolder.price.setText(market.getPrice());
        Picasso.get().load(market.getImage()).into(viewHolder.image);

        return convertView;
    }
}
