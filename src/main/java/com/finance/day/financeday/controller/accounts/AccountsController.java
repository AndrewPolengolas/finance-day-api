package com.finance.day.financeday.controller.accounts;

import com.finance.day.financeday.records.accounts.*;
import com.finance.day.financeday.services.accounts.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountsController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountRecord accountRecord){
        return accountService.createAccount(accountRecord);
    }

    @PutMapping("/edit/{accountId}")
    public ResponseEntity<?> editAccount(@PathVariable("accountId") Long accountId, @RequestBody @Valid UpdateAccountRecord updateAccountRecord) {
        return accountService.editAccount(accountId, updateAccountRecord);
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId") Long accountId) {
        return accountService.deleteAccount(accountId);
    }

    @GetMapping("/all")
    public ResponseEntity<?> listAllAccounts(){
        return accountService.listAllAccounts();
    }
}
