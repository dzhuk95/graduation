package graduation.service;

import graduation.model.api.UserVoteReq;
import graduation.model.orm.RestaurantsEntity;
import graduation.model.orm.UserEntity;
import graduation.model.orm.UserVotesEntity;
import graduation.repository.RestaurantsRepository;
import graduation.repository.UserRepository;
import graduation.repository.dao.UserVoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Service
public class UserVoteService {
    private final UserRepository userRepository;
    private final UserVoteDao userVoteDao;
    private final RestaurantsRepository restaurantsRepository;

    public UserVoteService(@Autowired UserRepository userRepository, @Autowired UserVoteDao votesRepository,
                           @Autowired RestaurantsRepository restaurantsRepository) {
        this.userRepository = userRepository;
        this.userVoteDao = votesRepository;
        this.restaurantsRepository = restaurantsRepository;
    }

    public ResponseEntity voteForMenu(UserVoteReq userVoteReq) {
        int restaurantId = userVoteReq.getRestaurantId();
        Assert.isTrue(restaurantId != 0, "Restaurant id must not be 0");
        UserEntity userEntity = userRepository.getOne(1);
        LocalDateTime now = LocalDateTime.now();
        RestaurantsEntity one = restaurantsRepository.getOne(restaurantId);
        UserVotesEntity topById = userVoteDao.getByUserIdAndCurrentDate(userEntity.getId(), now);
        if (topById == null)
            userVoteDao.save(UserVotesEntity.of(userEntity, one, now));
        else {
            if (now.toLocalTime().getHour() >= 11)
                throw new IllegalArgumentException("You can't change your vote after 11:00");
            topById.setRestaurant(one);
            userVoteDao.save(topById);
        }
        return ResponseEntity.ok("Vote accepted");
    }
}
