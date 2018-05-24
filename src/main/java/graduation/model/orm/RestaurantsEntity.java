package graduation.model.orm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

import static graduation.model.orm.RestaurantsEntity.MENU;

@Entity
@Table(name = "restaurants")
@NamedEntityGraph(name = MENU, attributeNodes = {@NamedAttributeNode(value = "menu")})
@NoArgsConstructor
public class RestaurantsEntity extends AbstractBaseEntity {
    public static final String MENU = "Restaurants.withMenu";

    @Column(name = "restaurant_name")
    @Getter
    @Setter
    private String restaurantName;

    @OneToMany(mappedBy = "restaurantsEntity", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Getter
    @Setter
    private List<RestaurantsMenuEntity> menu;

    public RestaurantsEntity(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public static RestaurantsEntity of(String restaurantName) {
        return new RestaurantsEntity(restaurantName);
    }

}
