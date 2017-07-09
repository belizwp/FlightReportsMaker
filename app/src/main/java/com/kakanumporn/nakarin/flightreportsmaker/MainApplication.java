package com.kakanumporn.nakarin.flightreportsmaker;

import android.app.Application;

import com.kakanumporn.nakarin.flightreportsmaker.manager.Contextor;

/**
 * Created by Belizwp on 7/9/2017.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Initialize
        Contextor.getInstance().init(getApplicationContext());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
