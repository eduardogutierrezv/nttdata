package nttdata.usertest.repository;

import nttdata.usertest.entity.CellphoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellphoneRepository extends JpaRepository<CellphoneEntity, Long> {
}
