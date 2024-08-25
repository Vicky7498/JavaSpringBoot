package com.infy.repository;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Objects;

public interface CustomerRespository {
    public List<CustomerDTO> getCustomerDetails();

    public List<Object[]> getCustomerNameAndDob();

    public List<String> customerName();

    public List<CustomerDTO> getCustomerDetailsWithCustomerId(Integer customerId);

    public Integer updateCityOfCustomer(Integer customerId, String city);

    public Integer deleteCustomerById(Integer customerId);
}
