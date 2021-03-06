package graduation.model.api;

import graduation.model.item.RestaurantMenuItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CreateRestaurantReq extends UserBaseReq {
    private String restaurantName;
    private List<RestaurantMenuItem> menu;

}
