package com.sueed.exp_mid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class manipulate {
    SQLiteOpenHelper dbHandler;
    SQLiteDatabase db;

    private static final String[] columns = {
            nDatabase.ID,
            nDatabase.CONTENT,
            nDatabase.TIME,
            nDatabase.MODE
    };

    public manipulate(Context context){
        dbHandler = new nDatabase(context);
    }

    public void open(){
        db = dbHandler.getWritableDatabase();
    }

    public void close(){
        dbHandler.close();
    }

    public Note addNote(Note note){
        //add a note object to database
        ContentValues contentValues = new ContentValues();
        contentValues.put(nDatabase.CONTENT, note.getContent());
        contentValues.put(nDatabase.TIME, note.getTime());
        contentValues.put(nDatabase.MODE, note.getTag());
        long insertId = db.insert(nDatabase.TABLE_NAME, null, contentValues);
        note.setId(insertId);
        return note;
    }

    public Note getNote(long id){
        //get a note from database using cursor index
        Cursor cursor = db.query(nDatabase.TABLE_NAME,columns, nDatabase.ID + "=?",
                new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Note e = new Note(cursor.getString(1),cursor.getString(2), cursor.getInt(3));
        return e;
        //s
    }

    public List<Note> getAllNotes(){
        Cursor cursor = db.query(nDatabase.TABLE_NAME,columns,null,null,null, null, null);

        List<Note> notes = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Note note = new Note();
                note.setId(cursor.getLong(cursor.getColumnIndex(nDatabase.ID)));
                note.setContent(cursor.getString(cursor.getColumnIndex(nDatabase.CONTENT)));
                note.setTime(cursor.getString(cursor.getColumnIndex(nDatabase.TIME)));
                note.setTag(cursor.getInt(cursor.getColumnIndex(nDatabase.MODE)));
                notes.add(note);
            }
        }
        return notes;
    }

    public int updateNote(Note note) {
        //update the info of an existing note
        ContentValues values = new ContentValues();
        values.put(nDatabase.CONTENT, note.getContent());
        values.put(nDatabase.TIME, note.getTime());
        values.put(nDatabase.MODE, note.getTag());
        // updating row
        return db.update(nDatabase.TABLE_NAME, values,
                nDatabase.ID + "=?",new String[] { String.valueOf(note.getId())});
    }

    public void removeNote(Note note) {
        //remove a note according to ID value
        db.delete(nDatabase.TABLE_NAME, nDatabase.ID + "=" + note.getId(), null);
    }

}
