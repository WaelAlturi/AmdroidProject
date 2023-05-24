package com.example.androidprojectversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.List;

public class Product extends AppCompatActivity {
    List<Market> MarketList;
    GridView market_gv;
    private static  MarketAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
    }
}