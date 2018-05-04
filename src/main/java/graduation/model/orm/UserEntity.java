package graduation.model.orm;

import graduation.model.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class UserEntity extends AbstractBaseEntity {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;

    @Column(name = "user_role")
    @Enumerated
    private UserType userType;

    public UserEntity() {
    }

    public UserEntity(@NotNull @NotEmpty String name, @NotNull @NotEmpty String email, @NotNull @NotEmpty String username, @NotNull @NotEmpty String password, UserType userType) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
