package com.infy.service;

import com.infy.dto.AddressDTO;
import com.infy.dto.CustomerDTO;
import com.infy.entity.Address;
import com.infy.entity.Customer;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;
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
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmailId(customer.getEmailId());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(customer.getAddress().getAddressId());
        addressDTO.setStreet(customer.getAddress().getStreet());
        addressDTO.setCity(customer.getAddress().getCity());
        customerDTO.setAddressDTO(addressDTO);
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws InfyBankException {
        Iterable<Customer> customerIterable = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerIterable.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setAddressId(customer.getAddress().getAddressId());
            addressDTO.setStreet(customer.getAddress().getStreet());
            addressDTO.setCity(customer.getAddress().getCity());
            customerDTO.setAddressDTO(addressDTO);
            customerDTOList.add(customerDTO);
        });
        if (customerDTOList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
        }
        return customerDTOList;
    }

    @Override
    public Integer addCustomer(CustomerDTO customerDTO) throws InfyBankException {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setName(customerDTO.getName());
        customer.setEmailId(customerDTO.getEmailId());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        Address address = new Address();
        address.setStreet(customerDTO.getAddressDTO().getStreet());
        address.setCity(customerDTO.getAddressDTO().getCity());
        customer.setAddress(address);
        Customer customer1 = customerRepository.save(customer);
        return customer1.getCustomerId();
    }

    @Override
    public void updateCustomer(Integer customerId, String emailId) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        customer.setEmailId(emailId);
    }

    @Override
    public void deleteCustomer(Integer customerId) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        optionalCustomer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        customerRepository.deleteById(customerId);
    }
}
