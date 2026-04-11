package com.arrowwould.periodtracker.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.arrowwould.periodtracker.Activities.EditPeriodActivity;
import com.arrowwould.periodtracker.Databases.Entities.DateDetails;
import com.arrowwould.periodtracker.Databases.OvulationDetailsHandler;
import com.arrowwould.periodtracker.Databases.Params;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.ThemesFiles.MyCustomTheme;
import com.arrowwould.periodtracker.ThemesFiles.MyThemeHandler;
import com.arrowwould.periodtracker.Utils.MyDateUtils;
import com.arrowwould.periodtracker.Utils.OvulationCalculations;
import com.arrowwould.periodtracker.Utils.SharedPreferenceUtils;
import com.arrowwould.periodtracker.Utils.Utils;
import com.arrowwould.periodtracker.databinding.CalendarDayLayoutBinding;
import com.arrowwould.periodtracker.databinding.FragmentCalendarBinding;

import com.kizitonwose.calendar.core.CalendarDay;
import com.kizitonwose.calendar.core.DayPosition;
import com.kizitonwose.calendar.view.MonthDayBinder;
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder;
import com.kizitonwose.calendar.view.ViewContainer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment {
    private static final String TAG = "CalendarTag";
    private FragmentCalendarBinding binding;
    private OvulationDetailsHandler handler;
    private List<DateDetails> detailsList = new ArrayList<>();
    private LocalDate selectedDate = LocalDate.now();
    private final DateTimeFormatter monthHeaderFormatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentCalendarBinding.inflate(layoutInflater);
        this.handler = new OvulationDetailsHandler(getActivity());
        this.detailsList = handler.getAllOvulationDetails(Params.OVULATION_DETAILS_TABLE_CALENDAR);

        setupCalendar();
        
        this.binding.topCurrentDateTv.setText(selectedDate.format(monthHeaderFormatter));
        this.binding.editPeriodBtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), EditPeriodActivity.class)));

        mentionDataWithSelectedDate(selectedDate);
        setUpTheme();
        
        return this.binding.getRoot();
    }

    private void setupCalendar() {
        binding.calendarView.setDayBinder(new MonthDayBinder<DayViewContainer>() {
            @NonNull
            @Override
            public DayViewContainer create(@NonNull View view) {
                return new DayViewContainer(view);
            }

            @Override
            public void bind(@NonNull DayViewContainer container, CalendarDay day) {
                container.day = day;
                TextView tv = container.binding.dayText;
                tv.setText(String.valueOf(day.getDate().getDayOfMonth()));

                if (day.getPosition() == DayPosition.MonthDate) {
                    tv.setVisibility(View.VISIBLE);
                    
                    // Selection state
                    if (day.getDate().equals(selectedDate)) {
                        container.binding.selectionView.setVisibility(View.VISIBLE);
                    } else {
                        container.binding.selectionView.setVisibility(View.GONE);
                    }

                    // Period/Ovulation Logic
                    updateDayStyle(container, day.getDate());
                } else {
                    tv.setVisibility(View.INVISIBLE);
                    container.binding.selectionView.setVisibility(View.GONE);
                    container.binding.backgroundCircle.setVisibility(View.GONE);
                    container.binding.indicatorIcon.setVisibility(View.GONE);
                }
            }
        });

        binding.calendarView.setMonthScrollListener(calendarMonth -> {
            binding.topCurrentDateTv.setText(calendarMonth.getYearMonth().format(monthHeaderFormatter));
            return null;
        });

        binding.calendarView.setMonthHeaderBinder(new MonthHeaderFooterBinder<MonthViewContainer>() {
            @NonNull
            @Override
            public MonthViewContainer create(@NonNull View view) {
                return new MonthViewContainer(view);
            }

            @Override
            public void bind(@NonNull MonthViewContainer container, com.kizitonwose.calendar.core.CalendarMonth month) {
                // Persistent header, no dynamic binding needed
            }
        });

        YearMonth currentMonth = YearMonth.now();
        YearMonth startMonth = currentMonth.minusMonths(100);
        YearMonth endMonth = currentMonth.plusMonths(100);
        
        binding.calendarView.setup(startMonth, endMonth, DayOfWeek.SUNDAY);
        binding.calendarView.scrollToMonth(currentMonth);
    }

    private class MonthViewContainer extends ViewContainer {
        public MonthViewContainer(View view) {
            super(view);
        }
    }

    private void updateDayStyle(DayViewContainer container, LocalDate date) {
        String dateStr = date.toString(); // yyyy-MM-dd
        container.binding.backgroundCircle.setVisibility(View.GONE);
        container.binding.indicatorIcon.setVisibility(View.GONE);

        for (DateDetails details : detailsList) {
            try {
                // Period day
                if (details.getNextPeriod().equals(dateStr)) {
                    container.binding.backgroundCircle.setVisibility(View.VISIBLE);
                    container.binding.backgroundCircle.setBackgroundResource(R.drawable.circle_safe_days); // Need to create or use existing
                    container.binding.indicatorIcon.setVisibility(View.VISIBLE);
                    container.binding.indicatorIcon.setImageResource(R.drawable.ic_next_period_indicator);
                } 
                // Ovulation day
                else if (details.getOvulationPeriod().equals(dateStr)) {
                    container.binding.backgroundCircle.setVisibility(View.VISIBLE);
                    container.binding.backgroundCircle.setBackgroundResource(R.drawable.circle_fertile_days);
                    container.binding.indicatorIcon.setVisibility(View.VISIBLE);
                    container.binding.indicatorIcon.setImageResource(R.drawable.ic_next_ovulation_indicator);
                }
                // Fertile range
                else if (isDateInRange(dateStr, details.getFertileDays())) {
                    container.binding.backgroundCircle.setVisibility(View.VISIBLE);
                    container.binding.backgroundCircle.setBackgroundResource(R.drawable.circle_fertile_days_light);
                }
                // Safe range
                else if (isDateInRange(dateStr, details.getNextPeriod(), Integer.parseInt(SharedPreferenceUtils.getCycleLength(getActivity())))) {
                    container.binding.backgroundCircle.setVisibility(View.VISIBLE);
                    container.binding.backgroundCircle.setBackgroundResource(R.drawable.circle_safe_days_light);
                }
            } catch (Exception e) {
                Log.e(TAG, "Error matching date: " + e.getMessage());
            }
        }
    }

    private boolean isDateInRange(String date, String rangeStr) {
        try {
            String[] parts = rangeStr.split(" --- ");
            if (parts.length < 2) return false;
            return MyDateUtils.checkDate(date, parts[0].trim(), parts[1].trim(), "yyyy-MM-dd");
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isDateInRange(String date, String startDate, int days) {
        try {
            String endDate = OvulationCalculations.addDays(startDate, days);
            return MyDateUtils.checkDate(date, startDate, endDate, "yyyy-MM-dd");
        } catch (Exception e) {
            return false;
        }
    }

    private class DayViewContainer extends ViewContainer {
        CalendarDay day;
        CalendarDayLayoutBinding binding;

        public DayViewContainer(View view) {
            super(view);
            binding = CalendarDayLayoutBinding.bind(view);
            view.setOnClickListener(v -> {
                if (day.getPosition() == DayPosition.MonthDate) {
                    LocalDate oldSelected = selectedDate;
                    selectedDate = day.getDate();
                    CalendarFragment.this.binding.calendarView.notifyDateChanged(oldSelected);
                    CalendarFragment.this.binding.calendarView.notifyDateChanged(selectedDate);
                    mentionDataWithSelectedDate(selectedDate);
                }
            });
        }
    }

    public void mentionDataWithSelectedDate(LocalDate date) {
        binding.currentDateTv.setText(date.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)));
        binding.currentDayTv.setText(date.format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)));
        
        String dateStr = date.toString();
        for (DateDetails dateDetails : detailsList) {
            try {
                if (!isDateInRange(dateStr, dateDetails.getNextPeriod(), Integer.parseInt(SharedPreferenceUtils.getCycleLength(getActivity()))) && !dateStr.equals(dateDetails.getNextPeriod())) {
                    if (isDateInRange(dateStr, dateDetails.getFertileDays())) {
                        if (dateDetails.getOvulationPeriod().equals(dateStr)) {
                            binding.chancesTv.setText(getString(R.string.high));
                        } else {
                            binding.chancesTv.setText(getString(R.string.medium));
                        }
                    } else {
                        binding.chancesTv.setText(getString(R.string.low));
                    }
                } else {
                     binding.chancesTv.setText(getString(R.string.very_low));
                }
            } catch (Exception e) {
                Log.e(TAG, "error --> " + e.getMessage());
            }
        }
    }

    private void setUpTheme() {
        MyCustomTheme appTheme = new MyThemeHandler().getAppTheme(getActivity());
        Utils.setButtonTint(this.binding.editPeriodBtn, appTheme.getThemeColor());
        if (appTheme.isDark()) {
            this.binding.headingTv.setTextColor(getResources().getColor(R.color.white));
        }
    }
}
