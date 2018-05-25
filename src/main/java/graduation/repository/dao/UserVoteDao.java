package graduation.repository.dao;

import graduation.model.orm.UserVotesEntity;
import graduation.repository.UserVotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserVoteDao {
    @Autowired
    UserVotesRepository userVotesRepository;

    public UserVotesEntity getByUserIdAndCurrentDate(int userId, LocalDateTime currentDate, Pageable pageable) {
        List<UserVotesEntity> byUserIdAndCurrentTime = userVotesRepository.getByUserIdAndCurrentTime(userId, currentDate, pageable);
        if (byUserIdAndCurrentTime.isEmpty()) return null;
        return byUserIdAndCurrentTime.get(0);
    }
}
