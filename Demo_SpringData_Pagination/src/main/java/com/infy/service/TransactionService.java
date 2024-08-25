package com.infy.service;

import com.infy.dto.TransactionDTO;
import com.infy.exception.InfyBankException;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    public List<TransactionDTO> getAllTransaction(Integer pageNo, Integer pageSize) throws InfyBankException;

    public List<TransactionDTO> getAllTransactionByTransactionDateAfter(LocalDate afterDate, Integer pageNo, Integer pageSize) throws InfyBankException;
}
