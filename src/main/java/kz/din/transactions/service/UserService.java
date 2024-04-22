package kz.din.transactions.service;

import kz.din.transactions.model.dto.SetLimitDTO;
import kz.din.transactions.model.dto.UserDTO;
import kz.din.transactions.model.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserEntity getByBankAccount(String bankAccount);

    void setLimit(SetLimitDTO setLimitDTO);

    void save(UserDTO userDTO);

    void save(UserEntity userEntity);


}
