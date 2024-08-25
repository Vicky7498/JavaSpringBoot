package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRespository customerRespository;

    @Override
    public List<CustomerDTO> getCustomerDetails() throws InfyBankException {
        return customerRespository.getCustomerDetails();
    }

    @Override
    public List<Object[]> getCustomerNameAndDob() throws InfyBankException {
        return customerRespository.getCustomerNameAndDob();
    }

    @Override
    public List<String> getCustomerName() throws InfyBankException {
        return customerRespository.customerName();
    }

    @Override
    public List<CustomerDTO> getCustomerByCustomerId(Integer customerId) throws InfyBankException {
        return customerRespository.getCustomerDetailsWithCustomerId(customerId);
    }

    @Override
    public Integer updateCityOfCustomer(Integer customerId, String city) throws InfyBankException {
        return customerRespository.updateCityOfCustomer(customerId, city);
    }

    @Override
    public Integer deleteCustomer(Integer customerId) throws InfyBankException {
        return customerRespository.deleteCustomerById(customerId);
    }
}
