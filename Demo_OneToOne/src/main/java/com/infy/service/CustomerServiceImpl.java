package com.infy.service;

import com.infy.dto.AddressDTO;
import com.infy.dto.CustomerDTO;
import com.infy.dto.LoanDTO;
import com.infy.entity.Address;
import com.infy.entity.Customer;
import com.infy.entity.Loan;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;
import com.infy.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(() -> new InfyBankException("Service.INVALID_CUSTOMERID"));
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmailId(customer.getEmailId());
        customerDTO.setDateOfBirth(customer.getDataOfBirth());
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(customer.getAddress().getAddressId());
        addressDTO.setStreet(customer.getAddress().getStreet());
        addressDTO.setCity(customer.getAddress().getCity());
        customerDTO.setAddressDTO(addressDTO);
        return customerDTO;
    }

    @Override
    public Integer addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setName(customerDTO.getName());
        customer.setEmailId(customerDTO.getEmailId());
        customer.setDataOfBirth(customerDTO.getDateOfBirth());
        Address address = new Address();
        address.setAddressId(customerDTO.getAddressDTO().getAddressId());
        address.setStreet(customerDTO.getAddressDTO().getStreet());
        address.setCity(customerDTO.getAddressDTO().getCity());
        customer.setAddress(address);
        customerRepository.save(customer);
        return customer.getCustomerId();
    }

    @Override
    public void updateAddress(Integer customerId, AddressDTO addressDTO) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(() -> new InfyBankException("Service.INVALID_CUSTOMERID"));
        Address address = customer.getAddress();
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
    }

    @Override
    public void deleteCustomer(Integer customerId) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(() -> new InfyBankException("Service.INVALID_CUSTOMERID"));
        customerRepository.deleteById(customerId);
    }

    @Override
    public void deleteCustomerOnly(Integer customerId) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(() -> new InfyBankException("Service.INVALID_CUSTOMERID"));
        customer.setAddress(null);
        customerRepository.deleteById(customerId);
    }
}
