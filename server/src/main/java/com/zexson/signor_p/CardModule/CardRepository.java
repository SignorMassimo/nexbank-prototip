package com.zexson.signor_p.CardModule;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    public List<Card> findAllByUserId(Long userId);

    public Card findByCardNumber(String cardNumber);
}
