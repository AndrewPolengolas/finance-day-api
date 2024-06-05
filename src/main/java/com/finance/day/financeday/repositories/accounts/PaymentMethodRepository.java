package com.finance.day.financeday.repositories.accounts;

import com.finance.day.financeday.entities.Account;
import com.finance.day.financeday.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    PaymentMethod findByAccountAndName(Account account, String name);

    List<PaymentMethod> findByAccount(Account account);
}
