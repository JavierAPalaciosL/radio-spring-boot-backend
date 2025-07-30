package io.radioweather.radioweatherapi.infrastructure.postgresql.repository;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Country;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.State;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPARepositoryStates extends JpaRepository<State, String> {
    Slice<State> findByCountry(Country country,  Pageable pageable);
    int countByCountry(Country country);


    /*
    * WITH tableCountries as (
    SELECT
        RTRIM(c.name)      AS name_countries,
        c.latitude,
        c.longitude,
        s.country_id       AS country_id,
        RTRIM(c.iso2)      AS iso2,
        RTRIM(c.emoji)     AS emoji,
        RTRIM(c.emojiu)    AS emojiu
    FROM countries c
    INNER JOIN states s on s.country_id = c.id)
    SELECT DISTINCT * from tableCountries ORDER BY tableCountries.name_countries ASC;
    * */

}
