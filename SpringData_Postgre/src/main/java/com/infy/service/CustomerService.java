package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.exception.InfyBankException;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface CustomerService {
    public void addCustomer(CustomerDTO customerDto) throws InfyBankException;

    public List<CustomerDTO> finaAll() throws InfyBankException;

    public CustomerDTO getCustomer(Integer customerId) throws InfyBankException;

    public void updateCustomer(Integer customerId, String emailId) throws InfyBankException;

    public void deleteCustomer(Integer customerId) throws InfyBankException;
}
