package com.android.project3.view.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.android.project3.R;
import com.android.project3.controller.GetDistrict;
import com.android.project3.controller.GetLocation;
import com.android.project3.model.NLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.android.project3.model.ManagerStatic.province;
import static com.android.project3.model.ManagerStatic.district;
import static com.android.project3.model.ManagerStatic.location;

public class MapActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
                                                             OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
                                                             GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    RadioButton honda;
    RadioButton petrol_station;
    TextView found;
    Button detail;
    String[] item;

    int positionProvince = 0;
    int positionDistrict = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getSupportActionBar().setTitle("Tìm kiếm chỉ đường");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        found = (TextView) findViewById(R.id.found);
        detail = (Button) findViewById(R.id.detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        honda = (RadioButton) findViewById(R.id.honda);
        petrol_station = (RadioButton) findViewById(R.id.petrol_station);

        final Spinner mDistrict = (Spinner) findViewById(R.id.district);
        mDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionDistrict = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner mProvince = (Spinner) findViewById(R.id.province);
        mProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                positionProvince = position;
                Thread get = new Thread() {
                    public void run()
                    {
                        new GetDistrict("0" + position).Load();
                    }
                };
                get.start();
                try {
                    get.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String> b = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, district);
                b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mDistrict.setAdapter(b);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> a = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, province);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProvince.setAdapter(a);
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

    /*
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
        }
    }

    public void search(View view)
    {
        if(positionProvince != 0)
        {
            String address = "";
            if (honda.isChecked() == true)
            {
                address = "Honda";

                if(positionDistrict != 0)
                    address += "," + district.get(positionDistrict);

                address += "," + province.get(positionProvince);
            }
            else
            if (petrol_station.isChecked() == true)
            {
                address = "Cayxang";

                if(positionDistrict != 0)
                    address += "," + district.get(positionDistrict);

                address += "," + province.get(positionProvince);
            }

            address = address.replaceAll("\\s","");
            GetLocation.getHonda(this, address);

            item = new String[location.size()];
            int i = 0;
            for(NLocation l : location)
            {
                LatLng loc = new LatLng(l.getBuilder().getLatitude(), l.getBuilder().getLongitude());
                mMap.addMarker(new MarkerOptions().position(loc).title(l.getBuilder().getName()));
                item[i] = l.getBuilder().getName();

                if(i == 0)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 14));

                i++;
            }

            if (location != null)
            {
                found.setText("Tìm thấy " + location.size() + " địa điểm");
                detail.setText("[ ? ]");
                detail.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            }
        }
        else
            Toast.makeText(this, "Hãy chọn 1 tỉnh / thành phố nơi bạn muốn tìm kiếm", Toast.LENGTH_LONG).show();
    }

    public void getMyLocation(View view)
    {
        final Location[] mLocation = new Location[1];
        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        final Criteria criteria = new Criteria();

        Thread get = new Thread() {
            public void run()
            {
                if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                {
                    mLocation[0] = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
                }
            }
        };
        get.start();

        try {
            get.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (mLocation[0] != null)
        {
            LatLng latLng = new LatLng(mLocation[0].getLatitude(), mLocation[0].getLongitude());
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> streets = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 14);
                String address = "";
                if (honda.isChecked() == true)
                {
                    address = "Honda,";
                }
                else
                if (petrol_station.isChecked() == true)
                {
                    address = "Cayxang,";
                }
                if(streets.size() > 0)
                {
                    address += streets.get(0).getAddressLine(streets.get(0).getMaxAddressLineIndex() - 2) + ",";
                    address += streets.get(0).getAddressLine(streets.get(0).getMaxAddressLineIndex() - 1);
                }

                address = address.replaceAll("\\s","");
                GetLocation.getHonda(this, address);

                item = new String[location.size()];
                int i = 0;
                for(NLocation l : location)
                {
                    LatLng loc = new LatLng(l.getBuilder().getLatitude(), l.getBuilder().getLongitude());
                    mMap.addMarker(new MarkerOptions().position(loc).title(l.getBuilder().getName()));
                    item[i] = l.getBuilder().getName();

                    if(i == 0)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 14));

                    i++;
                }

                if (location != null)
                {
                    found.setText("Tìm thấy " + location.size() + " địa điểm");
                    detail.setText("[ ? ]");
                    detail.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void show(View view)
    {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.detail_dialog, null);
        ListView ls = (ListView) alertLayout.findViewById(R.id.ls);
        ArrayAdapter a = new SetDS(this);
        ls.setAdapter(a);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        AlertDialog dialog = alert.create();
        final AlertDialog final_dialog = dialog;

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final_dialog.hide();
            }
        });

        dialog = alert.create();
        dialog.show();

        final AlertDialog finalDialog = dialog;
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finalDialog.hide();
                LatLng loc = new LatLng(location.get(position).getBuilder().getLatitude(), location.get(position).getBuilder().getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 17));
            }
        });
    }

    public class SetDS extends ArrayAdapter<String> {

        Activity context;

        public SetDS(Activity context) {

            super(context, R.layout.dialog_honda, item);
            this.context = context;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View row = inflater.inflate(R.layout.dialog_honda, null);

            TextView name = (TextView) row.findViewById(R.id.name);
            TextView address = (TextView) row.findViewById(R.id.address);

            for(int i = 0; i < item.length; i++)
            {
                if(i == position)
                {
                    name.setText(location.get(i).getBuilder().getName());
                    address.setText(location.get(i).getBuilder().getAddress());
                }
            }

            return row;
        }
    }
}
