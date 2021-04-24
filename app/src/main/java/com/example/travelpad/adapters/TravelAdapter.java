package com.example.travelpad.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
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

        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(holder.itemView.getContext(), TravelActivity.class);
            intent.putExtra("TRAVEL_ID", currentTravel.getId());
            holder.itemView.getContext().startActivity(intent);
        });
        holder.travelName.setText(currentTravel.getName());
        String date = currentTravel.getStartDate() + " - " + currentTravel.getEndDate();
        holder.travelDate.setText(date);
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
        private TextView travelDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            travelName = itemView.findViewById(R.id.text_item_name);
            travelDate = itemView.findViewById(R.id.text_travel_list_date);
        }
    }
}
