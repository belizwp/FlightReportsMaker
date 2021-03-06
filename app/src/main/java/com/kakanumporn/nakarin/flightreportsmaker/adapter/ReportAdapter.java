package com.kakanumporn.nakarin.flightreportsmaker.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.activity.ReportActivity;
import com.kakanumporn.nakarin.flightreportsmaker.adapter.holder.ReportViewHolder;
import com.kakanumporn.nakarin.flightreportsmaker.manager.Contextor;
import com.kakanumporn.nakarin.flightreportsmaker.model.Report;
import com.kakanumporn.nakarin.flightreportsmaker.util.DBReportHelper;
import com.kakanumporn.nakarin.flightreportsmaker.util.MyDate;
import com.kakanumporn.nakarin.flightreportsmaker.util.ReportExporter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belizwp on 7/9/2017.
 */

public class ReportAdapter extends RecyclerSwipeAdapter {

    private static final int DATA_ADD = 0;
    private static final int DATA_DELETE = 1;
    private static final int DATA_UPDATE = 2;

    private Context context;
    private ArrayList<Report> reportList;
    private DBReportHelper dbHelper;

    public ReportAdapter(Context context) {
        this.context = context;
        this.reportList = new ArrayList<>();

        //user application context for legit data management
        this.dbHelper = new DBReportHelper(Contextor.getInstance().getContext());
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
        reportList.addAll(dbHelper.getReportList());
    }

    private void saveData(Report report, int mode) {
        // save reports data to persistent storage;
        switch (mode) {
            case DATA_ADD: {
                long id = dbHelper.addReport(report);
                report.setId(id);
                break;
            }
            case DATA_DELETE: {
                dbHelper.deleteReport(report);
                break;
            }
            case DATA_UPDATE: {
                dbHelper.updateReport(report);
                break;
            }
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("reportList", reportList);

        return bundle;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        reportList = savedInstanceState.getParcelableArrayList("reportList");
    }

    public void addReport(Report report) {
        report.setLastEdit(MyDate.getDate("MMM d yy h:mm a"));
        saveData(report, DATA_ADD);
        reportList.add(report);
        notifyItemInserted(reportList.size() - 1);
    }

    public void addReport(int i, Report report) {
        report.setLastEdit(MyDate.getDate("MMM d yy h:mm a"));
        saveData(report, DATA_ADD);
        reportList.add(i, report);
        notifyItemInserted(i);
    }

    public void removeReport(Report report) {
        saveData(report, DATA_DELETE);
        int index = reportList.indexOf(report);
        String title = report.getTitle();

        reportList.remove(report);
        notifyItemRemoved(index);

        Toast.makeText(context, "Report " + title + " deleted", Toast.LENGTH_SHORT).show();
    }

    public void updateReport(Report report) {
        report.setLastEdit(MyDate.getDate("MMM d yy h:mm a"));
        saveData(report, DATA_UPDATE);
        notifyItemChanged(reportList.indexOf(report));
    }

    public void exportReport(final Report report) {
        ReportExporter exporter = new ReportExporter(Contextor.getInstance().getContext(),
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator);

        final String fileName = "report_" + report.getId() + "_" + report.getTitle() + ".xls";

        exporter.export(report, fileName, new ReportExporter.ExportListener() {
            @Override
            public void onStart() {
                Log.d("export", "started");
            }

            @Override
            public void onCompleted(String filePath) {
                Toast.makeText(context, "Export " + report.getTitle(), Toast.LENGTH_SHORT).show();
                Log.d("export", "completed");
                Log.d("export", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator);

                // TODO: send to export activity (need fileProvider)
//                Uri uri = Uri.fromFile(new File(context.getFilesDir() + File.separator + fileName));
//
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(uri, "application/vnd.ms-excel");
//
//                try {
//                    context.startActivity(intent);
//                } catch (ActivityNotFoundException e) {
//                    Toast.makeText(context, "No Application Available to View Excel", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onError(Exception e) {
                Log.d("export", "err: " + e.getMessage());
            }
        });
    }

    public void openReport(Report report) {
        Intent intent = new Intent(context, ReportActivity.class);
        intent.putExtra("id", report.getId());
        intent.putExtra("title", report.getTitle());
        context.startActivity(intent);
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(ArrayList<Report> reportList) {
        this.reportList = reportList;
    }

    private void setupReportViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ReportViewHolder reportViewHolder = (ReportViewHolder) holder;

        final Report report = reportList.get(position);

        reportViewHolder.tvTitle
                .setText(report.getTitle());
        reportViewHolder.tvLastEdit
                .setText(report.getLastEdit());

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

        reportViewHolder.surface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReport(report);
            }
        });
        reportViewHolder.surface.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showEditReportTittleDialog(report);
                return true;
            }
        });
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
                .setNegativeButton("No", null)
                .show();
    }

    private void showEditReportTittleDialog(final Report report) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        final EditText etReportTitle = new EditText(context);
        etReportTitle.setText(report.getTitle());
        etReportTitle.requestFocus();

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etReportTitle, InputMethodManager.SHOW_IMPLICIT);

        dialog.setTitle("Enter Report Title");
        dialog.setView(etReportTitle);
        dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                report.setTitle(etReportTitle.getText().toString());
                updateReport(report);
            }
        });

        dialog.setNegativeButton("Cancel", null);
        dialog.show();
    }
}
