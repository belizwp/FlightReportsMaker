package com.kakanumporn.nakarin.flightreportsmaker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.fragment.ReportFragment;

public class ReportActivity extends AppCompatActivity {

    private long id;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentContainer, ReportFragment.newInstance(id))
                    .commit();
        }
    }

    private void initInstances() {
        Intent intent = getIntent();
        id = intent.getLongExtra("id", 0);
        title = intent.getStringExtra("title");
        setTitle(title + " ID: " + id);
    }
}
