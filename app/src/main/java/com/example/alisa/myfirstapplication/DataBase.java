package com.example.alisa.myfirstapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.WorkerThread;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Alisa on 11/29/2016.
 */

public class DataBase extends SQLiteOpenHelper {

    private static final String COMMA_SEP = ",";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BirthdayTable.TABLE_NAME;
    private static final String TAG = DataBase.class.getSimpleName();
    private static final String TEXT_TYPE = " TEXT";

    // create table favcol (_id int primary key, username string, fav_color string) ;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + BirthdayTable.TABLE_NAME +
            " (" + BirthdayTable.COLUMN_NAME + TEXT_TYPE + " PRIMARY KEY," +
            BirthdayTable.COLUMN_BIRTHDATE + TEXT_TYPE + COMMA_SEP +
            BirthdayTable.COLUMN_NEXTBIRTHDAY + " INTEGER" + COMMA_SEP +
            BirthdayTable.COLUMN_COMMENT + TEXT_TYPE + ")";

    private static final String SQL_SELECT_ENTRIES = "SELECT " +
            BirthdayTable.COLUMN_NAME + COMMA_SEP+
            BirthdayTable.COLUMN_BIRTHDATE  + COMMA_SEP +
            BirthdayTable.COLUMN_NEXTBIRTHDAY +  COMMA_SEP +
            BirthdayTable.COLUMN_COMMENT + " FROM "+BirthdayTable.TABLE_NAME;

    public static final String DATABASE_NAME = "my.db";
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    private static DataBase mInstance = null;

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) {

            Log.i(TAG, "db onCreate() called - database created");

        }
        db.execSQL(SQL_CREATE_ENTRIES);
        try {
            addRow(db,"Alisa",new SimpleDateFormat("yyyymmdd").parse("19910122"),"ring");
            addRow(db,"Alex",new SimpleDateFormat("yyyymmdd").parse("19820312"),"ring");
        } catch (ParseException e) {
            Log.e(TAG,"error in onCreate "+e.getMessage(),e);
        }
    }


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mInstance = this;
        // this call is needed for the onCreate to be called on the very first run.
        // and it doesn't do harm afterwards.
        getWritableDatabase();

        if (BuildConfig.DEBUG) {
            // tip:
            Log.i(TAG, "You can play with the database right on the emulator: \n adb shell '" +
                    "sqlite3 /data/data/" + BuildConfig.APPLICATION_ID + "/databases/" + DATABASE_NAME + "'\n" +
                    "or pull the sqlite database to your pc like that:\n" +
                    "adb pull /data/data/" + BuildConfig.APPLICATION_ID + "/databases/" + DATABASE_NAME);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int from, int to) {
        switch (from) {
            case 1:
                // you can drop the table and re-create it here, or use sql UPDATE command to keep the data.
                // up to you..


            case 2:
                // notice I'm not breaking between the cases. so if the app wasn't updated for awhile
                // it will go through all the stages. 1 to 2, 2 to 3, etc...
                break;
            default:
                if (to == DATABASE_VERSION) {
                    throw new IllegalArgumentException("I forgot to provide a db update code " +
                            "for the new db version #" + DATABASE_VERSION + ", silly me..");
                    // comment: don't worry about runtime cost of concatenating the above strings.
                    // they will become one string during compilation!
                }
        }
        // code sample for drop,
        db.execSQL(SQL_DELETE_ENTRIES);
        // you'll need to create the table again if dropped..
        onCreate(db);

    }

    //yyyy-MM-dd
    //
    public void addRow(String nameString, Date date, String comment) {
        ContentValues cv = new ContentValues();
        cv.put(BirthdayTable.COLUMN_NAME, nameString);
        cv.put(BirthdayTable.COLUMN_BIRTHDATE, new SimpleDateFormat(DATE_FORMAT).format(date));
        cv.put(BirthdayTable.COLUMN_NEXTBIRTHDAY, new SimpleDateFormat(DATE_FORMAT).format(getNextBirthday(date)));
        cv.put(BirthdayTable.COLUMN_COMMENT, comment);
        // I don't need the newKey, but I just demonstrate that you can get it if you want
        long newKey = getWritableDatabase().insertWithOnConflict(BirthdayTable.TABLE_NAME, null,
                cv, SQLiteDatabase.CONFLICT_REPLACE);
    }
    final String DATE_FORMAT="yyyy-MM-dd";

    public void addRow(SQLiteDatabase db,String nameString, Date date, String comment) {
        ContentValues cv = new ContentValues();
        cv.put(BirthdayTable.COLUMN_NAME, nameString);
        cv.put(BirthdayTable.COLUMN_BIRTHDATE, new SimpleDateFormat(DATE_FORMAT).format(date));
        cv.put(BirthdayTable.COLUMN_NEXTBIRTHDAY, new SimpleDateFormat(DATE_FORMAT).format(getNextBirthday(date)));
        cv.put(BirthdayTable.COLUMN_COMMENT, comment);
        // I don't need the newKey, but I just demonstrate that you can get it if you want
        long newKey = db.insertWithOnConflict(BirthdayTable.TABLE_NAME, null,
                cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

    private Date getNextBirthday(Date birthdayDate) {

        Calendar birthday = Calendar.getInstance();
        birthday.setTime(birthdayDate);

        Calendar currentDate = Calendar.getInstance();
        int yearCurrent = currentDate.get(Calendar.YEAR);
        int monthBirthday = birthday.get(Calendar.MONTH);
        int monthCurrent = currentDate.get(Calendar.MONTH);
        int diffMonth = monthBirthday - monthCurrent;
        int theSmallestDiff;
        boolean ItWiilBeSoon = false;

        if (diffMonth > 0) {
            theSmallestDiff = diffMonth;
            ItWiilBeSoon = true;
        } else if (diffMonth == 0) {
            int dayBirthday = birthday.get(Calendar.DAY_OF_MONTH);
            int dayCurrent = currentDate.get(Calendar.DAY_OF_MONTH);
            int diffDay = dayBirthday - dayCurrent;
            if (diffDay > 0)
                ItWiilBeSoon = true;
            else if (diffDay == 0) {
                ItWiilBeSoon = false;
            }
        } else {
            ItWiilBeSoon = false;
        }

        if (ItWiilBeSoon) {
            birthday.set(Calendar.YEAR, yearCurrent);
        } else {
            birthday.set(Calendar.YEAR, yearCurrent + 1);
        }
        return birthday.getTime();
    }

    /*
        public int whoIsNext(Date users_date)
        {
            Date current_date= Calendar.getInstance().getTime();
            if(users_date.after)
        }
        */
    public void updateRow(String nameString, String date) {
        ContentValues cv = new ContentValues();
        cv.put(BirthdayTable.COLUMN_NAME, nameString);
        cv.put(BirthdayTable.COLUMN_BIRTHDATE, date);
        // I don't need the newKey, but I just demonstrate that you can get it if you want
        long newKey = getWritableDatabase().insertWithOnConflict(BirthdayTable.TABLE_NAME, null,
                cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public ArrayList<String> whoHasBirthdayAt(String date) {
        // this is the needed sql:
        // select * from favcol where fav_color='blue';
        // you can test it via adb or locally

        ArrayList<String> ret = new ArrayList<>();

        String q = "select * from " + BirthdayTable.TABLE_NAME + " where " + BirthdayTable.COLUMN_BIRTHDATE + "=?;";

        Cursor c = getWritableDatabase().rawQuery(q, new String[]{date});

        while (c.moveToNext()) {

            // Comments:
            // 1) I know that the name is the second parameter, but it could change in the future,
            //    so it's safest to check the index
            // 2) the query could be "select username from ...", and we could just use index 0
            String name = c.getString(c.getColumnIndex(BirthdayTable.COLUMN_NAME));
            ret.add(name);

        }
        c.close(); // << very important to close the Cursor!
        return ret;
    }

    @WorkerThread
    public static DataBase getDb(Context context) {

        // Util.assertTrue(Util.getThreadType() != Util.TH_UI, "Must not call this on UI thread!");

        if (mInstance == null) {
            synchronized (DATABASE_NAME) {
                // checking again inside the sync block, since it's a critical section.
                // I'm not doing this in the above if because it's really rare
                // and I don't want the overhead of locks all the time
                if (mInstance == null) {
                    // making sure you're not using activity context!!!
                    new DataBase(context.getApplicationContext()); // mInstance is set in the CTOR
                }
            }
        }
        return mInstance;
    }

    public Birthday[] getBirthdays() {

       List<Birthday> birthdayList= new ArrayList<Birthday>();
        Birthday[] birthdayArray;
        int num;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(SQL_SELECT_ENTRIES, null);
        String[] data      = null;

        if (cursor.moveToFirst()) {
            do {
                try {
                    birthdayList.add(new Birthday(cursor.getString(0),
                                     new SimpleDateFormat(DATE_FORMAT).parse(cursor.getString(1)),
                                     new SimpleDateFormat(DATE_FORMAT).parse(cursor.getString(2)),
                                     cursor.getString(3)));

                } catch (ParseException e) {
                    Log.e(TAG,"error in onCreate "+e.getMessage(),e);
                }
            } while (cursor.moveToNext());
        }
        int size=birthdayList.size();
        cursor.close();
        birthdayArray= new Birthday[size];
        for (int i = 0; i <size ; i++) {
            birthdayArray[i]=birthdayList.get(i);
        }
        return birthdayArray;
    }
}