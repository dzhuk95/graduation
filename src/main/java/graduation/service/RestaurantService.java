package graduation.service;

import graduation.model.api.CreateRestaurantReq;
import graduation.model.api.DeleteRestaurantRequest;
import graduation.model.api.UpdateRestaurantMenuReq;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestaurantService {
    ResponseEntity getAll();

    ResponseEntity createRestaurant(CreateRestaurantReq req);

    ResponseEntity updateMenu(UpdateRestaurantMenuReq req);

    ResponseEntity deleteRestaurantMenu(List<Integer> ids);

    ResponseEntity deleteRestaurant(DeleteRestaurantRequest req);
}
