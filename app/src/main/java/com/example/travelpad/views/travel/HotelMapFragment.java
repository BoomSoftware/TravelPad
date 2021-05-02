package com.example.travelpad.views.travel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelpad.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HotelMapFragment extends Fragment implements OnMapReadyCallback {

    private String hotelName;
    private float lat;
    private float lng;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotel_map, container, false);
        hotelName = getArguments().getString("hotel_name", "none");
        lat = getArguments().getFloat("lat", 0);
        lng = getArguments().getFloat("lng", 0);
        prepareUI();
        return view;
    }

    private void prepareUI(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.hotel_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(lat, lng);
        if(googleMap != null){
            googleMap.addMarker(new MarkerOptions()
                    .position(location)
                    .title(hotelName));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 15);
            googleMap.animateCamera(cameraUpdate);
        }
    }
}