package com.finance.day.financeday.services.paymentMethods;

import com.finance.day.financeday.entities.Account;
import com.finance.day.financeday.entities.PaymentMethod;
import com.finance.day.financeday.entities.User;
import com.finance.day.financeday.records.accounts.PaymentMethodRecord;
import com.finance.day.financeday.repositories.accounts.AccountRepository;
import com.finance.day.financeday.repositories.accounts.PaymentMethodRepository;
import com.finance.day.financeday.repositories.users.UserRepository;
import com.finance.day.financeday.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentMethodService {

    private final AccountRepository accountRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;
    private final UserUtils userUtils;

    public ResponseEntity<?> addPaymentMethod(Long accountId, PaymentMethodRecord paymentMethodRecord) {

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (account.getUser() != user)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        PaymentMethod paymentMethod = paymentMethodRepository.findByAccountAndName(account, paymentMethodRecord.name());

        if (paymentMethod != null)
            return new ResponseEntity<>(HttpStatus.OK);

        paymentMethod = PaymentMethod.builder()
                .account(account)
                .name(paymentMethodRecord.name())
                .build();

        paymentMethodRepository.save(paymentMethod);

        return new ResponseEntity<>(paymentMethod, HttpStatus.CREATED);
    }

    public ResponseEntity<?> editPaymentMethod(Long paymentMethodId, PaymentMethodRecord paymentMethodRecord) {
        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        Account account = accountRepository.findById(paymentMethod.getAccount().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (account.getUser() != user)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        paymentMethod.setName(paymentMethodRecord.name());

        paymentMethodRepository.save(paymentMethod);

        return new ResponseEntity<>(paymentMethod, HttpStatus.OK);
    }

    public ResponseEntity<?> deletePaymentMethod(Long paymentMethodId) {
        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        Account account = accountRepository.findById(paymentMethod.getAccount().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (account.getUser() != user)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        paymentMethodRepository.delete(paymentMethod);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> listAllPaymentMethods(Long accountId){

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (account.getUser() != user)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        List<PaymentMethod> paymentMethods = paymentMethodRepository.findByAccount(account);

        return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
    }
}
