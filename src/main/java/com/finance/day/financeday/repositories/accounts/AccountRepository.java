package com.finance.day.financeday.repositories.accounts;

import com.finance.day.financeday.entities.Account;
import com.finance.day.financeday.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByNameAndUser(String name, User user);

    List<Account> findByUser(User user);
}
