package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Memo;
import com.example.myapplication.util.Constants;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final Context context;

    public DatabaseHandler(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMO_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_MEMO + " TEXT,"
                + Constants.KEY_DATE_NAME + " LONG);";

        db.execSQL(CREATE_MEMO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        onCreate(db);

    }

    public void addMemo(Memo memo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_MEMO, memo.getMemo());
        values.put(Constants.KEY_DATE_NAME, java.lang.System.currentTimeMillis());

        db.insert(Constants.TABLE_NAME, null, values);
        Log.d("DBHandler", "addMemo: ");
    }

    public Memo getMemo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                Constants.KEY_MEMO,
                Constants.KEY_DATE_NAME},
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();

        Memo memo = new Memo();
        if (cursor != null) {
            memo.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            memo.setMemo(cursor.getString(cursor.getColumnIndex(Constants.KEY_MEMO)));

            DateFormat dateFormat = DateFormat.getDateInstance();
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME))).getTime());
            memo.setDateMemoAdded(formattedDate);
        }

        return memo;
    }

    public List<Memo> getAllMemos() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Memo> memoList = new ArrayList<>();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID, Constants.KEY_MEMO, Constants.KEY_DATE_NAME}, null,null,null,null, Constants.KEY_DATE_NAME + " DESC");

        if (cursor.moveToFirst()) {
            do {
                Memo memo = new Memo();
                memo.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                memo.setMemo(cursor.getString(cursor.getColumnIndex(Constants.KEY_MEMO)));

                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME))).getTime());
                memo.setDateMemoAdded(formattedDate);

                memoList.add(memo);
            } while (cursor.moveToNext());
        }
        return memoList;
    }

}
