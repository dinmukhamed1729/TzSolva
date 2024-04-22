package kz.din.transactions.repositories;

import kz.din.transactions.model.entity.LimitChanges;
import kz.din.transactions.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitChangesRepository extends JpaRepository<LimitChanges,Long> {

    Optional<LimitChanges> findTopByUserOrderByLimitDatetimeDesc(UserEntity userEntity);
}
