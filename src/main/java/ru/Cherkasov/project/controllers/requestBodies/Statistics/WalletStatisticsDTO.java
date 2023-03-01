package ru.Cherkasov.project.controllers.requestBodies.Statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class WalletStatisticsDTO {
    @JsonProperty(value = "secret_key")
    @NotEmpty
    String secretKey;

    @JsonProperty(value = "currency")
    @NotEmpty
    String currency;

    public String getSecretKey() {
        return secretKey;
    }

    public String getCurrency() {
        return currency;
    }
}
