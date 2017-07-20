package com.kakanumporn.nakarin.flightreportsmaker.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cleveroad.adaptivetablelayout.AdaptiveTableLayout;
import com.cleveroad.adaptivetablelayout.OnItemLongClickListener;
import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.activity.FormActivity;
import com.kakanumporn.nakarin.flightreportsmaker.adapter.TableAdapter;
import com.kakanumporn.nakarin.flightreportsmaker.model.ReportRecord;
import com.kakanumporn.nakarin.flightreportsmaker.util.MyRequestCode;
import com.kakanumporn.nakarin.flightreportsmaker.util.ScreenUtil;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class ReportFragment extends Fragment {

    private AdaptiveTableLayout tableLayout;
    private TableAdapter tableAdapter;
    private long id;
    private FloatingActionButton fab;

    public ReportFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ReportFragment newInstance(long id) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        id = getArguments().getLong("id");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        tableLayout = rootView.findViewById(R.id.tableLayout);

        initAdapter();

        fab = rootView.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(fabOnClickListener);
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

    /**
     * Listener
     */
    View.OnClickListener fabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "Add Record", Toast.LENGTH_SHORT).show();
            // TODO: send to form activity
            Intent intent = new Intent(getContext(), FormActivity.class);
            startActivityForResult(intent, MyRequestCode.ADD_FLIGHT);
        }
    };

    OnItemLongClickListener tableAdapterListener = new OnItemLongClickListener() {
        @Override
        public void onItemLongClick(int row, int column) {
            Toast.makeText(getContext(), "Edit Record", Toast.LENGTH_SHORT).show();
            // TODO: send to form activity with record id
            Intent intent = new Intent(getContext(), FormActivity.class);
            intent.putExtra("id", tableAdapter.getRecord(row).getId());
            startActivityForResult(intent, MyRequestCode.EDIT_FLIGHT);
        }

        @Override
        public void onLeftTopHeaderLongClick() {

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MyRequestCode.ADD_FLIGHT) {
            if (resultCode == Activity.RESULT_OK) {
                ReportRecord record = data.getParcelableExtra("record");
                if (record != null) {
                    tableAdapter.addRecord(record);
                    // tableAdapter.notifyDataSetChanged(); // it's not work
                    Toast.makeText(getContext(), "Record Added " + id, Toast.LENGTH_SHORT).show();
                    // TODO: improve data refresh
                    initAdapter(); // re-init for now
                    tableLayout.scrollBy(0, 0);
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getContext(), "Discard Change", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == MyRequestCode.EDIT_FLIGHT) {
            if (resultCode == Activity.RESULT_OK) {
                ReportRecord record = data.getParcelableExtra("record");
                if (record != null) {
                    tableAdapter.updateRecord(record);
                    tableAdapter.notifyDataSetChanged(); // it's not work
                    Toast.makeText(getContext(), "Record " + record.getId() + " Edited ", Toast.LENGTH_SHORT).show();
                    // TODO: improve data refresh
                    initAdapter(); // re-init for now
                    tableLayout.scrollBy(0, 0);
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getContext(), "Discard Change", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initAdapter() {
        tableAdapter = new TableAdapter(getContext(), id);
        tableAdapter.setOnItemLongClickListener(tableAdapterListener);
        tableLayout.setAdapter(tableAdapter);
    }
}
