package kz.din.transactions.mapper;

import kz.din.transactions.model.dto.UserDTO;
import kz.din.transactions.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper mapper = Mappers.getMapper(UserMapper.class);
    UserEntity  toUserEntity(UserDTO userDTO);
}
