package kz.din.transactions.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExchangeRateDTO (
    @JsonProperty("1. From_Currency Code")
     String fromCurrencyCode,

    @JsonProperty("2. From_Currency Name")
     String fromCurrencyName,

    @JsonProperty("3. To_Currency Code")
     String toCurrencyCode,

    @JsonProperty("4. To_Currency Name")
     String toCurrencyName,

    @JsonProperty("5. Exchange Rate")
     String exchangeRate,

    @JsonProperty("6. Last Refreshed")
     String lastRefreshed,

    @JsonProperty("7. Time Zone")
     String timeZone,

    @JsonProperty("8. Bid Price")
     String bidPrice,

    @JsonProperty("9. Ask Price")
     String askPrice){}
