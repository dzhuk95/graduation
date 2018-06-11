package graduation.model.item;

import graduation.model.UserType;
import graduation.model.orm.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
@Setter
public class AuthorizedUser extends User {
    private UserEntity user;
    private UserType type;

    public AuthorizedUser(UserEntity user, UserType type) {
        super("JWT User", "JWT Password", Collections.singletonList(type));
        this.user = user;
        this.type = type;
    }
}
