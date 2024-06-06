package com.finance.day.financeday.records.transactions;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Date;

public record TransactionRecord(
        @NotEmpty Long categoryId,
        Long accountId,
        @NotEmpty BigDecimal amount,
        Date transactionDate,
        @NotEmpty String description) {
}
