package com.example.travelpad.views.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.travelpad.R;

public class MainViewFragment extends Fragment {

    private View view;
    private Button settingsButton;
    private Button newTravelButton;
    private Button travelsButton;
    private Button ideasButton;

    public MainViewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view =  inflater.inflate(R.layout.fragment_main_view, container, false);
        prepareUI(view);
        prepareOnClickEvents(view);
        return view;
    }

    private void prepareUI(View view) {
       settingsButton = view.findViewById(R.id.button_main_settings);
       newTravelButton = view.findViewById(R.id.button_main_new_travel);
       travelsButton = view.findViewById(R.id.button_main_travel_list);
       ideasButton = view.findViewById(R.id.button_main_travel_ideas);
    }

    private void prepareOnClickEvents(View view) {
        settingsButton.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.action_goToSettings));
        ideasButton.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.action_goToIdeas));
        newTravelButton.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.action_goToNewTravel));
        travelsButton.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.action_goToTravels));
    }
}