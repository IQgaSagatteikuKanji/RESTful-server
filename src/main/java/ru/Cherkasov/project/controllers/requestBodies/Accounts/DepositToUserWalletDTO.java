package ru.Cherkasov.project.controllers.requestBodies.Accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class DepositToUserWalletDTO {
    @JsonProperty(value = "secret_key")
    @NotEmpty
    String secretKey;
    @JsonProperty(value = "RUB_wallet")
    String RUB;
    @JsonProperty(value = "BTC_wallet")
    String BTC;
    @JsonProperty(value = "TON_wallet")
    String TON;

    public String getSecretKey() {
        return secretKey;
    }

    public String getRUB() {
        return RUB;
    }

    public String getBTC() {
        return BTC;
    }

    public String getTON() {
        return TON;
    }
}
