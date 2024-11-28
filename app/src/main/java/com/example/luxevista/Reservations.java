package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Reservations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        TextView reservationName = findViewById(R.id.reservation_name);
        EditText fullName = findViewById(R.id.full_name);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.phone);
        EditText reservationDate = findViewById(R.id.reservation_date);
        EditText reservationTime = findViewById(R.id.reservation_time);
        EditText numberOfGuests = findViewById(R.id.number_of_guests);
        Button reserveNowButton = findViewById(R.id.reserve_now);

        String serviceName = getIntent().getStringExtra("serviceName");
        reservationName.setText(serviceName);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reservationsRef = database.getReference("reservations");

        reserveNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullName.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                        phone.getText().toString().isEmpty() || reservationDate.getText().toString().isEmpty() ||
                        reservationTime.getText().toString().isEmpty() || numberOfGuests.getText().toString().isEmpty()) {
                    Toast.makeText(Reservations.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String id = reservationsRef.push().getKey();
                HashMap<String, String> data = new HashMap<>();
                data.put("id", id);
                data.put("serviceName", serviceName);
                data.put("fullName", fullName.getText().toString());
                data.put("email", email.getText().toString());
                data.put("phone", phone.getText().toString());
                data.put("reservationDate", reservationDate.getText().toString());
                data.put("reservationTime", reservationTime.getText().toString());
                data.put("numberOfGuests", numberOfGuests.getText().toString());

                reservationsRef.child(id).setValue(data).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Reservations.this, "Reservation Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Reservations.this, HomePage.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Reservations.this, "Reservation fail " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
