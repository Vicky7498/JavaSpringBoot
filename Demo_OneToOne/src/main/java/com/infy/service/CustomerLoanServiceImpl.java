package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.dto.LoanDTO;
import com.infy.entity.Address;
import com.infy.entity.Customer;
import com.infy.entity.Loan;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;
import com.infy.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "customerLoanService")
@Transactional
public class CustomerLoanServiceImpl implements CustomerLoanService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public LoanDTO getLoanDetails(Integer loanId) throws InfyBankException {
        Optional<Loan> optional = loanRepository.findById(loanId);
        Loan loan = optional.orElseThrow(() -> new InfyBankException("Service.LOAN_UNAVAILABLE"));
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setAmount(loan.getAmount());
        loanDTO.setLoanId(loan.getLoanId());
        loanDTO.setLoanIssueDate(loan.getLoanIssueDate());
        loanDTO.setStatus(loan.getStatus());
        Customer customer = loan.getCustomer();
        if (customer != null) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setDateOfBirth(customer.getDataOfBirth());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setName(customer.getName());
            loanDTO.setCustomerDTO(customerDTO);
        }
        return loanDTO;
    }

    @Override
    public Integer addLoanAndCustomer(LoanDTO loanDTO) throws InfyBankException {
        Loan loan = new Loan();
        loan.setAmount(loanDTO.getAmount());
        loan.setLoanIssueDate(loanDTO.getLoanIssueDate());
        loan.setStatus("open");
        CustomerDTO customerDTO = loanDTO.getCustomerDTO();
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setDataOfBirth(customerDTO.getDateOfBirth());
        customer.setEmailId(customerDTO.getEmailId());
        customer.setName(customerDTO.getName());
        Address address = null;
        customer.setAddress(address);
        loan.setCustomer(customer);
        loanRepository.save(loan);
        return loan.getLoanId();
    }
}
