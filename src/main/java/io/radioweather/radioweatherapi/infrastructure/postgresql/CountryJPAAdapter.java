package io.radioweather.radioweatherapi.infrastructure.postgresql;

import io.radioweather.radioweatherapi.application.out.CountryJPAPort;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Countrieswithstate;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Country;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryCountry;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.views.JPARepositoryViewCountry;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CountryJPAAdapter implements CountryJPAPort {

    private final JPARepositoryCountry jpaRepositoryCountry;
    private final JPARepositoryViewCountry  jpaRepositoryViewCountry;

    public CountryJPAAdapter(JPARepositoryCountry jpaRepositoryCountry,  JPARepositoryViewCountry jpaRepositoryViewCountry) {
        this.jpaRepositoryCountry = jpaRepositoryCountry;
        this.jpaRepositoryViewCountry = jpaRepositoryViewCountry;
    }

    @Override
    public List<io.radioweather.radioweatherapi.domain.Country> getCoordsCountry(String iso2, int numberPage, int sizePage) {
        Slice<Country> paginator =  jpaRepositoryCountry.findByIso2(iso2, PageRequest.of(numberPage, sizePage));

        return paginator.stream().map(city ->  {
            return new io.radioweather.radioweatherapi.domain.Country(city.getName().stripTrailing(), null, Double.parseDouble(city.getLatitude()), Double.parseDouble(city.getLongitude()), city.getIso3().replace(" ", ""), "state", city.getIso2().replace(" ", ""), city.getEmoji(), city.getEmojiu());
        }).collect(Collectors.toList());
    }

    @Override
    public io.radioweather.radioweatherapi.domain.Country findByCountryByLatitudeAndLongitude(double latitude, double longitude) {
        return null;
    }

    @Override
    public List<io.radioweather.radioweatherapi.domain.Country> getAllCountries(int page, int sizePage) {

        Slice<Countrieswithstate> countries = this.jpaRepositoryViewCountry.findAllBy(PageRequest.of(page, sizePage));

        return countries.stream().map(city ->  {
            return new io.radioweather.radioweatherapi.domain.Country(city.getNameCountries().stripTrailing(), null, Double.parseDouble(city.getLatitude()), Double.parseDouble(city.getLongitude()), city.getIso3().replace(" ", ""), "state", city.getIso2().replace(" ", ""), city.getEmoji().stripTrailing(), city.getEmojiu().stripTrailing());
        }).collect(Collectors.toList());
    }

    @Override
    public io.radioweather.radioweatherapi.domain.Country findByIso2(String iso2) {
        Country countryFound = this.jpaRepositoryCountry.findByIso2(iso2);

        return new io.radioweather.radioweatherapi.domain.Country(
                countryFound.getName(),
                null,
                Double.parseDouble(countryFound.getLatitude()),
                Double.parseDouble(countryFound.getLongitude()),
                countryFound.getName(),
                "",
                countryFound.getIso2(),
                countryFound.getEmoji(), countryFound.getEmojiu());
    }

    @Override
    public long countAllCountries() {
        return this.jpaRepositoryViewCountry.count();
    }


}
