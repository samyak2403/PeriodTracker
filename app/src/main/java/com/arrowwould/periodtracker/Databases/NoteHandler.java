package com.arrowwould.periodtracker.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arrowwould.periodtracker.Databases.Entities.Note;

import java.util.ArrayList;
import java.util.List;


public class NoteHandler extends DbHandler {
    Context context;

    public NoteHandler(Context context) {
        super(context, Params.DB_NAME_NOTES);
        this.context = context;
    }

    @Override 
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE notes_table(noteId INTEGER PRIMARY KEY,note TEXT, date TEXT)");
    }

    public List<Note> getAllNotes() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM notes_table", null);
        if (rawQuery.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(rawQuery.getString(0)));
                note.setNote(rawQuery.getString(1));
                note.setDate(rawQuery.getString(2));
                arrayList.add(note);
            } while (rawQuery.moveToNext());
            rawQuery.close();
            return arrayList;
        }
        rawQuery.close();
        return arrayList;
    }

    public Note getNoteById(int i) {
        Note note = null;
        Cursor rawQuery = getReadableDatabase().rawQuery("select * from notes_table where noteId='" + i + "'", null);
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                Note note2 = new Note();
                note2.setId(Integer.parseInt(rawQuery.getString(0)));
                note2.setNote(rawQuery.getString(1));
                note2.setDate(rawQuery.getString(2));
                note = note2;
            }
            rawQuery.close();
        }
        return note;
    }

    public void addNote(Note note) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_NOTE, note.getNote());
        contentValues.put(Params.KEY_DATE, note.getDate());
        String.valueOf(writableDatabase.insert(Params.NOTES_TABLE, null, contentValues));
        writableDatabase.close();
    }

    public void updateNotes(Note note, String str) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        contentValues.put(Params.KEY_NOTE, note.getNote());
        contentValues.put(Params.KEY_DATE, note.getDate());
        String.valueOf(writableDatabase.update(Params.NOTES_TABLE, contentValues, "noteId= ?", new String[]{str}));
        writableDatabase.close();
    }

    public void deleteNotes(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(Params.NOTES_TABLE, "noteId=" + str, null);
        writableDatabase.close();
    }
}
