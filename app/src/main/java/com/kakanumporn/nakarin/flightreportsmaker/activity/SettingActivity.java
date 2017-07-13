package com.kakanumporn.nakarin.flightreportsmaker.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.fragment.SettingFragment;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentContainer, SettingFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {
        setTitle("Settings");
    }
}
