package com.zexson.signor_p.Transaction;

import java.util.List;

public interface ITransactionService {

    public List<Transaction> findAll();

    public Transaction save(Transaction transaction);

    public List<Transaction> findByUserId(Long fromUserId, Long toUserId);
}
