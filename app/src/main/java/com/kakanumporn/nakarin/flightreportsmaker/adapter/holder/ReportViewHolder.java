package com.kakanumporn.nakarin.flightreportsmaker.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.kakanumporn.nakarin.flightreportsmaker.R;

/**
 * Created by Belizwp on 7/9/2017.
 */

public class ReportViewHolder extends RecyclerView.ViewHolder {

    public SwipeLayout swipeLayout;
    public TextView tvTitle;
    public TextView tvLastEdit;
    public Button btnExport;
    public Button btnDelete;
    public LinearLayout surface;

    public ReportViewHolder(View itemView) {
        super(itemView);
        swipeLayout = itemView.findViewById(R.id.swipeLayout);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvLastEdit = itemView.findViewById(R.id.tvLastEdit);
        btnExport = itemView.findViewById(R.id.btnExport);
        btnDelete = itemView.findViewById(R.id.btnDelete);
        surface = itemView.findViewById(R.id.surface_wrapper);
    }

}
