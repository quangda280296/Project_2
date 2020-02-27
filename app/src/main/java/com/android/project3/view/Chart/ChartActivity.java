package com.android.project3.view.Chart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.android.project3.R;
import com.android.project3.controller.DrawChart;
import com.android.project3.controller.LoadUserRoad;

import static com.android.project3.model.ManagerStatic.cus;

public class ChartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DateFormat fmtDateAndTime = DateFormat.getDateInstance();
    Calendar calendarStart = Calendar.getInstance();
    Calendar calendarEnd = Calendar.getInstance();
    Button dateStart;
    Button dateEnd;
    LinearLayout linearLayout;

    DatePickerDialog.OnDateSetListener s = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear, int dayOfMonth) {
            calendarStart.set(Calendar.YEAR, year);
            calendarStart.set(Calendar.MONTH, monthOfYear);
            calendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelStart();
        }
    };

    DatePickerDialog.OnDateSetListener e = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear, int dayOfMonth) {
            calendarEnd.set(Calendar.YEAR, year);
            calendarEnd.set(Calendar.MONTH, monthOfYear);
            calendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelEnd();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        getSupportActionBar().setTitle("Xem biểu đồ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        linearLayout = (LinearLayout)findViewById(R.id.chart_view);

        dateStart = (Button) findViewById(R.id.date_start);
        dateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ChartActivity.this, s,
                        calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dateEnd = (Button) findViewById(R.id.date_end);
        dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ChartActivity.this, e,
                        calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH),
                        calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
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

    public void updateLabelStart()
    {
        dateStart.setText(fmtDateAndTime.format(calendarStart.getTime()));
    }

    public void updateLabelEnd()
    {
        dateEnd.setText(fmtDateAndTime.format(calendarEnd.getTime()));
    }

    public void updateChart(View view)
    {
        Thread get = new Thread() {
            public void run()
            {
                new LoadUserRoad(fmtDateAndTime.format(calendarStart.getTime()), fmtDateAndTime.format(calendarEnd.getTime()) ).Load();
            }
        };
        get.start();
        try {
            get.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> date = new ArrayList<>();
        List<Integer> data = new ArrayList<>();

        HashMap hm = cus.getBuilder().getUserRoad();

        if(hm != null ) {

            if (hm.size() != 0) {

                rearrange(hm);

                for(int i = 0; i < index; i++) {
                    String[] words = key[i].split("-");

                    if (words.length == 3) {
                        date.add(words[0] + "/" + words[1]);
                    }

                    data.add(value[i]);
                }

                DrawChart dr = new DrawChart(this, date, data);
                linearLayout.addView(dr.Draw(),0);

            } else
                Toast.makeText(this, "Không có dữ liệu trong khoảng thời gian này!", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "Không có dữ liệu trong khoảng thời gian này!", Toast.LENGTH_LONG).show();
    }

    String[] key;
    int[] value;
    int index;;

    public void rearrange(HashMap m)
    {
        index = 0;

        int[] key_day = new int[m.size()];
        int[] key_month = new int[m.size()];
        int[] key_year = new int[m.size()];

        key = new String[m.size()];
        value = new int[m.size()];

        // Lay mot tap hop cac entry
        Set set = m.entrySet();
        // Lay mot iterator
        Iterator i = set.iterator();
        // Hien thi cac phan tu
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            String[] words = me.getKey().toString().split("-");

            if (words.length == 3)
            {
                key[index] = me.getKey().toString();

                key_day[index] = Integer.parseInt(words[0]);
                key_month[index] = Integer.parseInt(words[1]);
                key_year[index] = Integer.parseInt(words[2]);

                value[index] = Integer.parseInt(String.valueOf(me.getValue()));

                index++;
            }
        }

        for(int j = 0; j < index - 1 ; j++)
        {
            for (int k = j + 1; k < index; k++)
            {
                if (key_year[j] > key_year[k])
                {
                    int temp;
                    String temporary;

                    temp = value[j];
                    value[j] = value[k];
                    value[k] = temp;

                    temporary = key[j];
                    key[j] = key[k];
                    key[k] = temporary;


                    temp = key_day[j];
                    key_day[j] = key_day[k];
                    key_day[k] = temp;

                    temp = key_month[j];
                    key_month[j] = key_month[k];
                    key_month[k] = temp;

                    temp = key_year[j];
                    key_year[j] = key_year[k];
                    key_year[k] = temp;
                }
                else if (key_year[j] == key_year[k])
                {
                    if (key_month[j] > key_month[k])
                    {
                        int temp;
                        String temporary;

                        temp = value[j];
                        value[j] = value[k];
                        value[k] = temp;

                        temporary = key[j];
                        key[j] = key[k];
                        key[k] = temporary;


                        temp = key_day[j];
                        key_day[j] = key_day[k];
                        key_day[k] = temp;

                        temp = key_month[j];
                        key_month[j] = key_month[k];
                        key_month[k] = temp;

                        temp = key_year[j];
                        key_year[j] = key_year[k];
                        key_year[k] = temp;
                    }
                    else if (key_month[j] == key_month[k])
                    {
                        if (key_day[j] > key_day[k]) {
                            int temp;
                            String temporary;

                            temp = value[j];
                            value[j] = value[k];
                            value[k] = temp;

                            temporary = key[j];
                            key[j] = key[k];
                            key[k] = temporary;


                            temp = key_day[j];
                            key_day[j] = key_day[k];
                            key_day[k] = temp;

                            temp = key_month[j];
                            key_month[j] = key_month[k];
                            key_month[k] = temp;

                            temp = key_year[j];
                            key_year[j] = key_year[k];
                            key_year[k] = temp;
                        }
                    }
                }
            }
        }
    }

}
