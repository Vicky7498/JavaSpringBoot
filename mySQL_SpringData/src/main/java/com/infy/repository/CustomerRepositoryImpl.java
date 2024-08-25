package com.infy.repository;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "customerRepository")
public class CustomerRepositoryImpl implements CustomerRespository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerDTO> getCustomerDetails() {
        List<CustomerDTO> customerDTOS = null;
        String queryString = "select c from Customer c";
        Query query = entityManager.createQuery(queryString);
        List<Customer> customers = query.getResultList();
        customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setCity(customer.getCity());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public List<Object[]> getCustomerNameAndDob() {
        String queryString = "select c.name,c.dateOfBirth from Customer c";
        Query query = entityManager.createQuery(queryString);
        List<Object[]> result = query.getResultList();
        return result;
    }

    @Override
    public List<String> customerName() {
        List<String> customeNames = null;
        String queryString = "select c.name from Customer c";
        Query query = entityManager.createQuery(queryString);
        customeNames = query.getResultList();
        return customeNames;
    }

    @Override
    public List<CustomerDTO> getCustomerDetailsWithCustomerId(Integer customerId) {
        Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId");
        query.setParameter("customerId", customerId);
        List<Customer> customers = query.getResultList();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setCity(customer.getCity());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public Integer updateCityOfCustomer(Integer customerId, String city) {
        Query query = entityManager.createQuery("UPDATE Customer c SET c.city=:city WHERE c.customerId = :customerId");
        query.setParameter("city", city);
        query.setParameter("customerId", customerId);
        return query.executeUpdate();
    }

    @Override
    public Integer deleteCustomerById(Integer customerId) {
        Query query = entityManager.createQuery("DELETE FROM Customer c where c.customerId = :customerId");
        query.setParameter("customerId", customerId);
        return query.executeUpdate();
    }
}
