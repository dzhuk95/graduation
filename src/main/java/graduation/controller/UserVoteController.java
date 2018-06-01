package graduation.controller;

import graduation.model.api.UserVoteReq;
import graduation.service.UserVoteService;
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

    private final UserVoteService userVoteService;

    public UserVoteController(@Autowired UserVoteService userVoteService) {
        this.userVoteService = userVoteService;
    }

    @PostMapping()
    public ResponseEntity vote(@RequestBody UserVoteReq voteRequest) {
       return userVoteService.voteForMenu(voteRequest);
    }
}
