package com.example.androidprojectversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Market> MarketList;
    GridView market_gv;
    private static  MarketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestData requestData = new RequestData();
        requestData.execute("https://fakestoreapi.com/products");
    }
    public void ShowInfo(){
        try {
            market_gv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Log.e("ERROR",e.toString());
        }

    }

    private class RequestData extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                String results = HttpRequestHelper.downloadURL(strings[0]);
                return results;
            } catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String results) {
            if(results != null){
                try {
                    MarketList = Handler.ParseResults(results);
                    market_gv = findViewById(R.id.market_gv);
                    adapter = new MarketAdapter((ArrayList<Market>) MarketList,getApplicationContext());
                    market_gv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}