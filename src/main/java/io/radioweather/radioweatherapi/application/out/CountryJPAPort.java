package io.radioweather.radioweatherapi.application.out;

import io.radioweather.radioweatherapi.domain.Country;

import java.util.List;

public interface CountryJPAPort {
    List<Country> getCoordsCountry(String iso2, int numberPage, int sizePage);
    public Country findByCountryByLatitudeAndLongitude(double latitude, double longitude);
    List<Country> getAllCountries(int page, int sizePage);
    Country findByIso2(String iso2);
    long countAllCountries();
}
