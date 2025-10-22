package com.zexson.signor_p.CardModule;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.DTO.CardByUserDTO;
import com.zexson.signor_p.DTO.CardCreateDTO;
import com.zexson.signor_p.UserModule.User;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cards")
public class CardController implements ICardController {

    @Autowired
    CardService cardService;

    @PostMapping("/by-user")
    @Override
    public BaseResponse byUser(@Valid @RequestBody CardByUserDTO card) {
        User user = this.cardService.findById(card.getUserId()).getUser();
        List<Card> cards = this.cardService.findAllByUserId(user.getId());
        return new BaseResponse(cards, true, "");
    }

    @PostMapping("/create")
    @Override
    public BaseResponse create(@RequestBody CardCreateDTO cardDTO) {
        Card card = this.cardService.create(cardDTO);
        return new BaseResponse(card, true, "Created Card Successful");
    }

    @PostMapping("/by-id")
    @Override
    public BaseResponse byId(@RequestBody Card card) {
        return new BaseResponse(this.cardService.cardRepo.findById(card.getId()), true, "");
    }

    @PutMapping("/{cardId}/balance")
    @Override
    public BaseResponse balance(@RequestBody Card card, @PathVariable Long cardId) {
        return new BaseResponse(null, this.cardService.transferBalance(card, cardId), "");
    }

    @PostMapping("/by-number")
    @Override
    public BaseResponse byNumber(@RequestBody Card card) {
        return new BaseResponse(cardService.cardRepo.findByCardNumber(card.getCardNumber()), true, "");
    }

    @PostMapping("/delete")
    @Override
    public BaseResponse delete(@RequestBody Card card) {
        return new BaseResponse(null, this.cardService.delete(card.getId()), "");
    }
}
