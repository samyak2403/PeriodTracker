package com.arrowwould.periodtracker.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arrowwould.periodtracker.Databases.Entities.Recents;

import java.util.ArrayList;
import java.util.List;


public class RecentsHandler extends DbHandler {
    Context context;

    public RecentsHandler(Context context) {
        super(context, Params.DB_NAME_RECENTS);
        this.context = context;
    }

    @Override 
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE recents_table(recents_Id INTEGER PRIMARY KEY,recents_title TEXT, recents_heading TEXT)");
    }

    public List<Recents> getAllRecents() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM recents_table", null);
        if (rawQuery.moveToFirst()) {
            do {
                Recents recents = new Recents();
                recents.setId(Integer.parseInt(rawQuery.getString(0)));
                recents.setTitle(rawQuery.getString(1));
                recents.setHeading(rawQuery.getString(2));
                arrayList.add(recents);
            } while (rawQuery.moveToNext());
            rawQuery.close();
            return arrayList;
        }
        rawQuery.close();
        return arrayList;
    }

    public Recents getRecentByParam(String str, String str2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Recents recents = null;
        Cursor rawQuery = readableDatabase.rawQuery("select * from recents_table where " + str2 + "='" + str.replace("'", "''") + "'", null);
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                recents = new Recents();
                recents.setId(Integer.parseInt(rawQuery.getString(0)));
                recents.setTitle(rawQuery.getString(1));
                recents.setHeading(rawQuery.getString(2));
            }
            rawQuery.close();
        }
        return recents;
    }

    public void addRecent(Recents recents) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_RECENTS_TITLE, recents.getTitle());
        contentValues.put(Params.KEY_RECENTS_HEADING, recents.getHeading());
        String.valueOf(writableDatabase.insert(Params.RECENTS_TABLE, null, contentValues));
        writableDatabase.close();
    }

    public void updateRecent(Recents recents, String str) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        contentValues.put(Params.KEY_RECENTS_TITLE, recents.getTitle());
        contentValues.put(Params.KEY_RECENTS_HEADING, recents.getHeading());
        String.valueOf(writableDatabase.update(Params.KEY_RECENTS_HEADING, contentValues, "recents_Id= ?", new String[]{str}));
        writableDatabase.close();
    }

    public void deleteRecent(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(Params.RECENTS_TABLE, "recents_Id=" + str, null);
        writableDatabase.close();
    }
}
