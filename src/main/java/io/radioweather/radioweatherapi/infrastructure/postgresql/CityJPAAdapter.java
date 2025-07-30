package io.radioweather.radioweatherapi.infrastructure.postgresql;

import io.radioweather.radioweatherapi.application.out.CityJPAPort;

import io.radioweather.radioweatherapi.domain.Country;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.City;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryCities;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CityJPAAdapter implements CityJPAPort {

    private final JPARepositoryCities jpaRepositoryCities;

    public CityJPAAdapter(JPARepositoryCities jpaRepositoryCities) {
        this.jpaRepositoryCities = jpaRepositoryCities;
    }


    @Override
    public List<Country> getCoordsCountry(String countryName) {
        Slice<City> paginator =  jpaRepositoryCities.findByNameContainingIgnoreCase(countryName, PageRequest.of(0, 10));

        /*return paginator.stream().map(city ->  {
            return new Country(city.getName().stripTrailing(), null, city.getLatitude(), city.getLongitude(), city.getState().getCountry().getIso3().replace(" ", ""), "state",city.getState().getCountry().getIso2().replace(" ", ""));
        }).toList();*/
        return null;
    }

    @Override
    public Country findByCountryByLatitudeAndLongitude(double latitude, double longitude) {
        City city = this.jpaRepositoryCities.findByLatitudeAndLongitude(latitude, longitude);

        /*return new Country(city.getName().stripTrailing(), null, city.getLatitude(), city.getLongitude(), city.getState().getCountry().getIso3().replace(" ", ""), "state",city.getState().getCountry().getIso2().replace(" ", ""));*/
        return null;
    }

}
