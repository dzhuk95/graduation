package graduation.model.orm;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

import static graduation.model.orm.RestaurantsEntity.MENU;

@Entity
@Table(name = "restaurants")
@NamedEntityGraph(name = MENU, attributeNodes = {@NamedAttributeNode(value = "menu")})
public class RestaurantsEntity extends AbstractBaseEntity {
    public static final String MENU = "Restaurants.withMenu";
    @Column(name = "restaurant_name")
    private String restaurantName;

    @OneToMany(mappedBy = "restaurantsEntity", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<RestaurantsMenuEntity> menu;

    public RestaurantsEntity() {
    }

    public RestaurantsEntity(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<RestaurantsMenuEntity> getMenu() {
        return menu;
    }

    public void setMenu(List<RestaurantsMenuEntity> menu) {
        this.menu = menu;
    }
}
