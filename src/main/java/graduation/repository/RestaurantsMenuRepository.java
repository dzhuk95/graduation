package graduation.repository;

import graduation.model.orm.RestaurantsMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantsMenuRepository extends JpaRepository<RestaurantsMenuEntity,Integer> {
}
