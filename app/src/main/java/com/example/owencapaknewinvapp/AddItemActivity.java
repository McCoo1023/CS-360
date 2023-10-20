package com.example.owencapaknewinvapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    private EditText editTextItemName;
    private EditText editTextItemQuantity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editTextItemName = findViewById(R.id.editTextItemName);
        editTextItemQuantity = findViewById(R.id.editTextItemQuantity);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClick();
            }
        });
    }

    private void onSaveButtonClick() {
        String name = editTextItemName.getText().toString();
        String quantityString = editTextItemQuantity.getText().toString();

        if (!name.isEmpty() && !quantityString.isEmpty()) {
            int quantity = Integer.parseInt(quantityString);
            Item item = new Item(name, quantity);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("newItem", item);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            // Show an error message if either field is empty
            Toast.makeText(this, "Both fields must be filled out", Toast.LENGTH_SHORT).show();
        }
    }
}
