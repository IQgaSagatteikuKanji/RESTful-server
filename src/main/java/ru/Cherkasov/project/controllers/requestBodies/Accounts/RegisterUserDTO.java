package ru.Cherkasov.project.controllers.requestBodies.Accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class RegisterUserDTO {
    @JsonProperty(value = "username")
    @NotEmpty
    String username;
    @JsonProperty(value = "email")
    @NotEmpty
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "please provide a valid email")
    String email;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
