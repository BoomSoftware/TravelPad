package com.example.travelpad.views.travel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
import com.example.travelpad.models.Hotel;
import com.example.travelpad.models.TransportationDirections;
import com.example.travelpad.viewmodels.travel.MainTravelPageViewModel;

import org.w3c.dom.Text;

public class TravelMainPage extends Fragment {

    private MainTravelPageViewModel viewModel;

    private TextView travelName;
    private TextView currency;
    private TextView travelDate;
    private TextView totalPrice;
    private TextView itemsNotPacked;
    private TextView transports;
    private TextView hotels;
    private Button virtualBagButton;
    private Button hotelButton;
    private Button transportFrom;
    private Button transportTo;
    private double totalPriceNumber;

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
        loadTravelOverview();
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
        hotels = view.findViewById(R.id.text_main_hotels);
        transports = view.findViewById(R.id.text_main_transports);
        totalPrice = view.findViewById(R.id.text_main_total_price);
        itemsNotPacked = view.findViewById(R.id.text_main_items);
        currency = view.findViewById(R.id.text_main_currency);
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
        hotelButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_goToHotel));
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

    private void loadTravelOverview(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        currency.setText(sharedPreferences.getString("pref_currency", ""));

        viewModel.getHotelsWithoutReservation(travelID).observe(getViewLifecycleOwner(), number -> {
            String text = hotels.getText().toString() + " " + number;
            hotels.setText(text);
        });

        viewModel.getTransportationWithoutTicket(travelID).observe(getViewLifecycleOwner(), number -> {
            String text = transports.getText().toString() + " " + number;
            transports.setText(text);
        });

        viewModel.getNotPackedItems(travelID).observe(getViewLifecycleOwner(), number -> {
            String text = itemsNotPacked.getText().toString() + " " + number;
            itemsNotPacked.setText(text);
        });

        viewModel.getTransportTotalPrice(travelID).observe(getViewLifecycleOwner(), number -> {
            if(number != null){
                double temp = Double.parseDouble(totalPrice.getText().toString());
                totalPrice.setText(String.valueOf(temp+= number));
            }
        });

        viewModel.getHotels(travelID).observe(getViewLifecycleOwner(), hotels -> {
            if(!hotels.isEmpty()){
                double hotelPrice = 0;
                for (Hotel hotel : hotels){
                    hotelPrice+= hotel.getPricePerDay() * hotel.getDays();
                }
                double temp = Double.parseDouble(totalPrice.getText().toString());
                totalPrice.setText(String.valueOf(temp+= hotelPrice));
            }
        });
    }


}