package com.finance.day.financeday.records.accounts;

import jakarta.validation.constraints.NotEmpty;

public record UpdateAccountRecord (@NotEmpty String name, @NotEmpty String color) {
}
