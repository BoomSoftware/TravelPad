package com.example.travelpad;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.example.travelpad.viewmodels.HomeAppActivityViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class HomeAppActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private HomeAppActivityViewModel homeAppActivityViewModel;
    private NavigationView navigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAppActivityViewModel = new ViewModelProvider(this).get(HomeAppActivityViewModel.class);
        setContentView(R.layout.activity_home_app);
        prepareToolbar();
        setNavigationViewListener();
        checkIfSignedIn();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_logout: {
                homeAppActivityViewModel.signOut();
                break;
            }
            case R.id.nav_item_home_page: {
                navController.navigate(R.id.mainViewFragment);
                drawerLayout.close();
                break;
            }
            case R.id.nav_item_add_travel: {
                navController.navigate(R.id.newTravelFragment);
                drawerLayout.close();
                break;
            }
            case R.id.nav_item_ideas: {
                navController.navigate(R.id.ideasFragment);
                drawerLayout.close();
                break;
            }
            case R.id.nav_item_travel_list: {
                navController.navigate(R.id.travelsFragment);
                drawerLayout.close();
                break;
            }
        }
        return true;
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

    private void checkIfSignedIn() {
        homeAppActivityViewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                setNavigationHeader();
            } else{
                startActivity(new Intent(this, MainActivity.class));
            }
        });
    }

    private void setNavigationViewListener() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setNavigationHeader(){
        View navHeader = navigationView.getHeaderView(0);
        ImageView avatar = navHeader.findViewById(R.id.img_nav_header);
        TextView userName = navHeader.findViewById(R.id.text_nav_header_name);
        TextView userEmail = navHeader.findViewById(R.id.text_nav_header_email);

        homeAppActivityViewModel.getCurrentUser().observe(this, user -> {
            if(user != null){
                userName.setText(user.getDisplayName());
                userEmail.setText(user.getEmail());
                Glide.with(this).load(user.getPhotoUrl()).into(avatar);
            }
        });
    }
}