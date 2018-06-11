package graduation.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@PropertySource(value = "classpath:jwt/jwt")
@Service
@Getter
@Setter
@NoArgsConstructor
public class PropertyService {

    @Value(value = "${jwt.secret.key}")
    private String secret;
    @Value(value = "${jwt.expire.day}")
    private long expireAfterDays;
    @Value(value = "${jwt.refresh.expire.day}")
    private long expireRefreshAfterDays;

}
