package ru.Cherkasov.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.Cherkasov.project.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,Long> {
    @Query("FROM ExchangeRate WHERE currencyFrom = ?1")
    List<ExchangeRate> findByFromCurrency(String currencyFrom);

    @Query("FROM ExchangeRate WHERE currencyFrom = ?1 AND currencyTo = ?2")
    ExchangeRate findByFromAndToCurrency(String currencyFrom, String currencyTo);
}
