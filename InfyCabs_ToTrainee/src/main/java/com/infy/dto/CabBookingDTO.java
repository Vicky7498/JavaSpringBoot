package com.infy.dto;

import java.time.LocalDate;

public class CabBookingDTO {
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

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CabBookingDTO{" + "bookingId=" + bookingId + ", source='" + source + '\'' + ", destination='" + destination + '\'' + ", fare=" + fare + ", travelDate=" + travelDate + ", userMobile=" + userMobile + ", status=" + status + '}';
    }
}
