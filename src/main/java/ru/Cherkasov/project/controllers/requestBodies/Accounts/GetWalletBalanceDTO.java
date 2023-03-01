package ru.Cherkasov.project.controllers.requestBodies.Accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class GetWalletBalanceDTO {
    @JsonProperty(value = "secret_key")
    @NotEmpty
    String secretKey;

    public String getSecretKey() {
        return secretKey;
    }
}
