package ru.Cherkasov.project.model;


import jakarta.persistence.*;
import ru.Cherkasov.project.exceptions.NotExistingCurrencyException;

import java.util.UUID;

@Entity
@Table(name = "Users", indexes = @Index(columnList = "secretKey"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String secretKey;
    private Boolean isAdmin = true;
    private Double RUB_wallet = 0.0;
    private Double TON_wallet = 0.0;
    private Double BTC_wallet = 0.0;

    public void setWallet(String currency, Double wallet){
        switch(currency){
            case "RUB":
                RUB_wallet = wallet;
                return;
            case "TON":
                TON_wallet = wallet;
                return;
            case "BTC":
                BTC_wallet = wallet;
                return;
            default:
                throw new NotExistingCurrencyException(currency + " currency doesn't exist");
        }
    }
    public Double getWallet(String currency){
        switch (currency){
            case "RUB":
                return RUB_wallet;
            case "TON":
                return  TON_wallet;
            case "BTC":
                return  BTC_wallet;
            default:
                throw new NotExistingCurrencyException(currency + " currency doesn't exist");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Double getRUB_wallet() {
        return RUB_wallet;
    }

    public void setRUB_wallet(Double RUB_wallet) {
        this.RUB_wallet = RUB_wallet;
    }

    public Double getTON_wallet() {
        return TON_wallet;
    }

    public void setTON_wallet(Double TON_wallet) {
        this.TON_wallet = TON_wallet;
    }

    public Double getBTC_wallet() {
        return BTC_wallet;
    }

    public void setBTC_wallet(Double BTC_wallet) {
        this.BTC_wallet = BTC_wallet;
    }

    public User(Long id, String email, String nickname, String secretKey, Boolean isAdmin, Double RUB_wallet, Double TON_wallet, Double BTC_wallet) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.secretKey = secretKey;
        this.isAdmin = isAdmin;
        this.RUB_wallet = RUB_wallet;
        this.TON_wallet = TON_wallet;
        this.BTC_wallet = BTC_wallet;
    }

    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public User() {
    }
}
