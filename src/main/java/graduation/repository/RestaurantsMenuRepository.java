package graduation.repository;

import graduation.model.orm.RestaurantsMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RestaurantsMenuRepository extends JpaRepository<RestaurantsMenuEntity, Integer> {
    @Transactional
    void deleteAllByIdInList(List<Integer> list);
}
