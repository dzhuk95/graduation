package graduation.service;

import graduation.model.AuthorizedUser;
import graduation.model.UserType;
import graduation.model.api.CreateUserReq;
import graduation.model.orm.UserEntity;
import graduation.repository.UserRepository;
import graduation.repository.dao.UserDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    public UserService(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

    public ResponseEntity createUser(CreateUserReq createUserReq) {
        check(createUserReq);
        saveUser(createUserReq, UserType.USER);
        return ResponseEntity.ok("User created");
    }

    private void saveUser(CreateUserReq createUserReq, UserType userType) {
        UserEntity of = UserEntity.of(createUserReq, userType);
        userDao.save(of);
    }


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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDao.loadUserByUsername(username);
        if (user == null)
            throw new IllegalArgumentException("User not found");
        return new AuthorizedUser(user);
    }
}
