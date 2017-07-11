package com.kakanumporn.nakarin.flightreportsmaker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.adapter.ReportAdapter;
import com.kakanumporn.nakarin.flightreportsmaker.model.Report;

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

    private void addReport() {
        // TODO: add real report data

        reportsAdapter.addReport(new Report("1", "ADD", "Jul-9-17 5:35 PM"));
        Toast.makeText(getContext(), "Report Added", Toast.LENGTH_SHORT).show();
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

    /***********
     * Listener
     ***********/
    View.OnClickListener fabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addReport();
        }
    };
}
