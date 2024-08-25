package com.infy.dto;

import com.infy.entity.Customer;

import java.time.LocalDate;

public class CustomerDTO {
    private Integer customerId;
    private String name;
    private String emailId;
    private LocalDate dateOfBirth;

    public CustomerDTO(Integer customerId, String name, String emailId, LocalDate dateOfBirth) {
        this.customerId = customerId;
        this.name = name;
        this.emailId = emailId;
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" + "customerId=" + customerId + ", name='" + name + '\'' + ", emailId='" + emailId + '\'' + ", dateOfBirth=" + dateOfBirth + '}';
    }

    public static CustomerDTO prepareDTO(Customer customer){
        return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getEmailId(), customer.getDateOfBirth());
    }
}
