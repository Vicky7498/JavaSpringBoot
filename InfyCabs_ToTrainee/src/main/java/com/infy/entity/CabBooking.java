package com.infy.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Booking")
public class CabBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    private String source;
    private String destination;
    private Float fare;
    private LocalDate travelDate;
    private Long userMobile;
    private Character status;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Float getFare() {
        return fare;
    }

    public void setFare(Float fare) {
        this.fare = fare;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public Long getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(Long userMobile) {
        this.userMobile = userMobile;
    }

    public Character getStatus(char c) {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CabBooking{" + "bookingId=" + bookingId + ", source='" + source + '\'' + ", destination='" + destination + '\'' + ", fare=" + fare + ", travelDate=" + travelDate + ", userMobile=" + userMobile + ", status=" + status + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CabBooking that)) return false;
        return Objects.equals(bookingId, that.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookingId);
    }
}
