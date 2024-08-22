package com.arrowwould.periodtracker.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arrowwould.periodtracker.Activities.InputActivity;
import com.arrowwould.periodtracker.Activities.MainActivity;
import com.arrowwould.periodtracker.Adapters.CustomThemesSelectorAdapter;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.ThemesFiles.MyCustomTheme;
import com.arrowwould.periodtracker.ThemesFiles.MyThemeHandler;
import com.arrowwould.periodtracker.Utils.OvulationCalculations;
import com.arrowwould.periodtracker.Utils.SharedPreferenceUtils;
import com.arrowwould.periodtracker.databinding.FragmentSettingsBinding;

import java.util.ArrayList;
import java.util.Arrays;


public class SettingsFragment extends Fragment {
    CustomThemesSelectorAdapter adapter;
    FragmentSettingsBinding binding;
    private final MyThemeHandler handler = new MyThemeHandler();

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentSettingsBinding.inflate(layoutInflater);
        setData();
        this.binding.recalculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                SettingsFragment.this.m137x1636f66(view);
            }
        });
        this.binding.shareUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                SettingsFragment.this.m138xc78df827(view);
            }
        });
        this.binding.rateUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                SettingsFragment.this.m139x8db880e8(view);
            }
        });
        this.binding.privacyPolicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                SettingsFragment.this.m140x53e309a9(view);
            }
        });
        setUpThemesRecycler();
        setUpTheme();
        return this.binding.getRoot();
    }


    public void m137x1636f66(View view) {
        Intent intent = new Intent(getActivity(), InputActivity.class);
        intent.putExtra("recalculate", true);
        startActivity(intent);
    }


    public void m138xc78df827(View view) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "SUBJECT");
        intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + requireActivity().getPackageName());
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_us)));
    }


    public void m139x8db880e8(View view) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + requireActivity().getPackageName())));
        } catch (ActivityNotFoundException unused) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + requireActivity().getPackageName())));
        }
    }


    public void m140x53e309a9(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://google.com")));
    }


    private void setUpTheme() {
        if (new MyThemeHandler().getAppTheme(getActivity()).isDark()) {
            this.binding.headingTv.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void setData() {
        int parseInt = Integer.parseInt(SharedPreferenceUtils.getCycles(getActivity()));
        String date = SharedPreferenceUtils.getDate(getActivity());
        TextView textView = this.binding.cycleLengthTv;
        textView.setText(parseInt + " " + getResources().getString(R.string.days));
        TextView textView2 = this.binding.periodLengthTv;
        textView2.setText(SharedPreferenceUtils.getCycleLength(getActivity()) + " " + getResources().getString(R.string.days));
        int daysBetweenTwoDates = (int) OvulationCalculations.daysBetweenTwoDates(OvulationCalculations.getOvulation(date, parseInt), OvulationCalculations.getNextPeriod(date, parseInt));
        TextView textView3 = this.binding.lutealPhaseTv;
        textView3.setText(daysBetweenTwoDates + " " + getResources().getString(R.string.days));
    }

    private void setUpThemesRecycler() {
        this.adapter = new CustomThemesSelectorAdapter(getActivity(), new ArrayList(Arrays.asList(MyThemeHandler.CUSTOM_THEMES)), this.handler.getAppThemeIndex(getActivity())) {
            @Override
            public void onThemeItemSelected(MyCustomTheme myCustomTheme) {
                SettingsFragment.this.handler.setAppTheme(SettingsFragment.this.adapter.getSelectedTheme(), SettingsFragment.this.getActivity());
                SettingsFragment.this.startActivity(new Intent(SettingsFragment.this.getContext(), MainActivity.class));
                SettingsFragment.this.getActivity().finishAffinity();
            }
        };
        this.binding.themesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        this.binding.themesRecycler.setAdapter(this.adapter);
    }
}
