package com.infy;

import com.infy.dto.CustomerDTO;
import com.infy.service.CustomerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;

@SpringBootApplication
public class SpringDataPostgreApplication implements CommandLineRunner {
    public static final Logger LOGGER = LogManager.getLogger(SpringDataPostgreApplication.class);
    @Autowired
    CustomerServiceImpl customerServiceImpl;
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataPostgreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        addCustomer();
//        getCustomer();
        finaAllCustomer();
//        updateCustomer();
//        deleteCustomer();
    }

    private void deleteCustomer() {
        try {
            customerServiceImpl.deleteCustomer(3);
            LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
        } catch (Exception exception) {
            if (exception.getMessage() != null) {
                LOGGER.info(environment.getProperty(exception.getMessage(), "Something went wrong. Please check log file for more details."));
            }
        }
    }

    private void updateCustomer() {
        try {
            customerServiceImpl.updateCustomer(2, "tim01@infy.com");
            LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
        } catch (Exception exception) {
            if (exception.getMessage() != null) {
                LOGGER.info(environment.getProperty(exception.getMessage(), "Something went wrong. Please check log file for more details."));
            }
        }
    }

    private void finaAllCustomer() {
        try {
            customerServiceImpl.finaAll().forEach(LOGGER::info);
        } catch (Exception exception) {
            if (exception.getMessage() != null) {
                LOGGER.info(environment.getProperty(exception.getMessage(), "Something went wrong. Please check log file for more details."));
            }
        }
    }

    private void getCustomer() {
        try {
            CustomerDTO customerDTO = customerServiceImpl.getCustomer(1);
            LOGGER.info(customerDTO);
        } catch (Exception exception) {
            if (exception.getMessage() != null) {
                LOGGER.info(environment.getProperty(exception.getMessage(), "Something went wrong. Please check log file for more details."));
            }
        }
    }

    private void addCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(6);
        customerDTO.setEmailId("harry@infy.com");
        customerDTO.setName("Harry");
        customerDTO.setDateOfBirth(LocalDate.now());
        try {
            customerServiceImpl.addCustomer(customerDTO);
            LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
        } catch (Exception exception) {
            if (exception.getMessage() != null) {
                LOGGER.info(environment.getProperty(exception.getMessage(), "Something went wrong. Please check log file for more details."));
            }
        }
    }
}
