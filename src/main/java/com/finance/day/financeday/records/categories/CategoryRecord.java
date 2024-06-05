package com.finance.day.financeday.records.categories;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;

public record CategoryRecord(@NotEmpty String name, String description) {
}
