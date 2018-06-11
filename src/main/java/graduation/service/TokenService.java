package graduation.service;

import graduation.model.api.AuthResp;
import graduation.model.orm.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public interface TokenService {

    AuthResp createToken(UserEntity entity);

    String createTokenFromRefreshToken(String refresh);

    default Claims getClaims(String token, String key) {
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

}
