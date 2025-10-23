package com.zexson.signor_p.CardModule;

import java.util.List;
import com.zexson.signor_p.DTO.CardBalanceDTO;
import com.zexson.signor_p.DTO.CardCreateDTO;

public interface ICardService {

    public Card create(CardCreateDTO cardDTO);

    public List<Card> findAllByUserId(Long userId);

    public Card findByCardNumber(String cardNumber);

    public Card findById(Long id);

    public Card save(Card card);

    public boolean transferBalance(CardBalanceDTO card, Long cardId);

    public boolean delete(Long cardId);
}
