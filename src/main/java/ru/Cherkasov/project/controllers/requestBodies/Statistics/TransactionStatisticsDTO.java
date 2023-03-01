package ru.Cherkasov.project.controllers.requestBodies.Statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class TransactionStatisticsDTO {
    @JsonProperty(value = "secret_key")
    @NotEmpty
    String secretKey;
    @JsonProperty(value = "date_from")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
    @NotEmpty
    String dateFrom;
    @JsonProperty(value = "date_to")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
    @NotEmpty
    String dateTo;

    public String getSecretKey() {
        return secretKey;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }
}
