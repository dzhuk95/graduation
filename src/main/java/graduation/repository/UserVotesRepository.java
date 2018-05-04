package graduation.repository;

import graduation.model.orm.UserVotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVotesRepository extends JpaRepository<UserVotesEntity, Integer> {

}
