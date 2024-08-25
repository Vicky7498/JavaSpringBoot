package com.infy.repository;

import com.infy.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Optional<Customer> findByEmailId(String emailId);

    Optional<Customer> findByEmailIdAndName(String emailId, String name);

    List<Customer> findByEmailIdOrName(String emailId, String name);

    List<Customer> findByDateOfBirthBetween(LocalDate fromDate, LocalDate toDate);

    List<Customer> findByDateOfBirthLessThan(LocalDate dateOfBirth);

    List<Customer> findByDateOfBirthGreaterThan(LocalDate dateOfBirth);

    List<Customer> findByDateOfBirthAfter(LocalDate dateOfBirth);

    List<Customer> findByDateOfBirthBefore(LocalDate dateOfBirth);

    List<Customer> findByEmailIdNull();

    List<Customer> findByNameLike(String namePattern);

    List<Customer> findByNameOrderByDateOfBirth(String name);

    List<Customer> findByNameOrderByDateOfBirthDesc(String name);

    @Query("SELECT c.customerId FROM Customer c WHERE c.emailId = :emailId")
    String findCustomerIdByEmailId(@Param("emailId") String emailId);

    @Query("UPDATE Customer c SET c.emailId = :emailId WHERE c.customerId = :customerId")
    @Modifying
    @Transactional
    Integer updateCustomerEmailId(@Param("emailId") String emailId, @Param("customerId") Integer customerId);

    @Query("DELETE FROM Customer c WHERE c.emailId = :emailId")
    @Modifying
    @Transactional
    Integer deleteCustomerByEmailId(@Param("emailId") String emailId);

    String findEmailIdByCustomerId(@Param("customerId") Integer customerId);
}
