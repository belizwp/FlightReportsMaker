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
import com.kakanumporn.nakarin.flightreportsmaker.manager.Contextor;
import com.kakanumporn.nakarin.flightreportsmaker.model.ReportRecord;
import com.kakanumporn.nakarin.flightreportsmaker.util.DBReportRecordHelper;
import com.kakanumporn.nakarin.flightreportsmaker.util.ScreenUtil;

import java.util.ArrayList;

/**
 * Created by Belizwp on 7/18/2017.
 */

public class TableAdapter extends LinkedAdaptiveTableAdapter<ViewHolderImpl> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private ArrayList<ReportRecord> records;
    private DBReportRecordHelper dbHelper;
    private long id;

    public TableAdapter(Context context, long id) {
        this.context = context;
        this.id = id;

        mLayoutInflater = LayoutInflater.from(context);

        records = new ArrayList<>();
        dbHelper = new DBReportRecordHelper(Contextor.getInstance().getContext());

        loadData();
    }

    private void loadData() {
        records.addAll(dbHelper.getReportRecordList(id));
    }

    public ReportRecord getRecord(int i) {
        return records.get(i);
    }

    public void addRecord(ReportRecord record) {
        record.setReportId(id);
        dbHelper.addReport(record);
        records.add(record);
    }

    public void updateRecord(ReportRecord record) {
        record.setReportId(id);
        dbHelper.updateRecord(record);
        records.remove(record.getCurrentRow());
        records.add(record.getCurrentRow(), record);
    }

    public void deleteRecord(ReportRecord record) {
        dbHelper.deleteRecord(record);
        records.remove(record.getCurrentRow());
    }

    @Override
    public int getRowCount() {
        return records.size() + 1; // row header reserve
    }

    @Override
    public int getColumnCount() {
        return ReportRecordColumType.HEADER.length;
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

        int index = row - 1;
        String text = "";

        switch (column) {
            case ReportRecordColumType.AC:
                text = records.get(index).getAc();
                break;
            case ReportRecordColumType.DEP_DATE:
                text = records.get(index).getDepDate();
                break;
            case ReportRecordColumType.DEP_FLIGHT:
                text = records.get(index).getDepFlight();
                break;
            case ReportRecordColumType.DEP:
                text = records.get(index).getDep();
                break;
            case ReportRecordColumType.DEP_DELAY_CODE_A:
                text = records.get(index).getDepDelayCodeA();
                break;
            case ReportRecordColumType.DEP_DELAY_MIN_A:
                text = filterNumber(records.get(index).getDepDelayMinA());
                break;
            case ReportRecordColumType.DEP_DELAY_CODE_B:
                text = records.get(index).getDepDelayCodeB();
                break;
            case ReportRecordColumType.DEP_DELAY_MIN_B:
                text = filterNumber(records.get(index).getDepDelayMinB());
                break;
            case ReportRecordColumType.DEP_DELAY_TOTAL_MIN:
                text = filterNumber(records.get(index).getDepDelayTotalMin());
                break;
            case ReportRecordColumType.DEP_ADULT:
                text = filterNumber(records.get(index).getDepAdult());
                break;
            case ReportRecordColumType.DEP_CHD:
                text = filterNumber(records.get(index).getDepChd());
                break;
            case ReportRecordColumType.DEP_INF:
                text = filterNumber(records.get(index).getDepInf());
                break;
            case ReportRecordColumType.DEP_TOTAL:
                text = filterNumber(records.get(index).getDepTotal());
                break;
            case ReportRecordColumType.TOUCH_DOWN:
                text = records.get(index).getTouchDown();
                break;
            case ReportRecordColumType.BLOCK_IN:
                text = records.get(index).getBlockIn();
                break;
            case ReportRecordColumType.ARR_DATE:
                text = records.get(index).getArrDate();
                break;
            case ReportRecordColumType.ARR_FLIGHT:
                text = records.get(index).getArrFlight();
                break;
            case ReportRecordColumType.ARR:
                text = records.get(index).getArr();
                break;
            case ReportRecordColumType.OFF_BLOCK:
                text = records.get(index).getOffBlock();
                break;
            case ReportRecordColumType.AIRBORNE:
                text = records.get(index).getAirborne();
                break;
            case ReportRecordColumType.ARR_DELAY_CODE_A:
                text = records.get(index).getArrDelayCodeA();
                break;
            case ReportRecordColumType.ARR_DELAY_MIN_A:
                text = filterNumber(records.get(index).getArrDelayMinA());
                break;
            case ReportRecordColumType.ARR_DELAY_CODE_B:
                text = records.get(index).getArrDelayCodeB();
                break;
            case ReportRecordColumType.ARR_DELAY_MIN_B:
                text = filterNumber(records.get(index).getArrDelayMinB());
                break;
            case ReportRecordColumType.ARR_DELAY_TOTAL_MIN:
                text = filterNumber(records.get(index).getArrDelayTotalMin());
                break;
            case ReportRecordColumType.ARR_ADULT:
                text = filterNumber(records.get(index).getArrAdult());
                break;
            case ReportRecordColumType.ARR_CHD:
                text = filterNumber(records.get(index).getArrChd());
                break;
            case ReportRecordColumType.ARR_INF:
                text = filterNumber(records.get(index).getArrInf());
                break;
            case ReportRecordColumType.ARR_TOTAL:
                text = filterNumber(records.get(index).getArrTotal());
                break;
            case ReportRecordColumType.BAG_WEIGHT:
                text = filterNumber(records.get(index).getBagWeight());
                break;
            case ReportRecordColumType.TOTAL_TRAFFIC_LOAD:
                text = filterNumber(records.get(index).getTotalTrafficLoad());
                break;
            case ReportRecordColumType.UNDERLOAD_BEFORE_LMC:
                text = filterNumber(records.get(index).getUnderloadBeforeLMC());
                break;
            case ReportRecordColumType.ALLOWED_TRAFFIC_LOAD:
                text = filterNumber(records.get(index).getAllowedTrafficLoad());
                break;
            case ReportRecordColumType.SPECIAL_MEAL:
                text = records.get(index).getSpecialMeal();
                break;
            case ReportRecordColumType.TOTAL_MEAL:
                text = filterNumber(records.get(index).getTotalMeal());
                break;
            case ReportRecordColumType.AERO_BRIDGE:
                text = records.get(index).getAeroBridge();
                break;
            case ReportRecordColumType.START:
                text = records.get(index).getStart();
                break;
            case ReportRecordColumType.END:
                text = records.get(index).getEnd();
                break;
            case ReportRecordColumType.GSE_RQ:
                text = records.get(index).getGseRq();
                break;
            case ReportRecordColumType.INV_NO:
                text = records.get(index).getInvNo();
                break;
            case ReportRecordColumType.REFUEL_RECEIPT:
                text = filterNumber(records.get(index).getRefuelReceipt());
                break;
            case ReportRecordColumType.INV_FUEL:
                text = filterNumber(records.get(index).getInvFuel());
                break;
            case ReportRecordColumType.TEMP:
                text = filterNumber(records.get(index).getTemp());
                break;
            case ReportRecordColumType.ACTUAL_DENSITY:
                text = filterNumber(records.get(index).getActualDensity());
                break;
            case ReportRecordColumType.BASIC_PRICE:
                text = records.get(index).getBasicPrice();
                break;
            case ReportRecordColumType.FEES:
                text = records.get(index).getFees();
                break;
            case ReportRecordColumType.AMOUNT:
                text = records.get(index).getAmount();
                break;
            case ReportRecordColumType.GHA:
                text = records.get(index).getGha();
                break;
            case ReportRecordColumType.REMARK:
                text = records.get(index).getRemark();
                break;
        }

        vh.tvText.setText(text);
    }

    @Override
    public void onBindHeaderColumnViewHolder(@NonNull ViewHolderImpl viewHolder, int column) {
        final TestViewHolder vh = (TestViewHolder) viewHolder;
        vh.tvText.setText(ReportRecordColumType.HEADER[column]);
    }

    @Override
    public void onBindHeaderRowViewHolder(@NonNull ViewHolderImpl viewHolder, int row) {
        final TestViewHolder vh = (TestViewHolder) viewHolder;
        vh.tvText.setText(String.valueOf(row));
    }

    @Override
    public void onBindLeftTopHeaderViewHolder(@NonNull ViewHolderImpl viewHolder) {
        final TestViewHolder vh = (TestViewHolder) viewHolder;
        vh.tvText.setText(ReportRecordColumType.HEADER[0]);
    }

    @Override
    public int getColumnWidth(int column) {
        return (int) ScreenUtil.convertDpToPixel(96, context);
    }

    @Override
    public int getHeaderColumnHeight() {
        return (int) ScreenUtil.convertDpToPixel(48, context);
    }

    @Override
    public int getRowHeight(int row) {
        return (int) ScreenUtil.convertDpToPixel(48, context);
    }

    @Override
    public int getHeaderRowWidth() {
        return (int) ScreenUtil.convertDpToPixel(48, context);
    }

    private String filterNumber(int i) {
        if (i == 0) {
            return "";
        }
        return String.valueOf(i);
    }

    private String filterNumber(float i) {
        if (i == 0) {
            return "";
        }
        return String.valueOf(i);
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
