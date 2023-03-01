package ru.Cherkasov.project.controllers.requestBodies.Accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class WithdrawFromUserWalletDTO {
    @JsonProperty(value = "secret_key")
    @NotEmpty
    String secretKey;
    @JsonProperty(value = "currency")
    @NotEmpty
    String currency;
    @JsonProperty(value = "count")
    @NotEmpty
    String amount;
    @JsonProperty(value = "credit_card")
    String creditCard;
    @JsonProperty(value = "wallet")
    String wallet;

    public String getSecretKey() {
        return secretKey;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAmount() {
        return amount;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getWallet() {
        return wallet;
    }
}
