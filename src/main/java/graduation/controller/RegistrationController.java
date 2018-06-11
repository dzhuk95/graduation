package graduation.controller;

import graduation.model.api.CreateUserReq;
import graduation.service.UserService;
import graduation.service.impl.UserServiceImpl;
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
@RequestMapping(value = "/api/registration", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RegistrationController {

    @Getter
    private final UserService userServiceImpl;

    public RegistrationController(@Autowired UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registration(@RequestBody CreateUserReq createUserReq) {
        return userServiceImpl.createUser(createUserReq);
    }


    @PreAuthorize("hasRole(ADMIN)")
    @PostMapping(value = "/manager", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registrationManager(@RequestBody CreateUserReq createUserReq) {
        return userServiceImpl.createAdmin(createUserReq);
    }
}
