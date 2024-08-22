package com.arrowwould.periodtracker.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHandler extends SQLiteOpenHelper {
    Context context;

    @Override 
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override 
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DbHandler(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 2);
        this.context = context;
    }

    public boolean isTableExists(SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        if (z) {
            if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                sQLiteDatabase = getReadableDatabase();
            }
            if (!sQLiteDatabase.isReadOnly()) {
                sQLiteDatabase.close();
                sQLiteDatabase = getReadableDatabase();
            }
        }
        Cursor rawQuery = sQLiteDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + str + "'", null);
        if (rawQuery != null) {
            try {
                if (rawQuery.getCount() > 0) {
                    if (rawQuery != null) {
                        rawQuery.close();
                        return true;
                    }
                    return true;
                }
            } catch (Throwable th) {
                if (rawQuery != null) {
                    try {
                        rawQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
            return false;
        }
        return false;
    }
}
