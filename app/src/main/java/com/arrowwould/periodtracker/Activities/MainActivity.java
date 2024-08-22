package com.arrowwould.periodtracker.Activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.arrowwould.periodtracker.Adapters.FragmentsAdapter;
import com.arrowwould.periodtracker.ThemesFiles.MyCustomTheme;
import com.arrowwould.periodtracker.ThemesFiles.MyThemeHandler;
import com.arrowwould.periodtracker.Utils.Utils;
import com.arrowwould.periodtracker.databinding.ActivityMainBinding;

import com.google.android.material.tabs.TabLayout;
import com.arrowwould.periodtracker.R;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private final int[] tabIcons = {R.drawable.ic_home, R.drawable.ic_calendar, R.drawable.ic_blogs, R.drawable.ic_settings, R.drawable.ic_settings_gear};
    MyCustomTheme theme;

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityMainBinding inflate = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());

//        AdsGoogle adsGoogle = new AdsGoogle( this);
//        adsGoogle.Banner_Show((RelativeLayout) findViewById(R.id.banner), this);
//        adsGoogle.Interstitial_Show_Counter(this);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.theme = new MyThemeHandler().getAppTheme(this);
        this.binding.viewPager.setAdapter(new FragmentsAdapter(supportFragmentManager, getLifecycle()));
        this.binding.viewPager.setOffscreenPageLimit(4);
        this.binding.viewPager.setUserInputEnabled(false);
        setUpTheme();
        final TabLayout tabLayout = this.binding.tabLayout;
        for (int i : this.tabIcons) {
            tabLayout.addTab(tabLayout.newTab().setIcon(i));
        }
        for (int i2 = 0; i2 < tabLayout.getTabCount(); i2++) {
            tabLayout.getTabAt(i2).getIcon().setColorFilter(ContextCompat.getColor(this, this.theme.getThemeColor()), PorterDuff.Mode.SRC_IN);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { 
            @Override 
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override 
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override 
            public void onTabSelected(TabLayout.Tab tab) {
//                AdsGoogle adsGoogle = new AdsGoogle( MainActivity.this);
//                adsGoogle.Interstitial_Show_Counter(MainActivity.this);
                MainActivity.this.binding.viewPager.setCurrentItem(tab.getPosition());
            }
        });
        this.binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { 
            @Override 
            public void onPageSelected(int i3) {
                TabLayout tabLayout2 = tabLayout;
                tabLayout2.selectTab(tabLayout2.getTabAt(i3));
            }
        });
    }

    private void setUpTheme() {
        Utils.makeTransparentStatusBar(this);
        this.binding.mainParentLayout.setBackground(getResources().getDrawable(this.theme.getBgImg()));
        this.binding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(this.theme.getThemeColor()));
    }
    @Override
    public void onBackPressed() {
        int currentItem = this.binding.viewPager.getCurrentItem();
        
        if (currentItem != 0) {
            
            this.binding.viewPager.setCurrentItem(0);
        } else {
            
            super.onBackPressed();
        }

    }
}
