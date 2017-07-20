package com.kakanumporn.nakarin.flightreportsmaker.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kakanumporn.nakarin.flightreportsmaker.model.ReportRecord;
import com.kakanumporn.nakarin.flightreportsmaker.model.ReportRecord.Column;

import java.util.ArrayList;

/**
 * Created by Belizwp on 7/19/2017.
 */

public class DBReportRecordHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DBReportRecordHelper(Context context) {
        super(context, ReportRecord.DATABASE_NAME, null, ReportRecord.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_REPORT_DETAIL_TABLE = String.format(
                "CREATE TABLE IF NOT EXISTS %s " +
                        // id
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, " +
                        "%s INTEGER, " +

                        // aircraft
                        "%s TEXT, " +

                        // departure info
                        "%s TEXT, %s TEXT, %s TEXT, " + //date, flight, dep
                        "%s TEXT, %s INTEGER, %s TEXT, %s INTEGER, %s INTEGER, " + // code1, min1, code2, min2, total
                        "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, " + // adult, chd, inf, total

                        // arrive info
                        "%s TEXT, %s TEXT, " + // touch down, block in

                        "%s TEXT, %s TEXT, %s TEXT, " + //date, flight, arr
                        "%s TEXT, %s TEXT, " + // off block, airborne
                        "%s TEXT, %s INTEGER, %s TEXT, %s INTEGER, %s INTEGER, " + // code1, min1, code2, min2, total
                        "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, " + // adult, chd, inf, total

                        // load
                        "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, " + // bag, total, underload, allowed

                        // meal
                        "%s TEXT, %s INTEGER, " + // special, total

                        // bridge
                        "%s TEXT, %s TEXT, %s TEXT, %s TEXT, " + // bridge, start, end, gse_rq

                        // fuel
                        "%s TEXT, %s INTEGER, %s INTEGER, %s REAL, %s REAL, " + //inv_no, receipt, inv fuel, temp, dens
                        "%s TEXT, %s TEXT, %s TEXT, " + // basic price,  fees, amount

                        // etc.
                        "%s TEXT, %s TEXT)", // gsa, remark

                // table name
                ReportRecord.TABLE,

                // id
                Column.ID, Column.REPORT_ID,

                // aircraft
                Column.AC,

                // departure info
                Column.DEP_DATE, Column.DEP_FLIGHT, Column.DEP,
                Column.DEP_DELAY_CODE_A, Column.DEP_DELAY_MIN_A, Column.DEP_DELAY_CODE_B, Column.DEP_DELAY_MIN_B, Column.DEP_DELAY_TOTAL_MIN,
                Column.DEP_ADULT, Column.DEP_CHD, Column.DEP_INF, Column.DEP_TOTAL,

                // arrive info
                Column.TOUCH_DOWN, Column.BLOCK_IN,

                Column.ARR_DATE, Column.ARR_FLIGHT, Column.ARR,
                Column.OFF_BLOCK, Column.AIRBORNE,
                Column.ARR_DELAY_CODE_A, Column.ARR_DELAY_MIN_A, Column.ARR_DELAY_CODE_B, Column.ARR_DELAY_MIN_B, Column.ARR_DELAY_TOTAL_MIN,
                Column.ARR_ADULT, Column.ARR_CHD, Column.ARR_INF, Column.ARR_TOTAL,

                // load
                Column.BAG_WEIGHT, Column.TOTAL_TRAFFIC_LOAD, Column.UNDERLOAD_BEFORE_LMC, Column.ALLOWED_TRAFFIC_LOAD,

                // meal
                Column.SPECIAL_MEAL, Column.TOTAL_MEAL,

                // bridge
                Column.AERO_BRIDGE, Column.START, Column.END, Column.GSE_RQ,

                // fuel
                Column.INV_NO, Column.REFUEL_RECEIPT, Column.INV_FUEL, Column.TEMP, Column.ACTUAL_DENSITY,
                Column.BASIC_PRICE, Column.FEES, Column.AMOUNT,

                // etc.
                Column.GHA, Column.REMARK);


        sqLiteDatabase.execSQL(CREATE_REPORT_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_REPORT_DETAIL_TABLE = "DROP TABLE IF EXISTS " + ReportRecord.TABLE;

        sqLiteDatabase.execSQL(DROP_REPORT_DETAIL_TABLE);

        onCreate(sqLiteDatabase);
    }

    public ArrayList<ReportRecord> getReportRecordList(long id) {
        ArrayList<ReportRecord> records = new ArrayList<>();

        sqLiteDatabase = this.getWritableDatabase();

        String QUERY_STRING = String.format("SELECT * FROM %s WHERE %s = %d",
                ReportRecord.TABLE, Column.REPORT_ID, id);

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_STRING, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            ReportRecord record = new ReportRecord();

            // id
            record.setId(cursor.getLong(0));
            record.setReportId(cursor.getLong(1));

            // aircraft
            record.setAc(cursor.getString(2));

            // departure info
            record.setDepDate(cursor.getString(3));
            record.setDepFlight(cursor.getString(4));
            record.setDep(cursor.getString(5));
            record.setDepDelayCodeA(cursor.getString(6));
            record.setDepDelayMinA(cursor.getInt(7));
            record.setDepDelayCodeB(cursor.getString(8));
            record.setDepDelayMinB(cursor.getInt(9));
            record.setDepDelayTotalMin(cursor.getInt(10));
            record.setDepAdult(cursor.getInt(11));
            record.setDepChd(cursor.getInt(12));
            record.setDepInf(cursor.getInt(13));
            record.setDepTotal(cursor.getInt(14));

            // arrive info
            record.setTouchDown(cursor.getString(15));
            record.setBlockIn(cursor.getString(16));

            record.setArrDate(cursor.getString(17));
            record.setArrFlight(cursor.getString(18));
            record.setArr(cursor.getString(19));

            record.setOffBlock(cursor.getString(20));
            record.setAirborne(cursor.getString(21));

            record.setArrDelayCodeA(cursor.getString(22));
            record.setArrDelayMinA(cursor.getInt(23));
            record.setArrDelayCodeB(cursor.getString(24));
            record.setArrDelayMinB(cursor.getInt(25));
            record.setArrDelayTotalMin(cursor.getString(26));
            record.setArrAdult(cursor.getInt(27));
            record.setArrChd(cursor.getInt(28));
            record.setArrInf(cursor.getInt(29));
            record.setArrTotal(cursor.getInt(30));

            // load
            record.setBagWeight(cursor.getInt(31));
            record.setTotalTrafficLoad(cursor.getInt(32));
            record.setUnderloadBeforeLMC(cursor.getInt(33));
            record.setAllowedTrafficLoad(cursor.getInt(34));

            // meal
            record.setSpecialMeal(cursor.getString(35));
            record.setTotalMeal(cursor.getInt(36));

            // bridge
            record.setAeroBridge(cursor.getString(37));
            record.setStart(cursor.getString(38));
            record.setEnd(cursor.getString(39));
            record.setGseRq(cursor.getString(40));

            // fuel
            record.setInvNo(cursor.getString(41));
            record.setRefuelReceipt(cursor.getInt(42));
            record.setInvFuel(cursor.getInt(43));
            record.setTemp(cursor.getFloat(44));
            record.setActualDensity(cursor.getFloat(45));
            record.setBasicPrice(cursor.getString(46));
            record.setFees(cursor.getString(47));
            record.setAmount(cursor.getString(48));

            // etc.
            record.setGha(cursor.getString(49));
            record.setRemark(cursor.getString(50));

            records.add(record);

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return records;
    }

    public long addReport(ReportRecord record) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Column.ID, record.getId()); //Auto increment
        values.put(Column.REPORT_ID, record.getReportId());

        // aircraft
        values.put(Column.AC, record.getAc());

        // departure info
        values.put(Column.DEP_DATE, record.getDepDate());
        values.put(Column.DEP_FLIGHT, record.getDepFlight());
        values.put(Column.DEP, record.getDep());
        values.put(Column.DEP_DELAY_CODE_A, record.getDepDelayCodeA());
        values.put(Column.DEP_DELAY_MIN_A, record.getDepDelayMinA());
        values.put(Column.DEP_DELAY_CODE_B, record.getDepDelayCodeB());
        values.put(Column.DEP_DELAY_MIN_B, record.getDepDelayMinB());
        values.put(Column.DEP_DELAY_TOTAL_MIN, record.getDepDelayTotalMin());
        values.put(Column.DEP_ADULT, record.getDepAdult());
        values.put(Column.DEP_CHD, record.getDepChd());
        values.put(Column.DEP_INF, record.getDepInf());
        values.put(Column.DEP_TOTAL, record.getDepTotal());

        // arrive info
        values.put(Column.TOUCH_DOWN, record.getTouchDown());
        values.put(Column.BLOCK_IN, record.getBlockIn());

        values.put(Column.ARR_DATE, record.getArrDate());
        values.put(Column.ARR_FLIGHT, record.getArrFlight());
        values.put(Column.ARR, record.getArr());
        values.put(Column.OFF_BLOCK, record.getOffBlock());
        values.put(Column.AIRBORNE, record.getAirborne());
        values.put(Column.ARR_DELAY_CODE_A, record.getArrDelayCodeA());
        values.put(Column.ARR_DELAY_MIN_A, record.getArrDelayMinA());
        values.put(Column.ARR_DELAY_CODE_B, record.getArrDelayCodeB());
        values.put(Column.ARR_DELAY_MIN_B, record.getArrDelayMinB());
        values.put(Column.ARR_DELAY_TOTAL_MIN, record.getArrDelayTotalMin());
        values.put(Column.ARR_ADULT, record.getArrAdult());
        values.put(Column.ARR_CHD, record.getArrChd());
        values.put(Column.ARR_INF, record.getArrInf());
        values.put(Column.ARR_TOTAL, record.getArrTotal());

        // load
        values.put(Column.BAG_WEIGHT, record.getBagWeight());
        values.put(Column.TOTAL_TRAFFIC_LOAD, record.getTotalTrafficLoad());
        values.put(Column.UNDERLOAD_BEFORE_LMC, record.getUnderloadBeforeLMC());
        values.put(Column.ALLOWED_TRAFFIC_LOAD, record.getAllowedTrafficLoad());

        // meal
        values.put(Column.SPECIAL_MEAL, record.getSpecialMeal());
        values.put(Column.TOTAL_MEAL, record.getTotalMeal());

        // bridge
        values.put(Column.AERO_BRIDGE, record.getAeroBridge());
        values.put(Column.START, record.getStart());
        values.put(Column.END, record.getEnd());
        values.put(Column.GSE_RQ, record.getGseRq());

        // fuel
        values.put(Column.INV_NO, record.getInvNo());
        values.put(Column.REFUEL_RECEIPT, record.getRefuelReceipt());
        values.put(Column.INV_FUEL, record.getInvFuel());
        values.put(Column.TEMP, record.getTemp());
        values.put(Column.ACTUAL_DENSITY, record.getActualDensity());
        values.put(Column.BASIC_PRICE, record.getBasicPrice());
        values.put(Column.FEES, record.getFees());
        values.put(Column.AMOUNT, record.getAmount());

        // etc.
        values.put(Column.GHA, record.getGha());
        values.put(Column.REMARK, record.getRemark());

        long id = sqLiteDatabase.insert(ReportRecord.TABLE, null, values);

        sqLiteDatabase.close();

        return id;
    }

    public void updateRecord(ReportRecord record) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Column.REPORT_ID, record.getReportId());

        // aircraft
        values.put(Column.AC, record.getAc());

        // departure info
        values.put(Column.DEP_DATE, record.getDepDate());
        values.put(Column.DEP_FLIGHT, record.getDepFlight());
        values.put(Column.DEP, record.getDep());
        values.put(Column.DEP_DELAY_CODE_A, record.getDepDelayCodeA());
        values.put(Column.DEP_DELAY_MIN_A, record.getDepDelayMinA());
        values.put(Column.DEP_DELAY_CODE_B, record.getDepDelayCodeB());
        values.put(Column.DEP_DELAY_MIN_B, record.getDepDelayMinB());
        values.put(Column.DEP_DELAY_TOTAL_MIN, record.getDepDelayTotalMin());
        values.put(Column.DEP_ADULT, record.getDepAdult());
        values.put(Column.DEP_CHD, record.getDepChd());
        values.put(Column.DEP_INF, record.getDepInf());
        values.put(Column.DEP_TOTAL, record.getDepTotal());

        // arrive info
        values.put(Column.TOUCH_DOWN, record.getTouchDown());
        values.put(Column.BLOCK_IN, record.getBlockIn());

        values.put(Column.ARR_DATE, record.getArrDate());
        values.put(Column.ARR_FLIGHT, record.getArrFlight());
        values.put(Column.ARR, record.getArr());
        values.put(Column.OFF_BLOCK, record.getOffBlock());
        values.put(Column.AIRBORNE, record.getAirborne());
        values.put(Column.ARR_DELAY_CODE_A, record.getArrDelayCodeA());
        values.put(Column.ARR_DELAY_MIN_A, record.getArrDelayMinA());
        values.put(Column.ARR_DELAY_CODE_B, record.getArrDelayCodeB());
        values.put(Column.ARR_DELAY_MIN_B, record.getArrDelayMinB());
        values.put(Column.ARR_DELAY_TOTAL_MIN, record.getArrDelayTotalMin());
        values.put(Column.ARR_ADULT, record.getArrAdult());
        values.put(Column.ARR_CHD, record.getArrChd());
        values.put(Column.ARR_INF, record.getArrInf());
        values.put(Column.ARR_TOTAL, record.getArrTotal());

        // load
        values.put(Column.BAG_WEIGHT, record.getBagWeight());
        values.put(Column.TOTAL_TRAFFIC_LOAD, record.getTotalTrafficLoad());
        values.put(Column.UNDERLOAD_BEFORE_LMC, record.getUnderloadBeforeLMC());
        values.put(Column.ALLOWED_TRAFFIC_LOAD, record.getAllowedTrafficLoad());

        // meal
        values.put(Column.SPECIAL_MEAL, record.getSpecialMeal());
        values.put(Column.TOTAL_MEAL, record.getTotalMeal());

        // bridge
        values.put(Column.AERO_BRIDGE, record.getAeroBridge());
        values.put(Column.START, record.getStart());
        values.put(Column.END, record.getEnd());
        values.put(Column.GSE_RQ, record.getGseRq());

        // fuel
        values.put(Column.INV_NO, record.getInvNo());
        values.put(Column.REFUEL_RECEIPT, record.getRefuelReceipt());
        values.put(Column.INV_FUEL, record.getInvFuel());
        values.put(Column.TEMP, record.getTemp());
        values.put(Column.ACTUAL_DENSITY, record.getActualDensity());
        values.put(Column.BASIC_PRICE, record.getBasicPrice());
        values.put(Column.FEES, record.getFees());
        values.put(Column.AMOUNT, record.getAmount());

        // etc.
        values.put(Column.GHA, record.getGha());
        values.put(Column.REMARK, record.getRemark());

        sqLiteDatabase.update(ReportRecord.TABLE, values, Column.ID + " = " + record.getId(), null);

        sqLiteDatabase.close();
    }
}
