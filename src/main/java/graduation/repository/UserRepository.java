package graduation.repository;

import graduation.model.orm.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
