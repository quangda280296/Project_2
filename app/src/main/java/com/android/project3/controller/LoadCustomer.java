package com.android.project3.controller;

import android.util.Log;

import com.android.project3.model.Customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.project3.model.ManagerStatic.customer;
import static com.android.project3.model.ManagerStatic.URL;

/**
 * Created by keban on 4/17/2017.
 */
public class LoadCustomer
{
    // URL dùng để truy xuất bảng PlatfForm
    private String MY_URL = URL + "customer.php";

     public void Load()
     {
        MyService jsonParser = new MyService();
        String json = jsonParser.callService(MY_URL, MyService.GET);

        if (json != null) {
            try {

                if(json.contains("><br />"))
                    json = json.substring(json.indexOf("><br />") + 8);

                JSONObject jsonObj = new JSONObject(json);
                if (jsonObj != null) {
                    JSONArray platfform = jsonObj.getJSONArray("customer");
                    customer = new ArrayList<>(platfform.length());

                    for (int i = 0; i < platfform.length(); i++) {
                        JSONObject obj = (JSONObject) platfform.get(i);
                        Customer cus = new Customer.Builder().setId(obj.getString("id"))
                                                             .setName(obj.getString("name"))
                                                             .setVehicle(obj.getString("vehicle"))
                                                             .setType(obj.getString("type"))
                                                             .build();
                        customer.add(cus);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            Log.e("JSON Data", "Didn't receive any data from server!");
        }

//        customer = new ArrayList<>(1);
//        Customer cus = new Customer.Builder().setId("20146569")
//                                             .setName("Đỗ Anh Quang")
//                                             .setVehicle("Wave Alpha 100")
//                                             .setType("Xe số")
//                                             .build();
//        customer.add(cus);
    }
}
