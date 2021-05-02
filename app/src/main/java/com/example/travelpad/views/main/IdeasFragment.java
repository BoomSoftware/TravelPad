package com.example.travelpad.views.main;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.travelpad.R;
import com.example.travelpad.models.IdeaResponse;
import com.example.travelpad.viewmodels.main.IdeaViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class IdeasFragment extends Fragment implements OnMapReadyCallback {

    private IdeaViewModel viewModel;
    private TextView name;
    private TextView address;
    private GoogleMap googleMap;
    private RatingBar ratingBar;
    private ImageView backgroundImg;
    private IdeaResponse.Result randomPlace;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(IdeaViewModel.class);
        view = inflater.inflate(R.layout.fragment_ideas, container, false);
        prepareView();
        loadRandomPlace();
        getRandomPlace();
        return view;
    }

    private void prepareView(){
        name = view.findViewById(R.id.text_random_place_name);
        ratingBar = view.findViewById(R.id.rating_random_place);
        address = view.findViewById(R.id.text_random_place_address);
        backgroundImg = view.findViewById(R.id.img_random_place);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void loadRandomPlace(){
        viewModel.searchForRandomPlace("museum", getResources().getString(R.string.place_api_secret));
    }

    private void getRandomPlace(){
        viewModel.getRandomPlace().observe(getViewLifecycleOwner(), place -> {
            randomPlace = place;
            name.setText(place.getName());
            ratingBar.setRating((float)place.getRating());
            address.setText(place.getFormatted_address());

            if(place.getPhotos() != null && place.getPhotos()[0] != null){
                String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+ place.getPhotos()[0].getPhoto_reference() +"&key=" + getResources().getString(R.string.place_api_secret);
                Glide.with(view).load(url).into(backgroundImg);
            }

            LatLng location = new LatLng(randomPlace.getGeometry().getLocation().getLat(), randomPlace.getGeometry().getLocation().getLng());
            if(googleMap != null){
                googleMap.addMarker(new MarkerOptions()
                        .position(location)
                        .title(randomPlace.getName()));
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 15);
                googleMap.animateCamera(cameraUpdate);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

    }
}