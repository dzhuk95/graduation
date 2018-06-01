package graduation.controller;

import graduation.model.api.CreateRestaurantReq;
import graduation.model.api.DeleteRestaurantRequest;
import graduation.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/restaurant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RestaurantRestController {


    private final RestaurantService restaurantService;

    public RestaurantRestController(@Autowired RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("all")
    public ResponseEntity getAllRestaurants() {
        return restaurantService.getAll();
    }

    @PreAuthorize(value = "hasRole(USER)")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createRestaurant(@RequestBody CreateRestaurantReq createRestaurantReq) {
        return restaurantService.createRestaurant(createRestaurantReq);
    }

    @PreAuthorize(value = "hasRole(USER)")
    @DeleteMapping
    public ResponseEntity deleteRestaurant(@RequestBody DeleteRestaurantRequest deleteId) {
        return restaurantService.deleteRestaurant(deleteId);
    }

}
