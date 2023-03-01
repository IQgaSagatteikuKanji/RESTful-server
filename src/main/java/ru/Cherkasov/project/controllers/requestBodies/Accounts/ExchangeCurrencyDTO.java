package ru.Cherkasov.project.controllers.requestBodies.Accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class ExchangeCurrencyDTO {
    @JsonProperty(value = "secret_key")
    @NotEmpty
    String secretKey;
    @JsonProperty(value = "currency_from")
    @NotEmpty
    String currencyFrom;
    @JsonProperty(value = "currency_to")
    @NotEmpty
    String currencyTo;
    @JsonProperty(value = "amount")
    @NotEmpty
    String amount;

    public String getSecretKey() {
        return secretKey;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public String getAmount() {
        return amount;
    }
}
