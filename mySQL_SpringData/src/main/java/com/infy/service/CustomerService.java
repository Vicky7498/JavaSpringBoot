package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getCustomerDetails() throws InfyBankException;

    public List<Object[]> getCustomerNameAndDob() throws InfyBankException;

    public List<String> getCustomerName() throws InfyBankException;

    public List<CustomerDTO> getCustomerByCustomerId(Integer customerId) throws InfyBankException;

    public Integer updateCityOfCustomer(Integer customerId, String city) throws InfyBankException;

    public Integer deleteCustomer(Integer customerId) throws InfyBankException;

}

