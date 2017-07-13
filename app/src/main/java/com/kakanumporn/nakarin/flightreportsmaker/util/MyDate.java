package com.kakanumporn.nakarin.flightreportsmaker.util;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;

/**
 * Created by Belizwp on 7/13/2017.
 */

public class MyDate {

    public static String getDate(String format) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(Calendar.getInstance().getTime());
        } else {
            // TODO: return date format for api below 24
            return "N/A";
        }
    }

}
