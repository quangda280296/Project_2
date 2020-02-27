package com.android.project3.controller;

import android.util.Log;

import com.android.project3.model.Warranty;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.project3.model.ManagerStatic.URL;
import static com.android.project3.model.ManagerStatic.cus;

/**
 * Created by keban on 4/17/2017.
 */
public class LoadUserWarranty {

    // URL dùng để truy xuất bảng PlatfForm
    private String MY_URL = URL + "user_warranty.php";

    public void Load() {

        // Tạo danh sách tham số gửi đến máy chủ
        List<NameValuePair> args = new ArrayList<NameValuePair>();
        args.add(new BasicNameValuePair("id", cus.getBuilder().getId()));

        MyService jsonParser = new MyService();
        String json = jsonParser.callService(MY_URL, MyService.POST, args);

        if (json != null) {
            try {

                if(json.contains("><br />"))
                    json = json.substring(json.indexOf("><br />") + 8);

                JSONObject jsonObj = new JSONObject(json);
                if (jsonObj != null) {
                    JSONArray platfform = jsonObj.getJSONArray("user_warranty");

                    for (int i = 0; i < platfform.length(); i++) {
                        JSONObject obj = (JSONObject) platfform.get(i);
                        cus.getBuilder().setWarranty(new Warranty.Builder().setDauNhon(obj.getInt("oil1"))
                                .setDauGiamXoc(obj.getInt("oil2"))
                                .setDauHopSo(obj.getInt("oil3"))
                                .setDauPhanh(obj.getInt("oil4"))

                                .setLopTruoc(obj.getInt("tire1"))
                                .setLopSau(obj.getInt("tire2"))

                                .setAcquy(obj.getInt("accumulator"))
                                .setXich(obj.getInt("chain"))

                                .setPhanh(obj.getInt("brake"))
                                .setPhanhDau(obj.getInt("oil_brake"))

                                .setBugi(obj.getInt("spark_plug"))
                                .setTamLocGio(obj.getInt("air_filter"))
                                .setNuocLamMat(obj.getInt("cooling_water"))
                                .build()
                        );
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            Log.e("JSON Data", "Didn't receive any data from server!");
        }

//        cus.getBuilder().setWarranty(new Warranty.Builder()
//                        .setDauNhon(560)
//                        .setDauGiamXoc(435)
//                        .setDauHopSo(56)
//                        .setDauPhanh(780)
//
//                        .setLopTruoc(230)
//                        .setLopSau(19)
//
//                        .setAcquy(920)
//                        .setXich(809)
//
//                        .setPhanh(450)
//                        .setPhanhDau(506)
//
//                        .setBugi(37)
//                        .setTamLocGio(1870)
//                        .setNuocLamMat(10000)
//                        .build());
//    }
    }
}
