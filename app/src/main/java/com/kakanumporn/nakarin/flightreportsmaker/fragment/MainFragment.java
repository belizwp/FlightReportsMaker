package com.kakanumporn.nakarin.flightreportsmaker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.adapter.ReportsAdapter;

/**
 * Created by Belizwp on 7/9/2017.
 */

public class MainFragment extends Fragment {

    private RecyclerView rvReports;
    private ReportsAdapter reportsAdapter;
    private RecyclerView.LayoutManager layoutManager;

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
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // bind view
        rvReports = rootView.findViewById(R.id.rvReports);

        // setup view
        layoutManager = new LinearLayoutManager(getContext());
        rvReports.setLayoutManager(layoutManager);
        reportsAdapter = new ReportsAdapter();
        rvReports.setAdapter(reportsAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvReports.getContext(), DividerItemDecoration.VERTICAL);
        rvReports.addItemDecoration(dividerItemDecoration);

        // set dataSet
        // reportsAdapter.setDataSet(); TODO: set data to reports adapter
        reportsAdapter.notifyDataSetChanged();
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
        inflater.inflate(R.menu.menu_main, menu);
    }
}
