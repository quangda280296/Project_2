package com.android.project3.controller;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.android.project3.model.ManagerStatic.cus;
import static com.android.project3.model.ManagerStatic.URL;
/**
 * Created by keban on 4/17/2017.
 */
public class LoadUserRoad
{
    String start;
    String end;

    public LoadUserRoad(String start, String end)
    {
        this.start = start;
        this.end = end;
    }

    // URL dùng để truy xuất bảng PlatfForm
    private String MY_URL = URL + "user_road.php";

    public void Load() {

        // Tạo danh sách tham số gửi đến máy chủ
        List<NameValuePair> args = new ArrayList<NameValuePair>();
        args.add(new BasicNameValuePair("id", cus.getBuilder().getId()));
        args.add(new BasicNameValuePair("start", start));
        args.add(new BasicNameValuePair("end", end));

        MyService jsonParser = new MyService();
        String json = jsonParser.callService(MY_URL, MyService.POST, args);

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
    }

}
