package com.zexson.signor_p.Transaction;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.CardModule.Card;
import com.zexson.signor_p.CardModule.CardService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    TransactionService transactionService;
    CardService cardService;

    public TransactionController(TransactionService transactionService, CardService cardService) {
        this.transactionService = transactionService;
        this.cardService = cardService;
    }

    @PostMapping("/all")
    public BaseResponse all() {
        return new BaseResponse(this.transactionService.findAll(), true, "All Transactions");
    }

    @PostMapping("/create")
    @Transactional
    public BaseResponse create(@RequestBody Transaction data) {
        if (data.getFromCardId().equals(data.getToCardId())) {
            return new BaseResponse(null, false, "Dont be cards similar");
        }
        Card fromCard = cardService.findById(data.getFromCardId());
        Card toCard = cardService.findById(data.getToCardId());
        fromCard.setBalance(fromCard.getBalance() - data.getAmount());
        toCard.setBalance(toCard.getBalance() + data.getAmount());
        this.cardService.save(fromCard);
        this.cardService.save(toCard);
        data.setFromUserId(Long.parseLong(fromCard.getUserId()));
        data.setToUserId(Long.parseLong(toCard.getUserId()));
        Transaction newTransaction = this.transactionService.transactionRepo.save(data);
        return new BaseResponse(newTransaction, true, "Transaction Create Successfull");
    }

}
