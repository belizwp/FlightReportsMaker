package com.kakanumporn.nakarin.flightreportsmaker.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kakanumporn.nakarin.flightreportsmaker.model.Report;

import java.util.ArrayList;

/**
 * Created by Belizwp on 7/12/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, Report.DATABASE_NAME, null, Report.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_REPORTS_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT)",
                Report.TABLE,
                Report.Column.ID,
                Report.Column.TITLE,
                Report.Column.LAST_EDIT);

        sqLiteDatabase.execSQL(CREATE_REPORTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS reports";

        sqLiteDatabase.execSQL(DROP_FRIEND_TABLE);

        onCreate(sqLiteDatabase);
    }

    public ArrayList<Report> getReportList() {
        ArrayList<Report> reportList = new ArrayList<>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (Report.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            reportList.add(new Report(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2))
            );

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return reportList;
    }

    public void addReport(Report report) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Report.Column.ID, friend.getId()); //Auto increment
        values.put(Report.Column.TITLE, report.getTitle());
        values.put(Report.Column.LAST_EDIT, report.getLastEdit());

        sqLiteDatabase.insert(Report.TABLE, null, values);

        sqLiteDatabase.close();
    }

    public void deleteReport(Report report) {
        sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(Report.TABLE, Report.Column.ID + " = " + report.getId(), null);

        sqLiteDatabase.close();
    }
}
