package graduation.service;

import graduation.model.api.AuthReq;
import graduation.model.api.CreateUserReq;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity createUser(CreateUserReq createUserReq);

    ResponseEntity createAdmin(CreateUserReq createUserReq);

    ResponseEntity auth(AuthReq authReq);
}
