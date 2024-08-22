package com.arrowwould.periodtracker.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arrowwould.periodtracker.Databases.Entities.Likes;

import java.util.ArrayList;
import java.util.List;


public class LikesHandler extends DbHandler {
    Context context;

    public LikesHandler(Context context) {
        super(context, Params.DB_NAME_LIKES);
        this.context = context;
    }

    @Override 
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE likes_table(likes_Id INTEGER PRIMARY KEY,likes_title TEXT, likes_heading TEXT)");
    }

    public List<Likes> getAllLikes() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM likes_table", null);
        if (rawQuery.moveToFirst()) {
            do {
                Likes likes = new Likes();
                likes.setId(Integer.parseInt(rawQuery.getString(0)));
                likes.setTitle(rawQuery.getString(1));
                likes.setHeading(rawQuery.getString(2));
                arrayList.add(likes);
            } while (rawQuery.moveToNext());
            rawQuery.close();
            return arrayList;
        }
        rawQuery.close();
        return arrayList;
    }

    public Likes getLikeByParam(String str, String str2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String replace = str.replace("'", "''");
        Likes likes = null;
        Cursor rawQuery = readableDatabase.rawQuery("select * from likes_table where " + str2 + "='" + replace + "'", null);
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                likes = new Likes();
                likes.setId(Integer.parseInt(rawQuery.getString(0)));
                likes.setTitle(rawQuery.getString(1));
                likes.setHeading(rawQuery.getString(2));
            }
            rawQuery.close();
        }
        return likes;
    }

    public void addLike(Likes likes) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_LIKES_TITLE, likes.getTitle());
        contentValues.put(Params.KEY_LIKES_HEADING, likes.getHeading());
        String.valueOf(writableDatabase.insert(Params.LIKES_TABLE, null, contentValues));
        writableDatabase.close();
    }

    public void updateLike(Likes likes, String str) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        contentValues.put(Params.KEY_LIKES_TITLE, likes.getTitle());
        contentValues.put(Params.KEY_LIKES_HEADING, likes.getHeading());
        String.valueOf(writableDatabase.update(Params.KEY_LIKES_HEADING, contentValues, "likes_Id= ?", new String[]{str}));
        writableDatabase.close();
    }

    public void deleteLike(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(Params.LIKES_TABLE, "likes_Id=" + str, null);
        writableDatabase.close();
    }
}
