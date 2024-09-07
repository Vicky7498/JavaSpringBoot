package com.infy.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class BookingDTO {
    private Integer bookingId;
    @NotNull(message = "{booking.weight.absent}")
    @Min(value = 30, message = "{booking.invalid.weight}")
    private Integer weight;
    private LocalDate bookingDate;
    @NotNull(message = "{booking.source.absent}")
    @Pattern(regexp = "^[A-Za-z][A-Za-z ]*$", message = "{booking.source.invalid}")
    private String source;
    @NotNull(message = "{booking.destination.absent}")
    @Pattern(regexp = "^[A-Za-z][A-Za-z ]*$", message = "{booking.destination.invalid}")
    private String destination;
    @NotNull(message = "{booking.priority.absent}")
    @Pattern(regexp = "\\b(LOW|MEDIUM|HIGH)\\b", message = "{booking.invalid.priority}")
    private String priority;
    private Float bookingAmount;
    @Null(message = "{booking.invalid.status}")
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
    public String toString() {
        return "BookingDTO{" + "bookingId=" + bookingId + ", weight=" + weight + ", bookingDate=" + bookingDate + ", source='" + source + '\'' + ", destination='" + destination + '\'' + ", priority='" + priority + '\'' + ", bookingAmount=" + bookingAmount + ", status=" + status + '}';
    }
}
