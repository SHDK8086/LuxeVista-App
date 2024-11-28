package com.example.luxevista;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Reserves extends AppCompatActivity {

    private RecyclerView reservationsRecyclerView;
    private ReservationAdapter reservationAdapter;
    private List<Reservation> reservationList = new ArrayList<>();
    private String loggedInEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserves);

        reservationsRecyclerView = findViewById(R.id.reservations_recycler_view);
        reservationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reservationAdapter = new ReservationAdapter(reservationList);
        reservationsRecyclerView.setAdapter(reservationAdapter);

        loggedInEmail = getIntent().getStringExtra("email");

        fetchReservations();
    }

    private void fetchReservations() {
        DatabaseReference reservationsRef = FirebaseDatabase.getInstance().getReference("reservations");

        reservationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reservationList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Reservation reservation = dataSnapshot.getValue(Reservation.class);
                    if (reservation != null && reservation.getEmail().equals(loggedInEmail)) {
                        reservationList.add(reservation);
                    }
                }
                reservationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Reserves.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

