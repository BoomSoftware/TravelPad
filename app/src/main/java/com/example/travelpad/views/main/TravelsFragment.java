package com.example.travelpad.views.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelpad.R;
import com.example.travelpad.adapters.TravelAdapter;
import com.example.travelpad.viewmodels.main.TravelListViewModel;

import java.util.Collections;

import es.dmoral.toasty.Toasty;

public class TravelsFragment extends Fragment {

    private TravelListViewModel viewModel;
    private View view;
    private TravelAdapter adapter;
    private TextView emptyTravel;

    private RecyclerView travelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_travels, container, false);
        viewModel = new ViewModelProvider(this).get(TravelListViewModel.class);
        prepareUI(view);
        return view;
    }

    private void prepareUI(View view) {
        travelList = view.findViewById(R.id.recycler_travel_list_travels);
        emptyTravel = view.findViewById(R.id.text_empty_travel);
        travelList.hasFixedSize();
        travelList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new TravelAdapter();
        travelList.setAdapter(adapter);

        viewModel.getTravels().observe(getViewLifecycleOwner(), travels -> {
            if (travels.isEmpty()) {
                emptyTravel.setVisibility(View.VISIBLE);
                travelList.setVisibility(View.GONE);
                return;
            }
            emptyTravel.setVisibility(View.GONE);
            travelList.setVisibility(View.VISIBLE);
            Collections.reverse(travels);
            adapter.setTravels(travels);
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
                    viewModel.removeTravel(adapter.getItemAt(viewHolder.getAbsoluteAdapterPosition()));
                    Toasty.success(view.getContext(), view.getContext().getString(R.string.remove_item), Toast.LENGTH_SHORT, true).show();
                }
            }).attachToRecyclerView(travelList);
        }

    }