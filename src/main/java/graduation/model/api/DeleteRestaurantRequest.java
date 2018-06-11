package graduation.model.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteRestaurantRequest extends UserBaseReq {
    private int id;
}
