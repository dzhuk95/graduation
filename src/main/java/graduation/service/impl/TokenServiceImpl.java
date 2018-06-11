package graduation.service.impl;

import graduation.model.UserType;
import graduation.model.WrongTokenException;
import graduation.model.api.AuthResp;
import graduation.model.orm.TokenEntity;
import graduation.model.orm.UserEntity;
import graduation.repository.dao.TokenDao;
import graduation.service.PropertyService;
import graduation.service.TokenService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenServiceImpl implements TokenService {

    private final TokenDao tokenDao;

    private final PropertyService propertyService;

    private final String key;
    private final Long expireDay;
    private final Long refreshTokenExpireDay;

    public TokenServiceImpl(@Autowired TokenDao tokenDao,
                            @Autowired PropertyService propertyService) {
        this.tokenDao = tokenDao;
        this.propertyService = propertyService;
        this.key = propertyService.getSecret();
        this.expireDay = propertyService.getExpireAfterDays();
        this.refreshTokenExpireDay = propertyService.getExpireRefreshAfterDays();
    }

    @Override
    public AuthResp createToken(UserEntity entity) {
        String string = UUID.randomUUID().toString();
        LocalDateTime createTime = LocalDateTime.now();
        String token = createToken(entity, string, createTime.toLocalDate(), expireDay);
        String refreshToken = createToken(entity, string, createTime.toLocalDate(), refreshTokenExpireDay);
        tokenDao.save(TokenEntity.of(string, createTime.plusDays(refreshTokenExpireDay)));
        return AuthResp.of(token, refreshToken);
    }

    private Map<String, Object> createMap(Integer userId, String uuid, UserType userType) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", uuid);
        map.put("userId", userId);
        map.put("type", userType);
        return map;
    }

    private String createToken(UserEntity userEntity, String uuid, LocalDate date, Long expireDays) {
        return Jwts.builder()
                .setClaims(createMap(userEntity.getId(), uuid, userEntity.getUserType()))
                .setIssuedAt(new Date(date.format(DateTimeFormatter.ISO_DATE)))
                .setExpiration(new Date(date.plusDays(expireDays).format(DateTimeFormatter.ISO_DATE)))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    @Override
    public String createTokenFromRefreshToken(String refresh) {
        return null;
    }

    public boolean validateToken(String token) throws RuntimeException {
        try {
            Claims body = getClaims(token, key);
            return true;
        } catch (SignatureException e) {
            throw new WrongTokenException("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new WrongTokenException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new WrongTokenException("Token Expired");
        } catch (UnsupportedJwtException e) {
            throw new WrongTokenException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new WrongTokenException("JWT token compact of handler are invalid");
        }
    }
}
