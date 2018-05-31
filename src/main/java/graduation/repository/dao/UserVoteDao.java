package graduation.repository.dao;

import graduation.model.orm.UserVotesEntity;
import graduation.repository.UserVotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserVoteDao {
    @Autowired
    UserVotesRepository userVotesRepository;

    public UserVotesEntity getByUserIdAndCurrentDate(int userId, LocalDateTime currentDate) {
        LocalDateTime start = currentDate.toLocalDate().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        return userVotesRepository.getByUserIdAndCurrentTime(userId, start, end, PageRequest.of(0, 1));
    }

    @Transactional
    public UserVotesEntity save(UserVotesEntity entity) {
        return userVotesRepository.save(entity);
    }
}
