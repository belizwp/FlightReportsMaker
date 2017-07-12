package com.kakanumporn.nakarin.flightreportsmaker.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Belize on 7/11/2017 AD.
 */

public class Report implements Parcelable {

    //Database
    public static final String DATABASE_NAME = "flight_reports.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "reports";

    public class Column {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String LAST_EDIT = "last_edit";
    }

    private int id;
    private String title;
    private String lastEdit;

    public Report() {

    }

    public Report(int id, String title, String lastEdit) {
        this.id = id;
        this.title = title;
        this.lastEdit = lastEdit;
    }


    protected Report(Parcel in) {
        id = in.readInt();
        title = in.readString();
        lastEdit = in.readString();
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(lastEdit);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit = lastEdit;
    }
}
