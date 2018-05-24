package graduation.model.orm;

import graduation.model.item.RestaurantMenuItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurants_menu")
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantsMenuEntity extends AbstractBaseEntity {
    @Column(name = "item_name")
    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String itemName;

    @Column(name = "item_price")
    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private Double itemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @Getter
    @Setter
    private RestaurantsEntity restaurantsEntity;

    public static RestaurantsMenuEntity of(RestaurantMenuItem restaurantMenuItem, RestaurantsEntity restaurantsEntity) {
        return new RestaurantsMenuEntity(restaurantMenuItem.getName(), restaurantMenuItem.getPrice(), restaurantsEntity);
    }

    public RestaurantsMenuEntity update(RestaurantMenuItem restaurantMenuItem) {
        String name = restaurantMenuItem.getName();
        double price = restaurantMenuItem.getPrice();
        this.itemName = name == null ? this.itemName : name;
        this.itemPrice = price == 0 ? this.itemPrice : price;
        return this;
    }
}
