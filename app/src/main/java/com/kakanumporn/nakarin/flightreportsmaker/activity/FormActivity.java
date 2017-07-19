package com.kakanumporn.nakarin.flightreportsmaker.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.fragment.FormFragment;
import com.kakanumporn.nakarin.flightreportsmaker.model.ReportRecord;

public class FormActivity extends AppCompatActivity {

    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentContainer, FormFragment.newInstance(id))
                    .commit();
        }
    }

    private void initInstances() {
        Intent intent = getIntent();
        id = intent.getLongExtra("id", 0);
        setTitle(id == 0 ? "Add Flight" : "Edit Flight");
    }

    public void sendRecord(ReportRecord record) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("record", record);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
