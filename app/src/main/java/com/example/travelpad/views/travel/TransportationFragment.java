package com.example.travelpad.views.travel;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
import com.example.travelpad.adapters.TransportationAdapter;
import com.example.travelpad.models.TransportationDirections;
import com.example.travelpad.viewmodels.travel.TransportationViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TransportationFragment extends Fragment {

    private TransportationViewModel viewModel;
    private TransportationAdapter adapter;
    private RecyclerView transportationList;
    private String direction;
    private int travelID;
    private View view;

    private FloatingActionButton addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_travel_transportation, container, false);
        direction = getArguments().getString("direction", "none");
        travelID = ((TravelActivity)getActivity()).getTravelID();
        viewModel = new ViewModelProvider(this).get(TransportationViewModel.class);
        ((TravelActivity)getActivity()).setViewModel(viewModel);
        prepareUI();
        loadData();
        return view;
    }

    private void prepareUI(){
        addButton = view.findViewById(R.id.button_add_transport);
        addButton.setOnClickListener(v-> {
            Bundle bundle = new Bundle();
            bundle.putString("direction", direction);
            Navigation.findNavController(view).navigate(R.id.action_goToAddTransportation, bundle);
        });
    }

    private void loadData(){
        adapter = new TransportationAdapter(getActivity());
        transportationList = view.findViewById(R.id.recycler_transportation_list);
        transportationList.hasFixedSize();
        transportationList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        transportationList.setAdapter(adapter);
        if(direction.equals(TransportationDirections.TO.toString())){
            viewModel.getTransportationForTravel(travelID, TransportationDirections.TO).observe(getViewLifecycleOwner(), transportation -> {
                adapter.setTransportationList(transportation);
            });
        }
        if(direction.equals(TransportationDirections.FROM.toString())){
            viewModel.getTransportationForTravel(travelID, TransportationDirections.FROM).observe(getViewLifecycleOwner(), transportation -> {
                adapter.setTransportationList(transportation);
            });
        }

    }


}