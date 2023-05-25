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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public  class MarketAdapter extends ArrayAdapter<Market> {
    private ArrayList<Market> handlerList;
    Context context;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    private static final String KEY_PRODUCT_ID= "ID";
    private static final String KEY_PRODUCT_NAME = "Title";
    private static final String KEY_PRODUCT_PRICE = "Price";
    //private static final String KEY_PRODUCT_DESCRIPTION = "Info";
    private static final String KEY_PRODUCT_IMAGE= "Image";

    public MarketAdapter(ArrayList<Market> data , Context context){
        super(context,R.layout.item,data);
        this.handlerList = data;
        this.context = context;
    }

    private static class ViewHolder {
        TextView title,price;
        ImageView image;
        Button addProduct,info;
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
            viewHolder.addProduct = convertView.findViewById(R.id.add);
            viewHolder.info = convertView.findViewById(R.id.database);


            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(market.getTitle());
        viewHolder.price.setText(market.getPrice()+"$");
        Picasso.get().load(market.getImage()).into(viewHolder.image);

        viewHolder.addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Map<String, Object> data = new HashMap<>();
                    data.put(KEY_PRODUCT_ID, market.getId());
                    data.put(KEY_PRODUCT_NAME, market.getTitle());
                    data.put(KEY_PRODUCT_PRICE, viewHolder.price.getText());
                    data.put(KEY_PRODUCT_IMAGE, market.getImage());

                    database
                            .collection("product")
                            .add(data)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(getContext(), "Product Created!!", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "Error" + e, Toast.LENGTH_LONG).show();
                                }
                            });
                }catch (Exception e){
                    Log.e("ERROR",e.toString());
                }
            }
        });
        viewHolder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Product.class);
                intent.putExtra("Title",market.getTitle());
                intent.putExtra("Price",viewHolder.price.getText());
                intent.putExtra("Image",market.getImage());
                intent.putExtra("ID",market.getId());
                view.getContext().startActivity(intent);
            }
        });

        return convertView;
    }

}
