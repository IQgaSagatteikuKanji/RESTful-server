package ru.Cherkasov.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Cherkasov.project.controllers.requestBodies.Accounts.*;
import ru.Cherkasov.project.exceptions.InsuffientFundsException;
import ru.Cherkasov.project.exceptions.RateUninitialisedException;
import ru.Cherkasov.project.exceptions.UserNotFoundException;
import ru.Cherkasov.project.model.ExchangeRate;
import ru.Cherkasov.project.model.ExchangeTransaction;
import ru.Cherkasov.project.model.InOutTransaction;
import ru.Cherkasov.project.model.User;
import ru.Cherkasov.project.repositories.ExchangeRateRepository;
import ru.Cherkasov.project.repositories.ExchangeTransactionRepository;
import ru.Cherkasov.project.repositories.InOutTransactionRepository;
import ru.Cherkasov.project.repositories.UserRepository;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private ExchangeTransactionRepository exchangeTransactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InOutTransactionRepository inOutTransactionRepository;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    public boolean UserExists(String SecretKey){
        return userRepository.findUserBySecretKey(SecretKey) != null;
    }

    public boolean isUserAdmin(String SecretKey){
        boolean isAdmin = false;
        User user = userRepository.findUserBySecretKey(SecretKey);
        if(user != null){
            isAdmin = user.getAdmin();
        }
        return  isAdmin;
    }
    public boolean UserExists(String email, String nickname){
        User byEmailUser = userRepository.findUserByEmail(email);
        User byNicknameUser = userRepository.findUserByNickname(nickname);
        return byEmailUser != null || byNicknameUser != null;
    }

    public Map<String,String> getWalletOfUser(GetWalletBalanceDTO reqBody){
        Map<String,String> retval = new HashMap<String,String>();
        User user = userRepository.findUserBySecretKey(reqBody.getSecretKey());
        if(user!=null){
            retval.put("RUB_wallet", user.getRUB_wallet().toString());
            retval.put("TON_wallet", user.getTON_wallet().toString());
            retval.put("BTC_wallet", user.getBTC_wallet().toString());
        }
        else throw new UserNotFoundException(reqBody.getSecretKey() + " doesn't correspond to any user");
        return retval;
    }
    public Map<String, String> createNewUser(RegisterUserDTO reqBody){
        Map<String,String> retval = new HashMap<String,String>();
        User newUserEntry = new User(reqBody.getEmail(), reqBody.getUsername());
        newUserEntry.setSecretKey(UUID.randomUUID().toString());
        userRepository.save(newUserEntry);
        retval.put("secretKey", newUserEntry.getSecretKey().toString());
        return retval;
    }

    public Map<String,String> depositMoneyToUser(DepositToUserWalletDTO reqBody){
        Map<String,String> retval = new HashMap<String,String>();
        User user = userRepository.findUserBySecretKey(reqBody.getSecretKey());
        if(user != null){
            if(reqBody.getBTC() != null){
                user.setBTC_wallet(user.getBTC_wallet() + Double.parseDouble(reqBody.getBTC()));
                retval.put("BTC_wallet", user.getBTC_wallet().toString());
            }
            if(reqBody.getRUB() != null){
                user.setRUB_wallet(user.getRUB_wallet() + Double.parseDouble(reqBody.getRUB()));
                retval.put("RUB_wallet", user.getRUB_wallet().toString());
            }
            if(reqBody.getTON() != null){
                user.setTON_wallet(user.getTON_wallet() + Double.parseDouble(reqBody.getTON()));
                retval.put("TON_wallet", user.getTON_wallet().toString());
            }
            userRepository.save(user);
            saveInTransaction(user, reqBody);
        }
        else throw new UserNotFoundException(reqBody.getSecretKey() + " doesn't correspond to any user");
        return retval;
    }
    //TODO: make a builder(I'm not sure though)
    //Please include in review the best way to tackle this problem
    //I don't have any ideas for this
    private void saveInRUBTransaction(User user, DepositToUserWalletDTO reqBody){
        InOutTransaction inOutTransaction = new InOutTransaction();
        inOutTransaction.setUser(user);
        inOutTransaction.setAmount(Double.parseDouble(reqBody.getRUB()));
        inOutTransaction.setCurrency("RUB");
        inOutTransaction.setTimePerformed(Timestamp.from(Instant.now()));
        inOutTransactionRepository.save(inOutTransaction);
    }
    private void saveInTONTransaction(User user, DepositToUserWalletDTO reqBody){
        InOutTransaction inOutTransaction = new InOutTransaction();
        inOutTransaction.setUser(user);
        inOutTransaction.setAmount(Double.parseDouble(reqBody.getTON()));
        inOutTransaction.setCurrency("TON");
        inOutTransaction.setTimePerformed(Timestamp.from(Instant.now()));
        inOutTransactionRepository.save(inOutTransaction);
    }
    private void saveInBTCTransaction(User user, DepositToUserWalletDTO reqBody){
        InOutTransaction inOutTransaction = new InOutTransaction();
        inOutTransaction.setUser(user);
        inOutTransaction.setAmount(Double.parseDouble(reqBody.getBTC()));
        inOutTransaction.setCurrency("BTC");
        inOutTransaction.setTimePerformed(Timestamp.from(Instant.now()));
        inOutTransactionRepository.save(inOutTransaction);
    }
    private  void saveInTransaction(User user, DepositToUserWalletDTO reqBody){
        if(reqBody.getTON() != null)
            saveInTONTransaction(user, reqBody);
        if(reqBody.getRUB() != null)
            saveInRUBTransaction(user, reqBody);
        if(reqBody.getBTC() != null)
            saveInBTCTransaction(user, reqBody);
    }

    private void saveOutTransaction(User user, WithdrawFromUserWalletDTO reqBody){
        InOutTransaction inOutTransaction = new InOutTransaction();
        inOutTransaction.setUser(user);
        inOutTransaction.setAmount(Double.parseDouble(reqBody.getAmount()));
        inOutTransaction.setCurrency(reqBody.getCurrency());
        if(reqBody.getCreditCard() != null)
            inOutTransaction.setOutsideCardOrWallet(reqBody.getCreditCard());
        if(reqBody.getWallet() != null)
            inOutTransaction.setOutsideCardOrWallet(reqBody.getWallet());
        inOutTransaction.setTimePerformed(Timestamp.from(Instant.now()));
        inOutTransactionRepository.save(inOutTransaction);
    }
    public Map<String,String> withdrawMoneyFromUser(WithdrawFromUserWalletDTO reqBody){
        Map<String,String> retval = new HashMap<String,String>();
        User user = userRepository.findUserBySecretKey(reqBody.getSecretKey());
        if(user != null) {
                double amount = user.getWallet(reqBody.getCurrency()) - Double.parseDouble(reqBody.getAmount());
                if(amount > 0){
                    user.setWallet(reqBody.getCurrency(), amount);
                    retval.put(reqBody.getCurrency() + "_wallet", String.valueOf(amount));
                } else throw new InsuffientFundsException("There is not enough funds in the wallet");
            saveOutTransaction(user,reqBody);
        }
        else throw new UserNotFoundException(reqBody.getSecretKey() + " doesn't correspond to any user");
        return retval;
    }

    private void saveExchangeTransaction(ExchangeCurrencyDTO reqBody, double rate, User user){
        ExchangeTransaction exchangeTransaction = new ExchangeTransaction();
        exchangeTransaction.setAmount(Double.parseDouble(reqBody.getAmount()));
        exchangeTransaction.setRate(rate);
        exchangeTransaction.setUser(user);
        exchangeTransaction.setCurrencyFrom(reqBody.getCurrencyFrom());
        exchangeTransaction.setCurrencyTo(reqBody.getCurrencyTo());
        exchangeTransaction.setTimePerformed(Timestamp.from(Instant.now()));
        exchangeTransactionRepository.save(exchangeTransaction);
    }

    private Map<String,String> buildResponsetoExchangeMoney(ExchangeCurrencyDTO reqBody, Double amountTo){
        Map<String,String> retval = new HashMap<String,String>();
        retval.put("currency_from", reqBody.getCurrencyFrom());
        retval.put("currency_to", reqBody.getCurrencyTo());
        retval.put("amount_from", reqBody.getAmount());
        retval.put("amount_to", amountTo.toString());
        return  retval;
    }
    public Map<String, String> exchangeMoneyOfUser(ExchangeCurrencyDTO reqBody){
        User user = userRepository.findUserBySecretKey(reqBody.getSecretKey());
        if(user == null) throw new UserNotFoundException(reqBody.getSecretKey() + " doesn't correspond to any user");

        double amountFrom = Double.parseDouble(reqBody.getAmount());
        double amountTo;
        double walletAmount = user.getWallet(reqBody.getCurrencyFrom()) - Double.parseDouble(reqBody.getAmount());
        if(walletAmount > 0){
            user.setWallet(reqBody.getCurrencyFrom(), walletAmount);
            ExchangeRate exchangeRate = exchangeRateRepository.findByFromAndToCurrency(reqBody.getCurrencyFrom(), reqBody.getCurrencyTo());
            if(exchangeRate == null)
                throw new RateUninitialisedException("exchange rate from " + reqBody.getCurrencyFrom() + " to " + reqBody.getCurrencyTo() + " doesn't exist");
            amountTo = amountFrom * exchangeRate.getRate();
            user.setWallet(reqBody.getCurrencyTo(), user.getWallet(reqBody.getCurrencyTo()) + amountTo);
            userRepository.save(user);
            saveExchangeTransaction(reqBody, exchangeRate.getRate(), user);
        }
        else throw new InsuffientFundsException("There is not enough funds in the wallet");
        return buildResponsetoExchangeMoney(reqBody,amountTo);
    }
}
