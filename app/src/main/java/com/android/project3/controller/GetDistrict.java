package com.android.project3.controller;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.project3.model.ManagerStatic.district;
import static com.android.project3.model.ManagerStatic.URL;
/**
 * Created by keban on 5/16/2017.
 */

public class GetDistrict {

    String provinceid;

    // URL dùng để truy xuất bảng PlatfForm
    private String MY_URL = URL + "district.php";

    public GetDistrict(String provinceid)
    {
        this.provinceid = provinceid;
    }

    public void Load(){

        district = new ArrayList();
        district.add("Chọn quận / huyện");

        // Tạo danh sách tham số gửi đến máy chủ
        List<NameValuePair> args = new ArrayList<NameValuePair>();
        args.add(new BasicNameValuePair("id", provinceid));

        MyService jsonParser = new MyService();
        String json = jsonParser.callService(MY_URL, MyService.POST, args);

        if (json != null) {
            try {

                if(json.contains("><br />"))
                    json = json.substring(json.indexOf("><br />") + 8);

                JSONObject jsonObj = new JSONObject(json);
                if (jsonObj != null) {
                    JSONArray platfform = jsonObj.getJSONArray("district");

                    for (int i = 0; i < platfform.length(); i++) {
                        JSONObject obj = (JSONObject) platfform.get(i);
                        district.add(obj.getString("name"));
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