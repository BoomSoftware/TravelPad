package com.example.travelpad.views.travel;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

public class HotelListFragment extends Fragment implements HotelAdapter.OnReservationOptionsListener {

    private View view;
    private ProgressBar progressBar;
    private HotelListViewModel viewModel;
    private HotelAdapter adapter;
    private RecyclerView hotelList;
    private TextView emptyHotel;
    private int travelID;
    private int selectedHotelId;
    private ActivityResultLauncher<Intent> reservationActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hotel_list, container, false);
        travelID = ((TravelActivity) getActivity()).getTravelID();
        viewModel = new ViewModelProvider(this).get(HotelListViewModel.class);
        prepareUI();
        loadData();
        registerOnActivityResultListener();
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

    private void registerOnActivityResultListener(){
        reservationActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        viewModel.updateReservationPath(selectedHotelId, data.getData().toString());
                        getActivity().getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                });
    }

    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        adapter = new HotelAdapter(PreferenceManager.getDefaultSharedPreferences(getActivity()), getString(R.string.place_api_secret), this);
        hotelList = view.findViewById(R.id.recycler_hotel_list);
        hotelList.hasFixedSize();
        hotelList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        hotelList.setAdapter(adapter);

        viewModel.getHotelsForTravel(travelID).observe(getViewLifecycleOwner(), hotels -> {
            if (hotels.isEmpty()) {
                progressBar.setVisibility(View.GONE);
                emptyHotel.setVisibility(View.VISIBLE);
                hotelList.setVisibility(View.GONE);
                return;
            }
            adapter.setHotels(hotels);
            emptyHotel.setVisibility(View.GONE);
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

    @Override
    public void addReservationEvent(int hotelId) {
        Intent chooseFile = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        chooseFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        chooseFile.setType("*/*");

        selectedHotelId = hotelId;
        reservationActivity.launch(chooseFile);
    }

    @Override
    public void viewReservationEvent(String reservationPath) {
            if(reservationPath != null && !reservationPath.equals("")){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri uri = Uri.parse(reservationPath);
                intent.setData(uri);
                startActivity(intent);
            }
    }

    @Override
    public void openPhoneNoEvent(String phoneNo) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNo));
        startActivity(intent);
    }
}