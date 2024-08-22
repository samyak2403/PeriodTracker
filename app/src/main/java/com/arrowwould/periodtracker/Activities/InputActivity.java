package com.arrowwould.periodtracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.arrowwould.periodtracker.Adapters.OnBoardingFragmentsAdapter;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.Utils.SharedPreferenceUtils;
import com.arrowwould.periodtracker.Utils.Utils;
import com.arrowwould.periodtracker.databinding.ActivityInputBinding;


public class InputActivity extends AppCompatActivity {
    ActivityInputBinding binding;
    public int cyclesLength = 0;
    public int periodLength = 0;

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityInputBinding inflate = ActivityInputBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());

//        AdsGoogle adsGoogle = new AdsGoogle( this);
//        adsGoogle.Banner_Show((RelativeLayout) findViewById(R.id.banner), this);
//        adsGoogle.Interstitial_Show_Counter(this);


        getWindow().setFlags(512, 256);
        if (!SharedPreferenceUtils.getDate(this).isEmpty() && !SharedPreferenceUtils.getCycles(this).isEmpty() && !getIntent().getBooleanExtra("recalculate", false)) {
            startActivity(new Intent(this, MainActivity.class));
            finishAffinity();
            return;
        }
        this.binding.viewPager.setOffscreenPageLimit(3);
        this.binding.viewPager.setUserInputEnabled(false);
        Utils.setStatusBarColor(R.color.input_screen_bg_color, this);
        this.binding.viewPager.setAdapter(new OnBoardingFragmentsAdapter(getSupportFragmentManager(), getLifecycle()));
    }
}
