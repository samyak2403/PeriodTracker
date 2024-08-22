package com.arrowwould.periodtracker.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.arrowwould.periodtracker.Adapters.CategoryBlogsAdapter;
import com.arrowwould.periodtracker.Model.BlogCategory;
import com.arrowwould.periodtracker.Model.CategoryFeaturedBlog;
import com.arrowwould.periodtracker.Utils.Utils;
import com.arrowwould.periodtracker.databinding.FragmentCategoryBlogsBinding;
import com.google.gson.internal.LinkedTreeMap;
import com.arrowwould.periodtracker.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class CategoryBlogsFragment extends Fragment {
    FragmentCategoryBlogsBinding binding;
    private static final int[] categoriesRes = {R.string.anxiety_and_depression, R.string.pain_managment, R.string.boost_intimacy, R.string.birth_control, R.string.sex_worries, R.string.mental_stress, R.string.healthy_eating, R.string.yoga_and_exercise, R.string.increase_fertility, R.string.sexual_health, R.string.hormonal_health, R.string.menstrual_pain, R.string.high_bleeding_and_hormonal_imbalance, R.string.high_bleeding, R.string.stress_and_menstrual_cycle, R.string.pain_in_fertility_test, R.string.juice_during_period, R.string.menstrual_cramps, R.string.normal_discharge_time, R.string.female_reproductive, R.string.pms_symptoms};
    private static final String[] categories = {"Anxiety and depression", "Pain Management", "Boost intimacy", "Birth control", "Sex Worries", "Mental Stress", "Healthy Eating", "Yoga & exercise", "Increase Fertility", "Sexual Health ", "Hormonal Health", "Menstrual Pain", "High Bleeding and Hormonal Imbalance", "High Bleeding", "Stress and Menstrual Cycle", "Pain in Fertility Test", "Juice During Periods", "Menstrual Cramps", "Normal Discharge Time", "Female Reproductive", "PMS symptoms"};

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentCategoryBlogsBinding.inflate(layoutInflater);
        showCombinedData();
        return this.binding.getRoot();
    }

    private void showCombinedData() {
        this.binding.articlesRecycler.setVisibility(View.VISIBLE);
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        List<HashMap<String, Object>> readAssetFile = Utils.readAssetFile(context, Locale.getDefault().getLanguage() + "_c.json");
        List<HashMap<String, Object>> readAssetFile2 = Utils.readAssetFile(getContext(), "en_c.json");
        if (readAssetFile != null && readAssetFile2 != null) {
            for (int i = 0; i < readAssetFile.size() && i < readAssetFile2.size(); i++) {
                HashMap<String, Object> hashMap = readAssetFile.get(i);
                ArrayList arrayList2 = (ArrayList) hashMap.get("data");
                ArrayList arrayList3 = (ArrayList) readAssetFile2.get(i).get("data");
                ArrayList arrayList4 = new ArrayList();
                for (int i2 = 0; i2 < arrayList2.size() && i2 < arrayList3.size(); i2++) {
                    LinkedTreeMap linkedTreeMap = (LinkedTreeMap) arrayList2.get(i2);
                    if (!Utils.getStringFromObj(linkedTreeMap.get("heading")).isEmpty()) {
                        arrayList4.add(new CategoryFeaturedBlog(Utils.getStringFromObj(linkedTreeMap.get("heading")), Utils.getStringFromObj(linkedTreeMap.get("body")), Utils.lowerUnder(Utils.getStringFromObj(((LinkedTreeMap) arrayList3.get(i2)).get("title"))), Utils.getStringFromObj(linkedTreeMap.get("title")), Utils.getStringFromObj(((LinkedTreeMap) arrayList3.get(i2)).get("color")), Utils.getBoolFromObj(((LinkedTreeMap) arrayList3.get(i2)).get("dark"))));
                    }
                }
                arrayList.add(new BlogCategory(hashMap.get("category").toString(), categoriesRes[i], arrayList4));
            }
        }
        Collections.shuffle(arrayList);
        this.binding.articlesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.articlesRecycler.setAdapter(new CategoryBlogsAdapter(arrayList, getActivity()));
    }

    private void showSeparateData() {
        this.binding.articlesRecycler.setVisibility(View.VISIBLE);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = categories;
            if (i < strArr.length) {
                String str = strArr[i];
                ArrayList arrayList2 = new ArrayList();
                Context context = getContext();
                List<HashMap<String, Object>> readAssetFile = Utils.readAssetFile(context, Locale.getDefault().getLanguage() + "_" + str + ".json");
                Context context2 = getContext();
                StringBuilder sb = new StringBuilder("en_");
                sb.append(str);
                sb.append(".json");
                List<HashMap<String, Object>> readAssetFile2 = Utils.readAssetFile(context2, sb.toString());
                if (readAssetFile != null && readAssetFile2 != null) {
                    for (int i2 = 0; i2 < readAssetFile.size() && i2 < readAssetFile2.size(); i2++) {
                        HashMap<String, Object> hashMap = readAssetFile.get(i2);
                        arrayList2.add(new CategoryFeaturedBlog(Utils.getStringFromObj(hashMap.get("heading")), Utils.getStringFromObj(hashMap.get("body")), Utils.lowerUnder(Utils.getStringFromObj(readAssetFile2.get(i2).get("title"))), Utils.getStringFromObj(hashMap.get("title")), Utils.getStringFromObj(readAssetFile2.get(i2).get("color")), Utils.getBoolFromObj(readAssetFile2.get(i2).get("dark"))));
                    }
                }
                arrayList.add(new BlogCategory(str, categoriesRes[i], arrayList2));
                i++;
            } else {
                Collections.shuffle(arrayList);
                this.binding.articlesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                this.binding.articlesRecycler.setAdapter(new CategoryBlogsAdapter(arrayList, getActivity()));
                return;
            }
        }
    }
}
