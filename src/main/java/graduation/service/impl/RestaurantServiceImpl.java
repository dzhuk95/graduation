package graduation.service.impl;

import graduation.model.UserType;
import graduation.model.api.CreateRestaurantReq;
import graduation.model.api.DeleteRestaurantRequest;
import graduation.model.api.UpdateRestaurantMenuReq;
import graduation.model.api.UserBaseReq;
import graduation.model.item.RestaurantMenuItem;
import graduation.model.orm.RestaurantsEntity;
import graduation.model.orm.RestaurantsMenuEntity;
import graduation.model.orm.UserEntity;
import graduation.repository.dao.RestaurantMenuDao;
import graduation.repository.dao.RestaurantsDao;
import graduation.repository.dao.UserDao;
import graduation.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantsDao restaurantsDao;
    private final RestaurantMenuDao restaurantMenuDao;
    private final UserDao userDao;

    public RestaurantServiceImpl(@Autowired RestaurantsDao restaurantsDao,
                                 @Autowired RestaurantMenuDao restaurantMenuDao,
                                 @Autowired UserDao userDao) {
        this.restaurantsDao = restaurantsDao;
        this.restaurantMenuDao = restaurantMenuDao;
        this.userDao = userDao;
    }

    public ResponseEntity getAll() {
        return ResponseEntity.ok(restaurantsDao.getAll());
    }

    @Transactional
    public ResponseEntity createRestaurant(CreateRestaurantReq req) {
        assertReq(req);
        String restaurantName = req.getRestaurantName();
        List<RestaurantMenuItem> menu = req.getMenu();
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
    public ResponseEntity updateMenu(UpdateRestaurantMenuReq req) {
        Assert.notNull(req.getMenu(), "Update items must not be null");
        int userId = req.getUserId();
        Assert.isTrue(userId != 0, "User id must not be null");

        assertReq(req);

        List<RestaurantMenuItem> menu = req.getMenu();
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
    public ResponseEntity deleteRestaurant(DeleteRestaurantRequest req) {
        Assert.notNull(req, "Request must not be null");
        assertReq(req);
        int id = req.getId();
        Assert.isTrue(req.getId() != 0, "Delete id must not be null");
        restaurantsDao.delete(id);
        return ResponseEntity.ok().build();
    }

    private <T extends UserBaseReq> void assertReq(T req) {
        UserEntity entity = userDao.findUserById(req.getUserId());
        Assert.notNull(entity, "User must not be null");
        Assert.isTrue(entity.getUserType() == UserType.ADMIN, "User can do this");
    }
}
