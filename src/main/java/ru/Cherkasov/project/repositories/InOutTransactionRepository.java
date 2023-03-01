package ru.Cherkasov.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.Cherkasov.project.model.InOutTransaction;

import java.sql.Timestamp;

public interface InOutTransactionRepository extends JpaRepository<InOutTransaction,Long> {
    @Query("SELECT COUNT(*) FROM InOutTransaction WHERE timePerformed >= ?1 AND timePerformed < ?2")
    public Integer calculateNumberOfInOutTransactions(Timestamp from,  Timestamp to);
}
