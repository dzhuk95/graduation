package graduation.model.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserReq {
    private String name;
    private String username;
    private String password;
    private String email;
}
