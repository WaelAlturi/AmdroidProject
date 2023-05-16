package com.example.androidprojectversion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Handler {
    public static List<Market> ParseResults(String content) throws JSONException {

        JSONObject obj = new JSONObject(content);

        JSONArray array = obj.getJSONArray(content);

        List<Market> items = new ArrayList<>();

        for (int i = 0; i < array.length(); i++){

            JSONObject data = array.getJSONObject(i);

            Market market = new Market(
                    data.getString("title"),
                    data.getString("image"),
                    data.getString("price"),
                    data.getString("id"));
            items.add(market);
        }
        return items;
    }
}
