package com.example.travelpad.views.travel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
import com.example.travelpad.models.Transportation;
import com.example.travelpad.models.TransportationTypes;
import com.example.travelpad.viewmodels.travel.TransportationViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class AddTransportationFragment extends Fragment {

    private View view;
    private TransportationViewModel viewModel;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private TimePickerDialog durationPickerDialog;
    private Calendar calendar;
    private String date;

    private int hours = -1;
    private int minutes = -1;
    private int durationHours = -1;
    private int durationMinutes = -1;

    private Spinner transportationTypeSpinner;
    private EditText startingPointEditText;
    private EditText destinationPointEditText;
    private EditText priceEditText;
    private EditText durationEditText;
    private TextView dateTextView;
    private TextView timeTextView;
    private ImageView dateImageView;
    private ImageView timeImageView;
    private Button addNewTransportationButton;

    private String direction;
    private int travelID;
    private List<String> types;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        direction = getArguments().getString("direction", "none");
        view =  inflater.inflate(R.layout.fragment_add_transportation, container, false);
        travelID = ((TravelActivity)getActivity()).getTravelID();
        viewModel = new ViewModelProvider(this).get(TransportationViewModel.class);
        prepareUI();
        prepareOnClickEvents();
        return view;
    }

    private void prepareUI(){
        calendar = Calendar.getInstance();
        types = new ArrayList<>();

        transportationTypeSpinner = view.findViewById(R.id.spinner_transportation_type);
        startingPointEditText = view.findViewById(R.id.edit_add_transportation_start_point);
        destinationPointEditText = view.findViewById(R.id.edit_add_transportation_dest_point);
        priceEditText = view.findViewById(R.id.edit_add_transportation_price);
        durationEditText = view.findViewById(R.id.edit_add_transportation_duration);
        dateImageView = view.findViewById(R.id.img_add_transportation_calendar);
        timeImageView = view.findViewById(R.id.img_add_transportation_clock);
        addNewTransportationButton = view.findViewById(R.id.button_add_transportation_confrim);
        dateTextView = view.findViewById(R.id.text_add_transportation_date);
        timeTextView = view.findViewById(R.id.text_add_transportation_time);
    }

    private void prepareOnClickEvents() {

        TransportationTypes[] typesTemp = TransportationTypes.values();
        for(int i = 0 ; i < typesTemp.length - 1; i++){
            types.add(typesTemp[i].toString());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(view.getContext(), R.layout.row_spinner_type_item, types);
        transportationTypeSpinner.setAdapter(spinnerAdapter);

        dateImageView.setOnClickListener(v-> {
            datePickerDialog = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
                String date = dayOfMonth + "/" + month + "/" + year;
                this.date = date;
                dateTextView.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        timeImageView.setOnClickListener(v -> {
            timePickerDialog = new TimePickerDialog(view.getContext(), (view, hourOfDay, minute) -> {
                String time = hourOfDay + " : " + minute;
                hours = hourOfDay;
                minutes = minute;
                timeTextView.setText(time);
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timePickerDialog.show();
        });

        durationEditText.setOnClickListener(v->{
            durationPickerDialog = new TimePickerDialog(view.getContext(), (view, hourOfDay, minute) -> {
                String time = hourOfDay + " : " + minute;
                durationHours = hourOfDay;
                durationMinutes = minute;
                durationEditText.setText(time);
            }, 0, 0, true);
            durationPickerDialog.show();
        });



        addNewTransportationButton.setOnClickListener(v-> {
            if(transportationTypeSpinner.getSelectedItem() == null || types.get(transportationTypeSpinner.getSelectedItemPosition()).equals("")){
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_type), Toast.LENGTH_SHORT, true).show();
                return;
            }
            if(startingPointEditText == null || startingPointEditText.getText().toString().equals("")){
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_starting_point), Toast.LENGTH_SHORT, true).show();
                return;
            }
            if(destinationPointEditText == null || destinationPointEditText.getText().toString().equals("")){
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_destination_point), Toast.LENGTH_SHORT, true).show();
                return;
            }
            if(date == null || date.equals("")){
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_date), Toast.LENGTH_SHORT, true).show();
                return;
            }
            if(hours == -1 || minutes == -1){
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_time), Toast.LENGTH_SHORT, true).show();
                return;
            }
            if(priceEditText.getText().toString().equals("") || Double.parseDouble(priceEditText.getText().toString()) < 0){
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_price), Toast.LENGTH_SHORT, true).show();
                return;
            }
            if(durationEditText.getText().toString().equals("")){
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_duration), Toast.LENGTH_SHORT, true).show();
                return;
            }
            Transportation transportation = new Transportation(
                    travelID,
                    durationHours,
                    durationMinutes,
                    Double.parseDouble(priceEditText.getText().toString()),
                    destinationPointEditText.getText().toString(),
                    startingPointEditText.getText().toString(),
                    date,
                    hours,
                    minutes,
                    direction,
                    types.get(transportationTypeSpinner.getSelectedItemPosition()));
            viewModel.addTransportationToTravel(transportation);
            Toasty.success(view.getContext(), view.getContext().getString(R.string.transport_creation_success), Toast.LENGTH_SHORT, true).show();
        });
    }
}