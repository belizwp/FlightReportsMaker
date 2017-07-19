package com.kakanumporn.nakarin.flightreportsmaker.adapter;

/**
 * Created by Belizwp on 7/19/2017.
 */

public class ReportRecordColumType {

    public static String[] HEADER = new String[]{
            "No.", "A/C",
            "Date", "Flight", "Dep",
            "Delay Code", "Min",
            "Delay Code", "Min", "Total Delay MIN",
            "Adult", "CHD", "INF", "Total",
            "Touch Down", "Block In",
            "Date", "Flight", "Arr",
            "Off Block", "Airborne",
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

    /******
     * id
     ******/
    public static final int ID = 0;

    /***********
     * aircraft
     ***********/
    public static final int AC = 1;

    /*****************
     * departure info
     *****************/
    public static final int DEP_DATE = 2;
    public static final int DEP_FLIGHT = 3;
    public static final int DEP = 4;
    public static final int DEP_DELAY_CODE_A = 5;
    public static final int DEP_DELAY_MIN_A = 6;
    public static final int DEP_DELAY_CODE_B = 7;
    public static final int DEP_DELAY_MIN_B = 8;
    public static final int DEP_DELAY_TOTAL_MIN = 9;
    public static final int DEP_ADULT = 10;
    public static final int DEP_CHD = 11;
    public static final int DEP_INF = 12;
    public static final int DEP_TOTAL = 13;

    /***************
     * arrive info
     ***************/
    public static final int TOUCH_DOWN = 14;
    public static final int BLOCK_IN = 15;

    public static final int ARR_DATE = 16;
    public static final int ARR_FLIGHT = 17;
    public static final int ARR = 18;
    public static final int OFF_BLOCK = 19;
    public static final int AIRBORNE = 20;
    public static final int ARR_DELAY_CODE_A = 21;
    public static final int ARR_DELAY_MIN_A = 22;
    public static final int ARR_DELAY_CODE_B = 23;
    public static final int ARR_DELAY_MIN_B = 24;
    public static final int ARR_DELAY_TOTAL_MIN = 25;
    public static final int ARR_ADULT = 26;
    public static final int ARR_CHD = 27;
    public static final int ARR_INF = 28;
    public static final int ARR_TOTAL = 29;

    /*******
     * load
     *******/
    public static final int BAG_WEIGHT = 30;
    public static final int TOTAL_TRAFFIC_LOAD = 31;
    public static final int UNDERLOAD_BEFORE_LMC = 32;
    public static final int ALLOWED_TRAFFIC_LOAD = 33;

    /*******
     * meal
     *******/
    public static final int SPECIAL_MEAL = 34;
    public static final int TOTAL_MEAL = 35;

    /*********
     * bridge
     *********/
    public static final int AERO_BRIDGE = 36;
    public static final int START = 37;
    public static final int END = 38;
    public static final int GSE_RQ = 39;

    /*******
     * fuel
     *******/
    public static final int INV_NO = 40;
    public static final int REFUEL_RECEIPT = 41;
    public static final int INV_FUEL = 42;
    public static final int TEMP = 43;
    public static final int ACTUAL_DENSITY = 44;
    public static final int BASIC_PRICE = 45;
    public static final int FEES = 46;
    public static final int AMOUNT = 47;

    /*******
     * etc.
     *******/
    public static final int GHA = 48;
    public static final int REMARK = 49;

    /*********
     * ref id
     *********/
    public static final int REPORT_ID = 50;
}
