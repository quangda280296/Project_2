package com.android.project3.controller;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.android.project3.model.ManagerStatic.URL;
import static com.android.project3.model.ManagerStatic.cus;

/**
 * Created by keban on 4/17/2017.
 */
public class UpdateUserWarranty extends AsyncTask
{
    HashMap part;

    public UpdateUserWarranty(HashMap part)
    {
        this.part = part;
    }

    // URL dùng để truy xuất bảng PlatfForm
    private String MY_URL = URL + "update.php";

    @Override
    protected Object doInBackground(Object[] params) {

        // Tạo danh sách tham số gửi đến máy chủ
        List<NameValuePair> args = new ArrayList<NameValuePair>();
        args.add(new BasicNameValuePair("id", cus.getBuilder().getId()));

        // Lay mot tap hop cac entry
        Set set = part.entrySet();
        // Lay mot iterator
        Iterator i = set.iterator();
        // Hien thi cac phan tu
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            args.add(new BasicNameValuePair(me.getKey().toString(), String.valueOf(me.getValue())));
        }
        MyService jsonParser = new MyService();
        String json = jsonParser.callService(MY_URL, MyService.POST, args);

        return null;
    }
}
