package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.exception.CustomerException;
import com.infy.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getCustomerDetailsByDateOfBirth(LocalDate dateOfBirth) throws CustomerException {
        List<Customer> customerList = customerRepository.findCustomersAfterDOB_SP(dateOfBirth);
        return customerList.stream().map(CustomerDTO::prepareDTO).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomerDetailsByNameAndDateOfBirth(String name, LocalDate dateOfBirth) throws CustomerException {
        List<Customer> customerList = customerRepository.findCustomerByNameAndDateOfBirth_SP(name, dateOfBirth);
        if (customerList.isEmpty()) {
            throw new CustomerException("Service.CUSTOMERS_NOT_FOUND");
        }
        return customerList.stream().map(CustomerDTO::prepareDTO).collect(Collectors.toList());
    }

    @Override
    public Integer getTotalCustomersByEmailId(String name) throws CustomerException {
        Integer count = customerRepository.getTotalCustomersByEmailId(name);
        if (count == 0) {
            throw new CustomerException("Service.CUSTOMERS_NOT_FOUND");
        }
        return count;
    }
}
