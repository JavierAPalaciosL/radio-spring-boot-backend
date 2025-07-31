package io.radioweather.radioweatherapi.application.usecases;

import io.radioweather.radioweatherapi.application.in.GetCities;
import io.radioweather.radioweatherapi.application.out.CityJPAPort;
import io.radioweather.radioweatherapi.domain.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseCaseCities implements GetCities {

    private final CityJPAPort cityJPAPort;

    public UseCaseCities(CityJPAPort cityJPAPort) {
        this.cityJPAPort = cityJPAPort;
    }

    @Override
    public List<City> getCitiesByIso2CountryAndStateCode(String countryIso2, String stateCode, int page, int size) {
        return this.cityJPAPort.findCityByCountryIso2AndStateCode(countryIso2, stateCode, page, size);
    }

    @Override
    public long getCountCityByIso2CountryAndStateCode(String countryIso2, String stateCode) {
        return this.cityJPAPort.countCityByCountryIso2AndCodeState(countryIso2, stateCode);
    }

    @Override
    public City findCityByCountryIso2AndCodeState(String countryIso2, String codeState, String cityName) {

        return this.cityJPAPort.findCityByCountryIso2AndCodeState(countryIso2, codeState, cityName);
    }
}
