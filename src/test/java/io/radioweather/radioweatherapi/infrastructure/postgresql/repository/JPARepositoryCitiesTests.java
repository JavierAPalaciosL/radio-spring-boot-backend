package io.radioweather.radioweatherapi.infrastructure.postgresql.repository;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Country;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class JPARepositoryCitiesTests {

    @Autowired
    private JPARepositoryCities jpaRepository;

    @Autowired
    private JPARepositoryStates jpaRepositoryStates;

    @Autowired
    private JPARepositoryCountry jpaRepositoryCountry;

    @BeforeEach
    public void setUp(){

        Country country = new Country(1,
                "México",
                "MXE",
                "MX",
                "52",
                "52",
                "MEXICO",
                "currency",
                "currency_name",
                "currency_symbol",
                "tld",
                "native",
                "region",
                "subregion",
                "timezones",
                "latitude",
                "longitude",
                "emoji",
                "emojiU",
                null);

        this.jpaRepositoryCountry.save(country);

        State state = new State(
                1,
                "VERACRUZ",
                country,
                "VER",
                30.0,
                40.0,
                null);

        this.jpaRepositoryStates.save(state);

        this.jpaRepositoryStates.save(
                new io.radioweather.radioweatherapi.infrastructure.postgresql.entity.State(
                        2,
                        "SINALOA",
                        country,
                        "SN",
                        30.0,
                        40.0,
                        null)
        );

        this.jpaRepository.save(
                new io.radioweather.radioweatherapi.infrastructure.postgresql.entity.City(
                        1,
                        "name",
                        state,
                        30.0,
                        40.0,
                        "wikidataid",
                        null)
        );

    }

    @Test
    public void testCountCityForCountriesAndStatesWhenExists(){
        long count = this.jpaRepository.countByStateAndCountryWithJoin("MX", "VER");
        Assertions.assertTrue(count >=1, "I found cities");
    }

    @Test
    public void testCountCityForCountriesAndStatesWhenNotExists(){
        long count = this.jpaRepository.countByStateAndCountryWithJoin("MXE", "VER");
        Assertions.assertTrue(count <= 0, "I do not found cities");
    }


}
