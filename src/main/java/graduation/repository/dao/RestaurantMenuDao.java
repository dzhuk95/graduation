package graduation.repository.dao;

import graduation.model.orm.RestaurantsMenuEntity;
import graduation.repository.RestaurantsMenuRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RestaurantMenuDao {

    @Getter
    private final RestaurantsMenuRepository restaurantsMenuRepository;


    public RestaurantMenuDao(@Autowired RestaurantsMenuRepository restaurantsMenuRepository) {
        this.restaurantsMenuRepository = restaurantsMenuRepository;
    }

    public List<RestaurantsMenuEntity> getMenusItems(List<Integer> ids) {
        return restaurantsMenuRepository.findAllBy(ids);
    }

    @Transactional
    public void delete(List<Integer> ids) {
        restaurantsMenuRepository.deleteAllByIdInList(ids);
    }

    @Transactional
    public void delete(int id) {
        restaurantsMenuRepository.deleteById(id);
    }

    @Transactional
    public RestaurantsMenuEntity save(RestaurantsMenuEntity restaurantsMenuEntity) {
        return restaurantsMenuRepository.save(restaurantsMenuEntity);
    }

    @Transactional
    public List<RestaurantsMenuEntity> saveAll(List<RestaurantsMenuEntity> list) {
        return restaurantsMenuRepository.saveAll(list);
    }
}
