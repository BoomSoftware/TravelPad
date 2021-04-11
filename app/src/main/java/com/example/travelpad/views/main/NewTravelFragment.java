package com.example.travelpad.views.main;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import com.example.travelpad.R;
import com.example.travelpad.models.Travel;
import com.example.travelpad.viewmodels.NewTravelViewModel;

public class NewTravelFragment extends Fragment {

    private EditText nameEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private Button confirmButton;

    private DatePickerDialog startDatePickerDialog;
    private DatePickerDialog endDatePickerDialog;

    private NewTravelViewModel viewModel;
    private Calendar calendar;
    private String startDate;
    private String endDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_new_travel, container, false);
        viewModel = new ViewModelProvider(this).get(NewTravelViewModel.class);
        prepareUI(view);
        prepareOnClickEvents(view);
        return view;
    }

    private void prepareUI(View view) {
        calendar = Calendar.getInstance();

        confirmButton = view.findViewById(R.id.button_createTravelCreate);
        endDateEditText = view.findViewById(R.id.edit_createTravelEndDate);
        startDateEditText = view.findViewById(R.id.edit_createTravelStartDate);
        nameEditText = view.findViewById(R.id.edit_createTravelName);
    }

    private void prepareOnClickEvents(View view){
        startDateEditText.setOnClickListener(v -> {
            startDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = dayOfMonth + "/" + month + "/" + year;
                    startDate = date;
                    startDateEditText.setText(date);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            startDatePickerDialog.show();
        });

        endDateEditText.setOnClickListener(v -> {
            endDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = dayOfMonth + "/" + month + "/" + year;
                    endDate = date;
                    endDateEditText.setText(date);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            endDatePickerDialog.show();
        });

        confirmButton.setOnClickListener(v -> {
            if(nameEditText.getText() == null || nameEditText.getText().toString().equals("")){
                return;
            }

            if(startDate == null || startDate.equals("") || endDate == null || endDate.equals("")){
                return;
            }

            viewModel.createNewTravel(new Travel(nameEditText.getText().toString(), startDate, endDate));
        });
    }
}