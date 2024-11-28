package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Booking extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        String roomName = getIntent().getStringExtra("room_name");
        String roomPriceString = getIntent().getStringExtra("room_price");

        double roomPrice;
        try {
            roomPrice = Double.parseDouble(roomPriceString.replaceAll("[^\\d.]", ""));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid room price format", Toast.LENGTH_SHORT).show();
            return;
        }

        TextView nameTextView = findViewById(R.id.Name);
        TextView priceTextView = findViewById(R.id.Price);
        totalPriceTextView = findViewById(R.id.total_price);

        nameTextView.setText(roomName);
        priceTextView.setText(roomPriceString);

        databaseReference = FirebaseDatabase.getInstance().getReference("Bookings");

        EditText fullNameEditText = findViewById(R.id.full_name);
        EditText idNumberEditText = findViewById(R.id.ID_no);
        EditText emailEditText = findViewById(R.id.email);
        EditText numberEditText = findViewById(R.id.number);
        EditText addressEditText = findViewById(R.id.address);
        EditText daysEditText = findViewById(R.id.days);
        EditText timeEditText = findViewById(R.id.time);

        daysEditText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    try {
                        int days = Integer.parseInt(s.toString());
                        double totalPrice = roomPrice * days;
                        totalPriceTextView.setText("Total Price: $" + totalPrice);
                    } catch (NumberFormatException e) {
                        totalPriceTextView.setText("Total Price: $0");
                    }
                } else {
                    totalPriceTextView.setText("Total Price: $0");
                }
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        Button payNowButton = findViewById(R.id.pay_now);
        payNowButton.setOnClickListener(v -> {
            String fullName = fullNameEditText.getText().toString().trim();
            String idNumber = idNumberEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String phoneNumber = numberEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();
            String days = daysEditText.getText().toString().trim();
            String time = timeEditText.getText().toString().trim();

            if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(idNumber) || TextUtils.isEmpty(email) || TextUtils.isEmpty(days)) {
                Toast.makeText(Booking.this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            int numDays = Integer.parseInt(days);
            double totalPrice = roomPrice * numDays;

            String bookingId = databaseReference.push().getKey();
            BookingData bookingData = new BookingData(
                    roomName,
                    "$" + totalPrice,
                    fullName,
                    idNumber,
                    email,
                    phoneNumber,
                    address,
                    days,
                    time
            );

            databaseReference.child(bookingId).setValue(bookingData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(Booking.this, "Booking saved successfully!", Toast.LENGTH_SHORT).show();

                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(Booking.this, "Failed to save booking: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}
