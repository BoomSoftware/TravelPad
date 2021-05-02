package com.example.travelpad.adapters;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
import com.example.travelpad.models.Hotel;
import com.example.travelpad.models.HotelResponse;
import com.example.travelpad.models.Transportation;
import com.example.travelpad.viewmodels.travel.HotelListViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private List<HotelResponse> hotels;
    private SharedPreferences sharedPreferences;
    private String apiKey;
    private Activity activity;

    public HotelAdapter(SharedPreferences sharedPreferences, String apiKey, Activity activity){
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
        this.apiKey = apiKey;
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
        HotelResponse currentHotel = hotels.get(position);

        holder.ratingBar.setRating((float) currentHotel.getResult().getRating());
        holder.name.setText(currentHotel.getResult().getName());
        holder.address.setText(currentHotel.getResult().getFormatted_address());
        holder.phone.setText(currentHotel.getResult().getInternational_phone_number());
        holder.currency.setText(sharedPreferences.getString("pref_currency", ""));


        if(currentHotel.getResult().getPhotos() != null && currentHotel.getResult().getPhotos()[0] != null){
            String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+ currentHotel.getResult().getPhotos()[0].getPhoto_reference() +"&key=" + apiKey;
            Glide.with(holder.itemView).load(url).into(holder.imageView);
        }


        double totalPrice = currentHotel.getPricePerDay() * currentHotel.getDays();
        holder.price.setText(String.valueOf(totalPrice));

        addReservationEvent(holder.addReservation, currentHotel);
        viewReservationEvent(holder.showReservation, currentHotel);

        holder.phone.setOnClickListener(v-> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + currentHotel.getResult().getInternational_phone_number()));
            activity.startActivity(intent);
        });

        holder.address.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("hotel_name", currentHotel.getResult().getName());
            bundle.putFloat("lat", currentHotel.getResult().getGeometry().getLocation().getLat());
            bundle.putFloat("lng", currentHotel.getResult().getGeometry().getLocation().getLng());

            Navigation.findNavController(holder.itemView).navigate(R.id.action_hotelListFragment_to_hotelMapFragment, bundle);
        });
    }

    public HotelResponse getItemAt(int position) {
        return hotels.get(position);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void setHotels(List<HotelResponse> hotels){
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

    private void addReservationEvent(Button addReservationButton, HotelResponse hotel){
        addReservationButton.setOnClickListener(v-> {
            Intent chooseFile = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            chooseFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            chooseFile.setType("*/*");
            ((TravelActivity)activity).setPlaceId(hotel.getPlaceId());
            activity.startActivityForResult(chooseFile, 2);
        });
    }

    private void viewReservationEvent(Button viewReservationButton, HotelResponse hotel){
        viewReservationButton.setOnClickListener(v-> {
            if(hotel.getReservationPath() != null && !hotel.getReservationPath().equals("")){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri uri = Uri.parse(hotel.getReservationPath());
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });
    }
}
