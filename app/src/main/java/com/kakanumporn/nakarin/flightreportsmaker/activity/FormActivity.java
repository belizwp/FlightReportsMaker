package com.kakanumporn.nakarin.flightreportsmaker.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.fragment.FormFragment;
import com.kakanumporn.nakarin.flightreportsmaker.model.ReportRecord;
import com.kakanumporn.nakarin.flightreportsmaker.util.MyRequestCode;

public class FormActivity extends AppCompatActivity {

    private ReportRecord record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentContainer, FormFragment.newInstance(record))
                    .commit();
        }
    }

    private void initInstances() {
        Intent intent = getIntent();
        record = intent.getParcelableExtra("record");
        if (record == null) {
            setTitle("Add Flight");
        } else {
            setTitle("Edit Flight ID:" + record.getId());
        }
    }

    public void sendRecord(ReportRecord record) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("record", record);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void deleteRecord(ReportRecord record) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("record", record);
        setResult(MyRequestCode.DELETE_FLIGHT, returnIntent);
        finish();
    }
}
