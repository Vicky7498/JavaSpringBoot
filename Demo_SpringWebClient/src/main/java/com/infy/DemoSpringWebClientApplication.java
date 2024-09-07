package com.infy;

import com.infy.dto.CustomerDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@SpringBootApplication
public class DemoSpringWebClientApplication implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(DemoSpringWebClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringWebClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        getCustomerDetails(2);
        addCustomer();
        updateCustomer();
        deleteCustomer(3);
    }

    @SuppressWarnings("deprecation")
    private void deleteCustomer(Integer customerId) {
        String url = "http://localhost:8765/infybank/customers/{customerId}";
        WebClient webClient = WebClient.create();
        webClient.delete().uri(url, customerId).exchange().subscribe(clientResponse -> {
            if (clientResponse.statusCode().value() == 200) {
                LOGGER.info("Customer deleted successfully");
            } else {
                LOGGER.error("Failed to delete customer");
            }
        });
    }

    private void updateCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(2);
        customerDTO.setEmailId("tim123@infy.com");
        String url = "http://localhost:8765/infybank/customers/{customerId}";
        WebClient webClient = WebClient.create();
        String responce = webClient.put().uri(url, customerDTO.getCustomerId()).bodyValue(customerDTO).retrieve().bodyToMono(String.class).block();
        LOGGER.info(responce);
    }

    private void addCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Peter");
        customerDTO.setEmailId("peter@infy.com");
        customerDTO.setDateOfBirth(LocalDate.of(2024, 8, 07));
        String url = "http://localhost:8765/infybank/customers";
        WebClient webClient = WebClient.create();
        String response = webClient.post().uri(url).bodyValue(customerDTO).retrieve().bodyToMono(String.class).block();
        LOGGER.info(response);
    }

    private void getCustomerDetails(Integer customerId) {
        String url = "http://localhost:8765/infybank/customers/{customerId}";
        WebClient webClient = WebClient.create();
        CustomerDTO customerDTO = webClient.get().uri(url, customerId).retrieve().bodyToMono(CustomerDTO.class).block();
        LOGGER.info(customerDTO);
        LOGGER.info("\n");
    }
}
