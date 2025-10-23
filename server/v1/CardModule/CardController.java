package com.zexson.signor_p.CardModule;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.DTO.ByIdDTO;
import com.zexson.signor_p.DTO.ByUserDTO;
import com.zexson.signor_p.DTO.CardBalanceDTO;
import com.zexson.signor_p.DTO.CardByNumberDTO;
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
    public BaseResponse byUser(@Valid @RequestBody ByUserDTO card) {
        User user = this.cardService.findById(card.getUserId()).getUser();
        List<Card> cards = this.cardService.findAllByUserId(user.getId());
        return new BaseResponse(cards, true, "");
    }

    @PostMapping("/create")
    @Override
    public BaseResponse create(@Valid @RequestBody CardCreateDTO cardDTO) {
        Card card = this.cardService.create(cardDTO);
        return new BaseResponse(card, true, "Created Card Successful");
    }

    @PostMapping("/by-id")
    @Override
    public BaseResponse byId(@Valid @RequestBody ByIdDTO card) {
        if (card.getId() == null) {
            return new BaseResponse(null, false, "Card ID is required");
        }
        return new BaseResponse(cardService.findById(card.getId()), true, "");
    }

    @PutMapping("/balance")
    @Override
    public BaseResponse balance(@Valid @RequestBody CardBalanceDTO card) {
        return new BaseResponse(null, this.cardService.transferBalance(card, card.getCardId()), "");
    }

    @PostMapping("/by-number")
    @Override
    public BaseResponse byNumber(@Valid @RequestBody CardByNumberDTO card) {
        return new BaseResponse(cardService.cardRepo.findByCardNumber(card.getCardNumber()), true, "");
    }

    @PostMapping("/delete")
    @Override
    public BaseResponse delete(@Valid @RequestBody ByIdDTO card) {
        return new BaseResponse(null, this.cardService.delete(card.getId()), "");
    }
}
