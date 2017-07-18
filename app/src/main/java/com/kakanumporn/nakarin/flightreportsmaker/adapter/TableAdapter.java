package com.kakanumporn.nakarin.flightreportsmaker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cleveroad.adaptivetablelayout.LinkedAdaptiveTableAdapter;
import com.cleveroad.adaptivetablelayout.ViewHolderImpl;
import com.kakanumporn.nakarin.flightreportsmaker.R;

/**
 * Created by Belizwp on 7/18/2017.
 */

public class TableAdapter extends LinkedAdaptiveTableAdapter<ViewHolderImpl> {

    String[] headerList = new String[]{
            "No.", "A/C",
            "Date", "Flight", "Dep",
            "Delay Code", "Min",
            "Delay Code", "Min", "Total Delay MIN",
            "Adult", "CHD", "INF", "Total",
            "Touch Down", "Block In",
            "Date", "Flight", "Arr",
            "Delay Code", "Min",
            "Delay Code", "Min", "Total Delay MIN",
            "Adult", "CHD", "INF", "Total",
            "Bag Weight", "Total Traffic Load", "Underload before LMC", "Allowed Traffic Load",
            "Special Meal", "Total Meal",
            "Aerobrige", "START", "END", "GSE RQ",
            "Invoice No. Refuelling Receipt (LT)", "Invoice Fuel (KG)", "Temp", "Actual Density",
            "Basic Price (USD/KG)", "Comprehensive Fees (USD/KG)", "Amount (USD)",
            "GHA (RMB)",
            "Remark"};

    LayoutInflater mLayoutInflater;

    public TableAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getRowCount() {
        return 32;
    }

    @Override
    public int getColumnCount() {
        return 47;
    }

    @NonNull
    @Override
    public ViewHolderImpl onCreateItemViewHolder(@NonNull ViewGroup parent) {
        return new TestViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false));
    }

    @NonNull
    @Override
    public ViewHolderImpl onCreateColumnHeaderViewHolder(@NonNull ViewGroup parent) {
        return new TestViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false));
    }

    @NonNull
    @Override
    public ViewHolderImpl onCreateRowHeaderViewHolder(@NonNull ViewGroup parent) {
        return new TestViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false));
    }

    @NonNull
    @Override
    public ViewHolderImpl onCreateLeftTopHeaderViewHolder(@NonNull ViewGroup parent) {
        return new TestViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderImpl viewHolder, int row, int column) {
        final TestViewHolder vh = (TestViewHolder) viewHolder;
        vh.tvText.setText("item: " + row + ", " + column);
    }

    @Override
    public void onBindHeaderColumnViewHolder(@NonNull ViewHolderImpl viewHolder, int column) {
        final TestViewHolder vh = (TestViewHolder) viewHolder;
        vh.tvText.setText(headerList[column]);
    }

    @Override
    public void onBindHeaderRowViewHolder(@NonNull ViewHolderImpl viewHolder, int row) {
        final TestViewHolder vh = (TestViewHolder) viewHolder;
        vh.tvText.setText(row + "");
    }

    @Override
    public void onBindLeftTopHeaderViewHolder(@NonNull ViewHolderImpl viewHolder) {
        final TestViewHolder vh = (TestViewHolder) viewHolder;
        vh.tvText.setText("No.");
    }

    @Override
    public int getColumnWidth(int column) {
        return 420;
    }

    @Override
    public int getHeaderColumnHeight() {
        return 220;
    }

    @Override
    public int getRowHeight(int row) {
        return 220;
    }

    @Override
    public int getHeaderRowWidth() {
        return 144;
    }

    //------------------------------------- view holders ------------------------------------------

    private static class TestViewHolder extends ViewHolderImpl {
        TextView tvText;

        private TestViewHolder(@NonNull View itemView) {
            super(itemView);

            tvText = itemView.findViewById(R.id.tvText);
        }
    }
}
