package graduation.model;

import graduation.model.orm.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AuthorizedUser {
    @Getter
    @Setter
    private UserEntity userEntity;


}
