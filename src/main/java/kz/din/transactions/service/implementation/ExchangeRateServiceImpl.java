package kz.din.transactions.service.implementation;

import kz.din.transactions.mapper.ExchangeRateMapper;
import kz.din.transactions.model.entity.CurrencyShortName;
import kz.din.transactions.repositories.ExchangeRepository;
import kz.din.transactions.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRepository exchangeRepository;
    private final ApiConnection apiConnection;
    private final ExchangeRateMapper mapper = ExchangeRateMapper.mapper;
    

    @Scheduled(cron = "0 0 3 * * ?")
    public void refreshDailyExchangeRate(){
        refreshExchangeRate();
    }
    @Override
    public void refreshExchangeRate() {
        updateExchangeRatesFromApi(CurrencyShortName.KZT, CurrencyShortName.USD);
        updateExchangeRatesFromApi(CurrencyShortName.RUB, CurrencyShortName.KZT);
    }

    private void updateExchangeRatesFromApi(CurrencyShortName fromCurrency, CurrencyShortName toCurrency) {

        var existingExchangeRate = exchangeRepository.findByFromCurrencyCodeAndToCurrencyCode(fromCurrency.name(), toCurrency.name());
        var exchangeRateDTO = apiConnection.getExchangeRate(fromCurrency.name(), toCurrency.name());
        var exchangeRate = mapper.toExchangeRate(exchangeRateDTO);

        if (existingExchangeRate != null) {
            existingExchangeRate.setExchangeRate(exchangeRate.getExchangeRate());
            existingExchangeRate.setLastRefreshed(exchangeRate.getLastRefreshed());
            exchangeRepository.save(existingExchangeRate);
        } else
            exchangeRepository.save(exchangeRate);

    }
    @Override
    public BigDecimal convertCurrency(BigDecimal amount, CurrencyShortName fromCurrency, CurrencyShortName toCurrency) {
        var exchangeRate = exchangeRepository.findByFromCurrencyCodeAndToCurrencyCode(fromCurrency.name(), toCurrency.name());
        if (exchangeRate == null)
            throw new RuntimeException("Exchange rate not found for " + fromCurrency + " to " + toCurrency);

        BigDecimal exchangeRateValue = exchangeRate.getExchangeRate();
        return amount.multiply(exchangeRateValue);
    }

    
}
