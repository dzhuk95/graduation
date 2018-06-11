package graduation.repository.dao;

import graduation.model.orm.UserEntity;
import graduation.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserDao {

    @Getter
    private final UserRepository userRepository;

    public UserDao(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity findUserById(Integer userId) {
        return userRepository.getOne(userId);
    }

    public UserEntity findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}
