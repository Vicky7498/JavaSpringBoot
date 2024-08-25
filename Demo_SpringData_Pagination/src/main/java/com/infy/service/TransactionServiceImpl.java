package com.infy.service;

import com.infy.dto.TransactionDTO;
import com.infy.entity.Transaction;
import com.infy.exception.InfyBankException;
import com.infy.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionDTO> getAllTransaction(Integer pageNo, Integer pageSize) throws InfyBankException {
        Sort sort = Sort.by("transactionDate").descending().and(Sort.by("transactionAmount").descending());
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Transaction> page = transactionRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new InfyBankException("Service.NO_CUSTOMERS_IN_THIS_PAGE");
        }
        List<Transaction> transactionList = page.getContent();
        return transactionList.stream().map(p -> new TransactionDTO(p.getTransactionId(), p.getTransactionDate(), p.getTransactionAmount())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getAllTransactionByTransactionDateAfter(LocalDate afterDate, Integer pageNo, Integer pageSize) throws InfyBankException {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Transaction> transactionList = transactionRepository.findByTransactionDateAfter(afterDate, pageable);
        if (transactionList.isEmpty()) {
            throw new InfyBankException("Service.NO_CUSTOMERS_IN_THIS_PAGE");
        }
        return transactionList.stream().map(transaction -> new TransactionDTO(transaction.getTransactionId(), transaction.getTransactionDate(), transaction.getTransactionAmount())).collect(Collectors.toList());
    }
}
