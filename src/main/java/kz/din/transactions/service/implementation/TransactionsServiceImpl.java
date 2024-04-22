package kz.din.transactions.service.implementation;

import kz.din.transactions.model.dto.TransactionsDTO;
import kz.din.transactions.model.entity.CurrencyShortName;
import kz.din.transactions.model.entity.LimitChanges;
import kz.din.transactions.model.entity.Transactions;
import kz.din.transactions.model.entity.UserEntity;
import kz.din.transactions.repositories.TransactionsRepositories;
import kz.din.transactions.service.ExchangeRateService;
import kz.din.transactions.service.LimitChangesService;
import kz.din.transactions.service.TransactionsService;
import kz.din.transactions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service()
public class TransactionsServiceImpl implements TransactionsService {
    private final TransactionsRepositories transactionsRepositories;
    private final LimitChangesService limitChangesService;
    private final UserService userService;
    private final ExchangeRateService exchangeRateService;

    @Override

    public void makeTransaction(TransactionsDTO transactionsDTO) {
        var user = userService.getByBankAccount(transactionsDTO.accountFrom());
        LimitChanges lastLimitChanges  = limitChangesService.getUserLastLimitChange(user);

        Transactions prevTransaction = this.getLastByUser(user);
        BigDecimal prevRemainingMonthlyLimit  = (prevTransaction == null) ? lastLimitChanges.getLimitSum() : prevTransaction.getRemainingMonthlyLimit();
        BigDecimal thisTransactionAmount = exchangeRateService.convertCurrency(transactionsDTO.transactionAmount(),transactionsDTO.currencyShortname(), CurrencyShortName.USD);
        BigDecimal remainingMonthlyLimit = prevRemainingMonthlyLimit.subtract(thisTransactionAmount);
        System.out.println(prevRemainingMonthlyLimit + " Minus " + thisTransactionAmount + "==" + prevRemainingMonthlyLimit.subtract(thisTransactionAmount) + " remainingMonthlyLimit "+remainingMonthlyLimit);

        Transactions transaction = Transactions.builder()
                .transactionAmount(thisTransactionAmount)
                .remainingMonthlyLimit(remainingMonthlyLimit)
                .expenses(transactionsDTO.expenseCategory())
                .limitExceeded(!(remainingMonthlyLimit.compareTo(BigDecimal.ZERO) >= 0))
                .currencyShortName(CurrencyShortName.USD)
                .user(user)
                .build();
        save(transaction);
    }

    @Override
    public Transactions getLastByUser(UserEntity user) {
        return transactionsRepositories.findTopByUserOrderByTransactionDatetimeDesc(user);
    }

    @Override
    public void save(Transactions transactions) {
        transactionsRepositories.save(transactions);
    }

    @Override
    public List<Transactions> getAllLimit_exceededTransactions(String bank) {
        return transactionsRepositories.findByUserAndLimitExceededTrue(userService.getByBankAccount(bank));
    }

}
