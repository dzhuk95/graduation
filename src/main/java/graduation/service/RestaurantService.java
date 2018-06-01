package graduation.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graduation.model.api.CreateRestaurantReq;
import graduation.model.api.DeleteRestaurantRequest;
import graduation.model.item.RestaurantMenuItem;
import graduation.model.orm.RestaurantsEntity;
import graduation.model.orm.RestaurantsMenuEntity;
import graduation.repository.RestaurantsMenuRepository;
import graduation.repository.RestaurantsRepository;
import graduation.repository.dao.RestaurantMenuDao;
import graduation.repository.dao.RestaurantsDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantsDao restaurantsDao;
    private final RestaurantMenuDao restaurantMenuDao;
    private final ObjectMapper objectMapper;

    public RestaurantService(@Autowired RestaurantsDao restaurantsDao,
                             @Autowired RestaurantMenuDao restaurantMenuDao,
                             @Autowired ObjectMapper objectMapper) {
        this.restaurantsDao = restaurantsDao;
        this.restaurantMenuDao = restaurantMenuDao;
        this.objectMapper = objectMapper;
    }

    public ResponseEntity getAll() {
        return ResponseEntity.ok(restaurantsDao.getAll());
    }

    @Transactional
    public ResponseEntity createRestaurant(CreateRestaurantReq createRestaurantReq) {
        String restaurantName = createRestaurantReq.getRestaurantName();
        List<RestaurantMenuItem> menu = createRestaurantReq.getMenu();
        Assert.notNull(restaurantName, "Restaurant name must not be null");
        Assert.notNull(menu, "Restaurant menu must not be null");
        Assert.notEmpty(menu, "Restaurant menu must not be empty");
        RestaurantsEntity of = RestaurantsEntity.of(restaurantName);
        restaurantsDao.save(of);
        List<RestaurantsMenuEntity> menuList = menu.stream().map(item -> RestaurantsMenuEntity.of(item, of)).collect(Collectors.toList());
        restaurantMenuDao.saveAll(menuList);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @Modifying
    public ResponseEntity updateMenu(List<RestaurantMenuItem> menu) {
        Assert.notNull(menu, "Update items must not be null");
        menu.sort(Comparator.comparingInt(RestaurantMenuItem::getId));
        List<RestaurantsMenuEntity> allById =
                restaurantMenuDao.getMenusItems(menu.stream().map(RestaurantMenuItem::getId).collect(Collectors.toList()));

        if (menu.size() != allById.size())
            throw new IllegalArgumentException("Some of provided elements are doesn't exist");

        for (int i = 0; i < allById.size(); i++) {
            RestaurantsMenuEntity restaurantsMenuEntity = allById.get(i);
            restaurantsMenuEntity.update(menu.get(i));
        }
        restaurantMenuDao.saveAll(allById);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteRestaurantMenu(List<Integer> ids) {
        restaurantMenuDao.delete(ids);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity deleteRestaurant(DeleteRestaurantRequest deleteId) {
        Assert.notNull(deleteId,"Request must not be null");
        Assert.isTrue(deleteId.getId()!=0,"Delete id must not be null");
        Integer id = null;
        id = deleteId.getId();
        if (id == null) throw new IllegalArgumentException("Id is not provided");
        restaurantsDao.delete(id);
        return ResponseEntity.ok().build();
    }
}
