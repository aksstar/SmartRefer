package com.example.aakash.smartrefer;

/**
 * Created by User on 1/3/2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 5/5/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 1;
 //   private Dashboard daas;
    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ID3 = "_id3";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_MESSAGE = "msg";
    public static final String DASHTAB = "dashboard";

    /*
    create table users(
        id integer primary key autoincrement,
        email text,
        password text);
     */
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT);";

    public static final String CREATE_TABLE_DASH = "CREATE TABLE " + DASHTAB + "("
            + COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MESSAGE + " TEXT);";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_DASH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE" + DASHTAB);
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addmessage(String message)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MESSAGE, message);
        long id = db.insert(DASHTAB, null, values);
        db.close();

        Log.d(TAG, "message inserted" + id);
    }
    public void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);

        long id = db.insert(USER_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }

    public boolean getUser(String email, String pass){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public boolean dashemp(){
        String selectQuery = "select * from  " + DASHTAB;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                //daas.itemList.add(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE)));
            }
            return true;
        }
        cursor.close();
        db.close();
        return false;

    }
}