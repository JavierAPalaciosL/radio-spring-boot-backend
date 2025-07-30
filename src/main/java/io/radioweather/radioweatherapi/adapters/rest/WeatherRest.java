package io.radioweather.radioweatherapi.adapters.rest;

import io.radioweather.radioweatherapi.application.usecases.UseCaseCountries;
import io.radioweather.radioweatherapi.domain.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/weather")
public class WeatherRest {

    private final UseCaseCountries useCaseCountries;

    public WeatherRest(UseCaseCountries useCaseCountries) {
        this.useCaseCountries = useCaseCountries;
    }

    @GetMapping
    public ResponseEntity<Weather> getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        return ResponseEntity.ok(this.useCaseCountries.getWeather(latitude, longitude));
    }

}
