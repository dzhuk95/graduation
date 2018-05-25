package graduation.repository;

import graduation.model.orm.UserVotesEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserVotesRepository extends JpaRepository<UserVotesEntity, Integer> {

    @Query("SELECT uve FROM UserVotesEntity as uve WHERE uve.user.id=:userId and uve.localDateTime<:currentDate")
    List<UserVotesEntity> getByUserIdAndCurrentTime(@Param("userId") int userId, @Param("time")LocalDateTime currentDate, Pageable pageable);
}
