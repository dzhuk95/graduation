package graduation.model.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RestaurantMenuItem {
    private int id;
    private String name;
    private double price;
}
