package com.kakanumporn.nakarin.flightreportsmaker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.activity.FormActivity;
import com.kakanumporn.nakarin.flightreportsmaker.model.ReportRecord;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class FormFragment extends Fragment {

    private static final int MODE_ADD = 0;
    private static final int MODE_EDIT = 1;

    private int mode;

    private ReportRecord record;

    private Button btnSubmit;

    public FormFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static FormFragment newInstance(ReportRecord record) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putParcelable("record", record);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        record = getArguments().getParcelable("record");

        if (record != null) {
            mode = MODE_EDIT;
            fillForm();

            setHasOptionsMenu(true);
        } else {
            record = new ReportRecord();
            mode = MODE_ADD;
        }
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        btnSubmit = rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_form, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void fillForm() {
        // TODO: fill form value by record
    }

    private void fillRecord() {
        // TODO: fill record value by form
    }

    public void finish() {
        FormActivity activity = (FormActivity) getActivity();
        fillRecord();
        activity.sendRecord(record);
    }
}
