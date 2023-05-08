package com.sysops.controller;
import com.sysops.entity.Transaction;
import com.sysops.service.Implementation.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/transaction")
public class TransactionController {
    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @GetMapping("/")
    public List<Transaction> getAllTransactions() {
        return transactionServiceImpl.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionServiceImpl.getTransactionById(id);
    }

    @GetMapping("/customer/{id}")
    public List<Transaction> getTransactionsByCustomerId(@PathVariable Long id) {
        return transactionServiceImpl.getTransactionsByCustomerId(id);
    }

    @GetMapping("/expert/{id}")
    public List<Transaction> getTransactionsByExperttId(@PathVariable Long id) {
        return transactionServiceImpl.getTransactionByExpertId(id);
    }

    @GetMapping("/order/{id}")
    public List<Transaction> getTransactionsByOrderId(@PathVariable Long id) {
        return transactionServiceImpl.getTransactionsByOrderId(id);
    }

    @PostMapping("/")
    public Transaction saveTransaction(@PathVariable Transaction transaction) {
        return transactionServiceImpl.saveTransaction(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        return transactionServiceImpl.updateTransaction(transaction);
    }


}
