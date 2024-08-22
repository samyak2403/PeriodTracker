package com.arrowwould.periodtracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.arrowwould.periodtracker.Databases.Entities.DateDetails;
import com.arrowwould.periodtracker.Databases.OvulationDetailsHandler;
import com.arrowwould.periodtracker.Databases.Params;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.Utils.OvulationCalculations;
import com.arrowwould.periodtracker.Utils.SharedPreferenceUtils;
import com.arrowwould.periodtracker.databinding.ActivityEditPeriodBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class EditPeriodActivity extends AppCompatActivity {
    ActivityEditPeriodBinding binding;
    OvulationDetailsHandler handler;

    Calendar selectedDate;
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityEditPeriodBinding inflate = ActivityEditPeriodBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());

//        AdsGoogle adsGoogle = new AdsGoogle( this);
//        adsGoogle.Banner_Show((RelativeLayout) findViewById(R.id.banner), this);
//        adsGoogle.Interstitial_Show_Counter(this);

        selectedDate = Calendar.getInstance();
        this.binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                
                selectedDate.set(year, month, dayOfMonth);
                
                
            }
        });
        this.binding.cancelBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                EditPeriodActivity.this.m110x7f7ce216(view);
            }
        });
        this.handler = new OvulationDetailsHandler(this);
        this.binding.saveBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                EditPeriodActivity.this.m111x4f3d15b5(view);
            }
        });
    }

    
    
    public  void m110x7f7ce216(View view) {
        finish();
    }
    public  void m111x4f3d15b5(View view) {
        saveData();
    }
    private void saveData() {
        this.binding.cancelBtn.setVisibility(View.GONE);
        this.binding.saveBtn.setVisibility(View.GONE);
        this.binding.progressBar.setVisibility(View.VISIBLE);
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(selectedDate.getTime());
        int parseInt = Integer.parseInt(SharedPreferenceUtils.getCycles(this));
        String ovulation = OvulationCalculations.getOvulation(format, parseInt);
        String fertileWindow = OvulationCalculations.getFertileWindow(format, parseInt);
        String safeDays = OvulationCalculations.getSafeDays(format, parseInt, Integer.parseInt(SharedPreferenceUtils.getCycleLength(this)));
        OvulationDetailsHandler ovulationDetailsHandler = this.handler;
        ovulationDetailsHandler.addOvulationDetail(new DateDetails(fertileWindow, format + " --- " + OvulationCalculations.minusDays(format, 1), format, ovulation), Params.OVULATION_DETAILS_TABLE_HOME);
        this.handler.addOvulationDetail(new DateDetails(fertileWindow, safeDays, format, ovulation), Params.OVULATION_DETAILS_TABLE_CALENDAR);
        Toast.makeText(this, getResources().getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }
}
