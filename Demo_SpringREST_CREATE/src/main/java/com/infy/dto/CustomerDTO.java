package com.infy.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class CustomerDTO {
    private Integer customerId;
    @Email(message = "Please provide valid email address")
    @NotNull(message = "Please provide email address")
    private String emailId;
    @NotNull(message = "Please provide customer name")
    @Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should contain only alphabets and space")
    private String name;
    @PastOrPresent(message = "Date of birth should be past or present date")
    private LocalDate dateOfBirth;
    @NotNull
    @Valid
    private AddressDTO addressDTO;

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

    public @NotNull @Valid AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(@NotNull @Valid AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" + "customerId=" + customerId + ", emailId='" + emailId + '\'' + ", name='" + name + '\'' + ", dateOfBirth=" + dateOfBirth + ", addressDTO=" + addressDTO + '}';
    }
}