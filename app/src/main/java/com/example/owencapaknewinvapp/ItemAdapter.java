package com.example.owencapaknewinvapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.Manifest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> items;
    private Context context;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.itemNameTextView.setText(item.getName());
        holder.itemQuantityTextView.setText(String.valueOf(item.getQuantity()));

        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    // The position is valid
                    updateQuantityDialog(currentPosition);
                }
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    // The position is valid
                    deleteItem(currentPosition);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemNameTextView;
        public TextView itemQuantityTextView;
        public Button buttonUpdate;
        public Button buttonDelete;

        public ViewHolder(View view) {
            super(view);
            itemNameTextView = view.findViewById(R.id.item_name);
            itemQuantityTextView = view.findViewById(R.id.item_quantity);
            buttonUpdate = view.findViewById(R.id.button_update);
            buttonDelete = view.findViewById(R.id.button_delete);
        }
    }

    public void addItem(Item item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public List<Item> getItems() {
        return items;
    }


    public void updateItem(int position, Item updatedItem) {
        if (position >= 0 && position < items.size()) {
            items.set(position, updatedItem);
            notifyItemChanged(position);

            // Check if item quantity is greater than 10 and SMS permission is granted
            if (updatedItem.getQuantity() > 10 && ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                sendSmsNotification();
            }
        }
    }

    // Method to send an SMS notification
    private void sendSmsNotification() {
        // Define the phone number and message
        String phoneNumber = "1234567890";  // Replace with the actual phone number
        String message = "Item quantity is greater than 10.";

        // Send the SMS
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }


    public void deleteItem(int position) {
        if (position >= 0 && position < items.size()) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    private void updateQuantityDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update Quantity");
        final EditText input = new EditText(context);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int newQuantity;
                try {
                    newQuantity = Integer.parseInt(input.getText().toString());
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    return;
                }
                Item updatedItem = new Item(items.get(position).getName(), newQuantity);
                updateItem(position, updatedItem);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
