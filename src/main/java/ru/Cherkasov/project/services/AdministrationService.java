package ru.Cherkasov.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Cherkasov.project.controllers.requestBodies.Rates.ChangeRatesDTO;
import ru.Cherkasov.project.controllers.requestBodies.Statistics.TransactionStatisticsDTO;
import ru.Cherkasov.project.controllers.requestBodies.Statistics.WalletStatisticsDTO;
import ru.Cherkasov.project.exceptions.NotExistingCurrencyException;
import ru.Cherkasov.project.exceptions.UserNotAdminException;
import ru.Cherkasov.project.exceptions.UserNotFoundException;
import ru.Cherkasov.project.model.ExchangeRate;
import ru.Cherkasov.project.repositories.ExchangeRateRepository;
import ru.Cherkasov.project.repositories.ExchangeTransactionRepository;
import ru.Cherkasov.project.repositories.InOutTransactionRepository;
import ru.Cherkasov.project.repositories.UserRepository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdministrationService {
    @Autowired
    private ExchangeTransactionRepository exchangeTransactionRepository;

    @Autowired
    private InOutTransactionRepository inOutTransactionRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InformationService informationService;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private AccountService accountService;
    private void performExistenceAndRightsCheck(String secretKey){
        if(accountService.UserExists(secretKey)) {
            if (!accountService.isUserAdmin(secretKey))
                throw new UserNotAdminException("User doesn't have rights for the operation");
        }
        else throw new UserNotFoundException(secretKey + " doesn't correspond to any user");
    }
    private void updateCurrencyRateFor(String baseCurrency, String targetCurrency, double newRate){
        ExchangeRate exchangeRate = exchangeRateRepository.findByFromAndToCurrency(baseCurrency, targetCurrency);
        if(exchangeRate == null){
            ExchangeRate newEntry = new ExchangeRate();
            newEntry.setCurrencyTo(targetCurrency);
            newEntry.setCurrencyFrom(baseCurrency);
            newEntry.setRate(newRate);
            exchangeRateRepository.save(newEntry);
        }
        else{
            exchangeRate.setRate(newRate);
            exchangeRateRepository.save(exchangeRate);
        }
    }
    public Map<String,String> changeRates(ChangeRatesDTO currenciesRate){
        performExistenceAndRightsCheck(currenciesRate.getSecretKey());
        if(!informationService.currencyExists(currenciesRate.getBaseCurrency()))
            throw new NotExistingCurrencyException(currenciesRate.getBaseCurrency() + " is not supported");

        if(currenciesRate.getRUB() != null)
            updateCurrencyRateFor(currenciesRate.getBaseCurrency(), "RUB", Double.parseDouble(currenciesRate.getRUB()));
        if(currenciesRate.getTON() != null)
            updateCurrencyRateFor(currenciesRate.getBaseCurrency(), "TON", Double.parseDouble(currenciesRate.getTON()));
        if(currenciesRate.getBTC() != null)
            updateCurrencyRateFor(currenciesRate.getBaseCurrency(), "BTC", Double.parseDouble(currenciesRate.getBTC()));

        return informationService.getCurrentExchangeRates(currenciesRate.getBaseCurrency());
    }

    Double getSumForCurrency(String currency){
        switch (currency){
            case "RUB":
                return  userRepository.calculateRUBSumAcrossWalletsOfCurrency();
            case "TON":
                return  userRepository.calculateTONSumAcrossWalletsOfCurrency();
            case "BTC":
                return  userRepository.calculateBTCSumAcrossWalletsOfCurrency();
        }
        return 0.0;
    }
    public Map<String,String> getWalletsStatistics(WalletStatisticsDTO walletStatisticsDTO){
        performExistenceAndRightsCheck(walletStatisticsDTO.getSecretKey());
        if(!informationService.currencyExists(walletStatisticsDTO.getCurrency()))
            throw new NotExistingCurrencyException(walletStatisticsDTO.getCurrency() + " is not supported");
        Map<String,String> retval = new HashMap<String, String>();
        retval.put(walletStatisticsDTO.getCurrency(), getSumForCurrency(walletStatisticsDTO.getCurrency()).toString());
        return retval;
    }

    public Map<String,String> getTransactionStatistics(TransactionStatisticsDTO transactionStatisticsDTO){
        performExistenceAndRightsCheck(transactionStatisticsDTO.getSecretKey());
        Map<String,String> retval = new HashMap<String,String>();

        Timestamp from = Timestamp.valueOf(transactionStatisticsDTO.getDateFrom());
        Timestamp to = Timestamp.valueOf(transactionStatisticsDTO.getDateTo());
        int count = inOutTransactionRepository.calculateNumberOfInOutTransactions(from,to) + exchangeTransactionRepository.calculateNumberOfExchangeTransactions(from, to);
        retval.put("transaction_count", String.valueOf(count));
        return retval;
    }
}
