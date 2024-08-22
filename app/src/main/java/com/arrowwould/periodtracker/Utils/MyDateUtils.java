package com.arrowwould.periodtracker.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MyDateUtils {
    public static String getCurrentDate(String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(Calendar.getInstance().getTime());
    }

    public static Date getDateFromString(String str, String str2) {
        String[] split = str.split(str2);
        int parseInt = Integer.parseInt(split[2]);
        int parseInt2 = Integer.parseInt(split[1]);
        int parseInt3 = Integer.parseInt(split[0]);
        Date date = new Date();
        date.setDate(parseInt);
        date.setMonth(parseInt2 - 1);
        date.setYear(parseInt3 - 1900);
        return date;
    }

    public static List<String> getAllDatesStrInRange(Date date, Date date2) {
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        while (true) {
            if (!calendar.getTime().before(date2) && !calendar.getTime().equals(date2)) {
                return arrayList;
            }
            arrayList.add(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
            calendar.add(5, 1);
        }
    }

    public static String convertInto_yyyy_MMM_dd(String str, String str2, String str3) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str3, Locale.ENGLISH);
        try {
            if (str.contains(" --- ")) {
                return convertInto_yyyy_MMM_dd(str.split(" --- ")[0], str2, str3) + " --- " + convertInto_yyyy_MMM_dd(str.split(" --- ")[1], str2, str3);
            }
            return simpleDateFormat2.format(simpleDateFormat.parse(str));
        } catch (ParseException unused) {
            return null;
        }
    }

    public static boolean checkDate(String str, String str2, String str3, String str4) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str4, Locale.ENGLISH);
        Date parse = simpleDateFormat.parse(str2);
        Date parse2 = simpleDateFormat.parse(str3);
        Date parse3 = simpleDateFormat.parse(str);
        return parse3.after(parse) && parse3.before(parse2);
    }
}
