package com.kakanumporn.nakarin.flightreportsmaker.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.kakanumporn.nakarin.flightreportsmaker.adapter.ReportRecordColumType;
import com.kakanumporn.nakarin.flightreportsmaker.model.Report;
import com.kakanumporn.nakarin.flightreportsmaker.model.ReportRecord;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Belizwp on 7/30/2017.
 */

public class ReportExporter {

    private static Handler handler = new Handler(Looper.getMainLooper());

    private Context mContext;
    private String mExportPath;
    private HSSFWorkbook workbook;
    private DBReportRecordHelper dbRecordHelper;

    public ReportExporter(Context context, String exportPath) {
        mContext = context;
        mExportPath = exportPath;
        dbRecordHelper = new DBReportRecordHelper(context);
    }

    public void export(final Report report, final String fileName, final ExportListener listener) {
        if (listener != null) {
            listener.onStart();
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    exportReports(report, fileName);
                    if (listener != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onCompleted(mExportPath + fileName);
                            }
                        });
                    }
                } catch (final Exception e) {
                    if (listener != null)
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onError(e);
                            }
                        });
                }
            }
        }).start();
    }

    private void exportReports(Report report, final String fileName) throws Exception {
        workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet(report.getTitle());
        createSheet(report, sheet);

        File file = new File(mExportPath, fileName);
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.flush();
        fos.close();
        workbook.close();
    }

    private void createSheet(Report report, HSSFSheet sheet) {
        // set column header
        HSSFRow rowA = sheet.createRow(0);
        String[] header = ReportRecordColumType.HEADER;
        for (int i = 0; i < header.length; i++) {
            HSSFCell cellA = rowA.createCell(i);
            cellA.setCellValue(new HSSFRichTextString("" + header[i]));
        }

        // set cell
        insertItemToSheet(report, sheet, header);
    }

    private void insertItemToSheet(Report report, HSSFSheet sheet, String[] header) {
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        ArrayList<ReportRecord> records = dbRecordHelper.getReportRecordList(report.getId());

        for (int i = 0; i < records.size(); i++) {
            HSSFRow rowA = sheet.createRow(i + 1);
            for (int j = 0; j < header.length; j++) {
                HSSFCell cellA = rowA.createCell(j);

                String text = "";

                switch (j) {
                    case ReportRecordColumType.ID:
                        text = String.valueOf(i + 1);
                        break;
                    case ReportRecordColumType.AC:
                        text = records.get(i).getAc();
                        break;
                    case ReportRecordColumType.DEP_DATE:
                        text = records.get(i).getDepDate();
                        break;
                    case ReportRecordColumType.DEP_FLIGHT:
                        text = records.get(i).getDepFlight();
                        break;
                    case ReportRecordColumType.DEP:
                        text = records.get(i).getDep();
                        break;
                    case ReportRecordColumType.DEP_DELAY_CODE_A:
                        text = records.get(i).getDepDelayCodeA();
                        break;
                    case ReportRecordColumType.DEP_DELAY_MIN_A:
                        text = filterNumber(records.get(i).getDepDelayMinA());
                        break;
                    case ReportRecordColumType.DEP_DELAY_CODE_B:
                        text = records.get(i).getDepDelayCodeB();
                        break;
                    case ReportRecordColumType.DEP_DELAY_MIN_B:
                        text = filterNumber(records.get(i).getDepDelayMinB());
                        break;
                    case ReportRecordColumType.DEP_DELAY_TOTAL_MIN:
                        text = filterNumber(records.get(i).getDepDelayTotalMin());
                        break;
                    case ReportRecordColumType.DEP_ADULT:
                        text = filterNumber(records.get(i).getDepAdult());
                        break;
                    case ReportRecordColumType.DEP_CHD:
                        text = filterNumber(records.get(i).getDepChd());
                        break;
                    case ReportRecordColumType.DEP_INF:
                        text = filterNumber(records.get(i).getDepInf());
                        break;
                    case ReportRecordColumType.DEP_TOTAL:
                        text = filterNumber(records.get(i).getDepTotal());
                        break;
                    case ReportRecordColumType.TOUCH_DOWN:
                        text = records.get(i).getTouchDown();
                        break;
                    case ReportRecordColumType.BLOCK_IN:
                        text = records.get(i).getBlockIn();
                        break;
                    case ReportRecordColumType.ARR_DATE:
                        text = records.get(i).getArrDate();
                        break;
                    case ReportRecordColumType.ARR_FLIGHT:
                        text = records.get(i).getArrFlight();
                        break;
                    case ReportRecordColumType.ARR:
                        text = records.get(i).getArr();
                        break;
                    case ReportRecordColumType.OFF_BLOCK:
                        text = records.get(i).getOffBlock();
                        break;
                    case ReportRecordColumType.AIRBORNE:
                        text = records.get(i).getAirborne();
                        break;
                    case ReportRecordColumType.ARR_DELAY_CODE_A:
                        text = records.get(i).getArrDelayCodeA();
                        break;
                    case ReportRecordColumType.ARR_DELAY_MIN_A:
                        text = filterNumber(records.get(i).getArrDelayMinA());
                        break;
                    case ReportRecordColumType.ARR_DELAY_CODE_B:
                        text = records.get(i).getArrDelayCodeB();
                        break;
                    case ReportRecordColumType.ARR_DELAY_MIN_B:
                        text = filterNumber(records.get(i).getArrDelayMinB());
                        break;
                    case ReportRecordColumType.ARR_DELAY_TOTAL_MIN:
                        text = filterNumber(records.get(i).getArrDelayTotalMin());
                        break;
                    case ReportRecordColumType.ARR_ADULT:
                        text = filterNumber(records.get(i).getArrAdult());
                        break;
                    case ReportRecordColumType.ARR_CHD:
                        text = filterNumber(records.get(i).getArrChd());
                        break;
                    case ReportRecordColumType.ARR_INF:
                        text = filterNumber(records.get(i).getArrInf());
                        break;
                    case ReportRecordColumType.ARR_TOTAL:
                        text = filterNumber(records.get(i).getArrTotal());
                        break;
                    case ReportRecordColumType.BAG_WEIGHT:
                        text = filterNumber(records.get(i).getBagWeight());
                        break;
                    case ReportRecordColumType.TOTAL_TRAFFIC_LOAD:
                        text = filterNumber(records.get(i).getTotalTrafficLoad());
                        break;
                    case ReportRecordColumType.UNDERLOAD_BEFORE_LMC:
                        text = filterNumber(records.get(i).getUnderloadBeforeLMC());
                        break;
                    case ReportRecordColumType.ALLOWED_TRAFFIC_LOAD:
                        text = filterNumber(records.get(i).getAllowedTrafficLoad());
                        break;
                    case ReportRecordColumType.SPECIAL_MEAL:
                        text = records.get(i).getSpecialMeal();
                        break;
                    case ReportRecordColumType.TOTAL_MEAL:
                        text = filterNumber(records.get(i).getTotalMeal());
                        break;
                    case ReportRecordColumType.AERO_BRIDGE:
                        text = records.get(i).getAeroBridge();
                        break;
                    case ReportRecordColumType.START:
                        text = records.get(i).getStart();
                        break;
                    case ReportRecordColumType.END:
                        text = records.get(i).getEnd();
                        break;
                    case ReportRecordColumType.GSE_RQ:
                        text = records.get(i).getGseRq();
                        break;
                    case ReportRecordColumType.INV_NO:
                        text = records.get(i).getInvNo();
                        break;
                    case ReportRecordColumType.REFUEL_RECEIPT:
                        text = filterNumber(records.get(i).getRefuelReceipt());
                        break;
                    case ReportRecordColumType.INV_FUEL:
                        text = filterNumber(records.get(i).getInvFuel());
                        break;
                    case ReportRecordColumType.TEMP:
                        text = filterNumber(records.get(i).getTemp());
                        break;
                    case ReportRecordColumType.ACTUAL_DENSITY:
                        text = filterNumber(records.get(i).getActualDensity());
                        break;
                    case ReportRecordColumType.BASIC_PRICE:
                        text = records.get(i).getBasicPrice();
                        break;
                    case ReportRecordColumType.FEES:
                        text = records.get(i).getFees();
                        break;
                    case ReportRecordColumType.AMOUNT:
                        text = records.get(i).getAmount();
                        break;
                    case ReportRecordColumType.GHA:
                        text = records.get(i).getGha();
                        break;
                    case ReportRecordColumType.REMARK:
                        text = records.get(i).getRemark();
                        break;
                }

                cellA.setCellValue(new HSSFRichTextString(text));
            }
        }
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

    public interface ExportListener {
        void onStart();

        void onCompleted(String filePath);

        void onError(Exception e);
    }

}
