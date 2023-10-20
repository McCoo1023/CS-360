package com.example.owencapaknewinvapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserRegisterActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);

        Button registerButton = findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from text fields
                EditText usernameEditText = findViewById(R.id.editTextUsername);
                EditText passwordEditText = findViewById(R.id.editTextPassword);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(username.isEmpty() || password.isEmpty()) {
                    // Alert the user that they need to enter both a username and password
                    Toast.makeText(UserRegisterActivity.this, "Please enter both a username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save credentials to database
                DatabaseHelper databaseHelper = new DatabaseHelper(UserRegisterActivity.this);
                boolean isUserCreated = databaseHelper.addUser(username, password);

                if(isUserCreated) {
                    // User created successfully, navigate to InventoryGridActivity
                    Intent intent = new Intent(UserRegisterActivity.this, InventoryGridActivity.class);
                    startActivity(intent);
                } else {
                    // Something went wrong, alert the user
                    Toast.makeText(UserRegisterActivity.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}

