package com.android.project3.controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.android.project3.model.ManagerStatic.URL;
import static com.android.project3.model.ManagerStatic.cus;

/**
 * Created by keban on 4/17/2017.
 */
public class LoadAllUserRoad
{
    // URL dùng để truy xuất bảng PlatfForm
    private String MY_URL = URL + "load_all_user_road.php";

    public void Load() {

        MyService jsonParser = new MyService();
        String json = jsonParser.callService(MY_URL, MyService.GET);

        if (json != null) {
            try {

                if(json.contains("><br />"))
                    json = json.substring(json.indexOf("><br />") + 8);

                JSONObject jsonObj = new JSONObject(json);
                if (jsonObj != null) {
                    JSONArray platfform = jsonObj.getJSONArray("user_road");
                    HashMap m = new HashMap();

                    for (int i = 0; i < platfform.length(); i++)
                    {
                        JSONObject obj = (JSONObject) platfform.get(i);
                        m.put(obj.getString("date"), obj.getInt("km"));

                    }
                    cus.getBuilder().setUserRoad(m);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            Log.e("JSON Data", "Didn't receive any data from server!");
        }

//        HashMap m = new HashMap();
//        m.put("26/4/2017", 20);
//        m.put("27/4/2017", 12);
//        m.put("29/4/2017", 5);
//        m.put("30/4/2017", 7);
//        m.put("1/5/2017", 15);
//        m.put("2/5/2017", 24);
//        m.put("5/5/2017", 47);
//        m.put("7/5/2017", 2);
//        m.put("30/5/2017", 6);
//        m.put("5/9/2017", 19);
//
//        cus.getBuilder().setUserRoad(m);
    }
}
