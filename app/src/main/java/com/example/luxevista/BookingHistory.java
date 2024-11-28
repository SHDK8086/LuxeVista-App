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

public class BookingHistory extends AppCompatActivity {

    private RecyclerView bookingHistoryRecyclerView;
    private BookingAdapter bookingAdapter;
    private List<BookingData> bookingList = new ArrayList<>();
    private String loggedInEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        bookingHistoryRecyclerView = findViewById(R.id.booking_history_recycler_view);
        bookingHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookingAdapter = new BookingAdapter(bookingList);
        bookingHistoryRecyclerView.setAdapter(bookingAdapter);

        loggedInEmail = getIntent().getStringExtra("email");

        fetchBookings();
    }

    private void fetchBookings() {
        DatabaseReference bookingsRef = FirebaseDatabase.getInstance().getReference("Bookings");

        bookingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookingList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    BookingData booking = dataSnapshot.getValue(BookingData.class);
                    if (booking != null && booking.getEmail().equals(loggedInEmail)) {
                        bookingList.add(booking);
                    }
                }
                bookingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BookingHistory.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

