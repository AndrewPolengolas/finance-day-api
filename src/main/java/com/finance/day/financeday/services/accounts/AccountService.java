package com.finance.day.financeday.services.accounts;

import com.finance.day.financeday.entities.Account;
import com.finance.day.financeday.entities.User;
import com.finance.day.financeday.records.accounts.AccountRecord;
import com.finance.day.financeday.records.accounts.UpdateAccountRecord;
import com.finance.day.financeday.repositories.accounts.AccountRepository;
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
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final UserUtils userUtils;

    public ResponseEntity<?> createAccount(AccountRecord accountRecord) {

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Account account = accountRepository.findByNameAndUser(accountRecord.name(), user);

        if (account != null)
            return new ResponseEntity<>(HttpStatus.OK);

        account = Account.builder()
                .name(accountRecord.name())
                .color(accountRecord.color() != null ? accountRecord.color() : "D3D3D3")
                .user(user)
                .build();

        accountRepository.save(account);

        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    public ResponseEntity<?> editAccount(Long accountId, UpdateAccountRecord updateAccountRecord) {

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (account.getUser() != user)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        account.setName(updateAccountRecord.name());
        account.setColor(updateAccountRecord.color());

        accountRepository.save(account);

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAccount(Long accountId) {

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (account.getUser() != user)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        accountRepository.delete(account);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> listAllAccounts(){

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        List<Account> accounts = accountRepository.findByUser(user);

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
