package com.infy;

import com.infy.dto.CustomerDTO;
import com.infy.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringBootStoredProcedureExecutionApplication implements CommandLineRunner {
    public static final Logger LOGGER = LogManager.getLogger(SpringBootStoredProcedureExecutionApplication.class);
    @Autowired
    private CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStoredProcedureExecutionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<CustomerDTO> cList = customerService.getCustomerDetailsByDateOfBirth(LocalDate.of(1994, 05, 20));
        LOGGER.info("Details of customers born after 20-May-1994");
        LOGGER.info(cList);
        List<CustomerDTO> customers = customerService.getCustomerDetailsByNameAndDateOfBirth("alice", LocalDate.of(2000, 05, 20));
        LOGGER.info("Details of customers based on name and date of birth");
        LOGGER.info(customers);
        Integer count = customerService.getTotalCustomersByEmailId("alice01@infy.com");
        LOGGER.info("No. of customers with emailId 'alice01@infy.com' : " + count);
    }
}
