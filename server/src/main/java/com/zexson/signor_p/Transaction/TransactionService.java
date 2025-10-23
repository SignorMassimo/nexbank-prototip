package com.zexson.signor_p.Transaction;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepo;

    @Override
    public List<Transaction> findAll() {
        return this.transactionRepo.findAll();
    }

    @Override
    public Transaction save(Transaction transaction) {
        return this.transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> findByUserId(Long fromUserId, Long toUserId) {
        return this.transactionRepo.findByFromUserIdOrToUserId(fromUserId, toUserId);
    }
}
