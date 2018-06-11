package graduation.controller;

import graduation.model.api.AuthReq;
import graduation.service.UserService;
import graduation.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthController {
    private final UserService userService;

    public AuthController(@Autowired UserService userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity auth(@RequestBody AuthReq authReq){

    }
}
