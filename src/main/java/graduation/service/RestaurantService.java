package graduation.service;

import graduation.model.api.CreateRestaurantReq;
import graduation.model.item.RestaurantMenuItem;
import graduation.model.orm.RestaurantsEntity;
import graduation.model.orm.RestaurantsMenuEntity;
import graduation.repository.RestaurantsMenuRepository;
import graduation.repository.RestaurantsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Getter
    private final RestaurantsRepository restaurantsRepository;
    @Getter
    private final RestaurantsMenuRepository restaurantsMenuRepository;

    public RestaurantService(@Autowired RestaurantsRepository restaurantsRepository, @Autowired RestaurantsMenuRepository restaurantsMenuRepository) {
        this.restaurantsRepository = restaurantsRepository;
        this.restaurantsMenuRepository = restaurantsMenuRepository;
    }

    @Transactional
    public ResponseEntity createRestaurant(CreateRestaurantReq createRestaurantReq) {
        String restaurantName = createRestaurantReq.getRestaurantName();
        List<RestaurantMenuItem> menu = createRestaurantReq.getMenu();
        Assert.notNull(restaurantName, "Restaurant name must not be null");
        Assert.notNull(menu, "Restaurant menu must not be null");
        Assert.notEmpty(menu, "Restaurant menu must not be empty");
        RestaurantsEntity of = RestaurantsEntity.of(restaurantName);
        restaurantsRepository.save(of);
        List<RestaurantsMenuEntity> menuList = menu.stream().map(item -> RestaurantsMenuEntity.of(item, of)).collect(Collectors.toList());
        restaurantsMenuRepository.saveAll(menuList);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @Modifying
    public ResponseEntity updateMenu(List<RestaurantMenuItem> menu) {
        Assert.notNull(menu,"Update items must not be null");
        menu.sort(Comparator.comparingInt(RestaurantMenuItem::getId));
        List<RestaurantsMenuEntity> allById =
                restaurantsMenuRepository.findAllById(menu.stream().map(RestaurantMenuItem::getId).collect(Collectors.toList()));

        if (menu.size() != allById.size())
            throw new IllegalArgumentException("Some of provided elements are doesn't exist");

        for (int i = 0; i < allById.size(); i++) {
            RestaurantsMenuEntity restaurantsMenuEntity = allById.get(i);
            restaurantsMenuEntity.update(menu.get(i));
        }
        restaurantsMenuRepository.saveAll(allById);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteRestaurantMenu(List<Integer> ids) {
        restaurantsMenuRepository.deleteAllByIdInList(ids);
        return ResponseEntity.ok().build();
    }

}
