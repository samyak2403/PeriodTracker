package com.arrowwould.periodtracker.Utils;

import android.app.Activity;
import android.content.SharedPreferences;


public class SharedPreferenceUtils {
    private static final String KEY_CYCLE_DAYS = "O_cycles";
    private static final String KEY_CYCLE_Length = "O_length";
    private static final String KEY_DATE = "O_date";
    private static final String OVULATION_PREFERENCE_FILE_NAME = "OvulationDetails";

    public static void saveData(String str, String str2, String str3, Activity activity) {
        SharedPreferences.Editor edit = activity.getSharedPreferences(OVULATION_PREFERENCE_FILE_NAME, 0).edit();
        edit.putString(KEY_DATE, str2);
        edit.putString(KEY_CYCLE_DAYS, str);
        edit.putString(KEY_CYCLE_Length, str3);
        edit.apply();
    }

    public static String getCycles(Activity activity) {
        return activity.getSharedPreferences(OVULATION_PREFERENCE_FILE_NAME, 0).getString(KEY_CYCLE_DAYS, "");
    }

    public static String getDate(Activity activity) {
        return activity.getSharedPreferences(OVULATION_PREFERENCE_FILE_NAME, 0).getString(KEY_DATE, "");
    }

    public static String getCycleLength(Activity activity) {
        return activity.getSharedPreferences(OVULATION_PREFERENCE_FILE_NAME, 0).getString(KEY_CYCLE_Length, "");
    }
}
