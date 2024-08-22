package com.arrowwould.periodtracker.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arrowwould.periodtracker.Databases.Entities.DateDetails;

import java.util.ArrayList;
import java.util.List;


public class OvulationDetailsHandler extends DbHandler {
    public OvulationDetailsHandler(Context context) {
        super(context, Params.DB_NAME_DETAILS);
    }

    @Override 
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE ovulation_details_home(detailId INTEGER PRIMARY KEY,safe_days TEXT, fertile_days TEXT, next_period TEXT, next_ovulation TEXT)");
        sQLiteDatabase.execSQL("CREATE TABLE ovulation_details_calendar(detailId INTEGER PRIMARY KEY,safe_days TEXT, fertile_days TEXT, next_period TEXT, next_ovulation TEXT)");
    }

    public List<DateDetails> getAllOvulationDetails(String str) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM " + str, null);
        if (rawQuery != null && rawQuery.getCount() > 0 && rawQuery.moveToFirst()) {
            do {
                DateDetails dateDetails = new DateDetails();
                dateDetails.setId(Integer.parseInt(rawQuery.getString(0)));
                dateDetails.setSafeDays(rawQuery.getString(1));
                dateDetails.setFertileDays(rawQuery.getString(2));
                dateDetails.setNextPeriod(rawQuery.getString(3));
                dateDetails.setOvulationPeriod(rawQuery.getString(4));
                arrayList.add(dateDetails);
            } while (rawQuery.moveToNext());
            rawQuery.close();
        }
        return arrayList;
    }

    public String addOvulationDetail(DateDetails dateDetails, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_SAFE_DAYS, dateDetails.getSafeDays());
        contentValues.put(Params.KEY_FERTILE_DAYS, dateDetails.getFertileDays());
        contentValues.put(Params.KEY_NEXT_PERIOD, dateDetails.getNextPeriod());
        contentValues.put(Params.KEY_NEXT_OVULATION, dateDetails.getOvulationPeriod());
        String valueOf = String.valueOf(writableDatabase.insert(str, null, contentValues));
        writableDatabase.close();
        return valueOf;
    }
}
