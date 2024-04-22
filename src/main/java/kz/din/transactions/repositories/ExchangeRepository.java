package kz.din.transactions.repositories;

import kz.din.transactions.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeRate,Long> {
    ExchangeRate findByFromCurrencyCodeAndToCurrencyCode(String fromCurrency, String toCurrency);
}
