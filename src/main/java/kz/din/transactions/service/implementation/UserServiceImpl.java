package kz.din.transactions.service.implementation;

import kz.din.transactions.mapper.UserMapper;
import kz.din.transactions.model.dto.SetLimitDTO;
import kz.din.transactions.model.dto.UserDTO;
import kz.din.transactions.model.entity.UserEntity;
import kz.din.transactions.repositories.UserRepository;
import kz.din.transactions.service.LimitChangesService;
import kz.din.transactions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.  class);
    private final UserRepository userRepository;
    private final LimitChangesService limitChangesService;

    private final UserMapper userMapper = UserMapper.mapper;

    @Override
    public UserEntity getByBankAccount(String bankAccount) {
        return userRepository.findByBankAccount(bankAccount).orElseThrow();
    }
    @Override
    public void setLimit(SetLimitDTO setLimitDTO) {
        var user = userRepository.findByBankAccount(setLimitDTO.bankAccount()).orElseThrow();
        limitChangesService.setLimit(setLimitDTO,user);
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.toUserEntity(userDTO));
    }

    @Override
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }









}
