package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    private TextView signup;
    private TextView adminsignup;
    private Button login;
    private TextInputEditText emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signup = findViewById(R.id.signup);
        adminsignup = findViewById(R.id.adminsignup);
        login = findViewById(R.id.login);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        });

        adminsignup.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn.this, AdminSignIn.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(SignIn.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            authenticateUser(email, password);
        });
    }

    private void authenticateUser(String email, String password) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean userFound = false;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String dbEmail = snapshot.child("email").getValue(String.class);
                    String dbPassword = snapshot.child("password").getValue(String.class);
                    String fullName = snapshot.child("fullName").getValue(String.class);
                    String username = snapshot.getKey();

                    if (dbEmail != null && dbEmail.equals(email)) {
                        userFound = true;
                        if (dbPassword != null && dbPassword.equals(password)) {
                            Toast.makeText(SignIn.this, "Login successful", Toast.LENGTH_SHORT).show();


                            Intent homeIntent = new Intent(SignIn.this, HomePage.class);
                            homeIntent.putExtra("fullName", fullName);
                            homeIntent.putExtra("username", username);
                            homeIntent.putExtra("email", email);
                            startActivity(homeIntent);

                            finish();
                        } else {
                            Toast.makeText(SignIn.this, "Invalid password", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }

                if (!userFound) {
                    Toast.makeText(SignIn.this, "User not found. Please sign up.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SignIn.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}