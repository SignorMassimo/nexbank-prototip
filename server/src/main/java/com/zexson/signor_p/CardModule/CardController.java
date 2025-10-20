package com.zexson.signor_p.CardModule;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.Response.CreateCardResponse;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/cards")
public class CardController {

    CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/by-user")
    public BaseResponse postMethodName(@RequestBody Card card) {
        List<Card> cards = this.cardService.findAllByUserId(card.getUserId());
        return new BaseResponse(cards, true, "");
    }

    @PostMapping("/create")
    public BaseResponse create(@RequestBody Card card) {
        card.setBalance(0.0);
        cardService.cardRepo.save(card);
        return new BaseResponse(new CreateCardResponse(card), true, "Created Card Successful");
    }

    @PostMapping("/by-id")
    public BaseResponse byId(@RequestBody Card card) {
        return new BaseResponse(this.cardService.cardRepo.findById(card.getId()), true, "");
    }

    @PutMapping("/{cardId}/balance")
    @Transactional
    public BaseResponse balance(@RequestBody Card card, @PathVariable Long cardId) {
        Card existsCard = this.cardService.cardRepo.findById(cardId).orElse(null);
        if (existsCard == null) {
            return new BaseResponse(null, false, "");
        }
        existsCard.setBalance(card.getBalance());
        cardService.cardRepo.save(existsCard);
        return new BaseResponse(null, true, "");
    }

    @PostMapping("/by-number")
    public BaseResponse byNumber(@RequestBody Card card) {
        return new BaseResponse(cardService.cardRepo.findByCardNumber(card.getCardNumber()), true, "");
    }


    /* @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
     BaseResponse exceptionHandler(IllegalArgumentException ex) {
        return new BaseResponse(null, false, ex.getMessage());
    } */
}
