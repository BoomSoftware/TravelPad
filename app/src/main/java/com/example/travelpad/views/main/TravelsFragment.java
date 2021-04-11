package com.example.travelpad.views.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.travelpad.R;
import com.example.travelpad.adapters.TravelAdapter;
import com.example.travelpad.models.Travel;
import com.example.travelpad.viewmodels.TravelListViewModel;

public class TravelsFragment extends Fragment {

    private TravelListViewModel viewModel;
    private TravelAdapter adapter;

    private RecyclerView travelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travels, container, false);
        viewModel = new ViewModelProvider(this).get(TravelListViewModel.class);
        prepareUI(view);
        return view;
    }

    private void prepareUI(View view){
        travelList = view.findViewById(R.id.recycler_travel_list_travels);
        travelList.hasFixedSize();
        travelList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new TravelAdapter();
        travelList.setAdapter(adapter);

        viewModel.getTravels().observe(getViewLifecycleOwner(), travels -> {
            adapter.setTravels(travels);
        });

    }
}