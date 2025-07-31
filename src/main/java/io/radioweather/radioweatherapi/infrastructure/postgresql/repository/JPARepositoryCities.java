package io.radioweather.radioweatherapi.infrastructure.postgresql.repository;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.City;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Country;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.State;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JPARepositoryCities extends JpaRepository<City, Integer> {

    Slice<City> findByNameContainingIgnoreCase(String name, Pageable pageable);
    City findByLatitudeAndLongitude(Double latitude, Double longitude);

    @Query("SELECT c from City c INNER JOIN c.state s INNER JOIN s.country co WHERE s.stateCode =:stateCode AND co.iso2=:iso2")
    Slice<City> findByStateAndCountryWithJoin(String iso2, String stateCode, Pageable pageable);

    @Query("SELECT COUNT(c) from City c INNER JOIN c.state s INNER JOIN s.country co WHERE s.stateCode =:stateCode AND co.iso2=:iso2")
    long countByStateAndCountryWithJoin(String iso2, String stateCode);

    @Query("SELECT c from City c INNER JOIN c.state s INNER JOIN s.country co WHERE s.stateCode =:stateCode AND co.iso2=:iso2 AND c.name =:nameCity")
    City findByCity(String iso2, String stateCode, String nameCity);

}
