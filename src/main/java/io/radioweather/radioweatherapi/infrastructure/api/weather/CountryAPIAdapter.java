package io.radioweather.radioweatherapi.infrastructure.api.weather;

import io.radioweather.radioweatherapi.application.out.CountyAPIPort;
import io.radioweather.radioweatherapi.domain.Country;
import io.radioweather.radioweatherapi.domain.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Component
public class CountryAPIAdapter implements CountyAPIPort {

    @Value("${spring.apiKeys.weather}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public CountryAPIAdapter() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Country> getCoordsCountry(String countryName) {

        String url = "https://api.openweathermap.org/geo/1.0/direct?q="+countryName+"&limit=15&appid="+this.apiKey;
        Country[] countries = restTemplate.getForObject(
                url,
                Country[].class
        );

        return Arrays.asList(countries);
    }

    @Override
    public Weather getWeather(double latitude, double longitude) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&exclude=hourly,daily&appid="+this.apiKey;
        return restTemplate.getForObject(url, Weather.class);
    }


}
