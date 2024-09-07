package com.infy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "Fare")
public class FareEstimation {
    @Id
    private Integer fareId;
    private String source;
    private String destination;
    private Float fare;

    public Integer getFareId() {
        return fareId;
    }

    public void setFareId(Integer fareId) {
        this.fareId = fareId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FareEstimation that)) return false;
        return Objects.equals(fareId, that.fareId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fareId);
    }

    @Override
    public String toString() {
        return "FareEstimation{" + "fareId=" + fareId + ", source='" + source + '\'' + ", destination='" + destination + '\'' + ", fare=" + fare + '}';
    }
}
