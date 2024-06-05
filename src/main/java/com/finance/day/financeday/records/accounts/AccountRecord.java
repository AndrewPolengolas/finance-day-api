package com.finance.day.financeday.records.accounts;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AccountRecord(@NotEmpty String name, List<String> paymentMethods, String color) {
}
