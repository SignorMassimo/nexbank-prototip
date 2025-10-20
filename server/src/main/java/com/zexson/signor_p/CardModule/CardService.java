package com.zexson.signor_p.CardModule;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    CardRepository cardRepo;

    public CardService(CardRepository cardRepo) {
        this.cardRepo = cardRepo;
    }

    public List<Card> findAllByUserId(String userId) {
        return this.cardRepo.findAllByUserId(userId);
    }

    public Card findByCardNumber(String cardNumber) {
        return this.cardRepo.findByCardNumber(cardNumber);
    }

    public Card findById(Long id) {
        return this.cardRepo.findById(id).orElse(null);
    }

    public Card save(Card card) {
        return this.cardRepo.save(card);
    }
}
