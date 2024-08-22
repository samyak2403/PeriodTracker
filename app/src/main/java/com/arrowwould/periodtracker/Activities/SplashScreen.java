package com.arrowwould.periodtracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.arrowwould.periodtracker.R;
public class SplashScreen extends AppCompatActivity {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OpenNext1();
            }
        },5000);
    }
    public void OpenNext1() {
        startActivity(new Intent(this, InputActivity.class));
        finish();
    }
}
