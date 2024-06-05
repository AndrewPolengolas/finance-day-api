package com.finance.day.financeday.records.accounts;

import jakarta.validation.constraints.NotEmpty;

public record PaymentMethodRecord(@NotEmpty String name) {
}
