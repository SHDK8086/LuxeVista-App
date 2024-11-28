package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private TextInputEditText fullnameInput, usernameInput, emailInput, addressInput, phoneInput, passwordInput;
    private MaterialButton createaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        fullnameInput = findViewById(R.id.fullname_input);
        usernameInput = findViewById(R.id.username_input);
        emailInput = findViewById(R.id.email_input);
        addressInput = findViewById(R.id.address_input);
        phoneInput = findViewById(R.id.phoneno_input);
        passwordInput = findViewById(R.id.password_input);
        createaccount = findViewById(R.id.createaccount);

        createaccount.setOnClickListener(v -> {
            String fullName = fullnameInput.getText().toString().trim();
            String username = usernameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String address = addressInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(SignUp.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(SignUp.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> userData = new HashMap<>();
            userData.put("fullName", fullName);
            userData.put("username", username);
            userData.put("email", email);
            userData.put("address", address);
            userData.put("phone", phone);
            userData.put("password", password);

            usersRef.child(username).setValue(userData).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, SignIn.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignUp.this, "Failed to create account. Please try again.", Toast.LENGTH_SHORT).show();
                    Log.e("SignUp", "Error: ", task.getException());
                }
            });
        });

    }
}
