package com.infy.service;

import com.infy.dto.LoanDTO;
import com.infy.exception.InfyBankException;

public interface CustomerLoanService {
    public LoanDTO getLoanDetails(Integer loanId) throws InfyBankException;
    public Integer addLoanAndCustomer(LoanDTO loanDTO) throws InfyBankException;
}
