package com.example.luxevista;

public class BookingData {
    private String roomName, roomPrice, fullName, idNumber, email, phoneNumber, address, days, time;

    public BookingData() {
    }

    public BookingData(String roomName, String roomPrice, String fullName, String idNumber, String email,
                       String phoneNumber, String address, String days, String time) {
        this.roomName = roomName;
        this.roomPrice = roomPrice;
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.days = days;
        this.time = time;
    }

    public String getRoomName() { return roomName; }
    public String getRoomPrice() { return roomPrice; }
    public String getFullName() { return fullName; }
    public String getIdNumber() { return idNumber; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getDays() { return days; }
    public String getTime() { return time; }
}
