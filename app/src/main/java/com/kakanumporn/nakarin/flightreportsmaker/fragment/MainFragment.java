package com.kakanumporn.nakarin.flightreportsmaker.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.activity.SettingActivity;
import com.kakanumporn.nakarin.flightreportsmaker.adapter.ReportAdapter;
import com.kakanumporn.nakarin.flightreportsmaker.model.Report;
import com.kakanumporn.nakarin.flightreportsmaker.util.MyDate;


/**
 * Created by Belizwp on 7/9/2017.
 */

public class MainFragment extends Fragment {

    private RecyclerView rvReports;
    private ReportAdapter reportsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private FloatingActionButton fab;

    public MainFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
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
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
        reportsAdapter = new ReportAdapter(getContext());
        reportsAdapter.loadData();
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // bind view
        rvReports = rootView.findViewById(R.id.rvReports);
        fab = rootView.findViewById(R.id.floatingActionButton);

        // setup view
        layoutManager = new LinearLayoutManager(getContext());
        dividerItemDecoration = new DividerItemDecoration(rvReports.getContext(),
                DividerItemDecoration.VERTICAL);

        rvReports.setLayoutManager(layoutManager);
        rvReports.addItemDecoration(dividerItemDecoration);
        rvReports.setAdapter(reportsAdapter);

        fab.setOnClickListener(fabOnClickListener);
    }

    private void addReport(String title) {
        reportsAdapter.addReport(new Report(title));
        rvReports.smoothScrollToPosition(reportsAdapter.getItemCount() - 1);
        Toast.makeText(getContext(), "Report Added", Toast.LENGTH_SHORT).show();
    }

    private void showAddReportDialog() {
        AlertDialog.Builder addReportDialog = new AlertDialog.Builder(getContext());
        final EditText etReportTitle = new EditText(getContext());
        etReportTitle.setText(MyDate.getDate("d/MMMM/yyyy"));
        etReportTitle.requestFocus();

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etReportTitle, InputMethodManager.SHOW_IMPLICIT);

        addReportDialog.setTitle("Enter Report Title");
        addReportDialog.setView(etReportTitle);
        addReportDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                addReport(etReportTitle.getText().toString());
            }
        });

        addReportDialog.setNegativeButton("Cancel", null);
        addReportDialog.show();
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
        outState.putBundle("reportAdapter", reportsAdapter.onSaveInstanceState());
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
        reportsAdapter.onRestoreInstanceState(savedInstanceState.getBundle("reportAdapter"));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_menu_setting) {
            Intent intent = new Intent(getContext(), SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    /***********
     * Listener
     ***********/
    View.OnClickListener fabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showAddReportDialog();
        }
    };
}
