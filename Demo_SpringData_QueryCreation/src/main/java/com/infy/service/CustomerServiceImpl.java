package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO findByEmailId(String emailId) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailId(emailId);
        Customer customer = optionalCustomer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_UNAVAILABLE"));
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmailId(customer.getEmailId());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());
        return customerDTO;
    }

    @Override
    public CustomerDTO findByEmailIdAndName(String emailId, String name) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailIdAndName(emailId, name);
        Customer customer = optionalCustomer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_UNAVAILABLE"));
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmailId(customer.getEmailId());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> findByEmailIdOrName(String emailId, String name) throws InfyBankException {
        List<Customer> customerList = customerRepository.findByEmailIdOrName(emailId, name);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (customerList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerList.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> findByDateOfBirthBetween(LocalDate fromDate, LocalDate toDate) throws InfyBankException {
        List<Customer> customerList = customerRepository.findByDateOfBirthBetween(fromDate, toDate);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (customerList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerList.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> findByDateOfBirthLessThan(LocalDate dateOfBirth) throws InfyBankException {
        List<Customer> customerList = customerRepository.findByDateOfBirthLessThan(dateOfBirth);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (customerList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerList.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> findByDateOfBirthGreaterThan(LocalDate dateOfBirth) throws InfyBankException {
        List<Customer> customerList = customerRepository.findByDateOfBirthGreaterThan(dateOfBirth);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (customerList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerList.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> findByDateOfBirthAfter(LocalDate dateOfBirth) throws InfyBankException {
        List<Customer> customerList = customerRepository.findByDateOfBirthAfter(dateOfBirth);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (customerList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerList.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> findByDateOfBirthBefore(LocalDate dateOfBirth) throws InfyBankException {
        List<Customer> customerList = customerRepository.findByDateOfBirthBefore(dateOfBirth);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (customerList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerList.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> findByEmailIdNull() throws InfyBankException {
        List<Customer> customerList = customerRepository.findByEmailIdNull();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (customerList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerList.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> findByNameLike(String namePattern) throws InfyBankException {
        List<Customer> customerList = customerRepository.findByNameLike(namePattern);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (customerList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerList.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> findByNameOrderByDateOfBirth(String name) throws InfyBankException {
        List<Customer> customerList = customerRepository.findByNameOrderByDateOfBirth(name);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (customerList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerList.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> findByNameOrderByDateOfBirthDesc(String name) throws InfyBankException {
        List<Customer> customerList = customerRepository.findByNameOrderByDateOfBirthDesc(name);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (customerList.isEmpty()) {
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerList.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    @Override
    public String findNameByEmailId(String emailId) {
        return customerRepository.findCustomerIdByEmailId(emailId);
    }

    @Override
    public void updateCustomerEmailId(String newEmailId, Integer customerId) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        optionalCustomer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_UNAVAILABLE"));
        customerRepository.updateCustomerEmailId(newEmailId, customerId);
    }

    @Override
    public void deleteCustomerByEmailId(String emailId) throws InfyBankException {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailId(emailId);
        optionalCustomer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_UNAVAILABLE"));
        customerRepository.deleteCustomerByEmailId(emailId);
    }

    @Override
    public String findEmailIdByCustomerId(Integer customerId) {
        return customerRepository.findEmailIdByCustomerId(customerId);
    }
}
