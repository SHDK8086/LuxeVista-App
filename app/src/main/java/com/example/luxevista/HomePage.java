package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    private ImageView standard, Midrange, Luxury, Services, profile, Locations, standardplus, standardplus2, standardplus3, luxuryplus, notification_icon, Reservations, Booking;
    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        standard = findViewById(R.id.standard);
        standard.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, StandardRooms.class);
            startActivity(intent);
        });

        Midrange = findViewById(R.id.Midrange);
        Midrange.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, MidRange.class);
            startActivity(intent);
        });

        Luxury = findViewById(R.id.Luxury);
        Luxury.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Luxury.class);
            startActivity(intent);
        });

        Services = findViewById(R.id.Services);
        Services.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Services.class);
            startActivity(intent);
        });

        Locations = findViewById(R.id.Locations);
        Locations.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Locations.class);
            startActivity(intent);
        });

        standardplus = findViewById(R.id.standardplus);
        standardplus.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, StandardRooms.class);
            startActivity(intent);
        });

        standardplus2 = findViewById(R.id.standardplus2);
        standardplus2.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, StandardRooms.class);
            startActivity(intent);
        });

        standardplus3 = findViewById(R.id.standardplus3);
        standardplus3.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, StandardRooms.class);
            startActivity(intent);
        });

        luxuryplus = findViewById(R.id.luxuryplus);
        luxuryplus.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Luxury.class);
            startActivity(intent);
        });

        notification_icon = findViewById(R.id.notification_icon);
        notification_icon.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, NotificationActivity.class);
            startActivity(intent);
        });


        Reservations = findViewById(R.id.Reservations);
        profile = findViewById(R.id.profile);
        usernameTextView = findViewById(R.id.Username);
        Booking = findViewById(R.id.Booking);


        String username = getIntent().getStringExtra("username");
        String fullName = getIntent().getStringExtra("fullName");
        String email = getIntent().getStringExtra("email");

        if (fullName != null && !fullName.isEmpty()) {
            usernameTextView.setText(fullName);
        }

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, ProfileUpdate.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        Reservations.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Reserves.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });

        Booking.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, BookingHistory.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });
    }
}
