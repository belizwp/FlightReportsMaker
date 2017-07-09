package com.kakanumporn.nakarin.flightreportsmaker.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kakanumporn.nakarin.flightreportsmaker.R;

/**
 * Created by Belizwp on 7/9/2017.
 */

public class ReportViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public ReportViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tvTitle);
    }
}
