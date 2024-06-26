package com.example.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SQLiteDatabse extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String ID_COLUMN = "ID";
    public static final String USERNAME_COLUMN = "USERNAME";
    public static final String PASSWORD_COLUMN = "PASSWORD";


    public SQLiteDatabse(@Nullable Context context) {
        super(context, "user.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE +
                " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_COLUMN + " TEXT, " + PASSWORD_COLUMN + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + USER_TABLE + " ADD COLUMN " + PASSWORD_COLUMN + " TEXT DEFAULT ''");
        }
    }

    public boolean addRecord(UserData userData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USERNAME_COLUMN, userData.getEmail());
        cv.put(PASSWORD_COLUMN, userData.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);

        return insert != -1;
    }

    public List<UserData> getAllUsers() {
        List<UserData> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                int userID = cursor.getInt(0);
                String userEmail = cursor.getString(1);
                String userPassword = cursor.getString(2);

                UserData newUser = new UserData(userID, userEmail, userPassword);
                returnList.add(newUser);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}

