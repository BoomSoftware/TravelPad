package com.example.travelpad.views.travel;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
import com.example.travelpad.models.Hotel;
import com.example.travelpad.viewmodels.travel.AddHotelViewModel;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import es.dmoral.toasty.Toasty;

public class AddHotelFragment extends Fragment {

    private View view;
    private AddHotelViewModel viewModel;
    private AutocompleteSupportFragment autocompleteFragment;
    private EditText pricePerDayEditText;
    private EditText stayDurationEditText;
    private Button addHotelButton;
    private Place selectedPlace;

    private static final String TAG = "PLACE_API_ERROR";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_hotel, container, false);
        viewModel = new ViewModelProvider(this).get(AddHotelViewModel.class);
        initializePlaceAPI();
        prepareUI();
        prepareAutocomplete();
        prepareOnClickEvents();
        return view;
    }

    private void prepareUI(){
        autocompleteFragment = (AutocompleteSupportFragment) getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        pricePerDayEditText = view.findViewById(R.id.edit_hotel_price_per_day);
        stayDurationEditText = view.findViewById(R.id.edit_stay_duration);
        addHotelButton = view.findViewById(R.id.button_confirm_add_hotel);
    }

    private void prepareOnClickEvents() {
        addHotelButton.setOnClickListener(v -> {
            if(selectedPlace == null) {
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_hotel_place), Toast.LENGTH_SHORT, true).show();
                return;
            }

            if(pricePerDayEditText.getText().toString().equals("") || Double.parseDouble(pricePerDayEditText.getText().toString()) < 0){
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_price_per_day), Toast.LENGTH_SHORT, true).show();
                return;
            }
            if(stayDurationEditText.getText().toString().equals("") || Integer.parseInt(stayDurationEditText.getText().toString()) < 0){
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_stay_duration), Toast.LENGTH_SHORT, true).show();
                return;
            }

            int travelId = ((TravelActivity) getActivity()).getTravelID();
            viewModel.addNewHotel(getResources().getString(R.string.place_api_secret),new Hotel(travelId, Integer.parseInt(stayDurationEditText.getText().toString()), Double.parseDouble(pricePerDayEditText.getText().toString())), selectedPlace.getId());
            Toasty.success(view.getContext(), view.getContext().getString(R.string.added_hotel), Toast.LENGTH_SHORT, true).show();
        });
    }

    private void prepareAutocomplete(){
        styleAutocompleteSearchInput();
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(@NotNull Place place) {
                selectedPlace = place;
            }

            @Override
            public void onError(@NotNull Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    private void styleAutocompleteSearchInput(){
        autocompleteFragment.getView().setBackground(ContextCompat.getDrawable(getContext(),R.drawable.input_only_borders));
        ((EditText)autocompleteFragment.getView().findViewById(R.id.places_autocomplete_search_input)).setHint(null);
        ((EditText)autocompleteFragment.getView().findViewById(R.id.places_autocomplete_search_input)).setTextColor(Color.WHITE);
        ((AppCompatImageButton)autocompleteFragment.getView().findViewById(R.id.places_autocomplete_search_button)).getDrawable().setTint(Color.WHITE);
        ((AppCompatImageButton)autocompleteFragment.getView().findViewById(R.id.places_autocomplete_clear_button)).getDrawable().setTint(Color.WHITE);
    }

    private void initializePlaceAPI(){
        if (!Places.isInitialized()) {
            Places.initialize(getContext(), getResources().getString(R.string.place_api_secret));
        }
    }
}