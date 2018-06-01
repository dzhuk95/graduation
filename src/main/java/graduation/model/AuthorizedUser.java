package graduation.model;

import graduation.model.orm.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

public class AuthorizedUser extends User {
    @Getter
    @Setter
    private UserEntity userEntity;

    public AuthorizedUser(UserEntity userEntity) {
        super(userEntity.getUsername(), userEntity.getPassword(), Collections.singletonList(userEntity.getUserType()));
        this.userEntity = userEntity;
    }

    private AuthorizedUser(String username, String password, boolean enabled, boolean accountNonExpired,
                           boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

    }

    public AuthorizedUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static UserEntity getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null)
            return null;
        AuthorizedUser user = (AuthorizedUser) principal;
        return user.getUserEntity();
    }
}
