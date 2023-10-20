package com.example.owencapaknewinvapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserLoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);

        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check credentials against database
                if (isValidCredentials(username, password)) {
                    // If valid, navigate to InventoryGridActivity
                    Intent intent = new Intent(UserLoginActivity.this, InventoryGridActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UserLoginActivity.this, "Invalid credentials, please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView registerButton = findViewById(R.id.textViewRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to UserRegisterActivity
                navigateToRegister();
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        return databaseHelper.getUser(username, password);
    }


    private void navigateToRegister() {
        Intent intent = new Intent(UserLoginActivity.this, UserRegisterActivity.class);
        startActivity(intent);
    }
}
