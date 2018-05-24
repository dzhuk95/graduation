package graduation.model.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class RestaurantMenuItem {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double price;
}
