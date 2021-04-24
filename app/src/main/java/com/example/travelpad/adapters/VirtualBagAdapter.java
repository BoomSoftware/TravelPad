package com.example.travelpad.adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelpad.R;
import com.example.travelpad.models.Item;
import com.example.travelpad.viewmodels.travel.VirtualBagViewModel;

import java.util.ArrayList;
import java.util.List;

public class VirtualBagAdapter extends RecyclerView.Adapter<VirtualBagAdapter.ViewHolder> {
    private VirtualBagViewModel virtualBagViewModel;
    private List<Item> items;


    public VirtualBagAdapter() { items = new ArrayList<>(); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_virtual_bag_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item currentItem = items.get(position);

        holder.itemName.setText(currentItem.getName());
        holder.checkBox.setChecked(currentItem.isPacked());

        if(!currentItem.isPacked()) {
            holder.boxBackground.setBackgroundTintList(ColorStateList.valueOf(holder.itemView.getContext().getColor(R.color.warning_light)));
        }
        else{
            holder.boxBackground.setBackgroundTintList(ColorStateList.valueOf(holder.itemView.getContext().getColor(R.color.green_200)));
        }

        holder.checkBox.setOnClickListener((v) -> {
            if(currentItem.isPacked()){
                virtualBagViewModel.updateItemStatus(currentItem.getId(), false);
                items.get(position).setPacked(false);
            }else{
                virtualBagViewModel.updateItemStatus(currentItem.getId(), true);
                items.get(position).setPacked(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private CheckBox checkBox;
        private ConstraintLayout boxBackground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.text_item_name);
            checkBox = itemView.findViewById(R.id.check_item_packed);
            boxBackground = itemView.findViewById(R.id.box_item_row);
        }
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setVirtualBagViewModel(VirtualBagViewModel virtualBagViewModel){
        this.virtualBagViewModel = virtualBagViewModel;
    }

    public Item getItemAt(int position) {
        return items.get(position);
    }
}
