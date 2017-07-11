package com.kakanumporn.nakarin.flightreportsmaker.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.adapter.holder.ReportViewHolder;
import com.kakanumporn.nakarin.flightreportsmaker.model.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belizwp on 7/9/2017.
 */

public class ReportAdapter extends RecyclerSwipeAdapter {

    Context context;
    ArrayList<Report> reportList;

    public ReportAdapter(Context context) {
        this.context = context;
        this.reportList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_list_item_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setupReportViewHolder(holder, position);

        mItemManger.bindView(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout;
    }

    public void loadData() {
        // TODO: load real reports data
        reportList.add(new Report("0", "AAA", "Jul-7-17 5:42 PM"));
        reportList.add(new Report("0", "BBB", "Jul-7-17 5:42 PM"));
        reportList.add(new Report("0", "CCC", "Jul-7-17 5:42 PM"));
        reportList.add(new Report("0", "DDD", "Jul-7-17 5:42 PM"));
    }

    private void saveData() {
        // TODO: save reports data to persistent storage;
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("reportList", reportList);

        return bundle;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        reportList = savedInstanceState.getParcelableArrayList("reportList");
    }

    private void setupReportViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ReportViewHolder reportViewHolder = (ReportViewHolder) holder;
        reportViewHolder.tvTitle
                .setText(reportList.get(position).getTitle());
        reportViewHolder.tvLastEdit
                .setText(reportList.get(position).getLastEdit());

        final Report report = reportList.get(position);

        View.OnClickListener reportItemButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnDelete) {
                    showDeleteComfirmationDialog(report);
                } else if (view.getId() == R.id.btnExport) {
                    exportReport(report);
                }
            }
        };
        reportViewHolder.btnDelete.setOnClickListener(reportItemButtonListener);
        reportViewHolder.btnExport.setOnClickListener(reportItemButtonListener);
    }

    public void addReport(Report report) {
        reportList.add(report);
        saveData();
        notifyItemInserted(reportList.size() - 1);
    }

    public void addReport(int i, Report report) {
        reportList.add(i, report);
        saveData();
        notifyItemInserted(i);
    }

    public void removeReport(Report report) {
        int index = reportList.indexOf(report);
        String title = report.getTitle();
        reportList.remove(report);
        saveData();
        notifyItemRemoved(index);
        Toast.makeText(context, "Report " + title + " deleted", Toast.LENGTH_SHORT).show();
    }

    public void exportReport(Report report) {
        // TODO: export report
        String title = report.getTitle();
        Toast.makeText(context, "Export " + title, Toast.LENGTH_SHORT).show();
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(ArrayList<Report> reportList) {
        this.reportList = reportList;
    }

    private void showDeleteComfirmationDialog(final Report report) {
        new AlertDialog.Builder(context)
                .setTitle("Deletion confirmation")
                .setMessage("Are you sure you want to delete this report?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeReport(report);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
}
