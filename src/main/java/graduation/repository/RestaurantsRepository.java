package graduation.repository;

import graduation.model.orm.RestaurantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantsRepository extends JpaRepository<RestaurantsEntity,Integer> {
}
