package ru.Cherkasov.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Cherkasov.project.exceptions.NotExistingCurrencyException;
import ru.Cherkasov.project.model.ExchangeRate;
import ru.Cherkasov.project.repositories.ExchangeRateRepository;

import java.util.*;

//the idea is it should be accessible by anyone?
@Service
public class InformationService {
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    private static Set<String> existingCurrencies= new HashSet<String>();
    //to get of it the structure of the wallet should be changed
    static {
        existingCurrencies.add("RUB");
        existingCurrencies.add("TON");
        existingCurrencies.add("BTC");
    }

    public boolean currencyExists(String currency){
        return  existingCurrencies.contains(currency);
    }
    public Map<String, String> getCurrentExchangeRates(String currency){
        if(!existingCurrencies.contains(currency))
            throw new NotExistingCurrencyException(currency + " is not supported");
        Map<String,String> map = new HashMap<String,String>();
        List<ExchangeRate> exchangeRates = exchangeRateRepository.findByFromCurrency(currency);
        Iterator<ExchangeRate> iterator = exchangeRates.listIterator();
        while(iterator.hasNext()){
            ExchangeRate elem = iterator.next();
            map.put(elem.getCurrencyTo(), elem.getRate().toString());
        }
        return map;
    }
}
