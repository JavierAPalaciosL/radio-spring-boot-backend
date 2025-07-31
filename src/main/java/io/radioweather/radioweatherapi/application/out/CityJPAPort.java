package io.radioweather.radioweatherapi.application.out;


import io.radioweather.radioweatherapi.domain.City;
import io.radioweather.radioweatherapi.domain.Country;

import java.util.List;

public interface CityJPAPort {
    List<Country> getCoordsCountry(String countryName);
    public Country findByCountryByLatitudeAndLongitude(double latitude, double longitude);
    List<City> findCityByCountryIso2AndStateCode(String countryIso2, String stateCode, int page, int size);
    long countCityByCountryIso2AndCodeState(String countryIso2, String codeState);
    City findCityByCountryIso2AndCodeState(String countryIso2, String codeState, String cityName);
}
