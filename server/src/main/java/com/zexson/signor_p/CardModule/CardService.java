package com.zexson.signor_p.CardModule;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zexson.signor_p.DTO.CardCreateDTO;
import com.zexson.signor_p.UserModule.UserService;
import jakarta.transaction.Transactional;

@Service
public class CardService implements ICardService {

    @Autowired
    CardRepository cardRepo;
    @Autowired
    UserService userService;

    @Override
    public Card create(CardCreateDTO cardDTO) {
        Card card = new Card();
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardHolder(cardDTO.getCardHolder());
        card.setBalance(0.0);
        card.setUser(this.userService.findById(cardDTO.getUserId()));
        this.cardRepo.save(card);
        return card;
    }

    @Override
    public List<Card> findAllByUserId(Long userId) {
        return this.cardRepo.findAllByUserId(userId);
    }

    @Override
    public Card findByCardNumber(String cardNumber) {
        return this.cardRepo.findByCardNumber(cardNumber);
    }

    @Override
    public Card findById(Long id) {
        return this.cardRepo.findById(id).orElse(null);
    }

    @Override
    public Card save(Card card) {
        return this.cardRepo.save(card);
    }

    @Override
    @Transactional
    public boolean transferBalance(Card card, Long cardId) {
        Card existsCard = this.cardRepo.findById(cardId).orElse(null);
        if (existsCard == null) {
            return false;
        }
        existsCard.setBalance(card.getBalance());
        this.cardRepo.save(existsCard);
        return true;
    }

    @Override
    public boolean delete(Long cardId) {
        Card card = this.findById(cardId);
        if (card == null) {
            return false;
        }
        this.cardRepo.delete(card);
        return true;
    }
}
