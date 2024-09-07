package com.infy.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String emailId;
    private String name;
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(customerId);
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", emailId='" + emailId + '\'' + ", name='" + name + '\'' + ", dateOfBirth=" + dateOfBirth + ", address=" + address + '}';
    }
}
