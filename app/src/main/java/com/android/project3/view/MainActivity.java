package com.android.project3.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import com.android.project3.R;
import com.android.project3.controller.UpdateUserWarranty;

import static com.android.project3.model.ManagerStatic.cus;
import static com.android.project3.model.ManagerStatic.warranty;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
                                                               Animation.AnimationListener {

    Animation anim;

    CheckBox check_oil1;
    CheckBox check_oil2;
    CheckBox check_oil3;
    CheckBox check_oil4;
    CheckBox check_tire1;
    CheckBox check_tire2;
    CheckBox check_chain;
    CheckBox check_accumulator;
    CheckBox check_brake1;
    CheckBox check_brake2;
    CheckBox check_spark_plug;
    CheckBox check_air_filter;
    CheckBox check_cooling_water;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Chọn linh kiện bảo trì");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
        anim.setAnimationListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView img_vehicle = (ImageView) findViewById(R.id.img_vehicle);
        TextView vehilce = (TextView) findViewById(R.id.vehicle);
        TextView text_oil = (TextView) findViewById((R.id.text_oil));

        ImageView img_oil = (ImageView) findViewById(R.id.img_oil);
        TextView oil = (TextView) findViewById(R.id.oil);
        TextView code_oil = (TextView) findViewById(R.id.code_oil);
        TextView cost_oil = (TextView) findViewById(R.id.cost_oil);

        TextView tire1 = (TextView) findViewById(R.id.tire_1);
        TextView tire2 = (TextView) findViewById(R.id.tire_2);
        TextView accumulator = (TextView) findViewById(R.id.accumulator);
        TextView chain = (TextView) findViewById(R.id.chain);
        TextView brake1 = (TextView) findViewById(R.id.brake_1);
        TextView brake2 = (TextView) findViewById(R.id.brake_2);
        TextView spark_plug = (TextView) findViewById(R.id.spark_plug);
        TextView air_filter = (TextView) findViewById(R.id.air_filter);
        TextView cooling_water = (TextView) findViewById(R.id.cooling_water);

        TextView cost_tire1 = (TextView) findViewById(R.id.cost_tire_1);
        TextView cost_tire2 = (TextView) findViewById(R.id.cost_tire_2);
        TextView cost_accumulator = (TextView) findViewById(R.id.cost_accumulator);
        TextView cost_chain = (TextView) findViewById(R.id.cost_chain);
        TextView cost_brake1 = (TextView) findViewById(R.id.cost_brake_1);
        TextView cost_brake2 = (TextView) findViewById(R.id.cost_brake_2);
        TextView cost_spark_plug = (TextView) findViewById(R.id.cost_spark_plug);
        TextView cost_air_filter = (TextView) findViewById(R.id.cost_air_filter);
        TextView cost_cooling_water = (TextView) findViewById(R.id.cost_cooling_water);

        cost_tire1.setText(warranty.getBuilder().getLopTruoc() + ",000 VNĐ");
        cost_tire2.setText(warranty.getBuilder().getLopSau() + ",000 VNĐ");
        cost_accumulator.setText(warranty.getBuilder().getAcquy() + ",000 VNĐ");
        cost_chain.setText(warranty.getBuilder().getXich() + ",000 VNĐ");
        cost_brake1.setText(warranty.getBuilder().getPhanh() + ",000 VNĐ");
        cost_brake2.setText(warranty.getBuilder().getPhanhDau() + ",000 VNĐ");
        cost_spark_plug.setText(warranty.getBuilder().getBugi() + ",000 VNĐ");
        cost_air_filter.setText(warranty.getBuilder().getTamLocGio() + ",000 VNĐ");
        cost_cooling_water.setText(warranty.getBuilder().getNuocLamMat() + ",000 VNĐ");

        final TextView quantity_oil_1 = (TextView) findViewById(R.id.quantity_oil_1);
        final TextView quantity_oil_2 = (TextView) findViewById(R.id.quantity_oil_2);
        final TextView quantity_oil_3 = (TextView) findViewById(R.id.quantity_oil_3);
        final  TextView quantity_oil_4 = (TextView) findViewById(R.id.quantity_oil_4);
        final TextView quantity_tire_1 = (TextView) findViewById(R.id.quantity_tire_1);
        final TextView quantity_tire_2 = (TextView) findViewById(R.id.quantity_tire_2);
        final TextView quantity_accumulator = (TextView) findViewById(R.id.quantity_accumulator);
        final TextView quantity_chain = (TextView) findViewById(R.id.quantity_chain);
        final TextView quantity_brake_1 = (TextView) findViewById(R.id.quantity_brake_1);
        final TextView quantity_brake_2 = (TextView) findViewById(R.id.quantity_brake_2);
        final TextView quantity_spark_plug = (TextView) findViewById(R.id.quantity_spark_plug);
        final TextView quantity_air_filter = (TextView) findViewById(R.id.quantity_air_filter);
        final TextView quantity_cooling_water = (TextView) findViewById(R.id.quantity_cooling_water);

        final TextView payment_oil_1 = (TextView) findViewById(R.id.payment_oil_1);
        final TextView payment_oil_2 = (TextView) findViewById(R.id.payment_oil_2);
        final TextView payment_oil_3 = (TextView) findViewById(R.id.payment_oil_3);
        final TextView payment_oil_4 = (TextView) findViewById(R.id.payment_oil_4);
        final TextView payment_tire_1 = (TextView) findViewById(R.id.payment_tire_1);
        final TextView payment_tire_2 = (TextView) findViewById(R.id.payment_tire_2);
        final TextView payment_accumulator = (TextView) findViewById(R.id.payment_accumulator);
        final TextView payment_chain = (TextView) findViewById(R.id.payment_chain);
        final TextView payment_brake_1 = (TextView) findViewById(R.id.payment_brake_1);
        final TextView payment_brake_2 = (TextView) findViewById(R.id.payment_brake_2);
        final TextView payment_spark_plug = (TextView) findViewById(R.id.payment_spark_plug);
        final TextView payment_air_filter = (TextView) findViewById(R.id.payment_air_filter);
        final TextView payment_cooling_water = (TextView) findViewById(R.id.payment_cooling_water);

        final TextView quantity = (TextView) findViewById(R.id.quantity);
        final TextView payment = (TextView) findViewById(R.id.payment);

        check_oil1 = (CheckBox) findViewById(R.id.check_oil_1);
        check_oil2 = (CheckBox) findViewById(R.id.check_oil_2);
        check_oil3 = (CheckBox) findViewById(R.id.check_oil_3);
        check_oil4 = (CheckBox) findViewById(R.id.check_oil_4);
        check_tire1 = (CheckBox) findViewById(R.id.check_tire_1);
        check_tire2 = (CheckBox) findViewById(R.id.check_tire_2);
        check_chain = (CheckBox) findViewById(R.id.check_chain);
        check_accumulator = (CheckBox) findViewById(R.id.check_accumulator);
        check_brake1  = (CheckBox) findViewById(R.id.check_brake_1);
        check_brake2 = (CheckBox) findViewById(R.id.check_brake_2);
        check_spark_plug = (CheckBox) findViewById(R.id.check_spark_plug);
        check_air_filter = (CheckBox) findViewById(R.id.check_air_filter);
        check_cooling_water = (CheckBox) findViewById(R.id.check_cooling_water);

        final int[] sum = {0};
        final int[] count = {0};

        check_oil1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_oil_1.setText("1");
                    payment_oil_1.setText(warranty.getBuilder().getDauNhon() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getDauNhon();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getDauNhon();
                    quantity_oil_1.setText("0");
                    payment_oil_1.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_oil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_oil_2.setText("1");
                    payment_oil_2.setText(warranty.getBuilder().getDauGiamXoc() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getDauGiamXoc();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getDauGiamXoc();
                    quantity_oil_2.setText("0");
                    payment_oil_2.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_oil3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_oil_3.setText("1");
                    payment_oil_3.setText(warranty.getBuilder().getDauHopSo() + ",000 VNĐ");
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getDauHopSo();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getDauHopSo();
                    quantity_oil_3.setText("0");
                    payment_oil_3.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_oil4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_oil_4.setText("1");
                    payment_oil_4.setText(warranty.getBuilder().getDauPhanh() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getDauPhanh();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getDauPhanh();
                    quantity_oil_4.setText("0");
                    payment_oil_4.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_tire1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_tire_1.setText("1");
                    payment_tire_1.setText(warranty.getBuilder().getLopTruoc() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getLopTruoc();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getLopTruoc();
                    quantity_tire_1.setText("0");
                    payment_tire_1.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_tire2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_tire_2.setText("1");
                    payment_tire_2.setText(warranty.getBuilder().getLopSau() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getLopSau();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getLopSau();
                    quantity_tire_2.setText("0");
                    payment_tire_2.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_accumulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_accumulator.setText("1");
                    payment_accumulator.setText(warranty.getBuilder().getAcquy() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getAcquy();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getAcquy();
                    quantity_accumulator.setText("0");
                    payment_accumulator.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_chain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_chain.setText("1");
                    payment_chain.setText(warranty.getBuilder().getXich() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getXich();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getXich();
                    quantity_chain.setText("0");
                    payment_chain.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_brake1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_brake_1.setText("1");
                    payment_brake_1.setText(warranty.getBuilder().getPhanh() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getPhanh();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getPhanh();
                    quantity_brake_1.setText("0");
                    payment_brake_1.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_brake2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_brake_2.setText("1");
                    payment_brake_2.setText(warranty.getBuilder().getPhanhDau() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getPhanhDau();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getPhanhDau();
                    quantity_brake_2.setText("0");
                    payment_brake_2.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_spark_plug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_spark_plug.setText("1");
                    payment_spark_plug.setText(warranty.getBuilder().getBugi() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getBugi();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getBugi();
                    quantity_spark_plug.setText("0");
                    payment_spark_plug.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_air_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_air_filter.setText("1");
                    payment_air_filter.setText(warranty.getBuilder().getTamLocGio() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getTamLocGio();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getTamLocGio();
                    quantity_air_filter.setText("0");
                    payment_air_filter.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        check_cooling_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnim(v);
                CheckBox c = (CheckBox) v;
                if(c.isChecked())
                {
                    quantity_cooling_water.setText("1");
                    payment_cooling_water.setText(warranty.getBuilder().getNuocLamMat() + ",000 VNĐ");
                    count[0]++;
                    sum[0] += warranty.getBuilder().getNuocLamMat();
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
                else
                {
                    count[0]--;
                    sum[0] -= warranty.getBuilder().getNuocLamMat();
                    quantity_cooling_water.setText("0");
                    payment_cooling_water.setText("0 VNĐ");
                    quantity.setText(String.valueOf(count[0]));
                    if(sum[0] != 0)
                        payment.setText(sum[0] + ",000 VNĐ");
                    else
                        payment.setText("0 VNĐ");
                }
            }
        });

        vehilce.setText(cus.getBuilder().getVehicle());
        tire1.setText(cus.getBuilder().getVehicle());
        tire2.setText(cus.getBuilder().getVehicle());
        accumulator.setText(cus.getBuilder().getVehicle());
        chain.setText(cus.getBuilder().getVehicle());
        brake1.setText(cus.getBuilder().getVehicle());
        brake2.setText(cus.getBuilder().getVehicle());
        spark_plug.setText(cus.getBuilder().getVehicle());
        air_filter.setText(cus.getBuilder().getVehicle());
        cooling_water.setText(cus.getBuilder().getVehicle());

        if(cus.getBuilder().getType().equals("Xe ga"))
        {
            img_oil.setImageResource(R.mipmap.a2);
            oil.setText("Dầu nhờn xe ga");
            code_oil.setText("(MB SL 10W-30)");
            cost_oil.setText("84,000 VNĐ");
            text_oil.setText("Dầu nhờn xe ga");
        }

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
        view.startAnimation(anim);
    }

    public void startAnim(View view)
    {
        if(view instanceof CheckBox)
        {
            CheckBox checkBox = (CheckBox) view;
            if(checkBox.isChecked())
                checkBox.setChecked(true);
            else
                checkBox.setChecked(false);

            ViewGroup root = (ViewGroup) view.getParent();
            for(int i = 0; i < root.getChildCount(); i++)
            {
                if(root.getChildAt(i) instanceof ViewGroup)
                {
                    ViewGroup son = (ViewGroup) root.getChildAt(i);
                    for(int j = 0; j < son.getChildCount(); j++)
                    {
                        if(son.getChildAt(j) instanceof ImageView)
                        {
                            son.getChildAt(j).startAnimation(anim);
                        }
                    }
                }
            }
        }
        else
        {
            view.startAnimation(anim);
        }
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

    public void apply(View view)
    {
        HashMap m = new HashMap();

        if(check_oil1.isChecked())
            m.put("oil1", 4000);
        if(check_oil2.isChecked())
            m.put("oil2", 4000);
        if(check_oil3.isChecked())
            m.put("oil3", 4000);
        if(check_oil4.isChecked())
            m.put("oil4", 4000);

        if(check_tire1.isChecked())
            m.put("tire1", 22000);
        if(check_tire2.isChecked())
            m.put("tire2", 22000);

        if(check_accumulator.isChecked())
            m.put("accumulator", 21900);
        if(check_chain.isChecked())
            m.put("chain", 1000);

        if(check_brake1.isChecked())
            m.put("brake", 4000);
        if(check_brake2.isChecked())
            m.put("oil_brake", 6000);

        if(check_spark_plug.isChecked())
            m.put("spark_plug", 8000);
        if(check_air_filter.isChecked())
            m.put("air_filter", 16000);
        if(check_cooling_water.isChecked())
            m.put("cooling_water", 14600);

        new UpdateUserWarranty(m).execute();
        Toast.makeText(this, "Các linh kiện trên xe bạn đã được cập nhật tình trạng mới", Toast.LENGTH_LONG).show();
    }
}
