package com.android.project3.controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.project3.model.ManagerStatic.district;
import static com.android.project3.model.ManagerStatic.province;
import static com.android.project3.model.ManagerStatic.URL;
/**
 * Created by keban on 5/16/2017.
 */

public class GetProvince{

    // URL dùng để truy xuất bảng PlatfForm
    private String MY_URL = URL + "province.php";

    public void Load() {

        province = new ArrayList();
        province.add("Chọn tỉnh / thành phố");

        district = new ArrayList();
        district.add("Chọn quận / huyện");

        MyService jsonParser = new MyService();
        String json = jsonParser.callService(MY_URL, MyService.GET);

        if (json != null) {
            try {

                if(json.contains("><br />"))
                    json = json.substring(json.indexOf("><br />") + 8);

                JSONObject jsonObj = new JSONObject(json);
                if (jsonObj != null) {
                    JSONArray platfform = jsonObj.getJSONArray("province");

                    for (int i = 0; i < platfform.length(); i++) {
                        JSONObject obj = (JSONObject) platfform.get(i);
                        province.add(obj.getString("name"));
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
