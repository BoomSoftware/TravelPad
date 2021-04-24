package com.example.travelpad.views.travel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelpad.R;
import com.example.travelpad.TravelActivity;
import com.example.travelpad.adapters.VirtualBagAdapter;
import com.example.travelpad.models.Item;
import com.example.travelpad.viewmodels.travel.VirtualBagViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.dmoral.toasty.Toasty;

public class VirtualBagFragment extends Fragment {

    private VirtualBagViewModel viewModel;
    private RecyclerView itemList;
    private FloatingActionButton addButton;
    private VirtualBagAdapter adapter;

    private View view;
    private int travelID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        travelID = ((TravelActivity) getActivity()).getTravelID();
        viewModel = new ViewModelProvider(this).get(VirtualBagViewModel.class);
        view = inflater.inflate(R.layout.fragment_virtual_bag, container, false);
        prepareUI();
        setAddItemDialog();
        return view;
    }

    private void prepareUI(){
        addButton = view.findViewById(R.id.button_bag_add_item);
        itemList = view.findViewById(R.id.recycler_transportation_list);
        itemList.hasFixedSize();
        itemList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new VirtualBagAdapter();
        adapter.setVirtualBagViewModel(viewModel);
        itemList.setAdapter(adapter);
        viewModel.getVirtualBagForTravel(travelID).observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.removeItemFromVirtualBag(adapter.getItemAt(viewHolder.getAbsoluteAdapterPosition()));
                Toasty.success(view.getContext(), view.getContext().getString(R.string.remove_item), Toast.LENGTH_SHORT, true).show();
            }
        }).attachToRecyclerView(itemList);
    }

    private void setAddItemDialog(){
        addButton.setOnClickListener(v -> {
            LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
            View dialogView = layoutInflater.inflate(R.layout.dialog_add_item, null);
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
            dialogBuilder.setView(dialogView);

            EditText itemNameEditText = dialogView.findViewById(R.id.input_item_name);

            dialogBuilder
                    .setPositiveButton(view.getContext().getString(R.string.add), null)
                    .setNegativeButton(view.getContext().getString(R.string.cancel), (dialogBox, id) -> dialogBox.cancel());

            AlertDialog dialog = dialogBuilder.create();
            dialog.show();

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v1 -> {
                if(itemNameEditText != null && !itemNameEditText.getText().toString().equals("")){
                    viewModel.addItemToVirtualBag(new Item(itemNameEditText.getText().toString(), travelID));
                    dialog.cancel();
                    return;
                }
                Toasty.error(view.getContext(), view.getContext().getString(R.string.empty_name), Toast.LENGTH_SHORT, true).show();
            });

        });
    }


}