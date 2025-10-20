package com.zexson.signor_p.CardModule;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Object> {

    public List<Card> findAllByUserId(String userId);

    public Card findByCardNumber(String cardNumber);
}
