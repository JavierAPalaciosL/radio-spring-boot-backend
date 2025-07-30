package io.radioweather.radioweatherapi.application.out;


import io.radioweather.radioweatherapi.domain.Country;

import java.util.List;

public interface CityJPAPort {
    List<Country> getCoordsCountry(String countryName);
    public Country findByCountryByLatitudeAndLongitude(double latitude, double longitude);
}
