package graduation.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
    USER(0), ADMIN(1);
    private int id;

    UserType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
