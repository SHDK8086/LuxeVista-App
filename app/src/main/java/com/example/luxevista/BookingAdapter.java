package com.example.luxevista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<BookingData> bookingList;

    public BookingAdapter(List<BookingData> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_card, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        BookingData booking = bookingList.get(position);
        holder.roomName.setText(booking.getRoomName());
        holder.roomPrice.setText("Price: " + booking.getRoomPrice());
        holder.bookingDetails.setText("Days: " + booking.getDays() +
                ", Time: " + booking.getTime() +
                ", Address: " + booking.getAddress());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView roomName, roomPrice, bookingDetails;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.card_room_name);
            roomPrice = itemView.findViewById(R.id.card_room_price);
            bookingDetails = itemView.findViewById(R.id.card_booking_details);
        }
    }
}
