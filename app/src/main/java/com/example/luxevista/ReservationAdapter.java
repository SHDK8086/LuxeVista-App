package com.example.luxevista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservations;

    public ReservationAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reservation_card, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.serviceName.setText(reservation.getServiceName());
        holder.fullName.setText(reservation.getFullName());
        holder.email.setText(reservation.getEmail());
        holder.reservationDetails.setText(
                "Date: " + reservation.getReservationDate() +
                        ", Time: " + reservation.getReservationTime() +
                        ", Guests: " + reservation.getNumberOfGuests()
        );
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName, fullName, email, reservationDetails;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.card_service_name);
            fullName = itemView.findViewById(R.id.card_full_name);
            email = itemView.findViewById(R.id.card_email);
            reservationDetails = itemView.findViewById(R.id.card_reservation_details);
        }
    }
}
