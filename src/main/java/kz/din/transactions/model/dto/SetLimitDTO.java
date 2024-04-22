package kz.din.transactions.model.dto;

import kz.din.transactions.model.entity.CurrencyShortName;
import lombok.NonNull;

import java.math.BigDecimal;

public record SetLimitDTO(
        @NonNull String bankAccount,
        @NonNull BigDecimal limitSum,
        @NonNull CurrencyShortName limitCurrencyShortname) { }

