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
import java.util.List;

@SpringBootApplication
public class DemoSpringDataQueryCreationApplication implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(DemoSpringDataQueryCreationApplication.class);

    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringDataQueryCreationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        findByEmailId();
        findByEmailIdAndName();
        findByEmailIdOrName();
        findByDateOfBirthBetween();
        findByDateOfBirthLessThan();
        findByDateOfBirthGreaterThan();
        findByDateOfBirthAfter();
        findByDateOfBirthBefore();
        findByEmailIdIsNull();
        findByNameLike();
        findByNameOrderByDateOfBirth();
        findByNameOrderByDateOfBirthDesc();
        findCustomerIdByEmailId();
        updateCustomerByEmailId();
        deleteCustomerByEmailId();
        findEmailIdByCustomerId();
    }

    public void findByEmailId() {
        try {
            CustomerDTO customerDTO = customerService.findByEmailId("martin@infy.com");
            LOGGER.info(customerDTO);
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findByEmailIdAndName() {
        try {
            CustomerDTO customerDTO = customerService.findByEmailIdAndName("martin@infy.com", "martin");
            LOGGER.info(customerDTO);
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findByEmailIdOrName() {
        try {
            List<CustomerDTO> customerDTOs = customerService.findByEmailIdOrName("martin@infy.com", "martin");
            customerDTOs.forEach(customerDTO -> {
                LOGGER.info(customerDTO);
            });
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findByDateOfBirthBetween() {
        try {
            LocalDate fromDate = LocalDate.of(1995, 1, 1);
            LocalDate toDate = LocalDate.of(2000, 12, 31);
            List<CustomerDTO> customerDTOs = customerService.findByDateOfBirthBetween(fromDate, toDate);
            customerDTOs.forEach(customerDTO -> {
                LOGGER.info(customerDTO);
            });
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findByDateOfBirthLessThan() {
        try {
            LocalDate dateOfBirth = LocalDate.of(2000, 12, 31);
            List<CustomerDTO> customerDTOs = customerService.findByDateOfBirthLessThan(dateOfBirth);
            customerDTOs.forEach(customerDTO -> {
                LOGGER.info(customerDTO);
            });
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findByDateOfBirthGreaterThan() {
        try {
            LocalDate dateOfBirth = LocalDate.of(1995, 12, 31);
            List<CustomerDTO> customerDTOs = customerService.findByDateOfBirthGreaterThan(dateOfBirth);
            customerDTOs.forEach(customerDTO -> {
                LOGGER.info(customerDTO);
            });
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findByDateOfBirthAfter() {
        try {
            LocalDate dateOfBirth = LocalDate.of(1995, 12, 31);
            List<CustomerDTO> customerDTOs = customerService.findByDateOfBirthAfter(dateOfBirth);
            customerDTOs.forEach(customerDTO -> {
                LOGGER.info(customerDTO);
            });
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findByDateOfBirthBefore() {
        try {
            LocalDate dateOfBirth = LocalDate.of(2000, 12, 31);
            List<CustomerDTO> customerDTOs = customerService.findByDateOfBirthBefore(dateOfBirth);
            customerDTOs.forEach(customerDTO -> {
                LOGGER.info(customerDTO);
            });
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findByEmailIdIsNull() {
        try {
            List<CustomerDTO> customerDTOs = customerService.findByEmailIdNull();
            customerDTOs.forEach(customerDTO -> {
                LOGGER.info(customerDTO);
            });
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findByNameLike() {
        try {
            List<CustomerDTO> customerDTOs = customerService.findByNameLike("j%");
            customerDTOs.forEach(customerDTO -> {
                LOGGER.info(customerDTO);
            });
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findByNameOrderByDateOfBirth() {
        try {
            List<CustomerDTO> customerDTOs = customerService.findByNameOrderByDateOfBirth("martin");
            customerDTOs.forEach(customerDTO -> {
                LOGGER.info(customerDTO);
            });
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    void findByNameOrderByDateOfBirthDesc() {
        try {
            List<CustomerDTO> customerDTOs = customerService.findByNameOrderByDateOfBirthDesc("martin");
            customerDTOs.forEach(customerDTO -> {
                LOGGER.info(customerDTO);
            });
            LOGGER.info("\n");
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }


    public void findCustomerIdByEmailId() {
        try {
            String name = customerService.findNameByEmailId("john@infy.com");
            LOGGER.info("Customer Id : " + name);
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void updateCustomerByEmailId() {
        try {
            customerService.updateCustomerEmailId("martin02@infy.com", 1);
            LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void deleteCustomerByEmailId() {
        try {
            customerService.deleteCustomerByEmailId("martin02@infy.com");
            LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }

    public void findEmailIdByCustomerId() {
        try {
            String customerEmailId = customerService.findEmailIdByCustomerId(2);
            LOGGER.info("Customer EmailId : " + customerEmailId);
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(), "Something went wrong. Please check log file for more details."));
        }
    }
}
