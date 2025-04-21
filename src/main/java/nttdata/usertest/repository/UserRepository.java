package nttdata.usertest.repository;


import nttdata.usertest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByCorreo(String email);

    Optional<UserEntity> findByCorreoAndPassword(String email, String password);
}
