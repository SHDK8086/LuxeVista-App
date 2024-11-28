package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProfileUpdate extends AppCompatActivity {

    private TextInputEditText fullnameInput, usernameInput, emailInput, addressInput, phoneInput, passwordInput;
    private MaterialButton updateButton, logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        TextInputEditText fullnameInput = findViewById(R.id.fullname_input);
        TextInputEditText emailInput = findViewById(R.id.email_input);
        TextInputEditText addressInput = findViewById(R.id.address_input);
        TextInputEditText phoneInput = findViewById(R.id.phone_input);
        TextInputEditText passwordInput = findViewById(R.id.password_input);
        MaterialButton updateButton = findViewById(R.id.update_button);
        MaterialButton logout_button = findViewById(R.id.logout_button);

        String username = getIntent().getStringExtra("username");

        if (username == null || username.isEmpty()) {
            Toast.makeText(this, "Error: Username is missing.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        usersRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    fullnameInput.setText(snapshot.child("fullName").getValue(String.class));
                    emailInput.setText(snapshot.child("email").getValue(String.class));
                    addressInput.setText(snapshot.child("address").getValue(String.class));
                    phoneInput.setText(snapshot.child("phone").getValue(String.class));
                    passwordInput.setText(snapshot.child("password").getValue(String.class));
                } else {
                    Toast.makeText(ProfileUpdate.this, "User data not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ProfileUpdate.this, "Error fetching data.", Toast.LENGTH_SHORT).show();
            }
        });

        updateButton.setOnClickListener(v -> {
            String updatedFullName = fullnameInput.getText().toString().trim();
            String updatedEmail = emailInput.getText().toString().trim();
            String updatedAddress = addressInput.getText().toString().trim();
            String updatedPhone = phoneInput.getText().toString().trim();
            String updatedPassword = passwordInput.getText().toString().trim();

            if (updatedFullName.isEmpty() || updatedEmail.isEmpty() || updatedAddress.isEmpty() ||
                    updatedPhone.isEmpty() || updatedPassword.isEmpty()) {
                Toast.makeText(ProfileUpdate.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> updatedData = new HashMap<>();
            updatedData.put("fullName", updatedFullName);
            updatedData.put("email", updatedEmail);
            updatedData.put("address", updatedAddress);
            updatedData.put("phone", updatedPhone);
            updatedData.put("password", updatedPassword);

            usersRef.child(username).updateChildren(updatedData).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ProfileUpdate.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Return to HomePage
                } else {
                    Toast.makeText(ProfileUpdate.this, "Failed to update profile. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        });
        logout_button.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(ProfileUpdate.this, "Logout successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ProfileUpdate.this, SignIn.class); // Replace with your login activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
            startActivity(intent);
            finish();
        });
    }
}