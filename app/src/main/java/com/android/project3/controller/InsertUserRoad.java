package com.android.project3.controller;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.android.project3.model.ManagerStatic.URL;
import static com.android.project3.model.ManagerStatic.cus;

/**
 * Created by keban on 4/17/2017.
 */
public class InsertUserRoad extends AsyncTask
{
    String date;
    String km;

    public InsertUserRoad(String date, String km)
    {
        this.date = date;
        this.km = km;
    }

    // URL dùng để truy xuất bảng PlatfForm
    private String MY_URL = URL + "insert.php";

    @Override
    protected Object doInBackground(Object[] params) {

        // Tạo danh sách tham số gửi đến máy chủ
        List<NameValuePair> args = new ArrayList<NameValuePair>();
        args.add(new BasicNameValuePair("id", cus.getBuilder().getId()));
        args.add(new BasicNameValuePair("date", date));
        args.add(new BasicNameValuePair("km", km));

        MyService jsonParser = new MyService();
        String json = jsonParser.callService(MY_URL, MyService.POST, args);

        return null;
    }
}
