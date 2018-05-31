package graduation.model.orm;

import graduation.util.DateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_votes")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserVotesEntity extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.JOIN)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @Fetch(FetchMode.JOIN)
    private RestaurantsEntity restaurant;

    @Column(name = "vote_datetime")
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime localDateTime;

    public static UserVotesEntity of(UserEntity entity, RestaurantsEntity restaurant, LocalDateTime time) {
        return new UserVotesEntity(entity, restaurant, time);
    }
}
