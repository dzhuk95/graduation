package graduation.model.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserVoteReq extends UserBaseReq {
    private int restaurantId;


}
