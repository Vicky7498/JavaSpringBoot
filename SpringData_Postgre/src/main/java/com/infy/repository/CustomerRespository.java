package com.infy.repository;

import com.infy.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRespository extends CrudRepository<Customer, Integer> {
}
