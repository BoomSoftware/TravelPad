package com.example.travelpad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.example.travelpad.models.TransportationDirections;
import com.example.travelpad.models.Travel;
import com.example.travelpad.viewmodels.TravelActivityViewModel;
import com.example.travelpad.viewmodels.travel.TransportationViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class TravelActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private int travelID;
    private TravelActivityViewModel viewModel;
    private NavigationView navigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        viewModel = new ViewModelProvider(this).get(TravelActivityViewModel.class);
        travelID = getIntent().getIntExtra("TRAVEL_ID", -1);
        prepareToolbar();
        setNavigationViewListener();
        setNavigationHeader();
        checkIfSignedIn();
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                setNavigationHeader();
            } else{
                startActivity(new Intent(this, MainActivity.class));
            }
        });
    }

    private void prepareToolbar() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_fragment_main);
        navController = navHostFragment.getNavController();
        drawerLayout = findViewById(R.id.drawer_layout);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }

    private void setNavigationViewListener() {
        navigationView = findViewById(R.id.nav_view_travel);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setNavigationHeader(){
        View navHeader = navigationView.getHeaderView(0);
        ImageView avatar = navHeader.findViewById(R.id.img_nav_header);
        TextView userName = navHeader.findViewById(R.id.text_nav_header_name);

        viewModel.getCurrentUser().observe(this, user -> {
            if(user != null){
                userName.setText(user.getDisplayName());
                Glide.with(this).load(user.getPhotoUrl()).into(avatar);
            }

        });
    }

    public int getTravelID() {
        return travelID;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_logout:
                viewModel.signOut();
                break;
            case R.id.nav_item_home_page:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.nav_item_hotel:
                break;
            case R.id.nav_item_virtual_bag:
                navController.navigate(R.id.virtualBagFragment);
                drawerLayout.close();
                break;
            case R.id.nav_item_transportation_to:
                Bundle bundleTo = new Bundle();
                bundleTo.putString("direction", TransportationDirections.TO.toString());
                navController.navigate(R.id.travelTransportationFragment, bundleTo);
                drawerLayout.close();
                break;
            case R.id.nav_item_transportation_from:
                Bundle bundleFrom = new Bundle();
                bundleFrom.putString("direction", TransportationDirections.FROM.toString());
                navController.navigate(R.id.travelTransportationFragment, bundleFrom);
                drawerLayout.close();
                break;
        }
        return true;
    }
}
