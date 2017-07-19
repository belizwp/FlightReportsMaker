package com.kakanumporn.nakarin.flightreportsmaker.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Belizwp on 7/19/2017.
 */

public class ReportRecord implements Parcelable {

    //Database
    public static final String DATABASE_NAME = "report_detail.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "report_detail";

    protected ReportRecord(Parcel in) {
        id = in.readLong();
        reportId = in.readLong();
        ac = in.readString();
        depDate = in.readString();
        depFlight = in.readString();
        dep = in.readString();
        depDelayCodeA = in.readString();
        depDelayMinA = in.readInt();
        depDelayCodeB = in.readString();
        depDelayMinB = in.readInt();
        depDelayTotalMin = in.readInt();
        depAdult = in.readInt();
        depChd = in.readInt();
        depInf = in.readInt();
        depTotal = in.readInt();
        touchDown = in.readString();
        blockIn = in.readString();
        arrDate = in.readString();
        arrFlight = in.readString();
        arr = in.readString();
        offBlock = in.readString();
        airborne = in.readString();
        arrDelayCodeA = in.readString();
        arrDelayMinA = in.readInt();
        arrDelayCodeB = in.readString();
        arrDelayMinB = in.readInt();
        arrDelayTotalMin = in.readString();
        arrAdult = in.readInt();
        arrChd = in.readInt();
        arrInf = in.readInt();
        arrTotal = in.readInt();
        bagWeight = in.readInt();
        totalTrafficLoad = in.readInt();
        underloadBeforeLMC = in.readInt();
        allowedTrafficLoad = in.readInt();
        specialMeal = in.readString();
        totalMeal = in.readInt();
        aeroBridge = in.readString();
        start = in.readString();
        end = in.readString();
        gseRq = in.readString();
        invNo = in.readString();
        refuelReceipt = in.readInt();
        invFuel = in.readInt();
        temp = in.readFloat();
        actualDensity = in.readFloat();
        basicPrice = in.readString();
        fees = in.readString();
        amount = in.readString();
        gha = in.readString();
        remark = in.readString();
    }

    public static final Creator<ReportRecord> CREATOR = new Creator<ReportRecord>() {
        @Override
        public ReportRecord createFromParcel(Parcel in) {
            return new ReportRecord(in);
        }

        @Override
        public ReportRecord[] newArray(int size) {
            return new ReportRecord[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(reportId);
        parcel.writeString(ac);
        parcel.writeString(depDate);
        parcel.writeString(depFlight);
        parcel.writeString(dep);
        parcel.writeString(depDelayCodeA);
        parcel.writeInt(depDelayMinA);
        parcel.writeString(depDelayCodeB);
        parcel.writeInt(depDelayMinB);
        parcel.writeInt(depDelayTotalMin);
        parcel.writeInt(depAdult);
        parcel.writeInt(depChd);
        parcel.writeInt(depInf);
        parcel.writeInt(depTotal);
        parcel.writeString(touchDown);
        parcel.writeString(blockIn);
        parcel.writeString(arrDate);
        parcel.writeString(arrFlight);
        parcel.writeString(arr);
        parcel.writeString(offBlock);
        parcel.writeString(airborne);
        parcel.writeString(arrDelayCodeA);
        parcel.writeInt(arrDelayMinA);
        parcel.writeString(arrDelayCodeB);
        parcel.writeInt(arrDelayMinB);
        parcel.writeString(arrDelayTotalMin);
        parcel.writeInt(arrAdult);
        parcel.writeInt(arrChd);
        parcel.writeInt(arrInf);
        parcel.writeInt(arrTotal);
        parcel.writeInt(bagWeight);
        parcel.writeInt(totalTrafficLoad);
        parcel.writeInt(underloadBeforeLMC);
        parcel.writeInt(allowedTrafficLoad);
        parcel.writeString(specialMeal);
        parcel.writeInt(totalMeal);
        parcel.writeString(aeroBridge);
        parcel.writeString(start);
        parcel.writeString(end);
        parcel.writeString(gseRq);
        parcel.writeString(invNo);
        parcel.writeInt(refuelReceipt);
        parcel.writeInt(invFuel);
        parcel.writeFloat(temp);
        parcel.writeFloat(actualDensity);
        parcel.writeString(basicPrice);
        parcel.writeString(fees);
        parcel.writeString(amount);
        parcel.writeString(gha);
        parcel.writeString(remark);
    }

    public class Column {
        /******
         * id
         ******/
        public static final String ID = "id";
        public static final String REPORT_ID = "report_id";

        /***********
         * aircraft
         ***********/
        public static final String AC = "ac";

        /*****************
         * departure info
         *****************/
        public static final String DEP_DATE = "dep_date";
        public static final String DEP_FLIGHT = "dep_flight";
        public static final String DEP = "dep";
        public static final String DEP_DELAY_CODE_A = "dep_delay_code_a";
        public static final String DEP_DELAY_MIN_A = "dep_delay_min_a";
        public static final String DEP_DELAY_CODE_B = "dep_delay_code_b";
        public static final String DEP_DELAY_MIN_B = "dep_delay_min_b";
        public static final String DEP_DELAY_TOTAL_MIN = "dep_delay_total_min";
        public static final String DEP_ADULT = "dep_adult";
        public static final String DEP_CHD = "dep_chd";
        public static final String DEP_INF = "dep_inf";
        public static final String DEP_TOTAL = "dep_total";

        /***************
         * arrive info
         ***************/
        public static final String TOUCH_DOWN = "touch_down";
        public static final String BLOCK_IN = "block_in";

        public static final String ARR_DATE = "arr_date";
        public static final String ARR_FLIGHT = "arr_flight";
        public static final String ARR = "arr";
        public static final String OFF_BLOCK = "off_block";
        public static final String AIRBORNE = "airborne";
        public static final String ARR_DELAY_CODE_A = "arr_delay_code_a";
        public static final String ARR_DELAY_MIN_A = "arr_delay_min_a";
        public static final String ARR_DELAY_CODE_B = "arr_delay_code_b";
        public static final String ARR_DELAY_MIN_B = "arr_delay_min_b";
        public static final String ARR_DELAY_TOTAL_MIN = "arr_delay_total_min";
        public static final String ARR_ADULT = "arr_adult";
        public static final String ARR_CHD = "arr_chd";
        public static final String ARR_INF = "arr_inf";
        public static final String ARR_TOTAL = "arr_total";

        /*******
         * load
         *******/
        public static final String BAG_WEIGHT = "bag_weight";
        public static final String TOTAL_TRAFFIC_LOAD = "total_traffic_load";
        public static final String UNDERLOAD_BEFORE_LMC = "underload_before_lmc";
        public static final String ALLOWED_TRAFFIC_LOAD = "allowed_traffic_load";

        /*******
         * meal
         *******/
        public static final String SPECIAL_MEAL = "special_meal";
        public static final String TOTAL_MEAL = "total_meal";

        /*********
         * bridge
         *********/
        public static final String AERO_BRIDGE = "aero_bridge";
        public static final String START = "start";
        public static final String END = "end";
        public static final String GSE_RQ = "gse_rq";

        /*******
         * fuel
         *******/
        public static final String INV_NO = "inv_no";
        public static final String REFUEL_RECEIPT = "refuel_receipt";
        public static final String INV_FUEL = "inv_fuel";
        public static final String TEMP = "temp";
        public static final String ACTUAL_DENSITY = "actual_density";
        public static final String BASIC_PRICE = "basic_price";
        public static final String FEES = "fees";
        public static final String AMOUNT = "amount";

        /*******
         * etc.
         *******/
        public static final String GHA = "gha";
        public static final String REMARK = "remark";

    }

    // id
    private long id;
    private long reportId;

    // aircraft
    private String ac;

    // departure info
    private String depDate;
    private String depFlight;
    private String dep;
    private String depDelayCodeA;
    private int depDelayMinA;
    private String depDelayCodeB;
    private int depDelayMinB;
    private int depDelayTotalMin;
    private int depAdult;
    private int depChd;
    private int depInf;
    private int depTotal;

    // arrive info
    private String touchDown;
    private String blockIn;

    private String arrDate;
    private String arrFlight;
    private String arr;
    private String offBlock;
    private String airborne;
    private String arrDelayCodeA;
    private int arrDelayMinA;
    private String arrDelayCodeB;
    private int arrDelayMinB;
    private String arrDelayTotalMin;
    private int arrAdult;
    private int arrChd;
    private int arrInf;
    private int arrTotal;

    // load
    private int bagWeight;
    private int totalTrafficLoad;
    private int underloadBeforeLMC;
    private int allowedTrafficLoad;

    // meal
    private String specialMeal;
    private int totalMeal;

    // bridge
    private String aeroBridge;
    private String start;
    private String end;
    private String gseRq;

    // fuel
    private String invNo;
    private int refuelReceipt;
    private int invFuel;
    private float temp;
    private float actualDensity;
    private String basicPrice;
    private String fees;
    private String amount;

    // ect.
    private String gha;
    private String remark;

    public ReportRecord() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getDepFlight() {
        return depFlight;
    }

    public void setDepFlight(String depFlight) {
        this.depFlight = depFlight;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getDepDelayCodeA() {
        return depDelayCodeA;
    }

    public void setDepDelayCodeA(String depDelayCodeA) {
        this.depDelayCodeA = depDelayCodeA;
    }

    public int getDepDelayMinA() {
        return depDelayMinA;
    }

    public void setDepDelayMinA(int depDelayMin) {
        this.depDelayMinA = depDelayMin;
    }

    public String getDepDelayCodeB() {
        return depDelayCodeB;
    }

    public void setDepDelayCodeB(String depDelayCodeB) {
        this.depDelayCodeB = depDelayCodeB;
    }

    public int getDepDelayMinB() {
        return depDelayMinB;
    }

    public void setDepDelayMinB(int depDelayMinB) {
        this.depDelayMinB = depDelayMinB;
    }

    public int getDepDelayTotalMin() {
        return depDelayTotalMin;
    }

    public void setDepDelayTotalMin(int depDelayTotalMin) {
        this.depDelayTotalMin = depDelayTotalMin;
    }

    public int getDepAdult() {
        return depAdult;
    }

    public void setDepAdult(int depAdult) {
        this.depAdult = depAdult;
    }

    public int getDepChd() {
        return depChd;
    }

    public void setDepChd(int depChd) {
        this.depChd = depChd;
    }

    public int getDepInf() {
        return depInf;
    }

    public void setDepInf(int depInf) {
        this.depInf = depInf;
    }

    public int getDepTotal() {
        return depTotal;
    }

    public void setDepTotal(int depTotal) {
        this.depTotal = depTotal;
    }

    public String getTouchDown() {
        return touchDown;
    }

    public void setTouchDown(String touchDown) {
        this.touchDown = touchDown;
    }

    public String getBlockIn() {
        return blockIn;
    }

    public void setBlockIn(String blockIn) {
        this.blockIn = blockIn;
    }

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String getArrFlight() {
        return arrFlight;
    }

    public void setArrFlight(String arrFlight) {
        this.arrFlight = arrFlight;
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr = arr;
    }

    public String getOffBlock() {
        return offBlock;
    }

    public void setOffBlock(String offBlock) {
        this.offBlock = offBlock;
    }

    public String getAirborne() {
        return airborne;
    }

    public void setAirborne(String airborne) {
        this.airborne = airborne;
    }

    public String getArrDelayCodeA() {
        return arrDelayCodeA;
    }

    public void setArrDelayCodeA(String arrDelayCodeA) {
        this.arrDelayCodeA = arrDelayCodeA;
    }

    public int getArrDelayMinA() {
        return arrDelayMinA;
    }

    public void setArrDelayMinA(int arrDelayMinA) {
        this.arrDelayMinA = arrDelayMinA;
    }

    public String getArrDelayCodeB() {
        return arrDelayCodeB;
    }

    public void setArrDelayCodeB(String arrDelayCodeB) {
        this.arrDelayCodeB = arrDelayCodeB;
    }

    public int getArrDelayMinB() {
        return arrDelayMinB;
    }

    public void setArrDelayMinB(int arrDelayMinB) {
        this.arrDelayMinB = arrDelayMinB;
    }

    public String getArrDelayTotalMin() {
        return arrDelayTotalMin;
    }

    public void setArrDelayTotalMin(String arrDelayTotalMin) {
        this.arrDelayTotalMin = arrDelayTotalMin;
    }

    public int getArrAdult() {
        return arrAdult;
    }

    public void setArrAdult(int arrAdult) {
        this.arrAdult = arrAdult;
    }

    public int getArrChd() {
        return arrChd;
    }

    public void setArrChd(int arrChd) {
        this.arrChd = arrChd;
    }

    public int getArrInf() {
        return arrInf;
    }

    public void setArrInf(int arrInf) {
        this.arrInf = arrInf;
    }

    public int getArrTotal() {
        return arrTotal;
    }

    public void setArrTotal(int arrTotal) {
        this.arrTotal = arrTotal;
    }

    public int getBagWeight() {
        return bagWeight;
    }

    public void setBagWeight(int bagWeight) {
        this.bagWeight = bagWeight;
    }

    public int getTotalTrafficLoad() {
        return totalTrafficLoad;
    }

    public void setTotalTrafficLoad(int totalTrafficLoad) {
        this.totalTrafficLoad = totalTrafficLoad;
    }

    public int getUnderloadBeforeLMC() {
        return underloadBeforeLMC;
    }

    public void setUnderloadBeforeLMC(int underloadBeforeLMC) {
        this.underloadBeforeLMC = underloadBeforeLMC;
    }

    public int getAllowedTrafficLoad() {
        return allowedTrafficLoad;
    }

    public void setAllowedTrafficLoad(int allowedTrafficLoad) {
        this.allowedTrafficLoad = allowedTrafficLoad;
    }

    public String getSpecialMeal() {
        return specialMeal;
    }

    public void setSpecialMeal(String specialMeal) {
        this.specialMeal = specialMeal;
    }

    public int getTotalMeal() {
        return totalMeal;
    }

    public void setTotalMeal(int totalMeal) {
        this.totalMeal = totalMeal;
    }

    public String getAeroBridge() {
        return aeroBridge;
    }

    public void setAeroBridge(String aeroBridge) {
        this.aeroBridge = aeroBridge;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getGseRq() {
        return gseRq;
    }

    public void setGseRq(String gseRq) {
        this.gseRq = gseRq;
    }

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public int getRefuelReceipt() {
        return refuelReceipt;
    }

    public void setRefuelReceipt(int refuelReceipt) {
        this.refuelReceipt = refuelReceipt;
    }

    public int getInvFuel() {
        return invFuel;
    }

    public void setInvFuel(int invFuel) {
        this.invFuel = invFuel;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getActualDensity() {
        return actualDensity;
    }

    public void setActualDensity(float actualDensity) {
        this.actualDensity = actualDensity;
    }

    public String getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(String basicPrice) {
        this.basicPrice = basicPrice;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getGha() {
        return gha;
    }

    public void setGha(String gha) {
        this.gha = gha;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
