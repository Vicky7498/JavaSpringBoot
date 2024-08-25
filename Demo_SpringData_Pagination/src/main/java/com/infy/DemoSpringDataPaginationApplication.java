package com.infy;

import com.infy.dto.TransactionDTO;
import com.infy.exception.InfyBankException;
import com.infy.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoSpringDataPaginationApplication implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(DemoSpringDataPaginationApplication.class);

    @Autowired
    TransactionService transactionService;
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringDataPaginationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info(LocalDateTime.now());
        getAllTransactions();
        getAllTransactionsByTransactionDate();
        LOGGER.info(LocalDateTime.now());
    }

    private void getAllTransactions() {
        try {
            List<TransactionDTO> transactionDTOList = transactionService.getAllTransaction(0, 5);
            transactionDTOList.forEach(LOGGER::info);
        } catch (Exception exception) {
            String message = environment.getProperty(exception.getMessage(), "Some exception occurred. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    private void getAllTransactionsByTransactionDate() {
        try {
            LocalDate transactionDate = LocalDate.of(1996, 1, 29);
            List<TransactionDTO> transactionList = transactionService.getAllTransactionByTransactionDateAfter(transactionDate, 0, 2);
            transactionList.forEach(LOGGER::info);
        } catch (Exception exception) {
            String message = environment.getProperty(exception.getMessage(), "Some exception occurred. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
}
