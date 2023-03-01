package ru.Cherkasov.project.controllers.requestBodies.Rates;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class ChangeRatesDTO {
    @JsonProperty(value = "base_currency")
    @NotEmpty
    String baseCurrency;
    @JsonProperty(value = "secret_key")
    @NotEmpty
    String secretKey;
    @JsonProperty(value = "BTC")
    String BTC;
    @JsonProperty(value = "TON")
    String TON;
    @JsonProperty(value = "RUB")
    String RUB;

    public String getBaseCurrency() {
        return baseCurrency;
    }


    public String getSecretKey() {
        return secretKey;
    }


    public String getBTC() {
        return BTC;
    }


    public String getTON() {
        return TON;
    }


    public String getRUB() {
        return RUB;
    }

}
