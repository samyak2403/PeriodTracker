package com.arrowwould.periodtracker.Utils;

import android.util.Log;


import com.arrowwould.periodtracker.Databases.Entities.DateDetails;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;


public class OvulationCalculations {
    public static String calculateDueDate(String str, int i) {
        if (i == 28) {
            return java.time.LocalDate.parse(str).plusDays(7L).plusMonths(9L).toString();
        }
        if (i > 28) {
            return java.time.LocalDate.parse(str).plusDays(i - 28).plusDays(7L).plusMonths(9L).toString();
        }
        return java.time.LocalDate.parse(str).plusDays(28 - i).plusDays(7L).plusMonths(9L).toString();
    }

    public static String addDays(String str, int i) {
        if (str.contains("---")) {
            String str2 = str.split(" --- ")[0];
            String str3 = str.split(" --- ")[1];
            return addDays(str2, i) + " --- " + addDays(str3, i);
        }
        return java.time.LocalDate.parse(str).plusDays(i).toString();
    }

    public static String minusDays(String str, int i) {
        if (str.contains("---")) {
            String str2 = str.split(" --- ")[0];
            String str3 = str.split(" --- ")[1];
            return minusDays(str2, i) + " --- " + minusDays(str3, i);
        }
        return java.time.LocalDate.parse(str).minusDays(i).toString();
    }

    public static DateDetails minusDays(DateDetails dateDetails, int i) {
        dateDetails.setFertileDays(minusDays(dateDetails.getFertileDays(), i));
        dateDetails.setSafeDays(minusDays(dateDetails.getSafeDays(), i));
        dateDetails.setOvulationPeriod(minusDays(dateDetails.getOvulationPeriod(), i));
        dateDetails.setNextPeriod(minusDays(dateDetails.getNextPeriod(), i));
        return dateDetails;
    }

    public static String getFertileWindow(String str, int i) {
        String ovulation = getOvulation(str, i - 2);
        String ovulation2 = getOvulation(str, i + 2);
        return ovulation + " --- " + ovulation2;
    }

    public static String getSafeDays(String str, int i, int i2) {
        String localDate = java.time.LocalDate.parse(str).plusDays(i2).toString();
        String str2 = getFertileWindow(str, i - 1).split(" --- ")[0];
        return localDate + " --- " + str2;
    }

    public static String getOvulation(String str, int i) {
        Log.e("MYTAG", "ErrorNo:1 str:" + str);
        Log.e("MYTAG", "ErrorNo:2 i:" + i);


        try {
            java.time.LocalDate result = java.time.LocalDate.parse("2023-06-28").plusDays(28 - 14);
            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return java.time.LocalDate.parse(str).plusDays(i - 14).toString();
    }

    public static String getPregnancyTest(String str, int i) {
        return java.time.LocalDate.parse(getOvulation(str, i)).plusDays(9L).toString();
    }

    public static String getNextPeriod(String str, int i) {
        return java.time.LocalDate.parse(str).plusDays(i).toString();
    }

    public static long daysBetweenTwoDates(String str, String str2) {
        String[] split = str.split("-");
        String[] split2 = str2.split("-");
        int parseInt = Integer.parseInt(split[2]);
        int parseInt2 = Integer.parseInt(split[1]);
        int parseInt3 = Integer.parseInt(split[0]);
        int parseInt4 = Integer.parseInt(split2[2]);
        int parseInt5 = Integer.parseInt(split2[1]);
        int parseInt6 = Integer.parseInt(split2[0]);
        long between = ChronoUnit.DAYS.between(LocalDate.of(parseInt3, parseInt2, parseInt), LocalDate.of(parseInt6, parseInt5, parseInt4)).getAmount();
        if (between < 0) {
            return 0L;
        }
        return between;
    }
}
