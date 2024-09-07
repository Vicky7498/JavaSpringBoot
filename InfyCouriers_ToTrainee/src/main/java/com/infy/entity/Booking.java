package com.infy.entity;

import com.infy.dto.CourierStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    private Integer weight;
    @Column(name = "booking_date")
    private LocalDate bookingDate;
    private String source;
    private String destination;
    private String priority;
    @Column(name = "booking_amount")
    private Float bookingAmount;
    @Enumerated(EnumType.STRING)
    private CourierStatus status;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Float getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(Float bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public CourierStatus getStatus() {
        return status;
    }

    public void setStatus(CourierStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking booking)) return false;
        return Objects.equals(bookingId, booking.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookingId);
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingId=" + bookingId + ", weight=" + weight + ", bookingDate=" + bookingDate + ", source='" + source + '\'' + ", destination='" + destination + '\'' + ", priority='" + priority + '\'' + ", bookingAmount=" + bookingAmount + ", status=" + status + '}';
    }
}
