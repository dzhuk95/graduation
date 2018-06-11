package graduation.controller;

import graduation.model.api.CreateRestaurantReq;
import graduation.model.api.DeleteRestaurantRequest;
import graduation.service.RestaurantService;
import graduation.service.impl.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/restaurant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RestaurantRestController {

    private final RestaurantService restaurantServiceImpl;

    public RestaurantRestController(@Autowired RestaurantService restaurantServiceImpl) {
        this.restaurantServiceImpl = restaurantServiceImpl;
    }

    @GetMapping("all")
    public ResponseEntity getAllRestaurants() {
        return restaurantServiceImpl.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createRestaurant(@RequestBody CreateRestaurantReq createRestaurantReq) {
        return restaurantServiceImpl.createRestaurant(createRestaurantReq);
    }

    @DeleteMapping
    public ResponseEntity deleteRestaurant(@RequestBody DeleteRestaurantRequest deleteId) {
        return restaurantServiceImpl.deleteRestaurant(deleteId);
    }

}
