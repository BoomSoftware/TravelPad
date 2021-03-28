package com.example.travelpad.data;

import com.example.travelpad.models.Travel;

import java.util.ArrayList;
import java.util.List;

public class TravelDAO {
    private static TravelDAO instance;
    private List<Travel> travels;

    private TravelDAO(){
        travels = new ArrayList<>();
    }

    public static TravelDAO getInstance(){
        if(instance == null) {
            instance = new TravelDAO();
        }
        return instance;
    }

    public int createNewTravel(Travel travel){
        travel.setId(2);
        travels.add(travel);
        return travel.getId();
    }

    public void removeTravel(int id){
        for(int i = 0; i < travels.size(); i++){
            if(travels.get(i).getId() == id){
                travels.remove(travels.get(i));
                return;
            }
        }
    }

    public void editTravelInfo(Travel editedTravel){
        for(Travel travel: travels){
            if(travel.getId() == editedTravel.getId()){
                travel = editedTravel;
                return;
            }
        }
    }
}
