package graduation.service;

import graduation.model.api.UserVoteReq;
import org.springframework.http.ResponseEntity;

public interface UserVoteService {
    ResponseEntity voteForMenu(UserVoteReq userVoteReq);
}
