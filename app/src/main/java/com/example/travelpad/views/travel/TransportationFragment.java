package com.example.travelpad.views.travel;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
import com.example.travelpad.adapters.TransportationAdapter;
import com.example.travelpad.models.TransportationDirections;
import com.example.travelpad.viewmodels.travel.TransportationViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.dmoral.toasty.Toasty;

public class TransportationFragment extends Fragment implements TransportationAdapter.OnTicketOptionsListener {

    private TransportationViewModel viewModel;
    private TransportationAdapter adapter;
    private RecyclerView transportationList;
    private TextView emptyTransportation;
    private String direction;
    private int travelID;
    private int selectedTransportationId;
    private View view;
    private ActivityResultLauncher<Intent> ticketActivity;

    private FloatingActionButton addButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_travel_transportation, container, false);
        direction = getArguments().getString("direction", "none");
        travelID = ((TravelActivity)getActivity()).getTravelID();
        viewModel = new ViewModelProvider(this).get(TransportationViewModel.class);
        prepareUI();
        loadData();

        registerOnActivityResultListener();
        return view;
    }

    private void prepareUI(){
        addButton = view.findViewById(R.id.button_add_transport);
        emptyTransportation = view.findViewById(R.id.empty_transportation);
        addButton.setOnClickListener(v-> {
            Bundle bundle = new Bundle();
            bundle.putString("direction", direction);
            Navigation.findNavController(view).navigate(R.id.action_goToAddTransportation, bundle);
        });
    }

    private void registerOnActivityResultListener(){
        ticketActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        viewModel.updateTicketPath(selectedTransportationId, data.getData().toString());
                        getActivity().getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                });
    }

    private void loadData(){
        String[] transportTypes = getResources().getStringArray(R.array.transport);
        adapter = new TransportationAdapter(transportTypes, this);
        transportationList = view.findViewById(R.id.recycler_transportation_list);
        transportationList.hasFixedSize();
        transportationList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        transportationList.setAdapter(adapter);
        if(direction.equals(TransportationDirections.TO.toString())){
            viewModel.getTransportationForTravel(travelID, TransportationDirections.TO).observe(getViewLifecycleOwner(), transportation -> {
                if(transportation.isEmpty()){
                    emptyTransportation.setVisibility(View.VISIBLE);
                    transportationList.setVisibility(View.GONE);
                    return;
                }
                emptyTransportation.setVisibility(View.GONE);
                transportationList.setVisibility(View.VISIBLE);
                adapter.setPreferences(PreferenceManager.getDefaultSharedPreferences(getActivity()));
                adapter.setTransportationList(transportation);
            });
        }
        if(direction.equals(TransportationDirections.FROM.toString())){
            viewModel.getTransportationForTravel(travelID, TransportationDirections.FROM).observe(getViewLifecycleOwner(), transportation -> {
                if(transportation.isEmpty()){
                    emptyTransportation.setVisibility(View.VISIBLE);
                    transportationList.setVisibility(View.GONE);
                    return;
                }
                emptyTransportation.setVisibility(View.GONE);
                transportationList.setVisibility(View.VISIBLE);
                adapter.setPreferences(PreferenceManager.getDefaultSharedPreferences(getActivity()));
                adapter.setTransportationList(transportation);
            });
        }
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
                viewModel.deleteTransportationFromTravel(adapter.getItemAt(viewHolder.getAbsoluteAdapterPosition()));
                Toasty.success(view.getContext(), view.getContext().getString(R.string.remove_item), Toast.LENGTH_SHORT, true).show();
            }
        }).attachToRecyclerView(transportationList);
    }


    @Override
    public void addTicketEvent(int transportId) {
        Intent chooseFile = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        chooseFile.setType("*/*");
        chooseFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

        selectedTransportationId = transportId;
        ticketActivity.launch(chooseFile);
    }
    @Override
    public void viewTicketEvent(String ticketPath) {
        if(ticketPath != null && !ticketPath.equals("")){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(ticketPath);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setData(uri);
            startActivity(intent);
        }
    }
}