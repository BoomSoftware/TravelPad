package com.example.travelpad.views.travel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
import com.example.travelpad.models.TransportationDirections;
import com.example.travelpad.viewmodels.travel.MainTravelPageViewModel;

import org.w3c.dom.Text;

public class TravelMainPage extends Fragment {

    private MainTravelPageViewModel viewModel;

    private TextView travelName;
    private TextView travelDate;
    private Button virtualBagButton;
    private Button hotelButton;
    private Button transportFrom;
    private Button transportTo;

    private int travelID;
    private View view;

    public TravelMainPage() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_travel_main_page, container, false);
        viewModel = new ViewModelProvider(this).get(MainTravelPageViewModel.class);
        travelID = ((TravelActivity)getActivity()).getTravelID();
        prepareUI();
        loadValues();
        prepareOnClickEvents();
        return view;
    }

    private void prepareUI(){
        travelName = view.findViewById(R.id.text_travel_title);
        travelDate = view.findViewById(R.id.text_travel_subtitle_date);
        virtualBagButton = view.findViewById(R.id.button_travel_virtual_bag);
        hotelButton = view.findViewById(R.id.button_travel_hotels);
        transportFrom = view.findViewById(R.id.button_travel_transports_from);
        transportTo = view.findViewById(R.id.button_travel_transports_to);
    }

    private void loadValues() {
        viewModel.getTravelByID(travelID).observe(getViewLifecycleOwner(), travel -> {
            travelName.setText(travel.getName());
            String date = travel.getStartDate() + " - " + travel.getEndDate();
            travelDate.setText(date);
        });
    }

    private void prepareOnClickEvents(){
        virtualBagButton.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.action_goToVirtualBag));
        transportTo.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("direction", TransportationDirections.TO.toString());
            Navigation.findNavController(view).navigate(R.id.action_goToTransportation, bundle);
        });
        transportFrom.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("direction", TransportationDirections.FROM.toString());
            Navigation.findNavController(view).navigate(R.id.action_goToTransportation, bundle);
        });
    }


}