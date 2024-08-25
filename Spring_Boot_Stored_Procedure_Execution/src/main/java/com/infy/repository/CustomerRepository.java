package com.infy.repository;

import com.infy.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Procedure
    List<Customer> findCustomersAfterDOB_SP(LocalDate dateOfBirth);

    @Query(value = "CALL findCustomersAfterDOB_SP(:dateOfBirth);", nativeQuery = true)
    List<Customer> findCustomersAfterDateOfBirth(@Param("dateOfBirth") LocalDate dateOfBirth);

    @Procedure("findCustomerByNameAndDOB_SP")
    List<Customer> findCustomerByNameAndDateOfBirth_SP(String name, LocalDate dateOfBirth);

//    @Procedure(procedureName = "findCustomerByNameAndDOB_SP")
//    List<Customer> findCustomerByNameAndDateOfBirth_SP(String name, LocalDate dateOfBirth);

//    @Procedure(value = "findCustomerByNameAndDOB_SP")
//    List<Customer> findCustomerByNameAndDateOfBirth_SP(String name,LocalDate dateOfBirth);

    @Procedure(name = "Customer.getTotalCustomersByEmailId")
    Integer getTotalCustomersByEmailId(@Param("email_id") String emailId);
}
