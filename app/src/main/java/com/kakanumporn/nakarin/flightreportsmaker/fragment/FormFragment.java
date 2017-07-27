package com.kakanumporn.nakarin.flightreportsmaker.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.kakanumporn.nakarin.flightreportsmaker.R;
import com.kakanumporn.nakarin.flightreportsmaker.activity.FormActivity;
import com.kakanumporn.nakarin.flightreportsmaker.model.ReportRecord;
import com.kakanumporn.nakarin.flightreportsmaker.util.MyDate;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class FormFragment extends Fragment implements View.OnClickListener, TextWatcher {

    private static final int MODE_ADD = 0;
    private static final int MODE_EDIT = 1;

    private int mode;

    private ReportRecord record;
    private ArrayAdapter<CharSequence> acAdapter;
    private ArrayAdapter<CharSequence> flightAdapter;
    private ArrayAdapter<CharSequence> depAdapter;
    private ArrayAdapter<CharSequence> delayCodeAdapter;

    private Spinner spinAc;

    private Button btnDepDate;
    private Spinner spinDepFlight;
    private Spinner spinDep;
    private Spinner spinDepDelayCode1;
    private Spinner spinDepDelayCode2;
    private EditText etDepMin1;
    private EditText etDepMin2;
    private EditText etDepTotalMinDelay;
    private EditText etDepAdult;
    private EditText etDepChd;
    private EditText etDepInf;
    private EditText etDepTotal;

    private Button btnTouchDown;
    private Button btnBlockIn;
    private Button btnArrDate;
    private Spinner spinArrFlight;
    private Spinner spinArr;
    private Spinner spinArrDelayCode1;
    private Spinner spinArrDelayCode2;
    private EditText etArrMin1;
    private EditText etArrMin2;
    private EditText etArrTotalMinDelay;
    private EditText etArrAdult;
    private EditText etArrChd;
    private EditText etArrInf;
    private EditText etArrTotal;

    private EditText etBagWeight;
    private EditText etTotalTrafficLoad;
    private EditText etUnderload;
    private EditText etAllowedTrafficLoad;

    private EditText etSpecialMeal;
    private EditText etTotalMeal;

    private EditText etAeroBridge;
    private Button btnStart;
    private Button btnEnd;
    private EditText etGseRq;

    private EditText etInvNo;
    private EditText etRefuelReceipt;
    private EditText etInvFuel;
    private EditText etTemp;
    private EditText etActualDens;
    private EditText etBasicPrice;
    private EditText etFees;
    private EditText etAmount;

    private EditText etGha;
    private EditText etRemark;

    private Button btnSubmit;

    public FormFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static FormFragment newInstance(ReportRecord record) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putParcelable("record", record);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        record = getArguments().getParcelable("record");

        if (record != null) {
            mode = MODE_EDIT;

            setHasOptionsMenu(true);
        } else {
            record = new ReportRecord();
            mode = MODE_ADD;
        }
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        bindView(rootView);
        setupView();
    }

    private void bindView(View rootView) {
        spinAc = rootView.findViewById(R.id.spinAc);

        btnDepDate = rootView.findViewById(R.id.btnDepDate);
        spinDepFlight = rootView.findViewById(R.id.spinDepFlight);
        spinDep = rootView.findViewById(R.id.spinDep);
        spinDepDelayCode1 = rootView.findViewById(R.id.spinDepDelayCode1);
        spinDepDelayCode2 = rootView.findViewById(R.id.spinDepDelayCode2);
        etDepMin1 = rootView.findViewById(R.id.etDepMin1);
        etDepMin2 = rootView.findViewById(R.id.etDepMin2);
        etDepTotalMinDelay = rootView.findViewById(R.id.etDepTotalDelayMin);
        etDepAdult = rootView.findViewById(R.id.etDepAdult);
        etDepChd = rootView.findViewById(R.id.etDepChd);
        etDepInf = rootView.findViewById(R.id.etDepInf);
        etDepTotal = rootView.findViewById(R.id.etDepTotal);

        btnTouchDown = rootView.findViewById(R.id.btnTouchDown);
        btnBlockIn = rootView.findViewById(R.id.btnBlockIn);
        btnArrDate = rootView.findViewById(R.id.btnArrDate);
        spinArrFlight = rootView.findViewById(R.id.spinArrFlight);
        spinArr = rootView.findViewById(R.id.spinArr);
        spinArrDelayCode1 = rootView.findViewById(R.id.spinArrDelayCode1);
        spinArrDelayCode2 = rootView.findViewById(R.id.spinArrDelayCode2);
        etArrMin1 = rootView.findViewById(R.id.etArrMin1);
        etArrMin2 = rootView.findViewById(R.id.etArrMin2);
        etArrTotalMinDelay = rootView.findViewById(R.id.etArrTotalDelayMin);
        etArrAdult = rootView.findViewById(R.id.etArrAdult);
        etArrChd = rootView.findViewById(R.id.etArrChd);
        etArrInf = rootView.findViewById(R.id.etArrInf);
        etArrTotal = rootView.findViewById(R.id.etArrTotal);

        etBagWeight = rootView.findViewById(R.id.etBagWeight);
        etTotalTrafficLoad = rootView.findViewById(R.id.etTotalTrafficLoad);
        etUnderload = rootView.findViewById(R.id.etUnderload);
        etAllowedTrafficLoad = rootView.findViewById(R.id.etAllowedTrafficLoad);

        etSpecialMeal = rootView.findViewById(R.id.etSpecialMeal);
        etTotalMeal = rootView.findViewById(R.id.etTotalMeal);

        etAeroBridge = rootView.findViewById(R.id.etAeroBridge);
        btnStart = rootView.findViewById(R.id.btnStart);
        btnEnd = rootView.findViewById(R.id.btnEnd);
        etGseRq = rootView.findViewById(R.id.etGseRq);

        etInvNo = rootView.findViewById(R.id.etInvNo);
        etRefuelReceipt = rootView.findViewById(R.id.etRefuelReceipt);
        etInvFuel = rootView.findViewById(R.id.etInvFuel);
        etTemp = rootView.findViewById(R.id.etTemp);
        etActualDens = rootView.findViewById(R.id.etActualDensity);
        etBasicPrice = rootView.findViewById(R.id.etBasicPrice);
        etFees = rootView.findViewById(R.id.etFees);
        etAmount = rootView.findViewById(R.id.etAmount);

        etGha = rootView.findViewById(R.id.etGha);
        etRemark = rootView.findViewById(R.id.etRemark);

        btnSubmit = rootView.findViewById(R.id.btnSubmit);
    }

    private void setupView() {
        acAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.aircraft_array, R.layout.spinner_item);
        acAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinAc.setAdapter(acAdapter);

        flightAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.flight_array, R.layout.spinner_item);
        flightAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinDepFlight.setAdapter(flightAdapter);
        spinArrFlight.setAdapter(flightAdapter);

        depAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.dep_array, R.layout.spinner_item);
        depAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinDep.setAdapter(depAdapter);
        spinArr.setAdapter(depAdapter);

        delayCodeAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.delay_code_array, R.layout.spinner_item);
        delayCodeAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinDepDelayCode1.setAdapter(delayCodeAdapter);
        spinDepDelayCode2.setAdapter(delayCodeAdapter);
        spinArrDelayCode1.setAdapter(delayCodeAdapter);
        spinArrDelayCode2.setAdapter(delayCodeAdapter);

        btnDepDate.setOnClickListener(this);
        btnArrDate.setOnClickListener(this);
        btnTouchDown.setOnClickListener(this);
        btnBlockIn.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        etDepMin1.addTextChangedListener(this);
        etDepMin2.addTextChangedListener(this);
        etArrMin1.addTextChangedListener(this);
        etArrMin2.addTextChangedListener(this);
        etTotalTrafficLoad.addTextChangedListener(this);
        etUnderload.addTextChangedListener(this);
        etDepAdult.addTextChangedListener(this);
        etDepChd.addTextChangedListener(this);
        etDepInf.addTextChangedListener(this);
        etArrAdult.addTextChangedListener(this);
        etArrChd.addTextChangedListener(this);
        etArrInf.addTextChangedListener(this);

        if (mode == MODE_EDIT) {
            fillForm();

            btnSubmit.setText("SAVE");
        } else {
            btnSubmit.setText("ADD");
        }
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_form, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_menu_form_delete) {
            FormActivity activity = (FormActivity) getActivity();
            activity.deleteRecord(record);
            return true;
        }
        return false;
    }

    private void fillForm() {
        spinAc.setSelection(acAdapter.getPosition(record.getAc()));

        btnDepDate.setText(filterText(record.getDepDate()));
        spinDepFlight.setSelection(flightAdapter.getPosition(record.getDepFlight()));
        spinDep.setSelection(depAdapter.getPosition(record.getDep()));
        spinDepDelayCode1.setSelection(delayCodeAdapter.getPosition(record.getDepDelayCodeA()));
        spinDepDelayCode2.setSelection(delayCodeAdapter.getPosition(record.getDepDelayCodeB()));
        etDepMin1.setText(filterNumber(record.getDepDelayMinA()));
        etDepMin2.setText(filterNumber(record.getDepDelayMinB()));
        etDepTotalMinDelay.setText(filterNumber(record.getDepDelayTotalMin()));
        etDepAdult.setText(filterNumber(record.getDepAdult()));
        etDepChd.setText(filterNumber(record.getDepChd()));
        etDepInf.setText(filterNumber(record.getDepInf()));
        etDepTotal.setText(filterNumber(record.getDepTotal()));

        btnTouchDown.setText(filterText(record.getTouchDown()));
        btnBlockIn.setText(filterText(record.getBlockIn()));
        btnArrDate.setText(filterText(record.getArrDate()));
        spinArrFlight.setSelection(flightAdapter.getPosition(record.getArrFlight()));
        spinArr.setSelection(depAdapter.getPosition(record.getArr()));
        spinArrDelayCode1.setSelection(delayCodeAdapter.getPosition(record.getArrDelayCodeA()));
        spinArrDelayCode2.setSelection(delayCodeAdapter.getPosition(record.getArrDelayCodeB()));
        etArrMin1.setText(filterNumber(record.getArrDelayMinA()));
        etArrMin2.setText(filterNumber(record.getArrDelayMinB()));
        etArrTotalMinDelay.setText(filterNumber(record.getArrDelayTotalMin()));
        etArrAdult.setText(filterNumber(record.getArrAdult()));
        etArrChd.setText(filterNumber(record.getArrChd()));
        etArrInf.setText(filterNumber(record.getArrInf()));
        etArrTotal.setText(filterNumber(record.getArrTotal()));

        etBagWeight.setText(filterNumber(record.getBagWeight()));
        etTotalTrafficLoad.setText(filterNumber(record.getTotalTrafficLoad()));
        etUnderload.setText(filterNumber(record.getUnderloadBeforeLMC()));
        etAllowedTrafficLoad.setText(filterNumber(record.getAllowedTrafficLoad()));

        etSpecialMeal.setText(record.getSpecialMeal());
        etTotalMeal.setText(filterNumber(record.getTotalMeal()));

        etAeroBridge.setText(record.getAeroBridge());
        btnStart.setText(filterText(record.getStart()));
        btnStart.setText(filterText(record.getEnd()));
        etGseRq.setText(record.getGseRq());

        etInvNo.setText(record.getInvNo());
        etRefuelReceipt.setText(filterNumber(record.getRefuelReceipt()));
        etInvFuel.setText(filterNumber(record.getInvFuel()));
        etTemp.setText(filterNumber(record.getTemp()));
        etActualDens.setText(filterNumber(record.getActualDensity()));
        etBasicPrice.setText(record.getBasicPrice());
        etFees.setText(record.getFees());
        etAmount.setText(record.getAmount());

    }

    private void fillRecord() {
        // aircraft
        record.setAc(acAdapter.getItem(spinAc.getSelectedItemPosition()).toString());

        // departure info
        record.setDepDate(filterText(btnDepDate.getText().toString()));
        record.setDepFlight(flightAdapter.getItem(spinDepFlight.getSelectedItemPosition()).toString());
        record.setDep(depAdapter.getItem(spinDep.getSelectedItemPosition()).toString());
        record.setDepDelayCodeA(delayCodeAdapter.getItem(spinDepDelayCode1.getSelectedItemPosition()).toString());
        record.setDepDelayMinA(getIntVal(etDepMin1.getText().toString()));
        record.setDepDelayCodeB(delayCodeAdapter.getItem(spinDepDelayCode2.getSelectedItemPosition()).toString());
        record.setDepDelayMinB(getIntVal(etDepMin2.getText().toString()));
        record.setDepDelayTotalMin(getIntVal(etDepTotalMinDelay.getText().toString()));
        record.setDepAdult(getIntVal(etDepAdult.getText().toString()));
        record.setDepChd(getIntVal(etDepChd.getText().toString()));
        record.setDepInf(getIntVal(etDepInf.getText().toString()));
        record.setDepTotal(getIntVal(etDepTotal.getText().toString()));

        // arrive info
        record.setTouchDown(filterText(btnTouchDown.getText().toString()));
        record.setBlockIn(filterText(btnBlockIn.getText().toString()));

        record.setArrDate(filterText(btnArrDate.getText().toString()));
        record.setArrFlight(flightAdapter.getItem(spinArrFlight.getSelectedItemPosition()).toString());
        record.setArr(depAdapter.getItem(spinArr.getSelectedItemPosition()).toString());
        record.setArrDelayCodeA(delayCodeAdapter.getItem(spinArrDelayCode1.getSelectedItemPosition()).toString());
        record.setArrDelayMinA(getIntVal(etArrMin1.getText().toString()));
        record.setArrDelayCodeB(delayCodeAdapter.getItem(spinArrDelayCode2.getSelectedItemPosition()).toString());
        record.setArrDelayMinB(getIntVal(etArrMin2.getText().toString()));
        record.setArrDelayTotalMin(getIntVal(etArrTotalMinDelay.getText().toString()));
        record.setArrAdult(getIntVal(etArrAdult.getText().toString()));
        record.setArrChd(getIntVal(etArrChd.getText().toString()));
        record.setArrInf(getIntVal(etArrInf.getText().toString()));
        record.setArrTotal(getIntVal(etArrTotal.getText().toString()));

        // load
        record.setBagWeight(getIntVal(etBagWeight.getText().toString()));
        record.setTotalTrafficLoad(getIntVal(etTotalTrafficLoad.getText().toString()));
        record.setUnderloadBeforeLMC(getIntVal(etUnderload.getText().toString()));
        record.setAllowedTrafficLoad(getIntVal(etAllowedTrafficLoad.getText().toString()));

        // meal
        record.setSpecialMeal(etSpecialMeal.getText().toString());
        record.setTotalMeal(getIntVal(etTotalMeal.getText().toString()));

        // bridge
        record.setAeroBridge(etAeroBridge.getText().toString());
        record.setStart(filterText(btnStart.getText().toString()));
        record.setEnd(filterText(btnEnd.getText().toString()));
        record.setGseRq(etGseRq.getText().toString());

        // fuel
        record.setInvNo(etInvNo.getText().toString());
        record.setRefuelReceipt(getIntVal(etRefuelReceipt.getText().toString()));
        record.setInvFuel(getIntVal(etInvFuel.getText().toString()));
        record.setTemp(getFloatVal(etTemp.getText().toString()));
        record.setActualDensity(getFloatVal(etActualDens.getText().toString()));
        record.setBasicPrice(etBasicPrice.getText().toString());
        record.setFees(etFees.getText().toString());
        record.setAmount(etAmount.getText().toString());

        // etc.
        record.setGha(etGha.getText().toString());
        record.setRemark(etRemark.getText().toString());
    }

    public void finish() {
        FormActivity activity = (FormActivity) getActivity();
        fillRecord();
        activity.sendRecord(record);
    }

    private String filterText(String text) {
        if (text.equals("Select")) {
            return "";
        } else if (text.equals("")) {
            return "Select";
        }
        return text;
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

    private int getIntVal(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private float getFloatVal(String text) {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                finish();
                break;
            case R.id.btnDepDate:
            case R.id.btnArrDate:
                showDatePikerDialog(view);
                break;
            case R.id.btnTouchDown:
            case R.id.btnBlockIn:
            case R.id.btnStart:
            case R.id.btnEnd:
                showTimePickerDialog(view);
                break;
        }
    }

    public void showDatePikerDialog(View view) {
        int day, month, year;
        final Button button = (Button) view;

        String[] date = button.getText().toString().split("/");
        try {
            day = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]) - 1;
            year = Integer.parseInt(date[2]);
        } catch (NumberFormatException e) {
            day = MyDate.getDay();
            month = MyDate.getMonth();
            year = MyDate.getYear();
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                button.setText(String.format("%d/%d/%d", day, month + 1, year));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void showTimePickerDialog(View view) {
        int hour, min;

        final Button button = (Button) view;

        String[] time = button.getText().toString().split(":");
        try {
            hour = Integer.parseInt(time[0]);
            min = Integer.parseInt(time[1]);
        } catch (NumberFormatException e) {
            hour = 0;
            min = 0;
        }

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                button.setText(hour + ":" + min);
            }
        }, hour, min, true);
        timePickerDialog.show();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        record.setDepDelayMinA(getIntVal(etDepMin1.getText().toString()));
        record.setDepDelayMinB(getIntVal(etDepMin2.getText().toString()));
        etDepTotalMinDelay.setText(filterNumber(record.getDepDelayTotalMin()));

        record.setArrDelayMinA(getIntVal(etArrMin1.getText().toString()));
        record.setArrDelayMinB(getIntVal(etArrMin2.getText().toString()));
        etArrTotalMinDelay.setText(filterNumber(record.getArrDelayTotalMin()));

        record.setTotalTrafficLoad(getIntVal(etTotalTrafficLoad.getText().toString()));
        record.setUnderloadBeforeLMC(getIntVal(etUnderload.getText().toString()));
        etAllowedTrafficLoad.setText(filterNumber(record.getAllowedTrafficLoad()));

        record.setDepAdult(getIntVal(etDepAdult.getText().toString()));
        record.setDepChd(getIntVal(etDepChd.getText().toString()));
        record.setDepInf(getIntVal(etDepInf.getText().toString()));
        etDepTotal.setText(filterNumber(record.getDepTotal()));

        record.setArrAdult(getIntVal(etArrAdult.getText().toString()));
        record.setArrChd(getIntVal(etArrChd.getText().toString()));
        record.setArrInf(getIntVal(etArrInf.getText().toString()));
        etArrTotal.setText(filterNumber(record.getArrTotal()));
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
