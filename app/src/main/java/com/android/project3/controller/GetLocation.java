package com.android.project3.controller;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.android.project3.model.NLocation;
import com.android.project3.R;

import static com.android.project3.model.ManagerStatic.location;

/**
 * Created by keban on 5/10/2017.
 */

public class GetLocation {

    public static void getHonda(Context context, String address){

        final String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?types=address&language=vi&input="
                    + address + "&key=" + context.getResources().getString(R.string.google_maps_key);

        final HttpHandler jsonParser = new HttpHandler();
        final String[] json = new String[1];

        Thread load = new Thread() {
            public void run() {
                json[0] = jsonParser.makeServiceCall(URL);
            }
        };
        load.start();

        try {
            load.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (json[0] != null) {

                try {
                    JSONObject jsonObj = new JSONObject(json[0]);

                    if (jsonObj != null) {
                        JSONArray platfform = jsonObj.getJSONArray("results");
                        location = new ArrayList<>(platfform.length());

                        for (int i = 0; i < platfform.length(); i++) {
                            JSONObject obj = (JSONObject) platfform.get(i);

                            NLocation addr = new NLocation.Builder().setAddress(obj.getString("formatted_address"))
                                                                    .setName(obj.getString("name"))
                                                                    .setLatitude(Double.parseDouble(obj.getJSONObject("geometry").getJSONObject("location").getString("lat")))
                                                                    .setLongitude(Double.parseDouble(obj.getJSONObject("geometry").getJSONObject("location").getString("lng")))
                                                                    .build();
                            location.add(addr);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }
    }
}
