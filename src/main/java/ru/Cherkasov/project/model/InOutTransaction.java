package ru.Cherkasov.project.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "InOutTransactions")
public class InOutTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;
    private java.sql.Timestamp timePerformed;
    private String currency;
    private Double amount;
    private String OutsideCardOrWallet;

    public InOutTransaction(Long id, User user, Timestamp timePerformed, String currency, Double amount, String outsideCardOrWallet) {
        this.id = id;
        this.user = user;
        this.timePerformed = timePerformed;
        this.currency = currency;
        this.amount = amount;
        OutsideCardOrWallet = outsideCardOrWallet;
    }

    public InOutTransaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimePerformed() {
        return timePerformed;
    }

    public void setTimePerformed(Timestamp timePerformed) {
        this.timePerformed = timePerformed;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOutsideCardOrWallet() {
        return OutsideCardOrWallet;
    }

    public void setOutsideCardOrWallet(String outsideCardOrWallet) {
        OutsideCardOrWallet = outsideCardOrWallet;
    }
}
