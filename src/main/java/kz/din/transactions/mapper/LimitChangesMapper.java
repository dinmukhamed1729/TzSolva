package kz.din.transactions.mapper;

import kz.din.transactions.model.dto.SetLimitDTO;
import kz.din.transactions.model.entity.LimitChanges;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LimitChangesMapper {
    LimitChangesMapper mapper = Mappers.getMapper(LimitChangesMapper.class);

    LimitChanges toLimitChanges(SetLimitDTO setLimitDTO);
}
