package com.finance.day.financeday.services.transactions;

import com.finance.day.financeday.entities.User;
import com.finance.day.financeday.records.categories.CategoryRecord;
import com.finance.day.financeday.records.transactions.TransactionRecord;
import com.finance.day.financeday.repositories.accounts.AccountRepository;
import com.finance.day.financeday.repositories.accounts.PaymentMethodRepository;
import com.finance.day.financeday.repositories.categories.CategoryRepository;
import com.finance.day.financeday.repositories.users.UserRepository;
import com.finance.day.financeday.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private final UserUtils userUtils;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final AccountRepository accountRepository;

    public ResponseEntity<?> createTransaction(TransactionRecord transactionRecord) {

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());



        return null;
    }
}
