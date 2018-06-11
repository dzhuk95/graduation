package graduation.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResp {
    String token;
    String refreshToken;

    public static AuthResp of(String token, String refreshToken) {
        return new AuthResp(token, refreshToken);
    }
}
