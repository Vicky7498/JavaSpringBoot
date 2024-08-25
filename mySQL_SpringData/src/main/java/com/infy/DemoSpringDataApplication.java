package com.infy;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;
import com.infy.service.CustomerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.List;

@SpringBootApplication
public class DemoSpringDataApplication implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(DemoSpringDataApplication.class);
    @Autowired
    CustomerServiceImpl customerServiceImpl;
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        getCustomerDetails();
        getCustomerNameAndDob();
        getCustomer();
        getCustomerDetailsByCustomerId(1002);
        updateCityOfCustomer(1002, "Seattle");
        deleteCustomer(1006);
    }

    private void deleteCustomer(Integer customerId) {
        try {
            Integer deleteCustomer = customerServiceImpl.deleteCustomer(customerId);
            LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
            LOGGER.info("/n");
        } catch (Exception exception) {
            String message = environment.getProperty(exception.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    private void updateCityOfCustomer(Integer customerId, String city) {
        try {
            customerServiceImpl.updateCityOfCustomer(customerId, city);
            LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
            LOGGER.info("/n");
        } catch (Exception exception) {
            String message = environment.getProperty(exception.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    private void getCustomerDetailsByCustomerId(Integer customerId) {
        try {
            List<CustomerDTO> customerNames = customerServiceImpl.getCustomerByCustomerId(customerId);
            for (CustomerDTO customerName : customerNames) {
                LOGGER.info(customerName);
            }
        } catch (Exception exception) {
            String message = environment.getProperty(exception.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    private void getCustomer() {
        try {
            List<String> customerNames = customerServiceImpl.getCustomerName();
            for (String customerName : customerNames) {
                LOGGER.info(customerName);
            }
        } catch (Exception exception) {
            String message = environment.getProperty(exception.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    private void getCustomerNameAndDob() {
        try {
            List<Object[]> objects = customerServiceImpl.getCustomerNameAndDob();
            for (Object[] object : objects) {
                LOGGER.info(object[0] + "/t/t" + object[1]);
            }
            LOGGER.info("/n");
        } catch (Exception exception) {
            String message = environment.getProperty(exception.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    private void getCustomerDetails() {
        try {
            List<CustomerDTO> customerDTOS = customerServiceImpl.getCustomerDetails();
            for (CustomerDTO customerDTO : customerDTOS) {
                LOGGER.info(customerDTO);
            }
            LOGGER.info("/n");
        } catch (Exception exception) {
            String message = environment.getProperty(exception.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
}
