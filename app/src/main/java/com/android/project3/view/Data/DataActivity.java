package com.android.project3.view.Data;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.android.project3.R;
import com.android.project3.controller.InsertUserRoad;

import static com.android.project3.model.ManagerStatic.cus;

public class DataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
                                                               Animation.AnimationListener {
    Animation anim;
    Animation anim_vehicle;
    HashMap hm;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        getSupportActionBar().setTitle("Dữ liệu cá nhân");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.reverse);
        anim.setAnimationListener(this);

        anim_vehicle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
        anim_vehicle.setAnimationListener(this);

        int average = 1;

        if(cus.getBuilder().getUserRoad() != null) {
            hm = cus.getBuilder().getUserRoad();

            //Lay mot tap hop cac entry
            Set set = hm.entrySet();
            // Lay mot iterator
            Iterator i = set.iterator();

            int sum = 0;
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                sum += (int) me.getValue();
            }

            average = sum / hm.size();
        }

        ImageView img_vehicle = (ImageView) findViewById(R.id.img_vehicle);
        TextView vehilce = (TextView) findViewById(R.id.vehicle);
        TextView text_oil1 = (TextView) findViewById((R.id.text_oil_1));
        TextView text_oil2 = (TextView) findViewById((R.id.text_oil_2));
        TextView text_oil3 = (TextView) findViewById((R.id.text_oil_3));
        TextView text_oil4 = (TextView) findViewById((R.id.text_oil_4));
        TextView text_tire1 = (TextView) findViewById((R.id.text_tire_1));
        TextView text_tire2 = (TextView) findViewById((R.id.text_tire_2));
        TextView text_accumulator = (TextView) findViewById((R.id.text_accumulator));
        TextView text_chain = (TextView) findViewById((R.id.text_chain));
        TextView text_brake1 = (TextView) findViewById((R.id.text_brake_1));
        TextView text_brake2 = (TextView) findViewById((R.id.text_brake_2));
        TextView text_spark_plug = (TextView) findViewById((R.id.text_spark_plug));
        TextView text_cooling_water = (TextView) findViewById((R.id.text_cooling_water));
        TextView text_air_filter = (TextView) findViewById((R.id.text_air_filter));

        TextView quantity_oil_1 = (TextView) findViewById(R.id.quantity_oil_1);
        TextView quantity_oil_2 = (TextView) findViewById(R.id.quantity_oil_2);
        TextView quantity_oil_3 = (TextView) findViewById(R.id.quantity_oil_3);
        TextView quantity_oil_4 = (TextView) findViewById(R.id.quantity_oil_4);
        TextView quantity_tire_1 = (TextView) findViewById(R.id.quantity_tire_1);
        TextView quantity_tire_2 = (TextView) findViewById(R.id.quantity_tire_2);
        TextView quantity_accumulator = (TextView) findViewById(R.id.quantity_accumulator);
        TextView quantity_chain = (TextView) findViewById(R.id.quantity_chain);
        TextView quantity_brake_1 = (TextView) findViewById(R.id.quantity_brake_1);
        TextView quantity_brake_2 = (TextView) findViewById(R.id.quantity_brake_2);
        TextView quantity_spark_plug = (TextView) findViewById(R.id.quantity_spark_plug);
        TextView quantity_air_filter = (TextView) findViewById(R.id.quantity_air_filter);
        TextView quantity_cooling_water = (TextView) findViewById(R.id.quantity_cooling_water);

        quantity_oil_1.setText(cus.getBuilder().getWarranty().getBuilder().getDauNhon() + " km");
        quantity_oil_2.setText(cus.getBuilder().getWarranty().getBuilder().getDauGiamXoc() + " km");
        quantity_oil_3.setText(cus.getBuilder().getWarranty().getBuilder().getDauHopSo() + " km");
        quantity_oil_4.setText(cus.getBuilder().getWarranty().getBuilder().getDauPhanh() + " km");
        quantity_tire_1.setText(cus.getBuilder().getWarranty().getBuilder().getLopTruoc() + " km");
        quantity_tire_2.setText(cus.getBuilder().getWarranty().getBuilder().getLopSau() + " km");
        quantity_accumulator.setText(cus.getBuilder().getWarranty().getBuilder().getAcquy() + " km");
        quantity_chain.setText(cus.getBuilder().getWarranty().getBuilder().getXich() + " km");
        quantity_brake_1.setText(cus.getBuilder().getWarranty().getBuilder().getPhanh() + " km");
        quantity_brake_2.setText(cus.getBuilder().getWarranty().getBuilder().getPhanhDau() + " km");
        quantity_spark_plug.setText(cus.getBuilder().getWarranty().getBuilder().getBugi() + " km");
        quantity_air_filter.setText(cus.getBuilder().getWarranty().getBuilder().getTamLocGio() + " km");
        quantity_cooling_water.setText(cus.getBuilder().getWarranty().getBuilder().getNuocLamMat() + " km");

        TextView payment_oil_1 = (TextView) findViewById(R.id.payment_oil_1);
        TextView payment_oil_2 = (TextView) findViewById(R.id.payment_oil_2);
        TextView payment_oil_3 = (TextView) findViewById(R.id.payment_oil_3);
        TextView payment_oil_4 = (TextView) findViewById(R.id.payment_oil_4);
        TextView payment_tire_1 = (TextView) findViewById(R.id.payment_tire_1);
        TextView payment_tire_2 = (TextView) findViewById(R.id.payment_tire_2);
        TextView payment_accumulator = (TextView) findViewById(R.id.payment_accumulator);
        TextView payment_chain = (TextView) findViewById(R.id.payment_chain);
        TextView payment_brake_1 = (TextView) findViewById(R.id.payment_brake_1);
        TextView payment_brake_2 = (TextView) findViewById(R.id.payment_brake_2);
        TextView payment_spark_plug = (TextView) findViewById(R.id.payment_spark_plug);
        TextView payment_air_filter = (TextView) findViewById(R.id.payment_air_filter);
        TextView payment_cooling_water = (TextView) findViewById(R.id.payment_cooling_water);

        payment_oil_1.setText((cus.getBuilder().getWarranty().getBuilder().getDauNhon() / average) + " ngày");
        payment_oil_2.setText((cus.getBuilder().getWarranty().getBuilder().getDauGiamXoc() / average) + " ngày");
        payment_oil_3.setText((cus.getBuilder().getWarranty().getBuilder().getDauHopSo() / average) + " ngày");
        payment_oil_4.setText((cus.getBuilder().getWarranty().getBuilder().getDauPhanh() / average) + " ngày");
        payment_tire_1.setText((cus.getBuilder().getWarranty().getBuilder().getLopTruoc() / average) + " ngày");
        payment_tire_2.setText((cus.getBuilder().getWarranty().getBuilder().getLopSau() / average) + " ngày");
        payment_accumulator.setText((cus.getBuilder().getWarranty().getBuilder().getAcquy() / average) + " ngày");
        payment_chain.setText((cus.getBuilder().getWarranty().getBuilder().getXich() / average) + " ngày");
        payment_brake_1.setText((cus.getBuilder().getWarranty().getBuilder().getPhanh() / average) + " ngày");
        payment_brake_2.setText((cus.getBuilder().getWarranty().getBuilder().getPhanhDau() / average) + " ngày");
        payment_spark_plug.setText((cus.getBuilder().getWarranty().getBuilder().getBugi() / average) + " ngày");
        payment_air_filter.setText((cus.getBuilder().getWarranty().getBuilder().getTamLocGio() / average) + " ngày");
        payment_cooling_water.setText((cus.getBuilder().getWarranty().getBuilder().getNuocLamMat() / average) + " ngày");

        if(cus.getBuilder().getType().equals("Xe ga"))
        {
            text_oil1.setText("Dầu nhờn xe ga");
        }

        vehilce.setText(cus.getBuilder().getVehicle());
        switch (cus.getBuilder().getVehicle())
        {
            case "Future 125":
                img_vehicle.setImageResource(R.mipmap.future_125);
                break;
            case "Wave RSX 110":
                img_vehicle.setImageResource(R.mipmap.wave_rsx_110);
                break;
            case "Blade 110":
                img_vehicle.setImageResource(R.mipmap.blade_110);
                break;
            case "Super Dream 110":
                img_vehicle.setImageResource(R.mipmap.super_dream_110);
                break;
            case "Wave Alpha 100":
                img_vehicle.setImageResource(R.mipmap.wave_alpha_100);
                break;
            case "LEAD 125":
                img_vehicle.setImageResource(R.mipmap.lead_125);
                break;
            case "Air Blade 125":
                img_vehicle.setImageResource(R.mipmap.air_blade_125);
                break;
            case "SH Mode 125":
                img_vehicle.setImageResource(R.mipmap.sh_mode_125);
                break;
            case "Vision 110":
                img_vehicle.setImageResource(R.mipmap.vision_110);
                break;
            case "PCX 125":
                img_vehicle.setImageResource(R.mipmap.pcx_125);
                break;
            case "SH 150":
                img_vehicle.setImageResource(R.mipmap.sh_150);
                break;
            case "SH 125":
                img_vehicle.setImageResource(R.mipmap.sh_125);
                break;
        }

        if(cus.getBuilder().getWarranty().getBuilder().getDauNhon() / average <= 15)
        {
            check++;
            text_oil1.startAnimation(anim);
            quantity_oil_1.startAnimation(anim);
            payment_oil_1.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getDauGiamXoc() / average <= 15)
        {
            check++;
            text_oil2.startAnimation(anim);
            quantity_oil_2.startAnimation(anim);
            payment_oil_2.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getDauHopSo() / average <= 15)
        {
            check++;
            text_oil3.startAnimation(anim);
            quantity_oil_3.startAnimation(anim);
            payment_oil_3.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getDauPhanh() / average <= 15)
        {
            check++;
            text_oil4.startAnimation(anim);
            quantity_oil_4.startAnimation(anim);
            payment_oil_4.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getLopTruoc() / average <= 15)
        {
            check++;
            text_tire1.startAnimation(anim);
            quantity_tire_1.startAnimation(anim);
            payment_tire_1.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getLopSau() / average <= 15)
        {
            check++;
            text_tire2.startAnimation(anim);
            quantity_tire_2.startAnimation(anim);
            payment_tire_2.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getAcquy() / average <= 15)
        {
            check++;
            text_accumulator.startAnimation(anim);
            quantity_accumulator.startAnimation(anim);
            payment_accumulator.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getXich() / average <= 15)
        {
            check++;
            text_chain.startAnimation(anim);
            quantity_chain.startAnimation(anim);
            payment_chain.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getPhanh() / average <= 15)
        {
            check++;
            text_brake1.startAnimation(anim);
            quantity_brake_1.startAnimation(anim);
            payment_brake_1.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getPhanhDau() / average <= 15)
        {
            check++;
            text_brake2.startAnimation(anim);
            quantity_brake_2.startAnimation(anim);
            payment_brake_2.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getBugi() / average <= 15)
        {
            check++;
            text_spark_plug.startAnimation(anim);
            quantity_spark_plug.startAnimation(anim);
            payment_spark_plug.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getTamLocGio() / average <= 15)
        {
            check++;
            text_air_filter.startAnimation(anim);
            quantity_air_filter.startAnimation(anim);
            payment_air_filter.startAnimation(anim);
        }

        if(cus.getBuilder().getWarranty().getBuilder().getNuocLamMat() / average <= 15)
        {
            check++;
            text_cooling_water.startAnimation(anim);
            quantity_cooling_water.startAnimation(anim);
            payment_cooling_water.startAnimation(anim);
        }

        check();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent("Login"));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent("Map"));
            finish();
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent("Chart"));
            finish();
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent("Data"));
            finish();
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent("Main"));
            finish();
        } else if (id == R.id.nav_share) {
            startActivity(new Intent("About"));
            finish();
        } else if (id == R.id.nav_send) {
            startActivity(new Intent("Login"));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startAnimVehicle(View view)
    {
        view.startAnimation(anim_vehicle);
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

    DateFormat fmtDateAndTime = DateFormat.getDateInstance();

    public void check()
    {
        Calendar calendar = Calendar.getInstance();
        final String date = fmtDateAndTime.format(calendar.getTime());
        boolean today = false;

        if(hm != null) {
            //Lay mot tap hop cac entry
            Set set = hm.entrySet();
            // Lay mot iterator
            Iterator i = set.iterator();

            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                if (me.getKey().toString().equals(date)) {
                    today = true;
                    break;
                }
            }
        }

        Toast.makeText(getBaseContext(), "Một vài linh kiện trong xe của bạn cần được đi bảo trì", Toast.LENGTH_LONG).show();

        if(today == false)
        {
            LayoutInflater inflater = getLayoutInflater();
            final View alertLayout = inflater.inflate(R.layout.insert_dialog, null);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setView(alertLayout);
            final AlertDialog dialog = alert.create();
            dialog.show();
//            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialog)
//                {
//                    Toast.makeText(getBaseContext(), "Một vài linh kiện trong xe của bạn cần được đi bảo trì", Toast.LENGTH_SHORT).show();
//                }
//            });

            final EditText input = (EditText)alertLayout.findViewById(R.id.input);
            final EditText edit = (EditText)alertLayout.findViewById(R.id.edit);

            Button btn1 = (Button)alertLayout.findViewById(R.id.btn_1);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(input.getText().toString().equals(""))
                        Toast.makeText(getBaseContext(), "Hãy nhập đầy đủ", Toast.LENGTH_LONG).show();
                    else
                    {
                        String km = input.getText().toString();
                        new InsertUserRoad(date, km).execute();
                        dialog.hide();
                    }
//                    if(check != 0)
//                    {
//                        Toast.makeText(getBaseContext(), "Một vài linh kiện trong xe của bạn cần được đi bảo trì", Toast.LENGTH_SHORT).show();
//                    }
                }
            });

            Button btn2 = (Button)alertLayout.findViewById(R.id.btn_2);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(edit.getText().toString().equals(""))
                        Toast.makeText(getBaseContext(), "Hãy nhập đầy đủ", Toast.LENGTH_LONG).show();
                    else {

                        String read = "";

                        try {
                            FileInputStream fIn = openFileInput("Code.txt");
                            InputStreamReader isr = new InputStreamReader(fIn);

                            char[] inputBuffer = new char[10];

                            int charRead;
                            while ((charRead = isr.read(inputBuffer)) > 0) {

                                read += String.copyValueOf(inputBuffer, 0, charRead);
                                inputBuffer = new char[10];
                            }

                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }

                        if (read.equals("")) {

                            try {
                                FileOutputStream fOut = openFileOutput("Code.txt", MODE_PRIVATE);
                                OutputStreamWriter osw = new OutputStreamWriter(fOut);

                                //---Bắt đầu quá trình ghi file---

                                String string = edit.getText().toString();
                                osw.write(string);
                                osw.flush();
                                osw.close();

                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }

                            Toast.makeText(getBaseContext(), "Dữ liệu bảng công tơ mét mới được thêm lần đầu, vui lòng nhập tiếp", Toast.LENGTH_LONG).show();
                        }

                        else {

                            try
                            {
                                long start = Long.parseLong(read);
                                long end = Long.parseLong(edit.getText().toString());

                                long sub = end - start;

                                if(sub >= 0)
                                {
                                    new InsertUserRoad(date, String.valueOf(sub)).execute();
                                    dialog.hide();
                                }
                                else
                                    Toast.makeText(getBaseContext(), "Bạn nhập không chính xác, vui lòng nhập lại!!!", Toast.LENGTH_LONG).show();
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getBaseContext(), "Lỗi chuyển đổi!!!", Toast.LENGTH_LONG).show();
                            }

                        }
                    }
//                    if(check != 0)
//                    {
//                        Toast.makeText(getBaseContext(), "Một vài linh kiện trong xe của bạn cần được đi bảo trì", Toast.LENGTH_SHORT).show();
//                    }
                }
            });

        }
    }
}
