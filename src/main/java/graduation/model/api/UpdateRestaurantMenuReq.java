package graduation.model.api;

import graduation.model.item.RestaurantMenuItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRestaurantMenuReq extends UserBaseReq {

    private List<RestaurantMenuItem> menu;
}
