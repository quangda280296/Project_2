package com.android.project3.view.Introduction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.android.project3.R;
import com.android.project3.controller.LoadCustomer;

import static com.android.project3.model.ManagerStatic.customer;

/**
 * Created by keban on 3/16/2017.
 */
public class Introduction extends AppCompatActivity {

    public static int[] resourceIds = { R.layout.video1, R.layout.video2, R.layout.video3 };
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static String readString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_activity);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        try {
            FileInputStream fIn = openFileInput("Login.txt");
            InputStreamReader isr = new InputStreamReader(fIn);

            char[] inputBuffer = new char[10];

            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {

                readString += String.copyValueOf(inputBuffer, 0, charRead);
                inputBuffer = new char[10];
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            int index = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView = inflater.inflate(resourceIds[index], container, false);
            Button ok = (Button) rootView.findViewById(R.id.ok);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Thread get = new Thread() {
                        public void run()
                        {
                            new LoadCustomer().Load();
                        }
                    };
                    get.start();
                    try {
                        get.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                if(customer == null)
                    Toast.makeText(getContext(), "Vui lòng bật kết nối Internet để tiếp tục", Toast.LENGTH_LONG).show();

                else
                {
                    if (readString.compareTo("") != 0) {
                        startActivity(new Intent("Welcome"));
                    } else {
                        startActivity(new Intent("Login"));
                    }
                }
              }
            });
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
