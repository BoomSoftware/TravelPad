package com.example.travelpad.adapters;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelpad.R;
import com.example.travelpad.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private List<Hotel> hotels;
    private SharedPreferences sharedPreferences;
    private String apiKey;
    private OnReservationOptionsListener onReservationOptionsListener;

    public HotelAdapter(SharedPreferences sharedPreferences, String apiKey, OnReservationOptionsListener listener){
        this.sharedPreferences = sharedPreferences;
        this.apiKey = apiKey;
        onReservationOptionsListener = listener;
        hotels = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_hotel, parent, false);
        return new HotelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel currentHotel = hotels.get(position);

        holder.ratingBar.setRating((float) currentHotel.getRating());
        holder.name.setText(currentHotel.getName());
        holder.address.setText(currentHotel.getAddress());
        holder.phone.setText(currentHotel.getPhoneNo());
        holder.currency.setText(sharedPreferences.getString("pref_currency", ""));

        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+ currentHotel.getPhotoUrl() +"&key=" + apiKey;
        Glide.with(holder.itemView).load(url).into(holder.imageView);


        double totalPrice = currentHotel.getPricePerDay() * currentHotel.getDays();
        holder.price.setText(String.format("%.2f", totalPrice));

        addReservationEvent(holder.addReservation, currentHotel);
        viewReservationEvent(holder.showReservation, currentHotel);

        holder.phone.setOnClickListener(v-> {
            onReservationOptionsListener.openPhoneNoEvent(currentHotel.getPhoneNo());
        });

        holder.address.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("hotel_name", currentHotel.getName());
            bundle.putFloat("lat", currentHotel.getLat());
            bundle.putFloat("lng", currentHotel.getLng());

            Navigation.findNavController(holder.itemView).navigate(R.id.action_hotelListFragment_to_hotelMapFragment, bundle);
        });
    }

    public Hotel getItemAt(int position) {
        return hotels.get(position);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void setHotels(List<Hotel> hotels){
        this.hotels = hotels;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView currency;
        TextView address;
        TextView phone;
        TextView price;
        TextView name;
        RatingBar ratingBar;
        Button addReservation;
        Button showReservation;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currency = itemView.findViewById(R.id.text_hotel_currency);
            address = itemView.findViewById(R.id.text_hotel_address);
            phone = itemView.findViewById(R.id.text_hotel_phone);
            price = itemView.findViewById(R.id.text_hotel_price);
            imageView = itemView.findViewById(R.id.img_hotel);
            name = itemView.findViewById(R.id.text_hotel_name);
            ratingBar = itemView.findViewById(R.id.rating_hotel);
            addReservation = itemView.findViewById(R.id.button_hotel_add_reservation);
            showReservation = itemView.findViewById(R.id.button_hotel_reservation);
        }
    }

    private void addReservationEvent(Button addReservationButton, Hotel hotel){
        addReservationButton.setOnClickListener(v-> {
            onReservationOptionsListener.addReservationEvent(hotel.getId());
        });
    }

    private void viewReservationEvent(Button viewReservationButton, Hotel hotel){
        viewReservationButton.setOnClickListener(v-> {
            onReservationOptionsListener.viewReservationEvent(hotel.getReservationPath());
        });
    }

    public interface OnReservationOptionsListener {
        void addReservationEvent(int hotelId);
        void viewReservationEvent(String reservationPath);
        void openPhoneNoEvent(String phoneNo);
    }
}
