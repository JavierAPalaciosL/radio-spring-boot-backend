package io.radioweather.radioweatherapi.infrastructure.postgresql.repository;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.City;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JPARepositoryCities extends JpaRepository<City, Integer> {
    Slice<City> findByNameContainingIgnoreCase(String name, Pageable pageable);
    City findByLatitudeAndLongitude(Double latitude, Double longitude);
}
