package kz.din.transactions.mapper;

import kz.din.transactions.model.dto.ExchangeRateDTO;
import kz.din.transactions.model.entity.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExchangeRateMapper {
    ExchangeRateMapper mapper = Mappers.getMapper(ExchangeRateMapper.class);
    @Mapping(target = "lastRefreshed", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ExchangeRate toExchangeRate(ExchangeRateDTO exchangeRateDTO);
}
