package graduation.model.orm;

import graduation.model.UserType;
import graduation.model.api.CreateUserReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public static UserEntity of(CreateUserReq createUserReq, UserType userType) {
        return new UserEntity(createUserReq.getName(), createUserReq.getEmail(), createUserReq.getUsername(), createUserReq.getPassword(), userType);
    }
}
