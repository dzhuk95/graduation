package graduation.repository.dao;

import graduation.model.orm.RestaurantsMenuEntity;
import graduation.repository.RestaurantsMenuRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RestaurantMenuDao {

    @Getter
    private final RestaurantsMenuRepository restaurantsMenuRepository;


    public RestaurantMenuDao(@Autowired RestaurantsMenuRepository restaurantsMenuRepository) {
        this.restaurantsMenuRepository = restaurantsMenuRepository;
    }

    List<RestaurantsMenuEntity> getMenusItems(List<Integer> ids) {
        return restaurantsMenuRepository.findAllBy(ids);
    }
}
