package graduation.service;

import graduation.model.AuthorizedUser;
import graduation.model.item.UserVoteReq;
import graduation.model.orm.UserEntity;
import graduation.model.orm.UserVotesEntity;
import graduation.repository.UserRepository;
import graduation.repository.UserVotesRepository;
import graduation.repository.dao.UserVoteDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserVoteService {
    @Getter
    private final UserRepository userRepository;

    @Getter
    private final UserVoteDao userVoteDao;

    public UserVoteService(@Autowired UserRepository userRepository, @Autowired UserVoteDao votesRepository) {
        this.userRepository = userRepository;
        this.userVoteDao = votesRepository;
    }

    //Todo
    public ResponseEntity voteForMenu(UserVoteReq userVoteReq) {
        UserEntity userEntity = userRepository.getOne(1);
        UserVotesEntity topById = userVoteDao.getByUserIdAndCurrentDate(userEntity.getId(), LocalDateTime.now(), PageRequest.of(
                0, 1, new Sort(Sort.Direction.DESC, "id")));

        return ResponseEntity.ok().build();
    }
}
