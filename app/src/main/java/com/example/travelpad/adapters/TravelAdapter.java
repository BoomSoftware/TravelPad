package com.example.travelpad.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travelpad.R;
import com.example.travelpad.models.Travel;
import java.util.ArrayList;
import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder> {
    private List<Travel> travels;

    public TravelAdapter(){
        travels = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_travel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Travel currentTravel = travels.get(position);

        //test
        //open travel activity and pass travel id
        holder.itemView.setOnClickListener(v ->{
            Toast.makeText(holder.itemView.getContext(), String.valueOf(currentTravel.getId()), Toast.LENGTH_LONG).show();
        });
        holder.travelName.setText(currentTravel.getName());
    }

    @Override
    public int getItemCount() {
        return travels.size();
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
        //maybe replace later
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView travelName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            travelName = itemView.findViewById(R.id.text_travel_list_name);
        }
    }
}
