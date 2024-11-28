package com.example.luxevista;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AdminSignIn extends AppCompatActivity {

    private static final String ADMIN_EMAIL = "Admin@gmail.com";
    private static final String ADMIN_PASSWORD = "Admin1234";

    private TextInputEditText username_input;
    private TextInputEditText password_input;
    private MaterialButton loginButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);

        username_input = findViewById(R.id.username_input);
        password_input = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login);

        if (username_input == null || password_input == null) {
            Log.e("AdminSignIn", "Please Enter the values");
            return;
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail = username_input.getText().toString().trim();
                String enteredPassword = password_input.getText().toString().trim();

                if (validateCredentials(enteredEmail, enteredPassword)) {
                    Toast.makeText(AdminSignIn.this, "Sign-In Successful!", Toast.LENGTH_SHORT).show();
                    navigateToDashboard();
                } else {
                    Toast.makeText(AdminSignIn.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateCredentials(String email, String password) {
        return ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password);
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(AdminSignIn.this, Admindashboard.class);
        startActivity(intent);
        finish();
    }
}
