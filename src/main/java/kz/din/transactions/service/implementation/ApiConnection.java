package kz.din.transactions.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import kz.din.transactions.model.dto.ExchangeRateDTO;
import kz.din.transactions.service.ApiService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Component
public class ApiConnection {

    @Value("${api.baseUrl}")
    private String baseUrl;
    @Value("${api.key}")
    private String apiKey;
    private ApiService apiService;

    @PostConstruct
    private void initialize() {
        this.apiService = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build().create(ApiService.class);
    }

    public ExchangeRateDTO getExchangeRate(String fromCurrency, String toCurrency) {
        System.out.println(baseUrl + fromCurrency + toCurrency + apiKey);
        try {
            Call<JsonNode> response = this.apiService.getExchangeRate(fromCurrency, toCurrency, apiKey);
            Response<JsonNode> jsonResponse = response.execute();
            if (jsonResponse.isSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = jsonResponse.body();
                assert jsonNode != null;
                return   mapper.treeToValue(jsonNode.get("Realtime Currency Exchange Rate"), ExchangeRateDTO.class);
            } else {
                throw new RuntimeException("Failed to get exchange rate. Response code: " + jsonResponse.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error executing request", e);
        }
    }
}