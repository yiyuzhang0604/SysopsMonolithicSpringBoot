package com.sysops.service;

import com.sysops.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction getTransactionById(Long id);

    Transaction saveTransaction(Transaction transaction);

    void deleteTransactionById(Long id);

    Transaction updateTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByCustomerId(Long id);

    List<Transaction> getTransactionByExpertId(Long id);

    List<Transaction> getTransactionsByOrderId(Long id);
}
