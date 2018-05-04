package graduation.repository;

import graduation.model.orm.RestaurantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantsRepository extends JpaRepository<RestaurantsEntity,Integer> {
}
