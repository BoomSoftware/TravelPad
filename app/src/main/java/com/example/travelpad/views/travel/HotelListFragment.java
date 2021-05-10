package com.example.travelpad.views.travel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
import com.example.travelpad.adapters.HotelAdapter;
import com.example.travelpad.viewmodels.travel.HotelListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.dmoral.toasty.Toasty;

public class HotelListFragment extends Fragment {

    private View view;
    private ProgressBar progressBar;
    private HotelListViewModel viewModel;
    private HotelAdapter adapter;
    private RecyclerView hotelList;
    private TextView emptyHotel;
    private int travelID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hotel_list, container, false);
        travelID = ((TravelActivity) getActivity()).getTravelID();
        viewModel = new ViewModelProvider(this).get(HotelListViewModel.class);
        prepareUI();
        loadData();
        return view;
    }

    private void prepareUI() {
        FloatingActionButton addButton = view.findViewById(R.id.button_add_hotel);
        emptyHotel = view.findViewById(R.id.empty_hotel);
        progressBar = view.findViewById(R.id.progress_hotel_list);
        addButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_goToAddHotel);
        });
    }

    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        adapter = new HotelAdapter(PreferenceManager.getDefaultSharedPreferences(getActivity()), getString(R.string.place_api_secret), getActivity());
        hotelList = view.findViewById(R.id.recycler_hotel_list);
        hotelList.hasFixedSize();
        hotelList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        hotelList.setAdapter(adapter);

        viewModel.getHotelsForTravel(travelID).observe(getViewLifecycleOwner(), hotels -> {
            if (hotels.isEmpty()) {
                emptyHotel.setVisibility(View.VISIBLE);
                hotelList.setVisibility(View.GONE);
                return;
            }
            emptyHotel.setVisibility(View.GONE);
            hotelList.setVisibility(View.VISIBLE);
            adapter.setHotels(hotels);
            progressBar.setVisibility(View.GONE);
        });
        setSwipeEvent();
    }

    private void setSwipeEvent(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteHotel(adapter.getItemAt(viewHolder.getAbsoluteAdapterPosition()).getId());
                Toasty.success(view.getContext(), view.getContext().getString(R.string.remove_item), Toast.LENGTH_SHORT, true).show();
            }
        }).attachToRecyclerView(hotelList);
    }
}