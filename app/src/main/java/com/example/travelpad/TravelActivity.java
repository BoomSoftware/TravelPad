package com.example.travelpad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.travelpad.viewmodels.travel.TransportationViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class TravelActivity extends AppCompatActivity {

    private int travelID;
    private TransportationViewModel viewModel;
    private int transportationID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        travelID = getIntent().getIntExtra("TRAVEL_ID", -1);
        prepareToolbar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK){
                    Uri selectedFile = data.getData();
                    viewModel.updateTicketPath(transportationID, selectedFile.toString());
                }
        }
    }

    public void setTransportationID(int id){
        transportationID = id;
    }

    public void setViewModel(TransportationViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private void prepareToolbar() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_fragment_main);
        NavController navController = navHostFragment.getNavController();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }

    public int getTravelID() {
        return travelID;
    }
}
