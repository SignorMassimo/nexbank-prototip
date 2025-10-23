package com.zexson.signor_p.Transaction;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Object> {

    public List<Transaction> findByFromUserIdOrToUserId(Long fromUserId, Long toUserId);
}
