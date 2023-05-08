package com.sysops.service.Implementation;

import com.sysops.dao.TransactionDao;
import com.sysops.entity.Transaction;
import com.sysops.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {



    @Autowired
    private TransactionDao transactionDao;

    public Transaction getTransactionById(Long id) {
        return transactionDao.findById(id).orElse(null);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    public void deleteTransactionById(Long id) {
        transactionDao.deleteById(id);
    }

    public Transaction updateTransaction(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionDao.findAll();
    }

    public List<Transaction> getTransactionsByCustomerId(Long id) {
        return transactionDao.findByCustomerId(id);
    }

    public List<Transaction> getTransactionByExpertId(Long id) {
        return transactionDao.findByExpertId(id);
    }

    public List<Transaction> getTransactionsByOrderId(Long id) {
        return transactionDao.findByOrderId(id);
    }

}
