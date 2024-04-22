package kz.din.transactions.repositories;

import kz.din.transactions.model.entity.Expenses;
import kz.din.transactions.model.entity.Transactions;
import kz.din.transactions.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionsRepositories extends JpaRepository<Transactions,Long> {
    List<Transactions> findByUserAndTransactionDatetimeBetween(UserEntity user, LocalDateTime transactionDatetime, LocalDateTime transactionDatetime2);
    Transactions findTopByUserOrderByTransactionDatetimeDesc(UserEntity user);
    Optional<Transactions> findTopByUserAndExpensesOrderByTransactionDatetimeDesc(UserEntity user, Expenses expenses);
    List<Transactions> findByUserAndLimitExceededTrue(UserEntity user);


}
