package kz.din.transactions.mapper;

import kz.din.transactions.model.dto.TransactionsDTO;
import kz.din.transactions.model.entity.Transactions;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionsMapper {
    TransactionsMapper mapper = Mappers.getMapper(TransactionsMapper.class);

    Transactions toTransactions(TransactionsDTO transactionsDTO);
}
