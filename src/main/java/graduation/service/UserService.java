package graduation.service;

import graduation.model.UserType;
import graduation.model.api.CreateUserReq;
import graduation.model.orm.UserEntity;
import graduation.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class UserService implements UserDetailsService {

    @Getter
    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity createUser(CreateUserReq createUserReq) {
        check(createUserReq);
        saveUser(createUserReq, UserType.USER);
        return ResponseEntity.ok("User created");
    }

    private void saveUser(CreateUserReq createUserReq, UserType userType) {
        UserEntity of = UserEntity.of(createUserReq, userType);
        userRepository.save(of);
    }

    @Transactional
    public ResponseEntity createAdmin(CreateUserReq createUserReq) {
        check(createUserReq);
        saveUser(createUserReq, UserType.ADMIN);
        return ResponseEntity.ok("User created");
    }

    private void check(CreateUserReq createUserReq) {
        String mustNotBeNull = " must not be null";
        Assert.notNull(createUserReq, "Request" + mustNotBeNull);
        Assert.notNull(createUserReq.getName(), "Name" + mustNotBeNull);
        Assert.notNull(createUserReq.getEmail(), "Email" + mustNotBeNull);
        Assert.notNull(createUserReq.getPassword(), "Password" + mustNotBeNull);
        Assert.notNull(createUserReq.getUsername(), "Username" + mustNotBeNull);
    }

    @Override
    // TODO: 31.05.2018 implement logic
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
