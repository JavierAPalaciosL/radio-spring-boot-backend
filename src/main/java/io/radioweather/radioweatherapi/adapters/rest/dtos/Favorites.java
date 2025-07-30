package io.radioweather.radioweatherapi.adapters.rest.dtos;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/favorites")
public class Favorites {

    @GetMapping
    public ResponseEntity<?> sayHello() {
        return ResponseEntity.ok("Hello");
    }

}
