package com.infy.api;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;
import com.infy.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/infybank")
@Validated
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private Environment environment;

    @GetMapping(value = "/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomer() throws InfyBankException {
        try {
            List<CustomerDTO> customerDTOList = customerService.getAllCustomer();
            return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
        }
    }

    @GetMapping(value = "/customers/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable @Min(value = 1, message = "Customer Id should be between 1 and 100") @Max(value = 1, message = "Customer Id should be between 1 and 100") Integer customerId) throws InfyBankException {
        try {
            CustomerDTO customerDTO = customerService.getCustomer(customerId);
            return new ResponseEntity<>(customerDTO, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
        }
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws InfyBankException {
        try {
            Integer customerId = customerService.addCustomer(customerDTO);
            String successMessage = environment.getProperty("API.INSERT_SUCCESS") + customerId;
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
        }
    }

    @PutMapping(value = "/customers/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable Integer customerId, @RequestBody CustomerDTO customerDTO) throws InfyBankException {
        try {
            customerService.updateCustomer(customerId, customerDTO.getEmailId());
            String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
        }
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) throws InfyBankException {
        try {
            customerService.deleteCustomer(customerId);
            String successMessage = environment.getProperty("API.DELETE_SUCCESS");
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
        }
    }
}
