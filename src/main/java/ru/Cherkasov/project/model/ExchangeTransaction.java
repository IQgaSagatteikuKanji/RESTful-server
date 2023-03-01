package ru.Cherkasov.project.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "ExchangeTransactions")
public class ExchangeTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;

    private String currencyFrom;
    private String currencyTo;
    private Double rate;

    private java.sql.Timestamp timePerformed;
    private Double amount;

    public ExchangeTransaction(Long exchTransId, User user, String currencyFrom, String currencyTo, Double rate, Timestamp timePerformed, Double amount) {
        this.id = exchTransId;
        this.user = user;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
        this.timePerformed = timePerformed;
        this.amount = amount;
    }

    public ExchangeTransaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long exchTransId) {
        this.id = exchTransId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Timestamp getTimePerformed() {
        return timePerformed;
    }

    public void setTimePerformed(Timestamp timePerformed) {
        this.timePerformed = timePerformed;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
