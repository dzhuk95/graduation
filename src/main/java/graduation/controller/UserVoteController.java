package graduation.controller;

import graduation.model.api.UserVoteReq;
import graduation.service.UserVoteService;
import graduation.service.impl.UserVoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user/vote", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserVoteController {

    private final UserVoteService userVoteServiceImpl;

    public UserVoteController(@Autowired UserVoteService userVoteServiceImpl) {
        this.userVoteServiceImpl = userVoteServiceImpl;
    }

    @PostMapping()
    public ResponseEntity vote(@RequestBody UserVoteReq voteRequest) {
        return userVoteServiceImpl.voteForMenu(voteRequest);
    }
}
