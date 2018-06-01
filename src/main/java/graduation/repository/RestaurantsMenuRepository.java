package graduation.repository;

import graduation.model.orm.RestaurantsMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RestaurantsMenuRepository extends JpaRepository<RestaurantsMenuEntity, Integer> {

    void deleteAllByIdInList(List<Integer> list);

    @Query("SELECT rme FROM RestaurantsMenuEntity rme WHERE rme.id in :ids ORDER BY rme.id ASC ")
    List<RestaurantsMenuEntity> findAllBy(@Param("ids") List<Integer> ids);
}
