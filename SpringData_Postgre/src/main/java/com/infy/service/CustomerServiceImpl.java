package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRespository customerRespository;

    @Override
    public void addCustomer(CustomerDTO customerDto) throws InfyBankException {
        Optional<Customer> optional = customerRespository.findById(customerDto.getCustomerId());
        if (optional.isPresent()) {
            throw new InfyBankException("Service.CUSTOMER_FOUND");
        }
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setName(customerDto.getName());
        customer.setEmailId(customerDto.getEmailId());
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        customerRespository.save(customer);
    }

    @Override
    public List<CustomerDTO> finaAll() throws InfyBankException {
        Iterable<Customer> customers = customerRespository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customers.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOList.add(customerDTO);
        });
        if (customerDTOList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
        }
        return customerDTOList;
    }

    @Override
    public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
        Optional<Customer> optional = customerRespository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmailId(customer.getEmailId());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());
        return customerDTO;
    }

    @Override
    public void updateCustomer(Integer customerId, String emailId) throws InfyBankException {
        Optional<Customer> optional = customerRespository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        customer.setEmailId(emailId);

    }

    @Override
    public void deleteCustomer(Integer customerId) throws InfyBankException {
        Optional<Customer> optional = customerRespository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        customerRespository.deleteById(customerId);
    }
}
