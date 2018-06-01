package graduation.repository.dao;

import graduation.model.orm.RestaurantsEntity;
import graduation.repository.RestaurantsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RestaurantsDao {

    @Getter
    private final RestaurantsRepository restaurantsRepository;

    public RestaurantsDao(@Autowired RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    @Transactional
    public RestaurantsEntity save(RestaurantsEntity entity) {
        return restaurantsRepository.save(entity);
    }

    public List<RestaurantsEntity> getAll() {
        return restaurantsRepository.getAll();
    }

    @Transactional
    public void delete(int id) {
        restaurantsRepository.deleteById(id);
    }
}
