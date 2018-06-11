package graduation.model.orm;

import graduation.util.DateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity extends AbstractBaseEntity {
    @Column(name = "token_id")
    @NotNull
    private String tokenId;

    @Column(name = "expire_date")
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime expireTime;

    public static TokenEntity of(String id, LocalDateTime expireDate) {
        return new TokenEntity(id, expireDate);
    }
}
