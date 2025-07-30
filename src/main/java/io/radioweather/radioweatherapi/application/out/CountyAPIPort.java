package io.radioweather.radioweatherapi.application.out;

import io.radioweather.radioweatherapi.domain.Country;
import io.radioweather.radioweatherapi.domain.Weather;

import java.util.List;

public interface CountyAPIPort {
    List<Country> getCoordsCountry(String countryName);
    Weather getWeather(double latitude, double longitude);

}
