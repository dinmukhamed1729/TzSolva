package kz.din.transactions.model.dto;

import kz.din.transactions.model.entity.CurrencyShortName;

import java.math.BigDecimal;


public record UserDTO (
     String name,
     CurrencyShortName currencyShortName,
     BigDecimal limitSum){ }