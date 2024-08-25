package com.infy;

import com.infy.dto.AddressDTO;
import com.infy.dto.CustomerDTO;
import com.infy.dto.LoanDTO;
import com.infy.service.CustomerLoanService;
import com.infy.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;

@SpringBootApplication
public class DemoOneToOneApplication implements CommandLineRunner {
    public static final Logger LOGGER = LogManager.getLogger(DemoOneToOneApplication.class);
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerLoanService customerLoanService;
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(DemoOneToOneApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        getCustomer();
//        addCustomer();
//        updateAddress();
//        deleteCustomer();
//        deleteCustomerOnly();
//        getLoanDetails();
        addLoanAndCustomer();
    }

    private void getLoanDetails() {
        try {
            LoanDTO loanDTO = customerLoanService.getLoanDetails(2001);
            LOGGER.info(loanDTO);
        } catch (Exception exception) {
            String message = environment.getProperty(exception.getMessage(), "Some exception occurred. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    private void updateAddress() {
        try {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCity("Rochester");
            addressDTO.setStreet("12 Tim Street");
            customerService.updateAddress(1234, addressDTO);
            LOGGER.info("\n{}", environment.getProperty("UserInterface.CUSTOMER_UPDATED"));
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    public void getCustomer() {
        try {
            CustomerDTO customerDTO = customerService.getCustomer(1001);
            LOGGER.info(customerDTO);
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    public void addCustomer() {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setName("Ron");
            customerDTO.setEmailId("ron@infy.com");
            customerDTO.setDateOfBirth(LocalDate.of(1993, 3, 24));
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setAddressId(104L);
            addressDTO.setCity("Albany");
            addressDTO.setStreet("93 Taylor Road");
            customerDTO.setAddressDTO(addressDTO);
            Integer customerId = customerService.addCustomer(customerDTO);
            LOGGER.info("\n{}{}", environment.getProperty("UserInterface.CUSTOMER_ADDED"), customerId);
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occurred. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    public void deleteCustomer() {
        try {
            customerService.deleteCustomer(1235);
            LOGGER.info("\n{}", environment.getProperty("UserInterface.CUSTOMER_ADDRESS_DELETED"));
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    public void deleteCustomerOnly() {
        try {
            customerService.deleteCustomerOnly(1236);
            LOGGER.info("\n{}", environment.getProperty("UserInterface.CUSTOMER_DELETED"));
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    public void addLoanAndCustomer() {
        try {
            LoanDTO loanDTO = new LoanDTO();
            loanDTO.setAmount(556279.0);
            loanDTO.setLoanIssueDate(LocalDate.of(2015, 11, 1));
            loanDTO.setStatus("Open");
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(1006);
            customerDTO.setDateOfBirth(LocalDate.of(1992, 1, 10));
            customerDTO.setEmailId("peter@infy.com");
            customerDTO.setName("Peter");
            customerDTO.setAddressDTO(null);

            loanDTO.setCustomerDTO(customerDTO);
            Integer loanId = customerLoanService.addLoanAndCustomer(loanDTO);
            LOGGER.info("{}{}", environment.getProperty("UserInterface.NEW_LOAN_CUSTOMER_SUCCESS"), loanId);
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
}
