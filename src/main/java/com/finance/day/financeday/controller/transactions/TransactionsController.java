package com.finance.day.financeday.controller.transactions;

import com.finance.day.financeday.records.transactions.TransactionRecord;
import com.finance.day.financeday.repositories.users.UserRepository;
import com.finance.day.financeday.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionsController {

    private final UserUtils userUtils;
    private final UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRecord createTransactionRecord) {
        return null;
    }
}
