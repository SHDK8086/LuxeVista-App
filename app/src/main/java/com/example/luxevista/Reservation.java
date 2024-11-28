package com.example.luxevista;

public class Reservation {
    private String id, serviceName, fullName, email, phone, reservationDate, reservationTime, numberOfGuests;

    public Reservation() {
    }

    public Reservation(String id, String serviceName, String fullName, String email, String phone,
                       String reservationDate, String reservationTime, String numberOfGuests) {
        this.id = id;
        this.serviceName = serviceName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.numberOfGuests = numberOfGuests;
    }

    public String getId() { return id; }
    public String getServiceName() { return serviceName; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getReservationDate() { return reservationDate; }
    public String getReservationTime() { return reservationTime; }
    public String getNumberOfGuests() { return numberOfGuests; }
}

