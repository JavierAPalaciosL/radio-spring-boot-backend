package io.radioweather.radioweatherapi.application.usecases;

import io.radioweather.radioweatherapi.application.in.GetAllCountries;
import io.radioweather.radioweatherapi.application.in.GetWeather;
import io.radioweather.radioweatherapi.application.in.SearchCountry;
import io.radioweather.radioweatherapi.application.out.CityJPAPort;
import io.radioweather.radioweatherapi.application.out.CountyAPIPort;
import io.radioweather.radioweatherapi.domain.Country;
import io.radioweather.radioweatherapi.domain.Weather;
import io.radioweather.radioweatherapi.infrastructure.postgresql.CountryJPAAdapter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UseCaseCountries implements SearchCountry, GetWeather, GetAllCountries {

    private final CountyAPIPort countyAPIPort;
    private final CountryJPAAdapter countryJPAAdapter;

    public UseCaseCountries(CountyAPIPort countyAPIPort, CountryJPAAdapter countryJPAAdapter) {
        this.countyAPIPort = countyAPIPort;
        this.countryJPAAdapter = countryJPAAdapter;
    }

    @Override
    public List<Country> getCoordsCountry(String countryName, int page, int sizePage) {

        return this.countryJPAAdapter.getCoordsCountry(countryName, page,sizePage);
                /*countyAPIPort.getCoordsCountry(countryName)*/
    }

    @Override
    public Country findByCoords(double latitude, double longitude) {
        return this.countryJPAAdapter.findByCountryByLatitudeAndLongitude(latitude, longitude);
    }

    @Override
    public Weather getWeather(double latitude, double longitude) {
        return countyAPIPort.getWeather(latitude, longitude);
    }

    @Override
    public List<Country> getAllCountries(int page, int sizePage) {
        return this.countryJPAAdapter.getAllCountries(page, sizePage);
    }

    @Override
    public long countAllCountries() {
        return this.countryJPAAdapter.countAllCountries();
    }


}
