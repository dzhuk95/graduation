package graduation.model.api;

import graduation.model.item.RestaurantMenuItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class CreateRestaurantReq {
    @Getter
    @Setter
    private String restaurantName;
    @Getter
    @Setter
    private List<RestaurantMenuItem> menu;
}
