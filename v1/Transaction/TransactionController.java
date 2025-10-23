package com.zexson.signor_p.Transaction;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.CardModule.Card;
import com.zexson.signor_p.CardModule.CardService;
import com.zexson.signor_p.DTO.ByUserDTO;
import com.zexson.signor_p.DTO.TransactionCreateDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController implements ITransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    CardService cardService;

    @GetMapping("/all")
    @Override
    public BaseResponse all() {
        return new BaseResponse(this.transactionService.findAll(), true, "All Transactions");
    }

    @PostMapping("/create")
    @Transactional
    @Override
    public BaseResponse create(@Valid @RequestBody TransactionCreateDTO data) {
        if (data.getFromCardId().equals(data.getToCardId())) {
            return new BaseResponse(null, false, "Dont be cards similar");
        }
        Card fromCard = cardService.findById(data.getFromCardId());
        Card toCard = cardService.findById(data.getToCardId());
        Transaction newTransaction = new Transaction();
        fromCard.setBalance(fromCard.getBalance() - data.getAmount());
        toCard.setBalance(toCard.getBalance() + data.getAmount());
        this.cardService.save(fromCard);
        this.cardService.save(toCard);
        newTransaction.setFromUserId(fromCard.getUser().getId());
        newTransaction.setToUserId(toCard.getUser().getId());
        transactionService.save(newTransaction);
        return new BaseResponse(newTransaction, true, "Transaction Create Successfull");
    }

    @PostMapping("/by-user")
    @Override
    public BaseResponse byUser(@RequestBody ByUserDTO data) {
        List<Transaction> allTransactions = this.transactionService.findByUserId(data.getUserId(), data.getUserId());
        /* for (int i = 0; i < allTransactions.size(); i++) {
            Long fromUserId = allTransactions.get(i).getFromUserId();
            Long toUserId = allTransactions.get(i).getToUserId();
            if (!fromUserId.equals(data.getUserId()) && !toUserId.equals(data.getUserId())) {
                allTransactions.remove(i);
                i--;
            }
        } */
        return new BaseResponse(allTransactions, true, "Transactions by User");
    }

}
