package com.arrowwould.periodtracker.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.arrowwould.periodtracker.Activities.EditPeriodActivity;
import com.arrowwould.periodtracker.Databases.Entities.DateDetails;
import com.arrowwould.periodtracker.Databases.OvulationDetailsHandler;
import com.arrowwould.periodtracker.Databases.Params;
import com.arrowwould.periodtracker.ThemesFiles.MyCustomTheme;
import com.arrowwould.periodtracker.ThemesFiles.MyThemeHandler;
import com.arrowwould.periodtracker.Utils.MyCustomMonthView;
import com.arrowwould.periodtracker.Utils.MyDateUtils;
import com.arrowwould.periodtracker.Utils.OvulationCalculations;
import com.arrowwould.periodtracker.Utils.SharedPreferenceUtils;
import com.arrowwould.periodtracker.Utils.Utils;
import com.arrowwould.periodtracker.databinding.FragmentCalendarBinding;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.arrowwould.periodtracker.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CalendarFragment extends Fragment {
    private static final String TAG = "CalendarTag";
    FragmentCalendarBinding binding;
    OvulationDetailsHandler handler;
    private final String format = "MMM yyyy";
    private List<DateDetails> detailsList = new ArrayList();

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentCalendarBinding.inflate(layoutInflater);
        OvulationDetailsHandler ovulationDetailsHandler = new OvulationDetailsHandler(getActivity());
        this.handler = ovulationDetailsHandler;
        this.detailsList = ovulationDetailsHandler.getAllOvulationDetails(Params.OVULATION_DETAILS_TABLE_CALENDAR);

        this.binding.calendarView.setMonthView(MyCustomMonthView.class);
        this.binding.topCurrentDateTv.setText(convertIntoFormat(new Date(), "MMM yyyy"));
        this.binding.calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() { 
            @Override 
            public void onCalendarOutOfRange(Calendar calendar) {
            }

            @Override 
            public void onCalendarSelect(Calendar calendar, boolean z) {
                if (z) {
                    CalendarFragment.this.mentionDataWithSelectedDate(new Date(calendar.getTimeInMillis()));
                    CalendarFragment.this.binding.topCurrentDateTv.setText(CalendarFragment.this.convertIntoFormat(new Date(calendar.getTimeInMillis()), "MMM yyyy"));
                }
            }
        });
        this.binding.calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() { 
            @Override 
            public void onMonthChange(int i, int i2) {
                Date date = new Date();
                date.setMonth(i2 - 1);
                date.setYear(i - 1900);
                CalendarFragment.this.binding.topCurrentDateTv.setText(new SimpleDateFormat("MMM yyyy").format(date));
            }
        });
        this.binding.editPeriodBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                CalendarFragment.this.m134x7fc86381(view);
            }
        });
        mentionDataWithSelectedDate(java.util.Calendar.getInstance().getTime());
        setUpTheme();
        setUpAllEvents();
        return this.binding.getRoot();
    }

    
    
    public  void m134x7fc86381(View view) {
        startActivity(new Intent(getActivity(), EditPeriodActivity.class));
    }

    
    public String convertIntoFormat(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    private void setUpAllEvents() {
        for (DateDetails dateDetails : this.detailsList) {
            this.binding.calendarView.addSchemeDate(getCalendarFromDate(dateDetails.getNextPeriod()));
            this.binding.calendarView.addSchemeDate(getCalendarFromDate(dateDetails.getOvulationPeriod()));
            mentionRange(dateDetails.getNextPeriod() + " --- " + OvulationCalculations.addDays(dateDetails.getNextPeriod(), Integer.parseInt(SharedPreferenceUtils.getCycleLength(getActivity()))));
            mentionRange(dateDetails.getFertileDays());
        }
    }

    private void mentionRange(String str) {
        Log.d(TAG, "========= mentioning range : " + str + " ===============");
        for (String str2 : MyDateUtils.getAllDatesStrInRange(MyDateUtils.getDateFromString(str.split(" --- ")[0], "-"), MyDateUtils.getDateFromString(str.split(" --- ")[1], "-"))) {
            this.binding.calendarView.addSchemeDate(getCalendarFromDate(str2));
        }
    }

    private void setUpTheme() {
        MyCustomTheme appTheme = new MyThemeHandler().getAppTheme(getActivity());
        Utils.setButtonTint(this.binding.editPeriodBtn, appTheme.getThemeColor());
        if (appTheme.isDark()) {
            this.binding.headingTv.setTextColor(getResources().getColor(R.color.white));
            this.binding.calendarView.setWeeColor(17170445, getResources().getColor(R.color.white));
        }
    }

    
    public void mentionDataWithSelectedDate(Date date) {
        String format = null;
        this.binding.currentDateTv.setText(new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(date));
        this.binding.currentDayTv.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date));
        for (DateDetails dateDetails : this.detailsList) {
            try {
                format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
                if (!MyDateUtils.checkDate(format, dateDetails.getNextPeriod(), OvulationCalculations.addDays(dateDetails.getNextPeriod(), Integer.parseInt(SharedPreferenceUtils.getCycleLength(getActivity()))), "yyyy-MM-dd") && !format.equals(dateDetails.getNextPeriod())) {
                    if (MyDateUtils.checkDate(format, OvulationCalculations.minusDays(dateDetails.getFertileDays().split("---")[0].trim(), 1), OvulationCalculations.addDays(dateDetails.getFertileDays().split("---")[1].trim(), 1), "yyyy-MM-dd")) {
                        if (dateDetails.getOvulationPeriod().equals(format)) {
                            mentionCondition(R.string.high, format, dateDetails);
                            return;
                        } else {
                            mentionCondition(R.string.medium, format, dateDetails);
                            return;
                        }
                    }
                    mentionCondition(R.string.low, format, dateDetails);
                }
            } catch (ParseException e) {
                Log.d(TAG, "error --> " + e.getMessage());
                Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();

                throw new RuntimeException(e);
            }
            mentionCondition(R.string.very_low, format, dateDetails);
            return;
        }
    }

    private void mentionCondition(int i, String str, DateDetails dateDetails) {
        Integer.parseInt(str.split("-")[2]);
        Integer.parseInt(str.split("-")[1]);
        Integer.parseInt(str.split("-")[0]);
        this.binding.chancesTv.setText(requireActivity().getResources().getString(i));
    }

    private Calendar getCalendarFromDate(String str) {
        int parseInt = Integer.parseInt(str.split("-")[2]);
        int parseInt2 = Integer.parseInt(str.split("-")[1]);
        int parseInt3 = Integer.parseInt(str.split("-")[0]);
        Calendar calendar = new Calendar();
        calendar.setDay(parseInt);
        calendar.setMonth(parseInt2);
        calendar.setYear(parseInt3);
        return calendar;
    }
}
