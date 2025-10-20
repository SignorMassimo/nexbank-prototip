package com.zexson.signor_p.Transaction;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    TransactionRepository transactionRepo;

    public TransactionService(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<Transaction> findAll() {
        return this.transactionRepo.findAll();
    }
}
