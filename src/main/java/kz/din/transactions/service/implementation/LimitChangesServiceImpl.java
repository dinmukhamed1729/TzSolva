package kz.din.transactions.service.implementation;

import kz.din.transactions.mapper.LimitChangesMapper;
import kz.din.transactions.model.dto.SetLimitDTO;
import kz.din.transactions.model.entity.LimitChanges;
import kz.din.transactions.model.entity.UserEntity;
import kz.din.transactions.repositories.LimitChangesRepository;
import kz.din.transactions.service.LimitChangesService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class LimitChangesServiceImpl implements LimitChangesService {
    private final LimitChangesMapper limitChangesMapper = LimitChangesMapper.mapper;
    private final LimitChangesRepository limitChangesRepository;
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.  class);
    @Override
    public void setLimit(SetLimitDTO setLimitDTO, UserEntity user) {
        var limitChanges = limitChangesMapper.toLimitChanges(setLimitDTO);
        limitChanges.setUser(user);
        try {
            limitChangesRepository.save(limitChanges);
        } catch (Exception ex) {
            LOGGER.error("Error saving user: {}", ex.getMessage());
            throw new RuntimeException("Error saving user", ex);
        }
        LOGGER.info("Установлен лимит для банковского счета: {}", setLimitDTO.bankAccount());

    }

    @Override
    public LimitChanges getUserLastLimitChange(UserEntity userEntity) {
        return limitChangesRepository.findTopByUserOrderByLimitDatetimeDesc(userEntity).orElseThrow();
    }

}
