package com.kakanumporn.nakarin.flightreportsmaker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.adapter.holder.ReportViewHolder;

/**
 * Created by Belizwp on 7/9/2017.
 */

public class ReportsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_item_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReportViewHolder reportViewHolder = (ReportViewHolder) holder;
        reportViewHolder.textView.setText("POS: " + position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
