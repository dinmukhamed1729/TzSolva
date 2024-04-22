package kz.din.transactions.model.dto;

import kz.din.transactions.model.entity.CurrencyShortName;
import kz.din.transactions.model.entity.Expenses;
import lombok.NonNull;

import java.math.BigDecimal;


public record TransactionsDTO(
        @NonNull String accountFrom,
        @NonNull String accountTo,
        @NonNull CurrencyShortName currencyShortname,
        @NonNull BigDecimal transactionAmount,
        @NonNull Expenses expenseCategory) { }
