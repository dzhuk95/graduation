package graduation.repository;

import graduation.model.orm.RestaurantsEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static graduation.model.orm.RestaurantsEntity.MENU;

@Repository
public interface RestaurantsRepository extends JpaRepository<RestaurantsEntity, Integer> {

    @EntityGraph(value = MENU)
    List<RestaurantsEntity> getAll();
}
