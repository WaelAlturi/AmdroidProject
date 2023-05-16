package com.example.androidprojectversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Market> pokemonList;
    GridView market_gv;
    private static  MarketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestData requestData = new RequestData();
        requestData.execute("https://fakestoreapi.com/products");
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
                    pokemonList = Handler.ParseResults(results);
                    market_gv = findViewById(R.id.market_gv);
                    adapter = new MarketAdapter((ArrayList<Market>) pokemonList,getApplicationContext());
                    market_gv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}