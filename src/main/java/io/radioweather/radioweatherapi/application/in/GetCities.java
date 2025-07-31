package io.radioweather.radioweatherapi.application.in;

import io.radioweather.radioweatherapi.domain.City;

import java.util.List;

public interface GetCities {

    public List<City> getCitiesByIso2CountryAndStateCode(String countryIso2, String stateCode, int page, int size);
    long getCountCityByIso2CountryAndStateCode(String countryIso2, String stateCode);
    City findCityByCountryIso2AndCodeState(String countryIso2, String codeState, String cityName);

}
