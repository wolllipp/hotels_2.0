package com.example.hotelapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ArrivalTime {

    @Column(name = "check_in", nullable = false)
    private String checkIn;

    @Column(name = "check_out")
    private String checkOut;

    public ArrivalTime() {
    }

    public ArrivalTime(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getCheckIn() { return checkIn; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }

    public String getCheckOut() { return checkOut; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
}
