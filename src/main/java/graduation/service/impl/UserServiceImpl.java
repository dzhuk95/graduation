package graduation.service.impl;

import graduation.model.UserType;
import graduation.model.api.AuthReq;
import graduation.model.api.CreateUserReq;
import graduation.model.orm.UserEntity;
import graduation.repository.dao.UserDao;
import graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

    public ResponseEntity auth(AuthReq authReq) {
        UserEntity authUser = userDao.findUserByUsernameAndPassword(authReq.getUsername(), authReq.getPassword());
        if (authUser == null) throw new IllegalArgumentException("User doesn't exist");
        
    }

    public ResponseEntity createUser(CreateUserReq createUserReq) {
        check(createUserReq);
        saveUser(createUserReq, UserType.USER);
        return ResponseEntity.ok("User created");
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

    private void saveUser(CreateUserReq createUserReq, UserType userType) {
        UserEntity of = UserEntity.of(createUserReq, userType);
        userDao.save(of);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDao.loadUserByUsername(username);
        if (user == null)
            throw new IllegalArgumentException("User not found");
        return new AuthorizedUser(user);
    }*/
}
