package com.arrowwould.periodtracker.ThemesFiles;

import android.app.Activity;
import android.content.SharedPreferences;

import com.arrowwould.periodtracker.R;


public class MyThemeHandler {
    public static final MyCustomTheme[] CUSTOM_THEMES = {new MyCustomTheme(R.drawable.theme10_bg, R.color.theme10, R.color.theme_7_bg, false), new MyCustomTheme(R.drawable.theme8_bg, R.color.theme8, R.color.theme_7_bg, false), new MyCustomTheme(R.drawable.theme9_bg, R.color.theme9, R.color.theme_7_bg, false), new MyCustomTheme(R.drawable.theme1_bg, R.color.theme1, R.color.theme_1_bg, true), new MyCustomTheme(R.drawable.theme2_bg, R.color.theme2, R.color.theme_2_bg, true), new MyCustomTheme(R.drawable.theme3_bg, R.color.theme3, R.color.theme_3_bg, false), new MyCustomTheme(R.drawable.theme4_bg, R.color.theme4, R.color.theme_4_bg, false), new MyCustomTheme(R.drawable.theme5_bg, R.color.theme5, R.color.theme_5_bg, false), new MyCustomTheme(R.drawable.theme6_bg, R.color.theme6, R.color.theme_6_bg, true), new MyCustomTheme(R.drawable.theme7_bg, R.color.theme7, R.color.theme_7_bg, false)};
    private final String THEME_DATA = "theme_data";

    public void setAppTheme(MyCustomTheme myCustomTheme, Activity activity) {
        int i = 0;
        while (true) {
            MyCustomTheme[] myCustomThemeArr = CUSTOM_THEMES;
            if (i >= myCustomThemeArr.length) {
                i = 0;
                break;
            } else if (myCustomThemeArr[i] == myCustomTheme) {
                break;
            } else {
                i++;
            }
        }
        SharedPreferences.Editor edit = activity.getSharedPreferences("theme_data", 0).edit();
        edit.putInt("themeNo", i);
        edit.apply();
    }

    public MyCustomTheme getAppTheme(Activity activity) {
        return CUSTOM_THEMES[activity.getSharedPreferences("theme_data", 0).getInt("themeNo", 0)];
    }

    public int getAppThemeIndex(Activity activity) {
        return activity.getSharedPreferences("theme_data", 0).getInt("themeNo", 0);
    }
}
