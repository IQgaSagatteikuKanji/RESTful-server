package ru.Cherkasov.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.Cherkasov.project.model.ExchangeTransaction;

import java.sql.Timestamp;

public interface ExchangeTransactionRepository extends JpaRepository<ExchangeTransaction,Long> {
    @Query("SELECT COUNT(*) FROM ExchangeTransaction WHERE timePerformed >= ?1 AND timePerformed < ?2")
    public Integer calculateNumberOfExchangeTransactions(Timestamp from, Timestamp to);
}
