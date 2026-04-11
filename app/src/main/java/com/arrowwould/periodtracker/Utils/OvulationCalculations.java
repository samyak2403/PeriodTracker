package com.arrowwould.periodtracker.Utils;

import android.util.Log;
import com.arrowwould.periodtracker.Databases.Entities.DateDetails;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OvulationCalculations {
    public static String calculateDueDate(String str, int i) {
        if (i == 28) {
            return LocalDate.parse(str).plusDays(7L).plusMonths(9L).toString();
        }
        if (i > 28) {
            return LocalDate.parse(str).plusDays(i - 28).plusDays(7L).plusMonths(9L).toString();
        }
        return LocalDate.parse(str).plusDays(28 - i).plusDays(7L).plusMonths(9L).toString();
    }

    public static String addDays(String str, int i) {
        if (str.contains(" --- ")) {
            String str2 = str.split(" --- ")[0];
            String str3 = str.split(" --- ")[1];
            return addDays(str2, i) + " --- " + addDays(str3, i);
        }
        return LocalDate.parse(str).plusDays(i).toString();
    }

    public static String minusDays(String str, int i) {
        if (str.contains(" --- ")) {
            String str2 = str.split(" --- ")[0];
            String str3 = str.split(" --- ")[1];
            return minusDays(str2, i) + " --- " + minusDays(str3, i);
        }
        return LocalDate.parse(str).minusDays(i).toString();
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
        String localDate = LocalDate.parse(str).plusDays(i2).toString();
        String str2 = getFertileWindow(str, i - 1).split(" --- ")[0];
        return localDate + " --- " + str2;
    }

    public static String getOvulation(String str, int i) {
        return LocalDate.parse(str).plusDays(i - 14).toString();
    }

    public static String getPregnancyTest(String str, int i) {
        return LocalDate.parse(getOvulation(str, i)).plusDays(9L).toString();
    }

    public static String getNextPeriod(String str, int i) {
        return LocalDate.parse(str).plusDays(i).toString();
    }

    public static long daysBetweenTwoDates(String str, String str2) {
        try {
            LocalDate d1 = LocalDate.parse(str);
            LocalDate d2 = LocalDate.parse(str2);
            long between = ChronoUnit.DAYS.between(d1, d2);
            return Math.max(0L, between);
        } catch (Exception e) {
            String[] split = str.split("-");
            String[] split2 = str2.split("-");
            int parseInt = Integer.parseInt(split[2]);
            int parseInt2 = Integer.parseInt(split[1]);
            int parseInt3 = Integer.parseInt(split[0]);
            int parseInt4 = Integer.parseInt(split2[2]);
            int parseInt5 = Integer.parseInt(split2[1]);
            int parseInt6 = Integer.parseInt(split2[0]);
            long between = ChronoUnit.DAYS.between(LocalDate.of(parseInt3, parseInt2, parseInt), LocalDate.of(parseInt6, parseInt5, parseInt4));
            return Math.max(0L, between);
        }
    }
}
