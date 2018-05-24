package graduation.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/restaurant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RestaurantRestController {

}
