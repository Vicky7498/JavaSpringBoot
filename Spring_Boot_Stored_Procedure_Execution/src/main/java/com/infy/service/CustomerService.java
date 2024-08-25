package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.exception.CustomerException;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomerDetailsByDateOfBirth(LocalDate dateOfBirth) throws CustomerException;

    List<CustomerDTO> getCustomerDetailsByNameAndDateOfBirth(String name, LocalDate dateOfBirth) throws CustomerException;

    Integer getTotalCustomersByEmailId(String name) throws CustomerException;
}
