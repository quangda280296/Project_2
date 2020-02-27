package com.android.project3.view.Welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.project3.R;
import com.android.project3.controller.GetProvince;
import com.android.project3.controller.LoadAllUserRoad;
import com.android.project3.controller.LoadUserWarranty;
import com.android.project3.controller.LoadWarranty;
import com.android.project3.model.Customer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static com.android.project3.model.ManagerStatic.cus;
import static com.android.project3.model.ManagerStatic.customer;
import static com.android.project3.view.Introduction.Introduction.readString;

/**
 * Created by keban on 5/17/2017.
 */

public class Welcome extends AppCompatActivity implements Animation.AnimationListener {

    Animation anim;
    Button access;
    CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        remember = (CheckBox) findViewById(R.id.remember);

        if (readString.compareTo("") != 0) {
            for (Customer c : customer) {
                if (c.getBuilder().getId().equals(readString)) {
                    cus = c;
                }
            }
        }

        TextView name = (TextView) findViewById(R.id.name);

        if (cus != null)
            name.setText(cus.getBuilder().getName());

        if (readString.compareTo("") != 0)
            remember.setChecked(true);
        else
            remember.setChecked(false);

        remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData();
            }
        });

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        anim.setAnimationListener(this);

        access = (Button) findViewById(R.id.access);
        access.startAnimation(anim);
    }


    public void myData(View view) {

        Thread get;

        get = new Thread() {
            public void run() {
                new GetProvince().Load();
            }
        };
        get.start();
        try {
            get.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        get = new Thread() {
            public void run() {
                new LoadAllUserRoad().Load();
            }
        };
        get.start();

        try {
            get.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        get = new Thread() {
            public void run() {
                new LoadUserWarranty().Load();
            }
        };
        get.start();

        try {
            get.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        get = new Thread() {
            public void run() {
                new LoadWarranty().Load();
            }
        };
        get.start();
        try {
            get.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startActivity(new Intent("Data"));
        finish();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void writeData() {
        if (remember.isChecked()) {
            try {
                FileOutputStream fOut = openFileOutput("Login.txt", MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fOut);

                //---Bắt đầu quá trình ghi file---

                String string = cus.getBuilder().getId();
                osw.write(string);
                osw.flush();
                osw.close();

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            try {
                FileOutputStream fOut = openFileOutput("Login.txt", MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fOut);

                //---Bắt đầu quá trình ghi file---

                String string = "";
                osw.write(string);
                osw.flush();
                osw.close();

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
