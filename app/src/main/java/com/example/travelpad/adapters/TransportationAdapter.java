package com.example.travelpad.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
import com.example.travelpad.models.Transportation;
import com.example.travelpad.models.TransportationTypes;
import com.example.travelpad.viewmodels.travel.TransportationViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransportationAdapter extends RecyclerView.Adapter<TransportationAdapter.ViewHolder> {
    private List<Transportation> transportationList;
    private Activity activity;

    public TransportationAdapter(Activity activity) {
        this.activity = activity;
        transportationList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_transportation, parent, false);
        return new TransportationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transportation currentTransportation = transportationList.get(position);

        holder.iconName.setText(currentTransportation.getType());
        holder.startingPointName.setText(currentTransportation.getStartingPointName());
        holder.destinationPointName.setText(currentTransportation.getDestinationPointName());

        String durationMin =  String.valueOf(currentTransportation.getDurationMinute());
        String durationHour = String.valueOf(currentTransportation.getDurationHour());
        if(currentTransportation.getDurationHour() < 10){
            durationHour = "0"+durationHour;
        }
        if(currentTransportation.getDurationMinute() < 10){
            durationMin = "0"+durationMin;
        }
        String duration = durationHour + ":" + durationMin;
        holder.duration.setText(duration);

        String startingHour = String.valueOf(currentTransportation.getStartingHour());
        String startingMin = String.valueOf(currentTransportation.getStartingMinute());
        if(currentTransportation.getStartingHour() < 10){
            startingHour = "0"+startingHour;
        }
        if(currentTransportation.getStartingMinute() < 10){
            startingMin = "0"+startingMin;
        }
        String startingDate = currentTransportation.getStartingDate() + " " + startingHour + ":" + startingMin;
        holder.startingDate.setText(startingDate);

        String[] tempDate = currentTransportation.getStartingDate().split("/");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(tempDate[2]), Integer.parseInt(tempDate[1]), Integer.parseInt(tempDate[0]), currentTransportation.getStartingHour(), currentTransportation.getStartingMinute());
        calendar.add(Calendar.HOUR, currentTransportation.getDurationHour());
        calendar.add(Calendar.MINUTE, currentTransportation.getDurationMinute());

        String destMin = String.valueOf(calendar.get(Calendar.MINUTE));
        String destHour  = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));

        if(calendar.get(Calendar.MINUTE) < 10){
            destMin = "0" + destMin;
        }
        if(calendar.get(Calendar.HOUR_OF_DAY) < 10){
            destHour = "0" + destHour;
        }

        String destDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + " " + destHour + ":" + destMin;
        holder.destinationDate.setText(destDate);


        if(currentTransportation.getType().equals(TransportationTypes.BOAT.toString())){
            holder.icon.setImageResource(R.drawable.ic_boat);
        }
        else if(currentTransportation.getType().equals(TransportationTypes.CAR.toString())){
            holder.icon.setImageResource(R.drawable.ic_car);
        }
        else if(currentTransportation.getType().equals(TransportationTypes.TRAIN.toString())){
            holder.icon.setImageResource(R.drawable.ic_travel_transport);
        }
        else if(currentTransportation.getType().equals(TransportationTypes.TRAM.toString())){
            holder.icon.setImageResource(R.drawable.ic_tram);
        }
        else if(currentTransportation.getType().equals(TransportationTypes.PLANE.toString())){
            holder.icon.setImageResource(R.drawable.ic_plane);
        }
        else if(currentTransportation.getType().equals(TransportationTypes.BUS.toString())){
            holder.icon.setImageResource(R.drawable.ic_bus);
        }

        addTicketEvent(holder.addTicketButton, currentTransportation);
        viewTicketEvent(holder.viewTicketsButton, currentTransportation);
    }

    @Override
    public int getItemCount() {
        return transportationList.size();
    }

    public void setTransportationList(List<Transportation> transportationList){
        this.transportationList = transportationList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView startingPointName;
        TextView destinationPointName;
        TextView startingDate;
        TextView destinationDate;
        TextView iconName;
        ImageView icon;
        TextView duration;
        Button addTicketButton;
        Button viewTicketsButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startingPointName = itemView.findViewById(R.id.text_transport_item_start_point);
            destinationPointName = itemView.findViewById(R.id.text_transport_item_dest_point);
            startingDate = itemView.findViewById(R.id.text_transport_item_start_date);
            destinationDate = itemView.findViewById(R.id.text_transport_item_dest_date);
            iconName = itemView.findViewById(R.id.text_transport_item_icon_name);
            icon = itemView.findViewById(R.id.img_transport_item_icon);
            duration = itemView.findViewById(R.id.text_transport_item_duration);
            addTicketButton = itemView.findViewById(R.id.button_transport_item_add_ticket);
            viewTicketsButton = itemView.findViewById(R.id.button_transport_item_view_tickets);
        }
    }

    private void addTicketEvent(Button addTicketButton, Transportation transportation){
        addTicketButton.setOnClickListener(v-> {
            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            ((TravelActivity)activity).setTransportationID(transportation.getId());
            activity.startActivityForResult(chooseFile, 1);
        });
    }

    private void viewTicketEvent(Button viewTicketButton, Transportation transportation){
        viewTicketButton.setOnClickListener(v-> {
            if(transportation.getTicketPath() != null && !transportation.getTicketPath().equals("")){
                Uri uri = Uri.parse(transportation.getTicketPath());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setData(uri);
                System.out.println(uri + "XXXXXXXXXXXXXXXXXXXXXXXXXX");
                activity.startActivity(intent);
            }
        });
    }
}
