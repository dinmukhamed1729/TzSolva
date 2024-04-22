package kz.din.transactions.service;

import kz.din.transactions.model.dto.SetLimitDTO;
import kz.din.transactions.model.entity.LimitChanges;
import kz.din.transactions.model.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface LimitChangesService {
    void setLimit(SetLimitDTO setLimitDTO, UserEntity user);

    LimitChanges getUserLastLimitChange(UserEntity userEntity);
}
