package kz.din.transactions.service;

import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {
    @GET("query?function=CURRENCY_EXCHANGE_RATE")
    Call<JsonNode> getExchangeRate(
            @Query("from_currency") String fromCurrency,
            @Query("to_currency") String toCurrency,
            @Query("apikey") String apiKey
    );
}
