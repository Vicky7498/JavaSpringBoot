package com.infy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    private Integer customerId;
    private String emailId;
    private String name;
    private LocalDate dateOfBirth;
    private String city;

    public Customer() {
        super();
    }

    public Customer(Integer customerId, String emailId, String name, LocalDate dateOfBirth, String city) {
        this.customerId = customerId;
        this.emailId = emailId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", emailId='" + emailId + '\'' + ", name='" + name + '\'' + ", dateOfBirth=" + dateOfBirth + ", city='" + city + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Customer other = (Customer) obj;
        if (this.getCustomerId() == null) {
            if (other.getCustomerId() != null) return false;
        } else if (!this.getCustomerId().equals(other.getCustomerId())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(customerId);
    }
}
