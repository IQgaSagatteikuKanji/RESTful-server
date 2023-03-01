package ru.Cherkasov.project.controllers.requestBodies.Rates;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class CurrentRatesDTO {
    @JsonProperty(value = "currency")
    @NotEmpty
    String baseCurrency;
    @JsonProperty(value = "secret_key")
    @NotEmpty
    String secretKey;

    public String getBaseCurrency() {
        return baseCurrency;
    }
}
