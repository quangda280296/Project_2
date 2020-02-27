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
import static com.android.project3.model.ManagerStatic.warranty;
/**
 * Created by keban on 4/17/2017.
 */
public class LoadWarranty
{
    public void Load()
    {
        String MY_URL;
        List<NameValuePair> args = new ArrayList<NameValuePair>();
        args.add(new BasicNameValuePair("type", cus.getBuilder().getVehicle()));
        MyService jsonParser;
        String json;

        warranty = new Warranty.Builder().build();
        warranty.getBuilder().setDauHopSo(32);
        warranty.getBuilder().setDauGiamXoc(34);
        warranty.getBuilder().setDauPhanh(47);

        if(cus.getBuilder().getType().equals("Xe ga"))
            warranty.getBuilder().setDauNhon(84);
        else
            warranty.getBuilder().setDauNhon(68);

        warranty.getBuilder().setNuocLamMat(32);

//        warranty.getBuilder().setLopTruoc(300);
//        warranty.getBuilder().setLopSau(300);
//        warranty.getBuilder().setXich(150);
//        warranty.getBuilder().setAcquy(140);
//        warranty.getBuilder().setPhanh(100);
//        warranty.getBuilder().setPhanhDau(200);
//        warranty.getBuilder().setBugi(70);
//        warranty.getBuilder().setTamLocGio(80);

        MY_URL = URL + "tire1.php";
        jsonParser = new MyService();
        json = jsonParser.callService(MY_URL, MyService.POST, args);
        if (json != null) {
            try {
                JSONObject jsonObj = new JSONObject(json);
                if (jsonObj != null) {
                    JSONArray platfform = jsonObj.getJSONArray("tire1");

                    for (int i = 0; i < platfform.length(); i++) {
                        JSONObject obj = (JSONObject) platfform.get(i);

                        warranty.getBuilder().setLopTruoc(obj.getInt("cost"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("JSON Data", "Didn't receive any data from server!");
        }

        MY_URL = URL + "tire2.php";
        jsonParser = new MyService();
        json = jsonParser.callService(MY_URL, MyService.POST, args);
        if (json != null) {
            try {
                JSONObject jsonObj = new JSONObject(json);
                if (jsonObj != null) {
                    JSONArray platfform = jsonObj.getJSONArray("tire2");

                    for (int i = 0; i < platfform.length(); i++) {
                        JSONObject obj = (JSONObject) platfform.get(i);

                        warranty.getBuilder().setLopSau(obj.getInt("cost"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("JSON Data", "Didn't receive any data from server!");
        }

        MY_URL = URL + "accumulator.php";
        jsonParser = new MyService();
        json = jsonParser.callService(MY_URL, MyService.POST, args);
        if (json != null) {
            try {
                JSONObject jsonObj = new JSONObject(json);
                if (jsonObj != null) {
                    JSONArray platfform = jsonObj.getJSONArray("accumulator");

                    for (int i = 0; i < platfform.length(); i++) {
                        JSONObject obj = (JSONObject) platfform.get(i);

                        warranty.getBuilder().setAcquy(obj.getInt("cost"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("JSON Data", "Didn't receive any data from server!");
        }

            MY_URL = URL + "chain.php";
            jsonParser = new MyService();
            json = jsonParser.callService(MY_URL, MyService.POST, args);
            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {
                        JSONArray platfform = jsonObj.getJSONArray("chain");

                        for (int i = 0; i < platfform.length(); i++) {
                            JSONObject obj = (JSONObject) platfform.get(i);

                            warranty.getBuilder().setXich(obj.getInt("cost"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            MY_URL = URL + "brake.php";
            jsonParser = new MyService();
            json = jsonParser.callService(MY_URL, MyService.POST, args);
            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {
                        JSONArray platfform = jsonObj.getJSONArray("brake");

                        for (int i = 0; i < platfform.length(); i++) {
                            JSONObject obj = (JSONObject) platfform.get(i);

                            warranty.getBuilder().setPhanh(obj.getInt("cost"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            MY_URL = URL + "oil_brake.php";
            jsonParser = new MyService();
            json = jsonParser.callService(MY_URL, MyService.POST, args);
            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {
                        JSONArray platfform = jsonObj.getJSONArray("oil_brake");

                        for (int i = 0; i < platfform.length(); i++) {
                            JSONObject obj = (JSONObject) platfform.get(i);

                            warranty.getBuilder().setPhanhDau(obj.getInt("cost"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            MY_URL = URL + "spark_plug.php";
            jsonParser = new MyService();
            json = jsonParser.callService(MY_URL, MyService.POST, args);
            if (json != null) {
                try {

                    if(json.contains("><br />"))
                        json = json.substring(json.indexOf("><br />") + 8);

                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {
                        JSONArray platfform = jsonObj.getJSONArray("spark_plug");

                        for (int i = 0; i < platfform.length(); i++) {
                            JSONObject obj = (JSONObject) platfform.get(i);

                            warranty.getBuilder().setBugi(obj.getInt("cost"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            MY_URL = URL + "air_filter.php";
            jsonParser = new MyService();
            json = jsonParser.callService(MY_URL, MyService.POST, args);
            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {
                        JSONArray platfform = jsonObj.getJSONArray("air_filter");

                        for (int i = 0; i < platfform.length(); i++) {
                            JSONObject obj = (JSONObject) platfform.get(i);

                            warranty.getBuilder().setTamLocGio(obj.getInt("cost"));
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
