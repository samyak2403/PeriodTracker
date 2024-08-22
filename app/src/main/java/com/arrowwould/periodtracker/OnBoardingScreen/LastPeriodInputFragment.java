package com.arrowwould.periodtracker.OnBoardingScreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.arrowwould.periodtracker.Activities.InputActivity;
import com.arrowwould.periodtracker.Activities.MainActivity;
import com.arrowwould.periodtracker.Databases.Entities.DateDetails;
import com.arrowwould.periodtracker.Databases.OvulationDetailsHandler;
import com.arrowwould.periodtracker.Databases.Params;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.Utils.OvulationCalculations;
import com.arrowwould.periodtracker.Utils.SharedPreferenceUtils;
import com.arrowwould.periodtracker.databinding.FragmentLastPeriodInputBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class LastPeriodInputFragment extends Fragment {
    FragmentLastPeriodInputBinding binding;
    OvulationDetailsHandler handler;
    Calendar selectedDate;

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentLastPeriodInputBinding.inflate(layoutInflater);
        this.handler = new OvulationDetailsHandler(getActivity());
        final InputActivity inputActivity = (InputActivity) requireActivity();
         selectedDate = Calendar.getInstance();
        this.binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                
                selectedDate.set(year, month, dayOfMonth);
                
                
            }
        });

        this.binding.backSessionBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                ((ViewPager2) inputActivity.findViewById(R.id.viewPager)).setCurrentItem(1, true);
            }
        });








        this.binding.getStartedBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                LastPeriodInputFragment.this.m142x23ec2a7(inputActivity, view);
            }
        });
        return this.binding.getRoot();
    }



    
    public  void m142x23ec2a7(InputActivity inputActivity, View view) {
        this.binding.progressBar.setVisibility(View.VISIBLE);
        this.binding.backSessionBtn.setVisibility(View.GONE);
        this.binding.getStartedBtn.setVisibility(View.GONE);
        requireActivity().deleteDatabase(Params.DB_NAME_DETAILS);
        requireActivity().deleteDatabase(Params.DB_NAME_NOTES);









        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(selectedDate.getTime());
        SharedPreferenceUtils.saveData(String.valueOf(inputActivity.cyclesLength), format, String.valueOf(inputActivity.periodLength), inputActivity);
        saveDateToDatabase(format, inputActivity);
        startActivity(new Intent(inputActivity, MainActivity.class));
        inputActivity.finishAffinity();
    }

    private void saveDateToDatabase(String str, InputActivity inputActivity) {
        int i = inputActivity.cyclesLength;
        int i2 = inputActivity.periodLength;
        String ovulation = OvulationCalculations.getOvulation(str, i);
        String nextPeriod = OvulationCalculations.getNextPeriod(str, i);
        String fertileWindow = OvulationCalculations.getFertileWindow(str, i);
        String safeDays = OvulationCalculations.getSafeDays(str, i, i2);
        for (int i3 = 0; i3 < 12; i3++) {
            int i4 = i * i3;
            this.handler.addOvulationDetail(new DateDetails(OvulationCalculations.addDays(fertileWindow, i4), OvulationCalculations.addDays(safeDays, i4), OvulationCalculations.addDays(nextPeriod, i4), OvulationCalculations.addDays(ovulation, i4)), Params.OVULATION_DETAILS_TABLE_HOME);
            this.handler.addOvulationDetail(new DateDetails(OvulationCalculations.minusDays(fertileWindow, i4), OvulationCalculations.minusDays(safeDays, i4), OvulationCalculations.minusDays(nextPeriod, i4), OvulationCalculations.minusDays(ovulation, i4)), Params.OVULATION_DETAILS_TABLE_HOME);
        }
        for (int i5 = 0; i5 < 6; i5++) {
            int i6 = i * i5;
            this.handler.addOvulationDetail(new DateDetails(OvulationCalculations.addDays(fertileWindow, i6), OvulationCalculations.addDays(safeDays, i6), OvulationCalculations.addDays(nextPeriod, i6), OvulationCalculations.addDays(ovulation, i6)), Params.OVULATION_DETAILS_TABLE_CALENDAR);
        }
        for (int i7 = 0; i7 < 2; i7++) {
            int i8 = i * i7;
            this.handler.addOvulationDetail(new DateDetails(OvulationCalculations.minusDays(fertileWindow, i8), OvulationCalculations.minusDays(safeDays, i8), OvulationCalculations.minusDays(nextPeriod, i8), OvulationCalculations.minusDays(ovulation, i8)), Params.OVULATION_DETAILS_TABLE_CALENDAR);
        }
    }
}
