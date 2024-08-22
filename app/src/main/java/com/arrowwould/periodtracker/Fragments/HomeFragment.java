package com.arrowwould.periodtracker.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arrowwould.periodtracker.Activities.EditPeriodActivity;
import com.arrowwould.periodtracker.Activities.NotesActivity;
import com.arrowwould.periodtracker.Adapters.CalendarSliderAdapter;
import com.arrowwould.periodtracker.Adapters.FeaturedBlogAdapter;
import com.arrowwould.periodtracker.Adapters.HomeRecyclerAdapter;
import com.arrowwould.periodtracker.Databases.Entities.DateDetails;
import com.arrowwould.periodtracker.Databases.OvulationDetailsHandler;
import com.arrowwould.periodtracker.Databases.Params;
import com.arrowwould.periodtracker.Model.FeaturedBlog;
import com.arrowwould.periodtracker.Model.OvulationData;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.ThemesFiles.MyCustomTheme;
import com.arrowwould.periodtracker.ThemesFiles.MyThemeHandler;
import com.arrowwould.periodtracker.Utils.MyDateUtils;
import com.arrowwould.periodtracker.Utils.OvulationCalculations;
import com.arrowwould.periodtracker.Utils.SharedPreferenceUtils;
import com.arrowwould.periodtracker.Utils.Utils;
import com.arrowwould.periodtracker.databinding.FragmentHomeBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    private int cycleLength;
    String fertileDays;
    OvulationDetailsHandler handler;
    CalendarSliderAdapter mAdapter;
    String safeDays;
    private List<DateDetails> detailsList = new ArrayList();
    List<Date> dates = new ArrayList();
    private int currentCalSliderPos = 0;

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentHomeBinding inflate = FragmentHomeBinding.inflate(layoutInflater);
        this.binding = inflate;
        inflate.editPeriodBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                HomeFragment.this.m135xd0820da2(view);
            }
        });
        this.binding.notesTv.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                HomeFragment.this.m136x3f091ee3(view);
            }
        });
        loadHorizontalDates();
        this.binding.articlesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        setUpTheme();
        return this.binding.getRoot();
    }

    
    
    public  void m135xd0820da2(View view) {
        startActivity(new Intent(getActivity(), EditPeriodActivity.class));
    }

    
    
    public  void m136x3f091ee3(View view) {
        getActivity().startActivity(new Intent(getActivity(), NotesActivity.class));
    }

    private void setUpTheme() {
        MyCustomTheme appTheme = new MyThemeHandler().getAppTheme(getActivity());
        int themeColor = appTheme.getThemeColor();
        Utils.setButtonTint(this.binding.editPeriodBtn, themeColor);
        this.binding.dateTv.setTextColor(getResources().getColor(themeColor));
        this.binding.bannerLayout.setImageResource(appTheme.getBgImg());
    }

    @Override 
    public void onResume() {
        super.onResume();
        OvulationDetailsHandler ovulationDetailsHandler = new OvulationDetailsHandler(getActivity());
        this.handler = ovulationDetailsHandler;
        this.detailsList = ovulationDetailsHandler.getAllOvulationDetails(Params.OVULATION_DETAILS_TABLE_HOME);
        handleRecyclerEvents(this.currentCalSliderPos);
        String date = SharedPreferenceUtils.getDate(getActivity());
        this.cycleLength = Integer.parseInt(SharedPreferenceUtils.getCycleLength(getActivity()));
        try {
            mentionResult(date, Integer.parseInt(SharedPreferenceUtils.getCycles(getActivity())), this.cycleLength);
        } catch (ParseException e) {
            Log.d("DatesMatching", "error --> " + e.getMessage());
        }
        showFeatureBlogs();
    }

    private void showFeatureBlogs() {
        ArrayList<FeaturedBlog> arrayList = new ArrayList<FeaturedBlog>();
        Context context = getContext();
        List<HashMap<String, Object>> readAssetFile = Utils.readAssetFile(context, Locale.getDefault().getLanguage() + ".json");
        List<HashMap<String, Object>> readAssetFile2 = Utils.readAssetFile(getContext(), "en.json");
        if (readAssetFile != null && readAssetFile2 != null) {
            for (int i = 0; i < readAssetFile.size() && i < readAssetFile2.size(); i++) {
                HashMap<String, Object> hashMap = readAssetFile.get(i);
                arrayList.add(new FeaturedBlog(hashMap.get("heading").toString(),
                        hashMap.get("body").toString(),
                       Utils.lowerUnder(readAssetFile2.get(i).get("title").toString()), hashMap.get("title").toString(),
                        readAssetFile2.get(i).get("color").toString(),
                        ((Boolean) readAssetFile2.get(i).get("dark")).booleanValue()));
            }
        }

        Collections.shuffle(arrayList);
        this.binding.articlesRecycler.setAdapter(new FeaturedBlogAdapter(arrayList, getActivity()));
    }

    private void loadHorizontalDates() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(6) - 1;
        int i2 = calendar.get(1);
        calendar.set(1, i2);
        calendar.set(6, 1);
        while (calendar.get(1) == i2) {
            this.dates.add(calendar.getTime());
            calendar.add(5, 1);
        }
        this.mAdapter = new CalendarSliderAdapter(this.dates, i, this.binding.calendarRecycler) { 
            @Override 
            protected void onDateClicked(int i3) {
                HomeFragment.this.handleRecyclerEvents(i3);
            }
        };
        this.binding.calendarRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        this.binding.calendarRecycler.setAdapter(this.mAdapter);
        if (i > 1) {
            this.binding.calendarRecycler.smoothScrollToPosition(i + 2);
        }
        this.binding.calendarRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() { 
            @Override 
            public void onScrolled(RecyclerView recyclerView, int i3, int i4) {
                super.onScrolled(recyclerView, i3, i4);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                HomeFragment.this.handleRecyclerEvents(linearLayoutManager.findFirstVisibleItemPosition() + (linearLayoutManager.getChildCount() / 2));
            }
        });
    }

    
    public void handleRecyclerEvents(int i) {
        this.currentCalSliderPos = i;
        Date date = this.dates.get(i);
        this.binding.dateTv.setText(new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date));
        Iterator<DateDetails> it = this.detailsList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DateDetails next = it.next();
            try {
                if (MyDateUtils.checkDate(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date), next.getNextPeriod(), OvulationCalculations.addDays(next.getNextPeriod(), this.cycleLength), "yyyy-MM-dd") || next.getNextPeriod().equals(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date))) {
                    break;
                } else if (MyDateUtils.checkDate(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date), OvulationCalculations.minusDays(next.getFertileDays().split("---")[0].trim(), 1), OvulationCalculations.addDays(next.getFertileDays().split("---")[1].trim(), 1), "yyyy-MM-dd")) {
                    if (next.getOvulationPeriod().equals(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date))) {
                        mentionCondition(R.string.high);
                    } else {
                        mentionCondition(R.string.medium);
                    }
                } else {
                    mentionCondition(R.string.low);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        mentionCondition(R.string.very_low);
        this.mAdapter.setSelectedPosition(i, this.binding.calendarRecycler);
    }

    private void mentionCondition(int i) {
        this.binding.conditionTv.setText(i);
        if (i == R.string.very_low) {
            this.binding.conditionImg.setImageResource(R.drawable.very_low_chances);
        } else if (i == R.string.high) {
            this.binding.conditionImg.setImageResource(R.drawable.high_chances);
            this.binding.conditionImg.setImageResource(R.drawable.high_chances);
        } else if (i == R.string.medium) {
            this.binding.conditionImg.setImageResource(R.drawable.high_chances);
        } else {
            this.binding.conditionImg.setImageResource(R.drawable.low_chances);
        }
    }

    private void mentionResult(String str, int i, int i2) throws ParseException {
        String str2;
        String ovulation = OvulationCalculations.getOvulation(str, i);
        String nextPeriod = OvulationCalculations.getNextPeriod(str, i);
        this.fertileDays = OvulationCalculations.getFertileWindow(str, i);
        Date time = Calendar.getInstance().getTime();
        int i3 = 0;
        while (true) {
            if (i3 >= this.detailsList.size()) {
                str2 = str;
                break;
            }
            DateDetails dateDetails = this.detailsList.get(i3);
            if (MyDateUtils.getDateFromString(dateDetails.getOvulationPeriod(), "-").after(time) && MyDateUtils.getDateFromString(dateDetails.getNextPeriod(), "-").after(time)) {
                ovulation = dateDetails.getOvulationPeriod();
                str2 = OvulationCalculations.addDays(str, i);
                break;
            }
            i3++;
        }
        this.safeDays = OvulationCalculations.getSafeDays(OvulationCalculations.addDays(str2, i), i, i2);
        Iterator<DateDetails> it = this.detailsList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DateDetails next = it.next();
            if (MyDateUtils.getDateFromString(next.getNextPeriod(), "-").after(time) && MyDateUtils.checkDate(next.getNextPeriod(), MyDateUtils.getCurrentDate("yyyy-MM-dd"), OvulationCalculations.addDays(MyDateUtils.getCurrentDate("yyyy-MM-dd"), i), "yyyy-MM-dd")) {
                nextPeriod = next.getNextPeriod();
                break;
            }
        }
        Iterator<DateDetails> it2 = this.detailsList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            DateDetails next2 = it2.next();
            Log.d("DatesMatching", "safe days : " + MyDateUtils.convertInto_yyyy_MMM_dd(next2.getSafeDays().split(" --- ")[0], "yyyy-MM-dd", "yyyy MMM dd") + " --- " + MyDateUtils.convertInto_yyyy_MMM_dd(next2.getSafeDays().split(" --- ")[1], "yyyy-MM-dd", "yyyy MMM dd"));
            if (MyDateUtils.getDateFromString(next2.getSafeDays().split(" --- ")[1], "-").after(time) && MyDateUtils.checkDate(next2.getSafeDays().split(" --- ")[1], MyDateUtils.getCurrentDate("yyyy-MM-dd"), OvulationCalculations.addDays(MyDateUtils.getCurrentDate("yyyy-MM-dd"), i), "yyyy-MM-dd")) {
                Log.d("DatesMatching", "dates really matched");
                this.safeDays = next2.getSafeDays();
                break;
            }
            Log.d("DatesMatching", "dates don't matched");
        }
        Iterator<DateDetails> it3 = this.detailsList.iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            DateDetails next3 = it3.next();
            if (MyDateUtils.getDateFromString(next3.getOvulationPeriod(), "-").after(time) && MyDateUtils.checkDate(next3.getOvulationPeriod(), MyDateUtils.getCurrentDate("yyyy-MM-dd"), OvulationCalculations.addDays(MyDateUtils.getCurrentDate("yyyy-MM-dd"), i), "yyyy-MM-dd")) {
                ovulation = next3.getOvulationPeriod();
                break;
            }
        }
        Iterator<DateDetails> it4 = this.detailsList.iterator();
        while (!it4.hasNext()) {
            DateDetails next4 = it4.next();
            if (MyDateUtils.getDateFromString(next4.getFertileDays().split(" --- ")[0], "-").after(time) && MyDateUtils.checkDate(next4.getFertileDays().split(" --- ")[0], MyDateUtils.getCurrentDate("yyyy-MM-dd"), OvulationCalculations.addDays(MyDateUtils.getCurrentDate("yyyy-MM-dd"), i), "yyyy-MM-dd")) {
                this.fertileDays = next4.getFertileDays();
                break;
            }
        }
        Log.d("DatesMatching", "size : " + this.detailsList.size());
        ArrayList<OvulationData> arrayList = new ArrayList<OvulationData>();
        arrayList.add(new OvulationData(R.drawable.ic_next_period, requireActivity().getResources().getString(R.string.next_period), MyDateUtils.convertInto_yyyy_MMM_dd(nextPeriod, "yyyy-MM-dd", "MMM dd"), String.valueOf(OvulationCalculations.daysBetweenTwoDates(MyDateUtils.getCurrentDate("yyyy-MM-dd"), nextPeriod)), "0", R.color.next_period_bg_color, R.color.next_period_front_color));
        arrayList.add(new OvulationData(R.drawable.ic_next_ovulation, requireActivity().getResources().getString(R.string.next_ovulation), MyDateUtils.convertInto_yyyy_MMM_dd(ovulation, "yyyy-MM-dd", "MMM dd"), String.valueOf(OvulationCalculations.daysBetweenTwoDates(MyDateUtils.getCurrentDate("yyyy-MM-dd"), ovulation)), "0", R.color.next_ovulation_bg_color, R.color.next_ovulation_front_color));
        arrayList.add(new OvulationData(R.drawable.ic_fertile_days, requireActivity().getResources().getString(R.string.fertile_days), MyDateUtils.convertInto_yyyy_MMM_dd(this.fertileDays, "yyyy-MM-dd", "dd MMM"), String.valueOf(OvulationCalculations.daysBetweenTwoDates(MyDateUtils.getCurrentDate("yyyy-MM-dd"), this.fertileDays.split(" --- ")[0])), "0", R.color.fertile_days_bg_color, R.color.fertile_days_front_color));
        arrayList.add(new OvulationData(R.drawable.ic_safe_days, requireActivity().getResources().getString(R.string.safe_days), MyDateUtils.convertInto_yyyy_MMM_dd(this.safeDays, "yyyy-MM-dd", "dd MMM"), String.valueOf(OvulationCalculations.daysBetweenTwoDates(MyDateUtils.getCurrentDate("yyyy-MM-dd"), this.safeDays.split(" ")[0])), "0", R.color.safe_days_bg_color, R.color.safe_days_front_color));
        this.binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.binding.homeRecyclerView.setAdapter(new HomeRecyclerAdapter(arrayList, getActivity()));
    }
}
