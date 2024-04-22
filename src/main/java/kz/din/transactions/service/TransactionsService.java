package kz.din.transactions.service;

import kz.din.transactions.model.dto.TransactionsDTO;
import kz.din.transactions.model.entity.Transactions;
import kz.din.transactions.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionsService {

    void makeTransaction(TransactionsDTO transactionsDTO);

    Transactions getLastByUser(UserEntity user);

    void save(Transactions transactions);

    List<Transactions> getAllLimit_exceededTransactions(String bank);
}
