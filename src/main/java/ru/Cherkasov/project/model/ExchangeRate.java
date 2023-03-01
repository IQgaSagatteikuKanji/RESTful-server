package ru.Cherkasov.project.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "ExchangeRates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ratesId;
    private String currencyFrom;
    private String currencyTo;
    private Double rate;

    public ExchangeRate(Long ratesId, String currencyFrom, String currencyTo, Double rate) {
        this.ratesId = ratesId;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }

    public ExchangeRate() {
    }

    public Long getRatesId() {
        return ratesId;
    }

    public void setRatesId(Long ratesId) {
        this.ratesId = ratesId;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
