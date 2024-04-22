package kz.din.transactions.service;

import kz.din.transactions.model.entity.CurrencyShortName;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface ExchangeRateService {

    void refreshExchangeRate();
    public BigDecimal convertCurrency(BigDecimal amount, CurrencyShortName fromCurrency, CurrencyShortName toCurrency);
}
