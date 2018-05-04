package graduation.model.orm;

import graduation.util.DateTimeConverter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_votes")
public class UserVotesEntity extends AbstractBaseEntity {

    @Column(name = "vote_datetime")
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @Fetch(FetchMode.JOIN)
    private RestaurantsEntity restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @Fetch(FetchMode.JOIN)
    private UserEntity user;

    public UserVotesEntity() {
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public RestaurantsEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantsEntity restaurant) {
        this.restaurant = restaurant;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
