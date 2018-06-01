package graduation.controller;

import graduation.model.api.AuthRequest;
import graduation.model.api.CreateUserReq;
import graduation.repository.dao.UserDao;
import graduation.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RegistrationController {

    @Getter
    private final UserService userService;

    public RegistrationController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registration(@RequestBody CreateUserReq createUserReq) {
        return userService.createUser(createUserReq);
    }


    @PreAuthorize("hasRole(ADMIN)")
    @PostMapping(value = "/registration/manager", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registrationManager(@RequestBody CreateUserReq createUserReq) {
        return userService.createAdmin(createUserReq);
    }
}
