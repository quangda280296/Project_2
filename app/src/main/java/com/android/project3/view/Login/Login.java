package com.android.project3.view.Login;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.project3.R;
import com.android.project3.model.Customer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static com.android.project3.model.ManagerStatic.customer;
import static com.android.project3.model.ManagerStatic.cus;
import static com.android.project3.view.Introduction.Introduction.readString;

/**
 * Created by keban on 3/15/2017.
 */
public class Login extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        TextView name = (TextView) findViewById(R.id.name);
        Typeface myNewFace = Typeface.createFromAsset(getAssets(), "fonts/mistral.TTF");
        name.setTypeface(myNewFace);

        readString = "";
    }

    public void map(View view)
    {
        startActivity(new Intent("MapNotLogin"));
    }

    public void start(View view)
    {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();     
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null)
        {
            String id = result.getContents();
                for (Customer c : customer) {
                    if (c.getBuilder().getId().equals(id)) {
                        cus = c;
                        startActivity(new Intent("Welcome"));
                        finish();
                        break;
                    }
                }
        }
        else {
            Toast.makeText(getBaseContext(), "Not Found", Toast.LENGTH_SHORT).show();
        }
    }
}

